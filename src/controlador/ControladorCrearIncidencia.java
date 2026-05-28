/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import modelo.Incidencia;
import modelo.Sistema;
import principal.Main;
import vista.VistaCrearIncidencia;

/**
 *
 * @author david
 */

public class ControladorCrearIncidencia {
    
    private VistaCrearIncidencia miVista;
    private Sistema miModelo;

    public ControladorCrearIncidencia(VistaCrearIncidencia vista) {
        this.miVista = vista;
        this.miModelo = Main.getSistema();
    }
    
    public void accionVolver() {
        // Usamos el Gestor de Vistas para volver al menú principal
        Main.getGestorVistas().mostrarMenuPrincipal();
    }
    
    public void accionGuardar() {
        try {
            // 1. Leer los datos de la vista
            String id = miVista.getTxtId();
            String dni = miVista.getTxtDni();
            String urb = miVista.getTxtUrbanizacion();
            String dir = miVista.getTxtDireccion();
            String fechaStr = miVista.getTxtFecha();
            String tipo = miVista.getCbTipo();
            String desc = miVista.getTxtAreaDescripcion();
            
            // 2. Comprobar si el ID ya existe en el sistema
            if (miModelo.existeId(id)) {
                JOptionPane.showMessageDialog(miVista, "Error: El ID de incidencia ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Cortamos la ejecución
            }

            // 3. Transformar el texto de la fecha a formato LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime fechaHora = LocalDateTime.parse(fechaStr, formatter);
            
            // 4. Intentar crear la incidencia (aquí saltarán los errores de tu compañero si hay datos mal)
            Incidencia nueva = new Incidencia(id, dni, urb, dir, tipo, desc, fechaHora);
            
            // 5. Si todo va bien, la guardamos en el sistema
            miModelo.agregarIncidencia(nueva);
            
            // 6. Mostramos mensaje de éxito y volvemos al menú
            JOptionPane.showMessageDialog(miVista, "¡Incidencia creada con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            accionVolver();
            
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(miVista, "Error: El formato de la fecha debe ser dd/MM/yyyy HH:mm", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            // Este catch captura automáticamente cualquier validación que falle en la clase Incidencia de tu compañero
            JOptionPane.showMessageDialog(miVista, ex.getMessage(), "Error en los datos", JOptionPane.ERROR_MESSAGE);
        }
    }
}
