package Modelo;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Factura {
    //Atributos
    private int numeroFactura;
    private Cliente cliente;
    private double total;
    private LinkedList<Aplicacion> listaApps;
    private LinkedList<Sistema> listaSistemas;
    
    // Constructor
    public Factura(int numeroFactura, Cliente cliente, double total, LinkedList<Aplicacion> listaApps, LinkedList<Sistema> listaSistemas) {
        this.numeroFactura = numeroFactura;
        this.cliente = cliente;
        this.total = total;
        this.listaApps = listaApps;
        this.listaSistemas = listaSistemas;
    }
 
    // Encapsulamiento
    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LinkedList<Aplicacion> getListaApps() {
        return listaApps;
    }

    public void setListaApps(LinkedList<Aplicacion> listaApps) {
        this.listaApps = listaApps;
    }

    public LinkedList<Sistema> getListaSistemas() {
        return listaSistemas;
    }

    public void setListaSistemas(LinkedList<Sistema> listaSistemas) {
        this.listaSistemas = listaSistemas;
    }
    
}
