package com.movi.tucasalab3.models;

public class Usuario {
private int Id_Usuario;
private String apellido;
private String nombre;
private int dni;

    public Usuario(int id_Usuario, String apellido, String nombre, int dni) {
        Id_Usuario = id_Usuario;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
    }

    public int getId_Usuario() {
        return Id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        Id_Usuario = id_Usuario;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
}
