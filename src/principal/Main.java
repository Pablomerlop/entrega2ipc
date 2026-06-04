package principal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import controlador.GestorVistas;
import modelo.Sistema;
/**
 *
 * @author mikfu
 */
public class Main {
    
    private static GestorVistas gestor;
    private static Sistema modelo;

    // Exponer el gestor y el modelo
    public static GestorVistas getGestorVistas() {
        return gestor;
    }

    public static Sistema getSistema() {
        return modelo;
    }

    // Inicialización de la aplicación
    public static void main(String args[]) {
        gestor = new GestorVistas();
        modelo = new Sistema(); 
        
        // Arrancamos mostrando el menú
        gestor.mostrarMenuPrincipal();
    }
}
