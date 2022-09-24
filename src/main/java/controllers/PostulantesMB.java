/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.UsuarioDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.Usuario;

@ManagedBean(name = "postulantesMB")
@RequestScoped
public class PostulantesMB implements Serializable {

    private List<Usuario> postulantes;
    private Usuario postulanteSelecionado;
    private UsuarioDAO usuariodao;
    
    public PostulantesMB() {
        postulantes = new ArrayList<Usuario>();
        usuariodao = new UsuarioDAO();
    }
    
    @PostConstruct
    public void init() {
        this.cargarDatos();
    }
    
    void cargarDatos() {
        
        this.postulantes = usuariodao.ObtenerPostulantesNoHabilitados();
    }

    public List<Usuario> getPostulantes() {
        return postulantes;
    }

    public void setPostulantes(List<Usuario> postulantes) {
        this.postulantes = postulantes;
    }

    public Usuario getPostulanteSelecionado() {
        return postulanteSelecionado;
    }

    public void setPostulanteSelecionado(Usuario postulanteSelecionado) {
        this.postulanteSelecionado = postulanteSelecionado;
    }
    
    public void aprobarPostulante() {
        System.out.println("Postulante aprobado " + postulanteSelecionado.getNombre());
        usuariodao.AprobarPostulante(postulanteSelecionado.getIdUsuario());
        this.postulantes = usuariodao.ObtenerPostulantesNoHabilitados();
        postulanteSelecionado = null;
    }
    
}
