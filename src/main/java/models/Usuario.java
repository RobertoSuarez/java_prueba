package models;

public class Usuario {
    private int idUsuario;
    private String username;
    private String password;
    private String role; // role administrador, postulante.
    private String nombre;
    private String apellido;
    private String correo;
    private String cedula;
    private boolean habilitado;
    private boolean encuestado; // si es true ya realizo la encuata, caso contrario no la ha realizado.

    public Usuario() {
        this.username = "";
        this.password = "";
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
    
    
}
