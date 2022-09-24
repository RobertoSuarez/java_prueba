package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;

public class Conexion {
    public Connection connection;
    

    //Nuevos parametros estandarizados
    //Credenciales para la conexion
    private String host = "jdbc:postgresql://localhost:5432/guardia";
    private String user = "postgres";
    //private String password = "eliza26";
    private String password = "123456";
    private String classForName = "org.postgresql.Driver";

    public Conexion() {
        
    }
    
    public void startConn() throws ClassNotFoundException, SQLException {
        
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection(this.host, this.user, this.password);
        
        
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClassForName() {
        return classForName;
    }

    public void setClassForName(String classForName) {
        this.classForName = classForName;
    }
    
    
    
}
