
package models;

import java.util.Date;
import org.primefaces.model.file.UploadedFile;


public class Encuesta {
    //encuesta
    private int id_encuesta;
    private String antece_penales;
    private String fuma;
    private String tatuajes;
    private String Forma_academica;
    //completar información personal
    private Date fecha_nace;
    private String foto;
    private UploadedFile fileFoto;
    private UploadedFile filepdf;
    private String celular;
    private String estado_civil;
    private String localizacion;
    private String solicitud;
    private int id_usuario;

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

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public UploadedFile getFileFoto() {
        return fileFoto;
    }

    public void setFileFoto(UploadedFile fileFoto) {
        this.fileFoto = fileFoto;
    }

    public UploadedFile getFilepdf() {
        return filepdf;
    }

    public void setFilepdf(UploadedFile filepdf) {
        this.filepdf = filepdf;
    }
    
    
    
}
