/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa una incidencia registrada en el sistema GesSec S.L.
 *
 * @author p2_grupo39
 */
public class Incidencia {

    //Constantes de tipo
    public static final String TIPO_INTRUSION = "Alarma de intrusión activada";
    public static final String TIPO_HUMO = "Alarma de humo";
    public static final String TIPO_ACCIDENTE = "Accidente";
    public static final String TIPO_CAUSA_NATURAL = "Problema de causa natural";
    public static final String TIPO_ASISTENCIA = "Solicitud de asistencia general";
    public static final String TIPO_OTRO = "Otro";

    //Constantes de estado
    public static final String ESTADO_ABIERTA = "Abierta";
    public static final String ESTADO_CERRADA = "Cerrada";

    private static final DateTimeFormatter FMT
            = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // --- Atributos ---
    private String id;
    private String estado;
    private String dniResidente;
    private String urbanizacion;
    private String direccion;
    private String tipo;
    private String descripcion;
    private LocalDateTime fechaHora;
    private String solucion;
    private Vigilante vigilante;

    /**
     * Crea una incidencia en estado abierto sin vigilante asignado. La
     * dirección, descripción y solución son opcionales (pueden ser cadena
     * vacía).
     *
     * @param id Identificador único (1-10 caracteres).
     * @param dniResidente DNI del residente que reporta (8 dígitos + letra).
     * @param urbanizacion Nombre de la urbanización (1-50 caracteres).
     * @param direccion Dirección dentro de la urbanización (puede ser vacía).
     * @param tipo Tipo de incidencia (usar las constantes TIPO_*).
     * @param descripcion Descripción libre del problema (puede ser vacía).
     * @param fechaHora Fecha y hora del registro.
     */
    public Incidencia(String id, String dniResidente, String urbanizacion,
            String direccion, String tipo, String descripcion,
            LocalDateTime fechaHora) {

        //id: obligatorio, 1-10 caracteres, no puede existir duplicado (eso lo comprueba Sistema)
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("El ID no puede estar vacío.");
        }
        if (id.length() > 10) {
            throw new IllegalArgumentException("El ID no puede superar 10 caracteres (actual: " + id.length() + ").");
        }

        //dniResidente: obligatorio, formato 8 dígitos + 1 letra
        if (dniResidente == null || !dniResidente.matches("\\d{8}[A-Za-z]")) {
            throw new IllegalArgumentException("El DNI debe tener 8 dígitos seguidos de una letra (recibido: '" + dniResidente + "').");
        }

        //urbanizacion: obligatoria, 1-50 caracteres
        if (urbanizacion == null || urbanizacion.isBlank()) {
            throw new IllegalArgumentException("El nombre de la urbanización no puede estar vacío.");
        }
        if (urbanizacion.length() > 50) {
            throw new IllegalArgumentException("El nombre de la urbanización no puede superar 50 caracteres (actual: " + urbanizacion.length() + ").");
        }

        //direccion: opcional, pero si se proporciona no puede superar 50 caracteres
        if (direccion != null && direccion.length() > 50) {
            throw new IllegalArgumentException("La dirección no puede superar 50 caracteres (actual: " + direccion.length() + ").");
        }

        //tipo: debe ser una de las constantes definidas
        if (tipo == null || (!tipo.equals(TIPO_INTRUSION)
                && !tipo.equals(TIPO_HUMO)
                && !tipo.equals(TIPO_ACCIDENTE)
                && !tipo.equals(TIPO_CAUSA_NATURAL)
                && !tipo.equals(TIPO_ASISTENCIA)
                && !tipo.equals(TIPO_OTRO))) {
            throw new IllegalArgumentException("Tipo de incidencia no válido: '" + tipo + "'.");
        }

        // --- fechaHora: obligatoria
        if (fechaHora == null) {
            throw new IllegalArgumentException("La fecha y hora no pueden ser nulas.");
        }

