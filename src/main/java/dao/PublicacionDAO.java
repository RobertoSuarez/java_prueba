package dao;

import database.Conexion;
import idao.IPublicacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Publicacion;

public class PublicacionDAO implements IPublicacion {

    @Override
    public Publicacion ObtenerPublicacionById(int id) {
        Publicacion pub = null;

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt = conn.connection.prepareStatement("SELECT id_publicacion, titulo, id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado, id_hallazgo FROM publicacion WHERE id_publicacion=? LIMIT 1;");
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                pub = new Publicacion();

                pub.setIdPublicacion(resultado.getInt("id_publicacion"));
                pub.setTitulo(resultado.getString("titulo"));
                pub.setAutor(resultado.getString("id_autor"));
                pub.setAnio(resultado.getInt("anio"));
                pub.setTipoPublicacion(resultado.getString("tipo_publicacion"));
                pub.setFuente(resultado.getString("fuente"));
                pub.setCitasRecibidas(resultado.getInt("citas"));
                pub.setResumen(resultado.getString("resumen"));
                pub.setIdHallazgo(resultado.getInt("id_hallazgo"));

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

        return pub;
    }

    @Override
    public int GuardarPublicacion(Publicacion p) {
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
                    .prepareStatement("insert into publicacion(id_autor, titulo, anio, tipo_publicacion, fuente, citas, resumen, aprobado, id_hallazgo, id_usuario)"
                            + "	values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
                            + "    RETURNING id_publicacion;");

            // establecer valores de la consulta
            stmt.setString(1, p.getAutor());
            stmt.setString(2, p.getTitulo());
            stmt.setInt(3, p.getAnio());
            stmt.setString(4, p.getTipoPublicacion());
            stmt.setString(5, p.getFuente());
            stmt.setInt(6, p.getCitasRecibidas());
            stmt.setString(7, p.getResumen());
            stmt.setBoolean(8, p.getAprobado());
            stmt.setInt(9, p.getIdHallazgo());
            stmt.setInt(10, p.getIdUsuario());

            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                id = resultado.getInt("id_publicacion");
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
    public List<Publicacion> ObtenerPublicaciones() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Publicacion> ObtenerPublicacionesPrevista() {
        List<Publicacion> publicaciones = new ArrayList<>();

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt = conn.connection.prepareStatement("SELECT id_publicacion, titulo, id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado, id_hallazgo FROM publicacion order by anio DESC;");
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Publicacion pub = new Publicacion();
                pub.setIdPublicacion(resultado.getInt("id_publicacion"));
                pub.setTitulo(resultado.getString("titulo"));
                pub.setAutor(resultado.getString("id_autor"));
                pub.setAnio(resultado.getInt("anio"));
                pub.setTipoPublicacion(resultado.getString("tipo_publicacion"));
                pub.setFuente(resultado.getString("fuente"));
                pub.setCitasRecibidas(resultado.getInt("citas"));
                pub.setResumen(resultado.getString("resumen"));
                pub.setAprobado(resultado.getBoolean("aprobado"));
                pub.setIdHallazgo(resultado.getInt("id_hallazgo"));

                publicaciones.add(pub);
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

        return publicaciones;
    }

    @Override
    public List<Publicacion> ObtenerPublicacionesPrevistaFiltro(String tipo, String text) {
        List<Publicacion> publicaciones = new ArrayList<>();
        int textInt = 0;
        String columnas = "id_publicacion, titulo, id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado, id_hallazgo";

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt;

            switch (tipo) {
                case "id":
                    stmt = conn.connection
                            .prepareStatement("SELECT "+ columnas +" FROM publicacion WHERE aprobado=true and id_publicacion=? order by id_publicacion;");

                    try {
                        textInt = Integer.parseInt(text);
                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir el id");
                    }
                    stmt.setInt(1, textInt);
                    break;
                case "anio":
                    stmt = conn.connection
                            .prepareStatement("SELECT "+ columnas +" FROM publicacion WHERE aprobado=true and anio >= ? order by anio;");
                    try {
                        textInt = Integer.parseInt(text);
                    } catch (NumberFormatException e) {
                        System.out.println("Error al convertir el id");
                    }

                    stmt.setInt(1, textInt);
                    break;
                case "fuente":
                    stmt = conn.connection
                            .prepareStatement("SELECT "+ columnas +" FROM publicacion WHERE aprobado=true and fuente LIKE ?;");
                    stmt.setString(1, text + '%');
                    break;

                case "autor":
                    stmt = conn.connection
                            .prepareStatement("SELECT "+ columnas +" FROM publicacion WHERE aprobado=true and id_autor like ?;");

                    stmt.setString(1, text + "%");
                    break;
                case "hallazgo":
                    stmt = conn.connection
                            .prepareStatement("SELECT "+ columnas +" FROM publicacion as p inner join hallazgo as h on p.id_hallazgo=h.id_hallazgo where aprobado=true and h.objetivo like ? ORDER BY h.objetivo;");

                    stmt.setString(1, "%s"+text + "%");
                    System.out.println("hallazgo");
                    break;
                case "titulo":
                    stmt = conn.connection
                            .prepareStatement("SELECT "+ columnas +" FROM publicacion where aprobado=true and titulo like ? ORDER BY titulo;");

                    stmt.setString(1, "%" + text + "%");
                    System.out.println("titulo");
                    break;

                default:
                    stmt = conn.connection
                            .prepareStatement("SELECT "+ columnas +" FROM publicacion where aprobado=true and ORDER BY anio;");
                    break;
            }

            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Publicacion pub = new Publicacion();
                pub.setIdPublicacion(resultado.getInt("id_publicacion"));
                pub.setTitulo(resultado.getString("titulo"));
                pub.setAutor(resultado.getString("id_autor"));
                pub.setAnio(resultado.getInt("anio"));
                pub.setTipoPublicacion(resultado.getString("tipo_publicacion"));
                pub.setFuente(resultado.getString("fuente"));
                pub.setCitasRecibidas(resultado.getInt("citas"));
                pub.setResumen(resultado.getString("resumen"));
                pub.setAprobado(resultado.getBoolean("aprobado"));
                pub.setIdHallazgo(resultado.getInt("id_hallazgo"));

                publicaciones.add(pub);
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

        return publicaciones;
    }

    @Override
    public void AprobarPublicacion(int idPublicacion) {

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt = conn.connection.prepareStatement("UPDATE publicacion SET aprobado=true WHERE id_publicacion=?;");
            stmt.setInt(1, idPublicacion);
            stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public List<Publicacion> ObtenerPublicacionesPorAprobar() {
        List<Publicacion> publicaciones = new ArrayList<>();

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt = conn.connection.prepareStatement("SELECT id_publicacion, titulo, id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado, id_hallazgo FROM publicacion where aprobado=false order by anio DESC;");
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Publicacion pub = new Publicacion();
                pub.setIdPublicacion(resultado.getInt("id_publicacion"));
                pub.setTitulo(resultado.getString("titulo"));
                pub.setAutor(resultado.getString("id_autor"));
                pub.setAnio(resultado.getInt("anio"));
                pub.setTipoPublicacion(resultado.getString("tipo_publicacion"));
                pub.setFuente(resultado.getString("fuente"));
                pub.setCitasRecibidas(resultado.getInt("citas"));
                pub.setResumen(resultado.getString("resumen"));
                pub.setAprobado(resultado.getBoolean("aprobado"));
                pub.setIdHallazgo(resultado.getInt("id_hallazgo"));

                publicaciones.add(pub);
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

        return publicaciones;
    }

    @Override
    public List<Publicacion> ObtenerMisPublicaciones(int idUsuario) {
        List<Publicacion> publicaciones = new ArrayList<>();

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt = conn.connection.prepareStatement("SELECT id_publicacion, titulo, id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado, id_hallazgo FROM publicacion where id_usuario=?;");
            stmt.setInt(1, idUsuario);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Publicacion pub = new Publicacion();
                pub.setIdPublicacion(resultado.getInt("id_publicacion"));
                pub.setTitulo(resultado.getString("titulo"));
                pub.setAutor(resultado.getString("id_autor"));
                pub.setAnio(resultado.getInt("anio"));
                pub.setTipoPublicacion(resultado.getString("tipo_publicacion"));
                pub.setFuente(resultado.getString("fuente"));
                pub.setCitasRecibidas(resultado.getInt("citas"));
                pub.setResumen(resultado.getString("resumen"));
                pub.setAprobado(resultado.getBoolean("aprobado"));
                pub.setIdHallazgo(resultado.getInt("id_hallazgo"));

                publicaciones.add(pub);
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

        return publicaciones;
    }

    @Override
    public List<Publicacion> ObtenerPublicacionesAprobadas() {
        List<Publicacion> publicaciones = new ArrayList<>();

        Conexion conn = new Conexion();

        try {
            try {
                conn.startConn();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            PreparedStatement stmt = conn.connection.prepareStatement("SELECT id_publicacion, titulo, id_autor, anio, tipo_publicacion, fuente, citas, resumen, aprobado, id_hallazgo, (select autor.nombre from autor where id_publicacion=publicacion.id_publicacion limit 1) as dato FROM publicacion where aprobado=true order by anio DESC;");
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Publicacion pub = new Publicacion();
                pub.setIdPublicacion(resultado.getInt("id_publicacion"));
                pub.setTitulo(resultado.getString("titulo"));
                pub.setAutor(resultado.getString("dato"));
                pub.setAnio(resultado.getInt("anio"));
                pub.setTipoPublicacion(resultado.getString("tipo_publicacion"));
                pub.setFuente(resultado.getString("fuente"));
                pub.setCitasRecibidas(resultado.getInt("citas"));
                pub.setResumen(resultado.getString("resumen"));
                pub.setAprobado(resultado.getBoolean("aprobado"));
                pub.setIdHallazgo(resultado.getInt("id_hallazgo"));

                publicaciones.add(pub);
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

        return publicaciones;
    }

}
