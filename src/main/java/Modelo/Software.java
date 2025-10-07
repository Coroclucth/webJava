package Modelo;

/**
 *
 * @author INTERNET
 */
public class Software {
    // Atributos
    String nombreSoftware;
    String version;
    String tipo;
    String codigo;
    int stock;
    double precio;
    boolean obsoleto;
    
    // Constructor
    public Software(String nombreSoftware, String version, String tipo,String codigo, int stock, double precio, boolean obsoleto) {
        this.nombreSoftware = nombreSoftware;
        this.version = version;
        this.tipo = tipo;
        this.codigo= codigo;
        this.stock = stock;
        this.precio = precio;
        this.obsoleto = obsoleto;
    }
    
    // Encapsulamiento
    public String getNombreSoftware() {
        return nombreSoftware;
    }

    public void setNombreSoftware(String nombreSoftware) {
        this.nombreSoftware = nombreSoftware;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isObsoleto() {
        return obsoleto;
    }

    public void setObsoleto(boolean obsoleto) {
        this.obsoleto = obsoleto;
    }
    
    
}
