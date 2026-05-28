/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 * Representa un vigilante de seguridad de la empresa GesSec S.L.
 *
 * @author p2_grupo39
 */
public class Vigilante {

    private String nombre;
    private String telefono;

    /**
     * Crea un vigilante con nombre y teléfono.
     *
     * @param nombre Nombre completo del vigilante.
     * @param telefono Número de teléfono de contacto.
     */
    public Vigilante(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    /**
     * Devuelve el nombre completo del vigilante.
     *
     * @return Nombre del vigilante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre completo del vigilante.
     *
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el número de teléfono del vigilante.
     *
     * @return Teléfono del vigilante.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del vigilante.
     *
     * @param telefono Nuevo teléfono.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Representación de texto, útil para JList y JComboBox.
     *
     * @return Nombre del vigilante.
     */
    @Override
    public String toString() {
        return nombre;
    }

}
