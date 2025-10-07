package Modelo;

import java.util.LinkedList;

/**
 *
 * @author INTERNET
 */
public class GestionUsuarios {
    // Metodos para el CRUD
    
    // Definir contenedora
    LinkedList <Cliente> listaUsuarios = new LinkedList<>();
    
    // CREAR USUARIO
    public void agregarUsuario (Cliente cliente){
        //Agregar lista
        listaUsuarios.add(cliente);
    }
    
        // CONSULTAR TODOS
        public LinkedList<Cliente>listarUsuarios(){
      return listaUsuarios;
}
     
// CONSULTAR CODIGOS
        public Cliente buscarPorCodigo (String codigo){
            // for each
            for (Cliente cliente : listaUsuarios){
                if(cliente.getCedula().equals(codigo)){
                        return cliente;
            }
        }
        return null;
        } 
        
        // EDITAR CLIENTE
    public boolean editarCliente(String codigo, Cliente clienteEditado) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getCedula().equals(codigo)) {
                listaUsuarios.set(i, clienteEditado);
                return true; 
            }
        }
        return false; 
    }

    // ELIMINAR CLIENTE
    public boolean eliminarCliente(String codigo) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getCedula().equals(codigo)) {
                listaUsuarios.remove(i);
                return true; 
            }
        }
        return false; 
    }
        
        //-------------------------------------------------------------------------------------------------
        
            // Metodos para el CRUD
    
    // Definir contenedora
    LinkedList <Vendedor> listaUsuarios2 = new LinkedList<>();
    
    // CREAR USUARIO
    public void agregarUsuario (Vendedor vendedor){
        //Agregar lista
        listaUsuarios2.add(vendedor);
    }
    
        // CONSULTAR TODOS
        public LinkedList<Vendedor>listarUsuarios2(){
      return listaUsuarios2;
}
     
// CONSULTAR CODIGOS
        public Vendedor buscarPorCodigo2 (String codigo2){
            // for each
            for (Vendedor vendedor : listaUsuarios2){
                if(vendedor.getCedula().equals(codigo2)){
                        return vendedor;
            }
        }
        return null;
        }
        
        // EDITAR VENDEDOR
    public boolean editarVendedor(String codigo2, Vendedor vendedorEditado) {
        for (int i = 0; i < listaUsuarios2.size(); i++) {
            if (listaUsuarios2.get(i).getCedula().equals(codigo2)) {
                listaUsuarios2.set(i, vendedorEditado);
                return true; // Editado correctamente
            }
        }
        return false; // No se encontró el vendedor
    }

    // ELIMINAR VENDEDOR
    public boolean eliminarVendedor(String codigo2) {
        for (int i = 0; i < listaUsuarios2.size(); i++) {
            if (listaUsuarios2.get(i).getCedula().equals(codigo2)) {
                listaUsuarios2.remove(i);
                return true; // Eliminado correctamente
            }
        }
        return false; // No se encontró el vendedor
    }
}
        //----------------------------------------------------------------------------------------------------
        

