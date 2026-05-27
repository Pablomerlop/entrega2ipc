/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JFrame;
import vista.VistaMenuPrincipal;

/**
 *
 * @author david
 */
public class GestorVistas {
    
    // Mantenemos el estado actual (ventana en la que nos encontramos)
    private JFrame estadoActual;

    public GestorVistas() {
    }

    public void mostrarMenuPrincipal() {
        if (estadoActual != null) {
            estadoActual.setVisible(false);
            estadoActual.dispose();
        }
        estadoActual = new VistaMenuPrincipal();
        estadoActual.setVisible(true);
    }
}
