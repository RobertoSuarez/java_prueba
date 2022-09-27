/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.EncuestaDAO;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import models.Encuesta;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author eliza
 */
@ManagedBean(name = "encuestaMB")
@RequestScoped
public class EncuestaMB implements Serializable {

    private UploadedFile file;
    private Encuesta encuesta;
    private EncuestaDAO encuesta_dao;
    //encuesta
    private int id_encuesta;
    private String antece_penales;
    private String fuma;
    private String tatuajes;
    private String Forma_academica;
    //completar información personal
    private Date fecha_nace;
    private String foto;
    private String celular;
    private String estado_civil;
    private String localizacion;
    private String solicitud;

    public EncuestaMB() {
        this.encuesta = new Encuesta();
        this.encuesta_dao = new EncuestaDAO();
    }

    public void guardar() {
        this.encuesta_dao.GuardarEncuesta(this.encuesta);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("postulante.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(RegistrarPostulantesMB.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.out.println("he guardado");
    }

    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public int getId_encuesta() {
        return id_encuesta;
    }

    public void setId_encuesta(int id_encuesta) {
        this.id_encuesta = id_encuesta;
    }

    public String getAntece_penales() {
        return antece_penales;
    }

    public void setAntece_penales(String antece_penales) {
        this.antece_penales = antece_penales;
    }

    public String getFuma() {
        return fuma;
    }

    public void setFuma(String fuma) {
        this.fuma = fuma;
    }

    public String getTatuajes() {
        return tatuajes;
    }

    public void setTatuajes(String tatuajes) {
        this.tatuajes = tatuajes;
    }

    public String getForma_academica() {
        return Forma_academica;
    }

    public void setForma_academica(String Forma_academica) {
        this.Forma_academica = Forma_academica;
    }

    public Date getFecha_nace() {
        return fecha_nace;
    }

    public void setFecha_nace(Date fecha_nace) {
        this.fecha_nace = fecha_nace;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(String solicitud) {
        this.solicitud = solicitud;
    }

    public EncuestaMB(UploadedFile file, Encuesta encuesta, int id_encuesta, String antece_penales, String fuma, String tatuajes, String Forma_academica, Date fecha_nace, String foto, String celular, String estado_civil, String localizacion, String solicitud) {
        this.file = file;
        this.encuesta = encuesta;
        this.id_encuesta = id_encuesta;
        this.antece_penales = antece_penales;
        this.fuma = fuma;
        this.tatuajes = tatuajes;
        this.Forma_academica = Forma_academica;
        this.fecha_nace = fecha_nace;
        this.foto = foto;
        this.celular = celular;
        this.estado_civil = estado_civil;
        this.localizacion = localizacion;
        this.solicitud = solicitud;
    }

}
