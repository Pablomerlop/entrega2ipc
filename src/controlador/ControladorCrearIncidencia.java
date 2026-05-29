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
        
        // Cargar vigilantes disponibles al iniciar
        miVista.getCbVigilante().addItem("Sin asignar");
        for (modelo.Vigilante v : miModelo.getVigilantesDisponibles()) {
            miVista.getCbVigilante().addItem(v.getNombre());
        }
    }
    
    public void accionVolver() {
        // Usamos el Gestor de Vistas para volver al menú principal
        Main.getGestorVistas().mostrarMenuPrincipal();
    }
    
    public void accionGuardar() {
        try {
            // Leer los datos de la vista
            String id = miVista.getTxtId();
            String dni = miVista.getTxtDni();
            String urb = miVista.getTxtUrbanizacion();
            String dir = miVista.getTxtDireccion();
            String fechaStr = miVista.getTxtFecha();
            String tipo = miVista.getCbTipo();
            String desc = miVista.getTxtAreaDescripcion();
            
            // Comprobar si el ID ya existe en el sistema
            if (miModelo.existeId(id)) {
                JOptionPane.showMessageDialog(miVista, "Error: El ID de incidencia ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Cortamos la ejecución
            }

            // Transformar el texto de la fecha a formato LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime fechaHora = LocalDateTime.parse(fechaStr, formatter);
            
            // Intentar crear la incidencia
            Incidencia nueva = new Incidencia(id, dni, urb, dir, tipo, desc, fechaHora);

            // Aplicar estado y solución
            String estado = miVista.getCbEstado();
            if (estado.equals("Cerrada")) {
                nueva.setEstado(Incidencia.ESTADO_CERRADA);
                nueva.setSolucion(miVista.getTxtAreaSolucion());
            }
            
            // Asignar vigilante si se ha seleccionado uno
            String nombreVig = miVista.getVigilanteSeleccionado();
            if (!nombreVig.equals("Sin asignar")) {
                for (modelo.Vigilante v : miModelo.getVigilantesDisponibles()) {
                    if (v.getNombre().equals(nombreVig)) {
                        nueva.setVigilante(v);
                        break;
                    }
                }
            }

            // Si todo va bien, la guardamos en el sistema
            miModelo.agregarIncidencia(nueva);
            
            // Mostramos mensaje de éxito y volvemos al menú
            JOptionPane.showMessageDialog(miVista, "¡Incidencia creada con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            accionVolver();
            
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(miVista, "Error: El formato de la fecha debe ser dd/MM/yyyy HH:mm", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            // Este catch captura automáticamente cualquier validación que falle en la clase Incidencia
            JOptionPane.showMessageDialog(miVista, ex.getMessage(), "Error en los datos", JOptionPane.ERROR_MESSAGE);
        }
    }
}
