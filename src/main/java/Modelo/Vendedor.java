package Modelo;

/**
 *
 * @author INTERNET
 */
public class Vendedor extends Persona {
    // Atributos
   String rango;
   String horario;
   String sueldo;

   // Constructor
    public Vendedor(String nombre, int edad, String cedula, String correo, String rango, String horario, String sueldo ) {
        super(nombre, edad, cedula, correo);
        this.rango = rango;
        this.horario = horario;
        this.sueldo = sueldo;
    }

   // Encapsulamiento
    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    
    
    
}
