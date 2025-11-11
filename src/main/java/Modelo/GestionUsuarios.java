package Modelo;

import java.io.*;
import java.util.LinkedList;

public class GestionUsuarios {

    // Contenedoras
    private LinkedList<Cliente> listaClientes = new LinkedList<>();
    private LinkedList<Vendedor> listaVendedores = new LinkedList<>();
    private String rutaArchivo;

    // Constructor
    public GestionUsuarios(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        cargarDatos();
    }

    // -----------------------------------------------------------
    // MÉTODOS CRUD DE CLIENTES
    // -----------------------------------------------------------

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
        guardarDatos();
    }

    public LinkedList<Cliente> listarClientes() {
        return listaClientes;
    }

    public Cliente buscarClientePorCedula(String cedula) {
        for (Cliente c : listaClientes) {
            if (c.getCedula().equals(cedula)) {
                return c;
            }
        }
        return null;
    }

    public void editarCliente(String cedulaOriginal, Cliente clienteActualizado) {
        Cliente clienteExistente = buscarClientePorCedula(cedulaOriginal);
        if (clienteExistente != null) {
            int index = listaClientes.indexOf(clienteExistente);
            listaClientes.set(index, clienteActualizado);
        }
        guardarDatos();
    }

    public void eliminarClientePorCedula(String cedula) {
        Cliente cliente = buscarClientePorCedula(cedula);
        if (cliente != null) {
            listaClientes.remove(cliente);
            guardarDatos();
        }
    }

    // -----------------------------------------------------------
    // MÉTODOS CRUD DE VENDEDORES
    // -----------------------------------------------------------

    public void agregarVendedor(Vendedor vendedor) {
        listaVendedores.add(vendedor);
        guardarDatos();
    }

    public LinkedList<Vendedor> listarVendedores() {
        return listaVendedores;
    }

    public Vendedor buscarVendedorPorCedula(String cedula) {
        for (Vendedor v : listaVendedores) {
            if (v.getCedula().equals(cedula)) {
                return v;
            }
        }
        return null;
    }

    public void editarVendedor(String cedulaOriginal, Vendedor vendedorActualizado) {
        Vendedor vendedorExistente = buscarVendedorPorCedula(cedulaOriginal);
        if (vendedorExistente != null) {
            int index = listaVendedores.indexOf(vendedorExistente);
            listaVendedores.set(index, vendedorActualizado);
        }
        guardarDatos();
    }

    public void eliminarVendedorPorCedula(String cedula) {
        Vendedor vendedor = buscarVendedorPorCedula(cedula);
        if (vendedor != null) {
            listaVendedores.remove(vendedor);
            guardarDatos();
        }
    }

    // -----------------------------------------------------------
    // MÉTODOS GENERALES (para UsuarioServlet)
    // -----------------------------------------------------------

    // 🔍 Buscar cualquier usuario (cliente o vendedor) por cédula
    public Persona buscarUsuarioPorCedula(String cedula) {
        Cliente cliente = buscarClientePorCedula(cedula);
        if (cliente != null) return cliente;

        Vendedor vendedor = buscarVendedorPorCedula(cedula);
        if (vendedor != null) return vendedor;

        return null;
    }

    // ❌ Eliminar cualquier usuario (cliente o vendedor) por cédula
    public boolean eliminarUsuarioPorCedula(String cedula) {
        Cliente cliente = buscarClientePorCedula(cedula);
        if (cliente != null) {
            listaClientes.remove(cliente);
            guardarDatos();
            return true;
        }

        Vendedor vendedor = buscarVendedorPorCedula(cedula);
        if (vendedor != null) {
            listaVendedores.remove(vendedor);
            guardarDatos();
            return true;
        }

        return false;
    }

    // -----------------------------------------------------------
    // GUARDAR Y CARGAR DATOS EN TXT
    // -----------------------------------------------------------

    public void guardarDatos() {
        try {
            File archivo = new File(rutaArchivo);
            archivo.getParentFile().mkdirs(); // Crea /data si no existe
            FileWriter writer = new FileWriter(archivo, false);

            // --- Guardar Clientes ---
            for (Cliente c : listaClientes) {
                writer.write("CLIENTE;" + c.getNombre() + ";" + c.getEdad() + ";" + c.getCedula() + ";" +
                        c.getCorreo() + ";" + c.getTelefono() + ";" + c.getGenero() + ";" + c.getDireccion() + ";" +
                        c.getTipoCliente() + ";" + c.getHistorial() + "\n");
            }

            // --- Guardar Vendedores ---
            for (Vendedor v : listaVendedores) {
                writer.write("VENDEDOR;" + v.getNombre() + ";" + v.getEdad() + ";" + v.getCedula() + ";" +
                        v.getCorreo() + ";" + v.getTelefono() + ";" + v.getGenero() + ";" + v.getDireccion() + ";" +
                        v.getRango() + ";" + v.getHorario() + ";" + v.getSueldo() + "\n");
            }

            writer.close();
            System.out.println("Datos de usuarios guardados en: " + rutaArchivo);

        } catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
        }
    }

    private void cargarDatos() {
        try {
            File archivo = new File(rutaArchivo);
            if (!archivo.exists()) return;

            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes[0].equals("CLIENTE")) {
                    Cliente c = new Cliente(
                            partes[1], Integer.parseInt(partes[2]), partes[3],
                            partes[4], partes[5], partes[6], partes[7],
                            partes[8], partes[9]);
                    listaClientes.add(c);
                } else if (partes[0].equals("VENDEDOR")) {
                    Vendedor v = new Vendedor(
                            partes[1], Integer.parseInt(partes[2]), partes[3],
                            partes[4], partes[5], partes[6], partes[7],
                            partes[8], partes[9], partes[10]);
                    listaVendedores.add(v);
                }
            }
            reader.close();
            System.out.println("Datos de usuarios cargados correctamente desde " + rutaArchivo);

        } catch (IOException e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
        }
    }
}
