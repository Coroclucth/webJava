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
public class GestionApps {
       // Metodos para el CRUD
    
    // Definir contenedora
    LinkedList <Aplicacion> listaApps = new LinkedList<>();
    
    // CREAR APP
    public void agregarApp (Aplicacion aplicacion){
        //Agregar lista
        listaApps.add(aplicacion);
    }
    
        // CONSULTAR TODOS
        public LinkedList<Aplicacion>listarApps(){
      return listaApps;
}
     
// CONSULTAR CODIGOS
        public Aplicacion buscarPorCodigo (String codigo){
            // for each
            for (Aplicacion aplicacion : listaApps){
                if(aplicacion.getCodigo().equals(codigo)){
                        return aplicacion;
            }
        }
        return null;
        } 
        
        // EDITAR APP
    public boolean editarApp(String codigo, Aplicacion appEditado) {
        for (int i = 0; i < listaApps.size(); i++) {
            if (listaApps.get(i).getCodigo().equals(codigo)) {
                listaApps.set(i, appEditado);
                return true; 
            }
        }
        return false; 
    }

    // ELIMINAR APP
    public boolean eliminarApp(String codigo) {
        for (int i = 0; i < listaApps.size(); i++) {
            if (listaApps.get(i).getCodigo().equals(codigo)) {
                listaApps.remove(i);
                return true; 
            }
        }
        return false; 
    }
 
    }
