package Modelo;

import java.util.LinkedList;

/**
 * Gestiona las operaciones CRUD para Clientes y Vendedores en memoria.
 */
public class GestionUsuarios {

    // Contenedores para los tipos de usuario
    private LinkedList<Cliente> listaClientes = new LinkedList<>();
    private LinkedList<Vendedor> listaVendedores = new LinkedList<>();

    // --- Métodos para Clientes ---

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public LinkedList<Cliente> listarClientes() {
        return listaClientes;
    }

    public Cliente buscarClientePorCedula(String cedula) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getCedula().equals(cedula)) {
                return cliente;
            }
        }
        return null;
    }

    public boolean editarCliente(String cedula, Cliente clienteEditado) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getCedula().equals(cedula)) {
                listaClientes.set(i, clienteEditado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarCliente(String cedula) {
        return listaClientes.removeIf(cliente -> cliente.getCedula().equals(cedula));
    }

    // --- Métodos para Vendedores ---

    public void agregarVendedor(Vendedor vendedor) {
        listaVendedores.add(vendedor);
    }

    public LinkedList<Vendedor> listarVendedores() {
        return listaVendedores;
    }

    public Vendedor buscarVendedorPorCedula(String cedula) {
        for (Vendedor vendedor : listaVendedores) {
            if (vendedor.getCedula().equals(cedula)) {
                return vendedor;
            }
        }
        return null;
    }

    public boolean editarVendedor(String cedula, Vendedor vendedorEditado) {
        for (int i = 0; i < listaVendedores.size(); i++) {
            if (listaVendedores.get(i).getCedula().equals(cedula)) {
                listaVendedores.set(i, vendedorEditado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarVendedor(String cedula) {
        return listaVendedores.removeIf(vendedor -> vendedor.getCedula().equals(cedula));
    }
    

    /**
     * Busca un usuario (Cliente o Vendedor) por su cédula.
     * @param cedula La cédula a buscar.
     * @return El objeto Persona si se encuentra, o null.
     */
    public Persona buscarUsuarioPorCedula(String cedula) {
        Persona persona = buscarClientePorCedula(cedula);
        if (persona != null) {
            return persona;
        }
        return buscarVendedorPorCedula(cedula);
    }
    
    /**
     * Elimina un usuario (Cliente o Vendedor) por su cédula.
     * @param cedula La cédula a eliminar.
     * @return true si se eliminó un usuario, false en caso contrario.
     */
    public boolean eliminarUsuarioPorCedula(String cedula) {
        if (eliminarCliente(cedula)) {
            return true;
        }
        return eliminarVendedor(cedula);
    }
    
    /**
     * Este método es para mantener la compatibilidad con el AppInitializer que usa
     * agregarUsuario(Cliente) y agregarUsuario(Vendedor).
     */
    public void agregarUsuario(Cliente cliente) {
        this.agregarCliente(cliente);
    }

    public void agregarUsuario(Vendedor vendedor) {
        this.agregarVendedor(vendedor);
    }
}
