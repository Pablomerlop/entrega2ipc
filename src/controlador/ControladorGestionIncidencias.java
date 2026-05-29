/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Incidencia;
import modelo.Sistema;
import modelo.Vigilante;
import principal.Main;
import vista.VistaGestionIncidencias;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author pablo
 */
public class ControladorGestionIncidencias {
    
    private VistaGestionIncidencias miVista;
    private Sistema miModelo;

    public ControladorGestionIncidencias(VistaGestionIncidencias vista) {
        this.miVista = vista;
        this.miModelo = Main.getSistema();
        
        // Cargar datos en la tabla
        actualizarTabla("Todas");
        
        // Cargar vigilantes en el combo inferior
        cargarVigilantesDisponibles();
        
        // Añadir el evento a la tabla para cuando el usuario haga clic en una fila
        miVista.getTablaIncidencias().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                accionSeleccionarFila();
            }
        });
    }

    public void actualizarTabla(String filtro) {
        DefaultTableModel modeloTabla = (DefaultTableModel) miVista.getTablaIncidencias().getModel();
        modeloTabla.setRowCount(0); // Limpiar tabla

        List<Incidencia> lista = miModelo.getIncidentes();
        for (Incidencia i : lista) {
            if (filtro.equals("Todas") || i.getEstado().equals(filtro)) {
                // Fila: ID, Hora, Urbanización, Tipo, Vigilante, Estado
                Object[] fila = { i.getId(), i.getFechaHoraStr(), i.getUrbanizacion(), i.getTipo(), i.getNombreVigilante(), i.getEstado() };
                modeloTabla.addRow(fila);
            }
        }
    }

    public void cargarVigilantesDisponibles() {
        miVista.getCbVigilantesAsignar().removeAllItems();
        for (Vigilante v : miModelo.getVigilantesDisponibles()) {
            miVista.getCbVigilantesAsignar().addItem(v.getNombre());
        }
    }

    // ACCIONES DE LOS BOTONES Y COMPONENTES
    public void accionVolver() {
        Main.getGestorVistas().mostrarMenuPrincipal();
    }
    
    public void accionFiltrar() {
        String filtro = miVista.getCbFiltro().getSelectedItem().toString();
        actualizarTabla(filtro);
    }

    public void accionSeleccionarFila() {
        int fila = miVista.getTablaIncidencias().getSelectedRow();
        if (fila != -1) { // Si hay una fila seleccionada
            String idFila = miVista.getTablaIncidencias().getValueAt(fila, 0).toString();
            
            // Buscar la incidencia completa en el sistema
            for (Incidencia i : miModelo.getIncidentes()) {
                if (i.getId().equals(idFila)) {
                    // Rellenar la zona de Detalles de la vista
                    miVista.setDetalleId(i.getId());
                    miVista.setDetalleDni(i.getDniResidente());
                    miVista.setDetalleUrb(i.getUrbanizacion());
                    miVista.setDetalleDir(i.getDireccion());
                    miVista.setDetalleFecha(i.getFechaHoraStr());
                    miVista.setDetalleTipo(i.getTipo());
                    miVista.setDetalleDesc(i.getDescripcion());
                    miVista.setDetalleSolucion(i.getSolucion());
                    break;
                }
            }
        }
    }

    public void accionCambiarEstado() {
        int fila = miVista.getTablaIncidencias().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(miVista, "Selecciona una incidencia de la tabla primero.");
            return;
        }
        
        String idFila = miVista.getTablaIncidencias().getValueAt(fila, 0).toString();
        
        // Comprobar si al cerrar falta la solución
        String estadoActual = miVista.getTablaIncidencias().getValueAt(fila, 5).toString();
        if (estadoActual.equals("Abierta") && miVista.getDetalleSolucion().trim().isEmpty()) {
            JOptionPane.showMessageDialog(miVista, "Para cerrar una incidencia, debes escribir la Solución primero y pulsar 'Guardar Cambios'.", "Falta Solución", JOptionPane.WARNING_MESSAGE);
            return;
        }

        miModelo.cambiarEstadoIncidencia(idFila);

        // Si la incidencia estaba "Abierta", debemos extraer el texto de la vista y guardarlo en el modelo.
        if (estadoActual.equals("Abierta")) {
            for (Incidencia i : miModelo.getIncidentes()) {
                if (i.getId().equals(idFila)) {
                    i.setSolucion(miVista.getDetalleSolucion());
                    break;
                }
            }
        }

        actualizarTabla(miVista.getCbFiltro().getSelectedItem().toString());
        cargarVigilantesDisponibles(); // Porque se puede liberar un vigilante
        JOptionPane.showMessageDialog(miVista, "Estado cambiado correctamente.");
    }

    public void accionAsignarVigilante() {
        int fila = miVista.getTablaIncidencias().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(miVista, "Selecciona una incidencia de la tabla primero.");
            return;
        }
        
        if (miVista.getCbVigilantesAsignar().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(miVista, "No hay vigilantes disponibles para asignar.");
            return;
        }

        String idFila = miVista.getTablaIncidencias().getValueAt(fila, 0).toString();
        String nombreVig = miVista.getCbVigilantesAsignar().getSelectedItem().toString();

        for (Vigilante v : miModelo.getVigilantesDisponibles()) {
            if (v.getNombre().equals(nombreVig)) {
                miModelo.asignarVigilante(idFila, v);
                actualizarTabla(miVista.getCbFiltro().getSelectedItem().toString());
                cargarVigilantesDisponibles();
                JOptionPane.showMessageDialog(miVista, "Vigilante asignado correctamente.");
                break;
            }
        }
    }

    public void accionGuardarModificacion() {
        String idFila = miVista.getDetalleId();
        if (idFila.isEmpty()) return;

        for (Incidencia i : miModelo.getIncidentes()) {
            if (i.getId().equals(idFila)) {
                
                // Comprobar que se puede modificar (Ni cerrada, ni asignada)
                if (!i.isAbierta()) {
                    JOptionPane.showMessageDialog(miVista, "No se puede modificar una incidencia que ya está cerrada.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (i.getVigilante() != null) {
                    JOptionPane.showMessageDialog(miVista, "No se puede modificar una incidencia que ya tiene un vigilante asignado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Intentar guardar los cambios
                try {
                    // Validacion y parseo de la nueva fecha
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    LocalDateTime nuevaFechaHora = LocalDateTime.parse(miVista.getDetalleFecha(), formatter);
                    
                    // Actualizamos el resto de los datos (estos métodos ahora lanzan IllegalArgumentException si fallan)
                    i.setDniResidente(miVista.getDetalleDni());
                    i.setUrbanizacion(miVista.getDetalleUrb());
                    i.setDireccion(miVista.getDetalleDir());
                    i.setTipo(miVista.getDetalleTipo());
                    i.setDescripcion(miVista.getDetalleDesc());
                    i.setSolucion(miVista.getDetalleSolucion());
                    
                    // Solo si todo lo de arriba no ha dado error, guardamos la fecha
                    i.setFechaHora(nuevaFechaHora); 
                    
                    actualizarTabla(miVista.getCbFiltro().getSelectedItem().toString());
                    JOptionPane.showMessageDialog(miVista, "Incidencia modificada correctamente.");
                    break;
                    
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(miVista, "Error: El formato de la fecha debe ser dd/MM/yyyy HH:mm", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                    return; // Cortamos la ejecución
                } catch (IllegalArgumentException ex) {
                    // Capturamos los fallos del DNI, Urbanización, etc.
                    JOptionPane.showMessageDialog(miVista, ex.getMessage(), "Error en los datos", JOptionPane.ERROR_MESSAGE);
                    return; // Cortamos la ejecución para no guardar a medias
                }
            }
        }
    }
}