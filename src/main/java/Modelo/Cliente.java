package Modelo;

/**
 *
 * @author INTERNET
 */
public class Cliente extends Persona {
    //Atributos
     String tipoCliente;
     String historial;
     
// Constructor
    public Cliente(String nombre, int edad, String cedula, String correo, String tipoCliente, String historial) {
        super(nombre, edad, cedula, correo);
        this.tipoCliente = tipoCliente;
        this.historial = historial;
    }
    
// Encapsulamiento  
    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

     
}
