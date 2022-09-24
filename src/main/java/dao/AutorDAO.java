package dao;

import database.Conexion;
import idao.IAutor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Autor;
import models.Dato;


public class AutorDAO implements IAutor {

    
    

    @Override
    public int GuardarAutor(Autor autor) {
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
                    .prepareStatement("insert into autor(nombre, id_publicacion) values (?, ?) returning id;");

            // establecer valores de la consulta
            stmt.setString(1, autor.getNombre());
            stmt.setInt(2, autor.getId_publicacion());
           

            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                id = resultado.getInt("id");
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

        return id;
    }

    @Override
    public Autor ObtenerAutor(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Obtener todos los autores por medio del id publicación
    @Override
    public List<Autor> ObtenerAutoresPublicacionID(int idPublicacion) {
        List<Autor> autores = new ArrayList<>();

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt = conn.connection.prepareStatement("select nombre, id_publicacion from autor as a where a.id_publicacion=?");
            stmt.setInt(1, idPublicacion);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Autor autor = new Autor();
                
                autor.setNombre(resultado.getString("nombre"));
                autor.setId_publicacion(resultado.getInt("id_publicacion"));
                

                autores.add(autor);
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

        return autores;
    }

    
    
}
