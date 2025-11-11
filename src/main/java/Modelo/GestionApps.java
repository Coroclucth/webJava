package Modelo;

import java.io.*;
import java.util.LinkedList;

public class GestionApps {

    private final File archivo;
    private LinkedList<Aplicacion> listaApps = new LinkedList<>();

    public GestionApps(String rutaArchivo) {
        this.archivo = new File(rutaArchivo);
        cargarDatos();
    }

    // === CREAR ===
    public void agregarApp(Aplicacion aplicacion) {
        listaApps.add(aplicacion);
        guardarDatos();
    }

    // === CONSULTAR TODOS ===
    public LinkedList<Aplicacion> listarApps() {
        return listaApps;
    }

    // === BUSCAR POR CÓDIGO ===
    public Aplicacion buscarPorCodigo(String codigo) {
        for (Aplicacion aplicacion : listaApps) {
            if (aplicacion.getCodigo().equals(codigo)) {
                return aplicacion;
            }
        }
        return null;
    }

    // === EDITAR ===
    public boolean editarApp(String codigo, Aplicacion appEditado) {
        for (int i = 0; i < listaApps.size(); i++) {
            if (listaApps.get(i).getCodigo().equals(codigo)) {
                listaApps.set(i, appEditado);
                guardarDatos();
                return true;
            }
        }
        return false;
    }

    // === ELIMINAR ===
    public boolean eliminarApp(String codigo) {
        for (int i = 0; i < listaApps.size(); i++) {
            if (listaApps.get(i).getCodigo().equals(codigo)) {
                listaApps.remove(i);
                guardarDatos();
                return true;
            }
        }
        return false;
    }

    // === GUARDAR EN ARCHIVO TXT ===
    public void guardarDatos() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Aplicacion a : listaApps) {
                writer.println("APP;" + a.getNombreSoftware() + ";" + a.getVersion() + ";" + a.getTipo() + ";" +
                        a.getCodigo() + ";" + a.getStock() + ";" + a.getPrecio() + ";" + a.isObsoleto() + ";" +
                        a.getJuegos() + ";" + a.getNavegadorWeb() + ";" + a.getDiseño());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar las aplicaciones: " + e.getMessage());
        }
    }

    // === CARGAR DESDE ARCHIVO TXT ===
    private void cargarDatos() {
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.startsWith("APP;")) continue; // solo lee líneas de apps
                if (linea.trim().isEmpty()) continue;    // evita líneas vacías

                String[] datos = linea.split(";");

                if (datos.length < 11) {
                    System.err.println("Línea inválida en apps.txt (faltan campos): " + linea);
                    continue; // pasa a la siguiente línea
                }

                try {
                    Aplicacion a = new Aplicacion(
                        datos[1], datos[2], datos[3], datos[4],
                        Integer.parseInt(datos[5]),
                        Double.parseDouble(datos[6]),
                        Boolean.parseBoolean(datos[7]),
                        datos[8], datos[9], datos[10]
                    );
                    listaApps.add(a);
                } catch (Exception e) {
                    System.err.println("Error al procesar línea: " + linea);
                }
            }

            System.out.println("Aplicaciones cargadas desde: " + archivo.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al cargar las aplicaciones: " + e.getMessage());
        }
    }
}