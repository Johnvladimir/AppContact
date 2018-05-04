package com.example.john.parcial;

public class Contacto {

    private String Nombre;
    private String Apellido;
    private String correo;
    private String direccion;
    private String Telefono;
    private int foto;

    public Contacto() {

    }

    public Contacto(String nombre, String apellido, String correo, String direccion, String telefono, int foto) {

        this.Nombre = nombre;
        this.Apellido = apellido;
        this.correo = correo;
        this.direccion = direccion;
        this.Telefono = telefono;
        this.foto = foto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
