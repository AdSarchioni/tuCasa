package com.movi.tucasalab3.models;

import java.io.Serializable;

public class Propietario implements Serializable {

    private int id_Propietario;
    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private String clave;
    private int estado_Propietario;

    public Propietario(String dni, String nombre, String apellido, String direccion, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Propietario(int id_Propietario, String dni, String nombre, String apellido, String direccion, String telefono, String email, String clave, int estado_Prpietario) {
        this.id_Propietario = id_Propietario;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
        this.estado_Propietario = estado_Prpietario;
    }
    //Getters y Setters

    public int getId_Propietario() {
        return id_Propietario;
    }

    public void setId_Propietario(int id_Propietario) {
        this.id_Propietario = id_Propietario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }



    public int getEstado_Prpietario() {
        return estado_Propietario;
    }

    public void setEstado_Prpietario(int estado_Prpietario) {
        estado_Propietario = estado_Prpietario;
    }

    @Override
    public String toString() {
        return "Propietario{" +
                "Id_Propietario=" + id_Propietario +
                ", Dni='" + dni + '\'' +
                ", Nombre='" + nombre + '\'' +
                ", Apellido='" + apellido + '\'' +
                ", Direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", Email='" + email + '\'' +
                ", Clave='" + clave + '\'' +
                ", Estado_Prpietario=" + estado_Propietario +
                '}';
    }
}
