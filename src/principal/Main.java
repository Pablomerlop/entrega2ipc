package principal;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import controlador.GestorVistas;
import modelo.Sistema; // Asegúrate de que tu clase principal del modelo se llama Sistema
/**
 *
 * @author mikfu
 */
public class Main {
    
    private static GestorVistas gestor;
    private static Sistema modelo;

    // Métodos estáticos para exponer el gestor y el modelo [cite: 233, 235, 239]
    public static GestorVistas getGestorVistas() {
        return gestor;
    }

    public static Sistema getSistema() {
        return modelo;
    }

    // En el método main se construye el gestor y se muestra la ventana inicial [cite: 240]
    public static void main(String args[]) {
        gestor = new GestorVistas();
        modelo = new Sistema(); 
        
        // Arrancamos mostrando el menú
        gestor.mostrarMenuPrincipal();
    }
}
