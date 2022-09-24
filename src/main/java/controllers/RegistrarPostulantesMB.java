package controllers;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import models.Usuario;

@ManagedBean(name = "registroPostulantesMB")
@RequestScoped
public class RegistrarPostulantesMB implements Serializable {

    private String username;
    private String password;
    private String role; // va hacer  postulante por defecto.
    private String nombre;
    private String apellido;
    private String correo;
    private String cedula;
    private boolean habilitado;
    private boolean encuestado;
    private Usuario postulante;
    
    private UsuarioDAO usuario_dao;

    public RegistrarPostulantesMB() {
        this.postulante = new Usuario();
        this.usuario_dao = new UsuarioDAO();
    }

    public RegistrarPostulantesMB(String username, String password, String role, String nombre, String apellido, String correo, String cedula, boolean habilitado, boolean encuestado) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.cedula = cedula;
        this.habilitado = habilitado;
        this.encuestado = encuestado;
    }
    
    public void registrar() {
        System.out.println("Registrando: " + this.postulante.getUsername());
        this.usuario_dao.RegistrarPostulante(this.postulante);
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(RegistrarPostulantesMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public boolean isEncuestado() {
        return encuestado;
    }

    public void setEncuestado(boolean encuestado) {
        this.encuestado = encuestado;
    }
     

    public Usuario getPostulante() {
        return postulante;
    }

    public void setPostulante(Usuario postulante) {
        this.postulante = postulante;
    }
    
    
}
