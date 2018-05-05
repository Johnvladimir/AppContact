package com.example.john.parcial;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacto implements Parcelable{

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

    protected Contacto(Parcel in) {
        Nombre = in.readString();
        Apellido = in.readString();
        correo = in.readString();
        direccion = in.readString();
        Telefono = in.readString();
        foto = in.readInt();
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Nombre);
        dest.writeString(Apellido);
        dest.writeString(correo);
        dest.writeString(direccion);
        dest.writeString(Telefono);
        dest.writeInt(foto);
    }
}
