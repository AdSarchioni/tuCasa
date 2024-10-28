package com.movi.tucasalab3.models;

public class CambioC {


   private String contrasenaActual;
   private String contrasenaNueva;

    public CambioC(String contrasenaActual, String contrasenaNueva) {
        this.contrasenaActual = contrasenaActual;
        this.contrasenaNueva = contrasenaNueva;
    }

    public String getContrasenaActual() {
        return contrasenaActual;
    }

    public void setContrasenaActual(String contrasenaActual) {
        this.contrasenaActual = contrasenaActual;
    }

    public String getContrasenaNueva() {
        return contrasenaNueva;
    }

    public void setContrasenaNueva(String contrasenaNueva) {
        this.contrasenaNueva = contrasenaNueva;
    }
}
