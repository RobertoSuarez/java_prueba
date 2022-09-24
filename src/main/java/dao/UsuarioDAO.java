/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.Conexion;
import idao.IUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Usuario;

public class UsuarioDAO implements IUsuario {

    @Override
    public Usuario ObtenerUsuario(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Debe conectarse a la base de datos y verificar que es un usuario legitimo
    @Override
    public Usuario Login(String username, String password) {
        Usuario usuario = null;
        Conexion conn = new Conexion();
        
        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /* ejemplo
            select id_usuario, username, password, role 
                from usuario
                where username='roberto' AND password='facil';
                
            */
            PreparedStatement stmt = conn.connection.prepareStatement("select id_usuario, username, password, role, encuestado FROM usuario WHERE username=? AND password=? AND habilitado=true;");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                usuario = new Usuario();
                // Establecemos los valores
                usuario.setIdUsuario(resultado.getInt("id_usuario"));
                usuario.setUsername(resultado.getString("username"));
                usuario.setPassword(resultado.getString("password"));
                usuario.setRole(resultado.getString("role"));
                usuario.setEncuestado(resultado.getBoolean("encuestado"));
            }
            
            System.out.println("usuario traido con exito");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return usuario;
    }

    @Override
    public List<Usuario> ObtenerPostulantesNoHabilitados() {
        List<Usuario> usuarios = new ArrayList<>();
        Conexion conn = new Conexion();
        
        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /* ejemplo
            select id_usuario, username, password, role 
                from usuario
                where username='roberto' AND password='facil';
                
            */
            PreparedStatement stmt = conn.connection.prepareStatement(
                    "select * " +
                    "	from usuario " +
                    "	where role='postulante' AND habilitado=false;");
            ResultSet resultado = stmt.executeQuery();
            while(resultado.next()) {
                Usuario user1 = new Usuario();
                // Establecemos los valores
                user1.setIdUsuario(resultado.getInt("id_usuario"));
                user1.setUsername(resultado.getString("username"));
                user1.setPassword(resultado.getString("password"));
                user1.setNombre(resultado.getString("nombre"));
                user1.setRole(resultado.getString("role"));
                user1.setApellido(resultado.getString("apellido"));
                user1.setCorreo(resultado.getString("correo"));
                user1.setCedula(resultado.getString("cedula"));
                user1.setHabilitado(resultado.getBoolean("habilitado"));
                user1.setEncuestado(resultado.getBoolean("encuestado"));
                usuarios.add(user1);
                
            }
            
            System.out.println("Postulantes traido con exito");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return usuarios;
    }

    @Override
    public boolean AprobarPostulante(int id) {

        Conexion conn = new Conexion();
        
        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /* ejemplo
            select id_usuario, username, password, role 
                from usuario
                where username='roberto' AND password='facil';
                
            */
            PreparedStatement stmt = conn.connection.prepareStatement(
                    "update usuario " +
                    "	set habilitado=true " +
                    "	where id_usuario=?;");
            stmt.setInt(1, id);
            stmt.executeQuery();
            
            System.out.println("Postulantes aprobado con exito");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return true;
    }

    @Override
    public Usuario RegistrarPostulante(Usuario postulante) {
        int id = 0;

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt = conn.connection
                    .prepareStatement(
                "INSERT INTO public.usuario( " +
                "	username, password, nombre, role, apellido, correo, cedula, habilitado, encuestado) " +
                "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "    RETURNING id_usuario;");

            // establecer valores de la consulta
            stmt.setString(1, postulante.getUsername());
            stmt.setString(2, postulante.getPassword());
            stmt.setString(3, postulante.getNombre());
            stmt.setString(4, "postulante");
            stmt.setString(5, postulante.getApellido());
            stmt.setString(6, postulante.getCorreo());
            stmt.setString(7, postulante.getCedula());
            stmt.setBoolean(8, false);
            stmt.setBoolean(9, false);

            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                id = resultado.getInt("id_usuario");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        postulante.setIdUsuario(id);
        return postulante;
    }
    
}
