package Modelo;

/**
 *
 * @author INTERNET
 */
public class Sistema extends Software {
// Atributos
    String macOs;
    String windows;
    String linux;
    
    // Constructor
    public Sistema(String nombreSoftware, String version, String tipo, String codigo, int stock, double precio, boolean obsoleto, String macOs, String windows, String linux ) {
        super(nombreSoftware, version, tipo, codigo, stock, precio, obsoleto);
        this.macOs = macOs;
        this.windows = windows;
        this.linux = linux;
    }
    
    // Encapsulamiento
    public String getMacOs() {
        return macOs;
    }

    public void setMacOs(String macOs) {
        this.macOs = macOs;
    }

    public String getWindows() {
        return windows;
    }

    public void setWindows(String windows) {
        this.windows = windows;
    }

    public String getLinux() {
        return linux;
    }

    public void setLinux(String linux) {
        this.linux = linux;
    }
    
}