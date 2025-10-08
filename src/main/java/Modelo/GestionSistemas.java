/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.LinkedList;

/**
 *
 * @author PC
 */
public class GestionSistemas {
       // Metodos para el CRUD
    
    // Definir contenedora
    LinkedList <Sistema> listaSistema = new LinkedList<>();
    
    // CREAR APP
    public void agregarApp (Sistema sistema){
        //Agregar lista
        listaSistema.add(sistema);
    }
    
        // CONSULTAR TODOS
        public LinkedList<Sistema>listarSistema(){
      return listaSistema;
}
     
// CONSULTAR CODIGOS
        public Sistema buscarPorCodigo (String codigo){
            // for each
            for (Sistema sistema : listaSistema){
                if(sistema.getCodigo().equals(codigo)){
                        return sistema;
            }
        }
        return null;
        } 
        
        // EDITAR APP
    public boolean editarSistema(String codigo, Sistema sistemaEditado) {
        for (int i = 0; i < listaSistema.size(); i++) {
            if (listaSistema.get(i).getCodigo().equals(codigo)) {
                listaSistema.set(i, sistemaEditado);
                return true; 
            }
        }
        return false; 
    }

    // ELIMINAR APP
    public boolean eliminarSistema(String codigo) {
        for (int i = 0; i < listaSistema.size(); i++) {
            if (listaSistema.get(i).getCodigo().equals(codigo)) {
                listaSistema.remove(i);
                return true; 
            }
        }
        return false; 
    }

    private static class listaSistema {

        public listaSistema() {
        }
    }
 
    
}
