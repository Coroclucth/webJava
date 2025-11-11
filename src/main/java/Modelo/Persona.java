package Modelo;

import java.io.Serializable;

public class Persona implements Serializable {
    // Atributos
    String nombre;
    int edad;
    String cedula;
    String correo;
    String telefono;
    String genero;
    String direccion;
    
    // Constructor
    public Persona(String nombre, int edad, String cedula, String correo, String telefono, String genero, String direccion) {
        this.nombre = nombre;
        this.edad = edad;
        this.cedula = cedula;
        this.correo = correo;
        this.telefono = telefono;
        this.genero = genero;
        this.direccion = direccion;
    }
    
    //Encapsulamiento
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getTipoUsuario() {
        if (this instanceof Cliente) {
            return "Cliente";
        } else if (this instanceof Vendedor) {
            return "Vendedor";
        }
        return "Desconocido";
    }
    
}
