package com.movi.tucasalab3.models;

public class LoginUs {

    private String Usuario;
    private String Clave;

    public LoginUs(String usuario, String clave ) {
        Clave = clave;
        Usuario = usuario;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }
}
