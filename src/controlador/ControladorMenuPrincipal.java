/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import vista.VistaMenuPrincipal;
import modelo.Sistema;
import principal.Main;

/**
 *
 * @author david
 */

public class ControladorMenuPrincipal {
    
    private VistaMenuPrincipal miVista;
    private Sistema miModelo;

    public ControladorMenuPrincipal(VistaMenuPrincipal v) {
        this.miVista = v;
        
        // Obtener instancia del sistema
        this.miModelo = Main.getSistema();
        
        // Inicializar contador
        actualizarContador();
    }
    
    public void actualizarContador() {
        int abiertas = miModelo.getNumIncidenciasAbiertas();
        miVista.setContadorAbiertas(abiertas);
    }

    // Eventos de la vista
    public void accionNuevaIncidencia() {
        System.out.println("¡Botón Nueva Incidencia pulsado!");
        // Aquí le diremos al Gestor de Vistas que cambie de pantalla
        Main.getGestorVistas().mostrarCrearIncidencia(); 
    }

    public void accionVerHistorial() {
        System.out.println("¡Botón Ver Historial pulsado!");
        // Navegar a Gestión
        Main.getGestorVistas().mostrarGestionIncidencias();
    }
}
