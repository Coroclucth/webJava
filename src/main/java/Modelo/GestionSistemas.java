package Modelo;

import java.io.*;
import java.util.LinkedList;

public class GestionSistemas {

    private final File archivo;
    private LinkedList<Sistema> listaSistema = new LinkedList<>();

    public GestionSistemas(String rutaArchivo) {
        this.archivo = new File(rutaArchivo);
        cargarDatos();
    }

    // === CREAR ===
    public void agregarSistema(Sistema sistema) {
        listaSistema.add(sistema);
        guardarDatos();
    }

    // === CONSULTAR TODOS ===
    public LinkedList<Sistema> listarSistema() {
        return listaSistema;
    }

    // === BUSCAR POR CÓDIGO ===
    public Sistema buscarPorCodigo(String codigo) {
        for (Sistema sistema : listaSistema) {
            if (sistema.getCodigo().equals(codigo)) {
                return sistema;
            }
        }
        return null;
    }

    // === EDITAR ===
    public boolean editarSistema(String codigo, Sistema sistemaEditado) {
        for (int i = 0; i < listaSistema.size(); i++) {
            if (listaSistema.get(i).getCodigo().equals(codigo)) {
                listaSistema.set(i, sistemaEditado);
                guardarDatos();
                return true;
            }
        }
        return false;
    }

    // === ELIMINAR ===
    public boolean eliminarSistema(String codigo) {
        for (int i = 0; i < listaSistema.size(); i++) {
            if (listaSistema.get(i).getCodigo().equals(codigo)) {
                listaSistema.remove(i);
                guardarDatos();
                return true;
            }
        }
        return false;
    }

    // === GUARDAR EN ARCHIVO TXT ===
    public void guardarDatos() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Sistema s : listaSistema) {
                writer.println("SISTEMA;" + s.getNombreSoftware() + ";" + s.getVersion() + ";" + s.getTipo() + ";" +
                        s.getCodigo() + ";" + s.getStock() + ";" + s.getPrecio() + ";" + s.isObsoleto() + ";" +
                        s.getMacOs() + ";" + s.getWindows() + ";" + s.getLinux());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar los sistemas: " + e.getMessage());
        }
    }

    // === CARGAR DESDE ARCHIVO TXT ===
    private void cargarDatos() {
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.startsWith("SISTEMA;")) continue; // solo lee las líneas de sistemas

                String[] datos = linea.split(";");
                Sistema s = new Sistema(
                        datos[1], datos[2], datos[3], datos[4],
                        Integer.parseInt(datos[5]), Double.parseDouble(datos[6]),
                        Boolean.parseBoolean(datos[7]), datos[8], datos[9], datos[10]
                );
                listaSistema.add(s);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar los sistemas: " + e.getMessage());
        }
    }
}


