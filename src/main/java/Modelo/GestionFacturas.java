package Modelo;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class GestionFacturas {

    private LinkedList<Factura> listaFacturas = new LinkedList<>();
    private int proximoNumeroFactura = 1;
    private String rutaArchivo;
    private GestionApps gestionApps;
    private GestionSistemas gestionSistemas;

    public GestionFacturas(String rutaArchivo, GestionApps gestionApps, GestionSistemas gestionSistemas) {
        this.rutaArchivo = rutaArchivo;
        this.gestionApps = gestionApps;
        this.gestionSistemas = gestionSistemas;
        cargarFacturas();
    }

    // Agregar una factura y guardar en archivo
    public void agregarFactura(Factura factura) {
        factura.setNumeroFactura(proximoNumeroFactura++);
        listaFacturas.add(factura);
        guardarFacturas();
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

    // -------------------------------------------------------------------
    // MÉTODOS PARA GUARDAR Y CARGAR FACTURAS
    // -------------------------------------------------------------------

    public void guardarFacturas() {
        try {
            File archivo = new File(rutaArchivo);
            archivo.getParentFile().mkdirs(); // Crear carpeta /data si no existe
            FileWriter writer = new FileWriter(archivo, false);

            for (Factura f : listaFacturas) {
                StringBuilder sb = new StringBuilder();
                sb.append(f.getNumeroFactura()).append(";")
                        .append(f.getCliente().getCedula()).append(";")
                        .append(f.getCliente().getNombre()).append(";")
                        .append(f.getTotal()).append(";");

                // Guardar nombres de apps y sistemas (si existen)
                sb.append("APPS:");
                if (f.getListaApps() != null && !f.getListaApps().isEmpty()) {
                    for (Aplicacion a : f.getListaApps()) {
                        sb.append(a.getCodigo()).append(",");
                    }
                } else {
                    sb.append("NINGUNA");
                }

                sb.append(";SISTEMAS:");
                if (f.getListaSistemas() != null && !f.getListaSistemas().isEmpty()) {
                    for (Sistema s : f.getListaSistemas()) {
                        sb.append(s.getCodigo()).append(",");
                    }
                } else {
                    sb.append("NINGUNO");
                }

                writer.write(sb.toString() + "\n");
            }

            writer.close();
            System.out.println("Facturas guardadas en: " + rutaArchivo);

        } catch (IOException e) {
            System.err.println("Error al guardar facturas: " + e.getMessage());
        }
    }

    private void cargarFacturas() {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists() || gestionApps == null || gestionSistemas == null) {
            return; // No cargar si no hay archivo o gestores
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            listaFacturas.clear(); // Limpiar lista antes de cargar

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");

                if (partes.length >= 6) {
                    int numero = Integer.parseInt(partes[0]);
                    String cedulaCliente = partes[1];
                    String nombreCliente = partes[2];
                    double total = Double.parseDouble(partes[3]);

                    // Crear cliente temporal
                    Cliente cliente = new Cliente(nombreCliente, 0, cedulaCliente, "", "", "", "", "", "");

                    LinkedList<Aplicacion> listaApps = new LinkedList<>();
                    LinkedList<Sistema> listaSistemas = new LinkedList<>();

                    // Reconstruir lista de Aplicaciones
                    String appsData = partes[4].replace("APPS:", "");
                    if (!appsData.equals("NINGUNA") && !appsData.isEmpty()) {
                        String[] appCodigos = appsData.split(",");
                        for (String codigoApp : appCodigos) {
                            Aplicacion app = gestionApps.buscarPorCodigo(codigoApp);
                            if (app != null) {
                                listaApps.add(app);
                            }
                        }
                    }

                    // Reconstruir lista de Sistemas
                    String sistemasData = partes[5].replace("SISTEMAS:", "");
                    if (!sistemasData.equals("NINGUNO") && !sistemasData.isEmpty()) {
                        String[] sistemaCodigos = sistemasData.split(",");
                        for (String codigoSistema : sistemaCodigos) {
                            Sistema sis = gestionSistemas.buscarPorCodigo(codigoSistema);
                            if (sis != null) {
                                listaSistemas.add(sis);
                            }
                        }
                    }

                    Factura factura = new Factura(numero, cliente, total, listaApps, listaSistemas);
                    listaFacturas.add(factura);
                    proximoNumeroFactura = Math.max(proximoNumeroFactura, numero + 1);
                }
            }
            System.out.println("Facturas cargadas y reconstruidas desde: " + rutaArchivo);

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar o procesar facturas: " + e.getMessage());
        }
    }
}
