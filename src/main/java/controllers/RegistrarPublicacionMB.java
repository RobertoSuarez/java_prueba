package controllers;

import dao.AutorDAO;
import dao.DatoDAO;
import dao.HallazgoDAO;
import dao.PublicacionDAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import models.Autor;
import models.Dato;
import models.Hallazgo;
import models.Publicacion;


@ManagedBean(name = "registrarPublicacionMB")
@SessionScoped
public class RegistrarPublicacionMB implements Serializable {
    
    private String titulo;
    private Publicacion publicacion;
    private Hallazgo hallazgo;
    private List<Dato> datos;
    private List<Autor> autores;

    public RegistrarPublicacionMB() {
        this.publicacion = new Publicacion();
        this.hallazgo = new Hallazgo();
        this.datos = new ArrayList<>();
        this.autores = new ArrayList<>();
        
        System.out.println("constructor");
     
    }
    
    @PostConstruct
    public void init() {
        this.titulo = "Registrar una publicación";
        this.datos.add(new Dato(){{
            this.setTitulo("");
            this.setDescripcion("");
        }});
        this.autores.add(new Autor(){{
            this.setNombre("");
        }});
        System.out.println("init");
    }
    
    public void onAddAutor() {
        this.autores.add(new Autor());
    }
    
    public void onDeleteAutores() {
        this.autores.remove(this.autores.size()-1);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
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

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
    
    public void agregarDato() {
        System.out.println("cantidad de datos: " + this.datos.size());
        this.datos.add(new Dato(String.format("%d", this.datos.size()+1)));
    }
    
    public void registrar() {
        
        if (!this.validarDatos()) {
            addMessage(FacesMessage.SEVERITY_WARN, "Faltan ingresar datos", "Faltan ingresar datos en los campos requeridos");
            return;
        }
        HttpSession session = SessionUtils.getSession();
        int idUsuario = Integer.parseInt(session.getAttribute("id_usuario").toString());
        
        // Los datos llegan correctamente
        
        System.out.println(this.publicacion);
        PublicacionDAO pubDAO = new PublicacionDAO();
        HallazgoDAO haDAO = new HallazgoDAO();
        DatoDAO datoDAO = new DatoDAO();
        AutorDAO autorDAO = new AutorDAO();
        
        final int idHallazgo = haDAO.GuardarHallazgo(this.hallazgo);
        
        // Registramos todos los datos
        for(int i = 0; i < this.datos.size(); i++) {
            Dato d = this.datos.get(i);
            d.setIdHallazgo(idHallazgo); // Establecemos el id hallazgo al que pertenece
            datoDAO.GuardarDato(d);
        }
        
        this.publicacion.setIdHallazgo(idHallazgo);
        this.publicacion.setIdUsuario(idUsuario);
        
        int id = pubDAO.GuardarPublicacion(this.publicacion);
        System.out.println("id publicación: " + id);
        
        
        // Guardamos los autores
        for(int i = 0; i < this.autores.size(); i++) {
            Autor au = this.autores.get(i);
            System.out.println(au.getNombre());
            au.setId_publicacion(id);
            autorDAO.GuardarAutor(au);
        }
        
        // Redireccionamiento
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("publicaciones.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(RegistrarPublicacionMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.clear();
    }
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    public Boolean validarDatos() {
        
        return !(this.publicacion.getTitulo().length() < 1 ||
                this.publicacion.getAnio() == 0 ||
                this.publicacion.getTipoPublicacion().length() < 1 ||
                this.publicacion.getFuente().length() < 1 ||
                this.publicacion.getResumen().length() < 1 ||
                this.hallazgo.getObjetivo().length() < 1);
    }
    
    public void clear() {
        this.publicacion = new Publicacion();
        this.hallazgo = new Hallazgo();
        this.datos = new ArrayList<>();
        System.out.println("Clear");
    }
    
}
