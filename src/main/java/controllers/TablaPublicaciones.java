package controllers;

import dao.AutorDAO;
import dao.DatoDAO;
import dao.HallazgoDAO;
import dao.PublicacionDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;
import models.Autor;
import models.Dato;
import models.Hallazgo;
import models.Publicacion;

@ManagedBean(name = "tablaPublicaciones")
@RequestScoped
public class TablaPublicaciones implements Serializable {
    private List<Publicacion> publicaciones;
    private Publicacion pubSelect;
    private Hallazgo hallazgo;
    private List<Dato> datos;
    private List<Autor> autores;
    private String mostrar;
    private String tipo;
    private String texto;

    public TablaPublicaciones() {
        PublicacionDAO pub = new PublicacionDAO();
        this.publicaciones = pub.ObtenerPublicacionesAprobadas();
        System.out.println(this.publicaciones.size());
        
        this.pubSelect = new Publicacion();
        this.hallazgo = new Hallazgo();
        this.datos = new ArrayList<>();
    }
    
    @PostConstruct
    public void init() {
    }
    
    public void buscar() {
        PublicacionDAO pubdao = new PublicacionDAO();
        System.out.println(this.tipo);
        System.out.println(this.texto);
        this.publicaciones = pubdao.ObtenerPublicacionesPrevistaFiltro(this.tipo, this.texto);
        
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public Publicacion getPubSelect() {
        return pubSelect;
    }
    
    public void onCambioMostrar() {
        System.out.println("Mostrar: " +this.mostrar);
        HttpSession session = SessionUtils.getSession();
        System.out.println(session.getAttribute("username"));
        System.out.println(session.getAttribute("id_usuario"));
        PublicacionDAO pdao = new PublicacionDAO();
        if (this.mostrar.equals("mispublicaciones")) {
            int idUsuario = Integer.parseInt(session.getAttribute("id_usuario").toString());
            this.publicaciones = pdao.ObtenerMisPublicaciones(idUsuario);
        } else {
            this.publicaciones = pdao.ObtenerPublicacionesAprobadas();
        }
    }

    public void setPubSelect(Publicacion pubSelect) {
        if (pubSelect != null) {
            System.out.println("set pub " + pubSelect.getAutor());


            HallazgoDAO hdao = new HallazgoDAO();
            DatoDAO ddao = new DatoDAO();
            AutorDAO autordao = new AutorDAO();

            System.out.println(pubSelect.getIdHallazgo());
            
            this.autores = autordao.ObtenerAutoresPublicacionID(pubSelect.getIdPublicacion());

            this.hallazgo = hdao.ObtenerHallazgoByID(pubSelect.getIdHallazgo());
            this.datos = ddao.ObtenerDatosByIDHallazgo(pubSelect.getIdHallazgo());

        
        this.pubSelect = pubSelect;
        }
    }
    
    public void selecionarPublicacion(int id) {
        System.out.println("set id: " +id);
        Publicacion pub = new Publicacion();
        for(Publicacion pubi : this.publicaciones) {
            if (pubi.getIdPublicacion() == id) {
                pub = pubi;
                break;
            }
        }
        
        
        
        System.out.println(pub.getAutor());   
    }
    
    public void mostrar(int id) {
        System.out.println("set id: " +id);
    }

    public Hallazgo getHallazgo() {
        return hallazgo;
    }

    public void setHallazgo(Hallazgo hallazgo) {
        this.hallazgo = hallazgo;
    }

    public List<Dato> getDatos() {
        return datos;
    }

    public void setDatos(List<Dato> datos) {
        this.datos = datos;
    }

    public String getMostrar() {
        return mostrar;
    }

    public void setMostrar(String mostrar) {
        this.mostrar = mostrar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
    
    
}
