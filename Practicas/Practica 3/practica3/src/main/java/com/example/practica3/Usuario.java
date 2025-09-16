package com.example.practica3;

public class Usuario {
    private String nombreDeUsuario;
    private String clave;
    private String perfil;

    public Usuario(String nombreDeUsuario, String clave, String perfil) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.clave = clave;
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}