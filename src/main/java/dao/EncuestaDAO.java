/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.Conexion;
import idao.IEncuesta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Encuesta;
import models.Usuario;

/**
 *
 * @author eliza
 */
public class EncuestaDAO implements IEncuesta {

    @Override
    public Usuario ObtenerUsuario(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Encuesta GuardarEncuesta(Encuesta encuesta) {
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
//
//            //actualizamos la tabla usuario
//            PreparedStatement stmtu = conn.connection
//                    .prepareStatement(
//                            "UPDATE public.usuario"
//                            + "	SET encuestado=true, fecha_nace=?, foto=?, celular=?, estado_civil=?, localizacion=?, solicitud=?"
//                            + "	WHERE id_usuario=?;");
//            stmtu.setInt(1, id);
//            stmtu.executeQuery();

            //insertamos a la tabla encuesta
            PreparedStatement stmt = conn.connection
                    .prepareStatement(
                            "INSERT INTO public.encuesta("
                            + "	antece_penales, fuma, tatuajes, forma_academica, id_usuario)"
                            + "	VALUES ( ?, ?, ?, ?, ?)"
                            + "RETURNING id_usuario;");

//            // establecer valores de la consulta
            stmt.setString(1, encuesta.getAntece_penales());
            stmt.setString(2, encuesta.getFuma());
            stmt.setString(3, encuesta.getTatuajes());
            stmt.setString(4, encuesta.getForma_academica());
            stmt.setInt(5, encuesta.getId_usuario());

            ResultSet resultado = stmt.executeQuery();
//            ResultSet result = stmtu.executeQuery();
            while (resultado.next()) {
                id = resultado.getInt("id_usuario");
            }
            
            
            // actulaizar los datos del usuario
            String datosUpdate = "update usuario " +
                    "	set " +
                    "		fecha_nace=?, " +
                    "		foto=?, " +
                    "		celular=?, " +
                    "		estado_civil=?, " +
                    "		localizacion=?," +
                    "		solicitud=?," +
                    "		encuestado=? " +
                    "	WHERE id_usuario=?; ";
            PreparedStatement stmtUpdate = conn.connection.prepareStatement(datosUpdate);
            
            stmtUpdate.setString(1, encuesta.getFecha_nace().toString());
            stmtUpdate.setString(2, encuesta.getFoto());
            stmtUpdate.setString(3, encuesta.getCelular());
            stmtUpdate.setString(4, encuesta.getEstado_civil());
            stmtUpdate.setString(5, encuesta.getLocalizacion());
            stmtUpdate.setString(6, encuesta.getSolicitud());
            stmtUpdate.setBoolean(7, true);
            stmtUpdate.setInt(8, encuesta.getId_usuario());
            
            int rowModificados = stmtUpdate.executeUpdate();
            
            System.out.println("row modificados: " + rowModificados);

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        

        //encuesta.setId_usuario(id);
        return encuesta;

    }

}
