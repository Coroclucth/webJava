package Modelo;
public class Aplicacion extends Software {
      // Atributos
    String juegos;
    String navegadorWeb;
    String diseño;
    
    // Constructor
    public Aplicacion(String nombreSoftware, String version, String tipo, String codigo, int stock, double precio, boolean obsoleto, String juegos, String navegadorWeb, String diseño) {
        super(nombreSoftware, version, tipo, codigo, stock, precio, obsoleto);
        this.juegos = juegos;
        this.navegadorWeb = navegadorWeb;
        this.diseño = diseño;
    }
    
    // Encapsulamiento
    public String getJuegos() {
        return juegos;
    }

    public void setJuegos(String juegos) {
        this.juegos = juegos;
    }

    public String getNavegadorWeb() {
        return navegadorWeb;
    }

    public void setNavegadorWeb(String navegadorWeb) {
        this.navegadorWeb = navegadorWeb;
    }

    public String getDiseño() {
        return diseño;
    }

    public void setDiseño(String diseño) {
        this.diseño = diseño;
    }
    
}
