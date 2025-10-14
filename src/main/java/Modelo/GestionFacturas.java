package Modelo;

import java.util.LinkedList;
import java.util.List;

public class GestionFacturas {

    private LinkedList<Factura> listaFacturas = new LinkedList<>();
    private int proximoNumeroFactura = 1;

    public void agregarFactura(Factura factura) {
        factura.setNumeroFactura(proximoNumeroFactura++);
        listaFacturas.add(factura);
    }

    public List<Factura> listarFacturas() {
        return listaFacturas;
    }
    
    public Factura buscarFacturaPorNumero(int numeroFactura) {
        for (Factura factura : listaFacturas) {
            if (factura.getNumeroFactura() == numeroFactura) {
                return factura;
            }
        }
        return null;
    }
}
