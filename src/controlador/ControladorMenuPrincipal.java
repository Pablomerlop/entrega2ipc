/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import vista.VistaMenuPrincipal;
import modelo.Sistema;
import principal.Main; // Importamos el Main de su nueva carpeta

/**
 *
 * @author david
 */

public class ControladorMenuPrincipal {
    
    private VistaMenuPrincipal miVista;
    private Sistema miModelo;

    // El constructor del controlador tiene como parámetro la vista 
    public ControladorMenuPrincipal(VistaMenuPrincipal v) {
        this.miVista = v;
        
        // Recuperamos los datos centrales desde el Main
        this.miModelo = Main.getSistema();
        
        // Actualizamos el número nada más arrancar
        actualizarContador();
    }
    
    public void actualizarContador() {
        // En el futuro, aquí contaremos las incidencias reales del miModelo
        // De momento ponemos un 0 para que no dé error
        miVista.setContadorAbiertas(0);
    }

    // Estos son los métodos que llama tu Vista al hacer doble clic
    public void accionNuevaIncidencia() {
        System.out.println("¡Botón Nueva Incidencia pulsado!");
        // Aquí le diremos al Gestor de Vistas que cambie de pantalla
        Main.getGestorVistas().mostrarCrearIncidencia(); 
    }

    public void accionVerHistorial() {
        System.out.println("¡Botón Ver Historial pulsado!");
        // Aquí le diremos al Gestor de Vistas que cambie de pantalla
        Main.getGestorVistas().mostrarGestionIncidencias();
    }
}