        this.id = id;
        this.estado = ESTADO_ABIERTA;
        this.dniResidente = dniResidente;
        this.urbanizacion = urbanizacion;
        this.direccion = (direccion == null) ? "" : direccion;
        this.tipo = tipo;
        this.descripcion = (descripcion == null) ? "" : descripcion;
        this.fechaHora = fechaHora;
        this.solucion = "";
        this.vigilante = null;
    }

    //getters
    /**
     * Devuelve el identificador único de la incidencia.
     *
     * @return Identificador.
     */
    public String getId() {
        return id;
    }

    /**
     * Devuelve el estado actual: {@value #ESTADO_ABIERTA} o
     * {@value #ESTADO_CERRADA}.
     *
     * @return Estado de la incidencia.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Indica si la incidencia está abierta.
     *
     * @return true si el estado es "Abierta".
     */
    public boolean isAbierta() {
        return ESTADO_ABIERTA.equals(estado);
    }

    /**
     * Devuelve el DNI del residente que reportó la incidencia.
     *
     * @return DNI del residente.
     */
    public String getDniResidente() {
        return dniResidente;
    }

    /**
     * Devuelve el nombre de la urbanización donde ocurre el incidente.
     *
     * @return Nombre de la urbanización.
     */
    public String getUrbanizacion() {
        return urbanizacion;
    }

    /**
     * Devuelve la dirección concreta dentro de la urbanización.
     *
     * @return Dirección, o cadena vacía si no se especificó.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Devuelve el tipo de incidencia.
     *
     * @return Tipo (una de las constantes TIPO_*).
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Devuelve la descripción libre del problema.
     *
     * @return Descripción, o cadena vacía si no se especificó.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Devuelve la fecha y hora de registro como objeto {@link LocalDateTime}.
     *
     * @return Fecha y hora.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Devuelve la fecha y hora formateada como "dd/MM/yyyy HH:mm". Usado
     * directamente en la tabla de la vista.
     *
     * @return Cadena con la fecha y hora.
     */
    public String getFechaHoraStr() {
        return fechaHora.format(FMT);
    }

    /**
     * Devuelve la descripción de la solución aplicada.
     *
     * @return Solución, o cadena vacía si no se ha registrado.
     */
    public String getSolucion() {
        return solucion;
    }

    /**
     * Devuelve el vigilante asignado a esta incidencia.
     *
     * @return Vigilante asignado, o {@code null} si no hay ninguno.
     */
    public Vigilante getVigilante() {
        return vigilante;
    }

    /**
     * Devuelve el nombre del vigilante asignado, o "Sin asignar" si no lo hay.
     * Usado directamente en la tabla de la vista sin lógica en el controlador.
     *
     * @return Nombre del vigilante o "Sin asignar".
     */
    public String getNombreVigilante() {
        return (vigilante == null) ? "Sin asignar" : vigilante.getNombre();
    }

    //setters
    /**
     * Cambia el estado de la incidencia. Usar las constantes
     * {@link #ESTADO_ABIERTA} o {@link #ESTADO_CERRADA}.
     *
     * @param estado Nuevo estado.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Actualiza el DNI del residente que reporta la incidencia.
     *
     * @param dniResidente Nuevo DNI.
     */
    public void setDniResidente(String dniResidente) {
        this.dniResidente = dniResidente;
    }

    /**
     * Actualiza el nombre de la urbanización.
     *
     * @param urbanizacion Nueva urbanización.
     */
    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }

    /**
     * Actualiza la dirección dentro de la urbanización.
     *
     * @param direccion Nueva dirección (puede ser vacía).
     */
    public void setDireccion(String direccion) {
        this.direccion = (direccion == null) ? "" : direccion;
    }

    /**
     * Actualiza el tipo de incidencia.
     *
     * @param tipo Nuevo tipo (usar constantes TIPO_*).
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Actualiza la descripción libre del problema.
     *
     * @param descripcion Nueva descripción (puede ser vacía).
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = (descripcion == null) ? "" : descripcion;
    }

    /**
     * Actualiza la fecha y hora de registro.
     *
     * @param fechaHora Nueva fecha y hora.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Registra la descripción de la solución aplicada al cerrar la incidencia.
     *
     * @param solucion Descripción de la solución (puede ser vacía).
     */
    public void setSolucion(String solucion) {
        this.solucion = (solucion == null) ? "" : solucion;
    }

    /**
     * Asigna un vigilante a esta incidencia. Pasar {@code null} para
     * desasignar.
     *
     * @param vigilante Vigilante a asignar, o null para quitar la asignación.
     */
    public void setVigilante(Vigilante vigilante) {
        this.vigilante = vigilante;
    }

    /**
     * Representación de texto de la incidencia, útil para depuración.
     *
     * @return Cadena con id, estado y urbanización.
     */
    @Override
    public String toString() {
        return "[" + id + "] " + estado + " — " + urbanizacion;
    }

}
