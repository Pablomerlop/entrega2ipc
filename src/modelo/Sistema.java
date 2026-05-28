/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Modelo principal de la aplicación GesSec (capa model). Gestiona los datos de
 * incidencias y vigilantes en memoria.
 *
 * @author p2_grupo39
 */
public class Sistema {

    private ArrayList<Incidencia> listaIncidencias;
    private ArrayList<Vigilante> listaVigilantes;

    /**
     * Crea el sistema con los datos iniciales del enunciado.
     */
    public Sistema() {
        listaIncidencias = new ArrayList<>();
        listaVigilantes = new ArrayList<>();
        inicializarDatos();
    }

    private void inicializarDatos() {
        // --- Vigilantes ---
        Vigilante jose = new Vigilante("José Miras", "600102030");
        Vigilante laura = new Vigilante("Laura Prisma", "692375610");
        Vigilante carlos = new Vigilante("Carlos Mirón", "612345678");
        Vigilante juan = new Vigilante("Juan Guerrero", "698765432");
        Vigilante francisco = new Vigilante("Francisco Cárceles", "611223344");
        listaVigilantes.add(jose);
        listaVigilantes.add(laura);
        listaVigilantes.add(carlos);
        listaVigilantes.add(juan);
        listaVigilantes.add(francisco);

        // --- Incidencias ---
        // Incidencia 1: cerrada, vigilante Carlos Mirón
        Incidencia i1 = new Incidencia(
                "INC001", "12000000A", "Los Pinos",
                "Calle Páramo, nº4", Incidencia.TIPO_OTRO,
                "Una farola de la calle no funciona correctamente y las condiciones "
                + "de baja luminosidad pueden hacer peligrosa la calle",
                LocalDateTime.of(2026, 5, 1, 3, 20)
        );
        i1.setEstado("Cerrada");
        i1.setSolucion("Un cable de la farola estaba pelado y se ha arreglado "
                + "con un poco de cinta aislante");
        i1.setVigilante(carlos);
        listaIncidencias.add(i1);

        // Incidencia 2: abierta, vigilante José Miras
        Incidencia i2 = new Incidencia(
                "INC002", "07777777B", "Los Limoneros",
                "", Incidencia.TIPO_HUMO,
                "Se puede ver humo extendiéndose por toda la urbanización "
                + "y no se tiene muy claro su origen",
                LocalDateTime.of(2026, 5, 7, 15, 15)
        );
        i2.setVigilante(jose);
        listaIncidencias.add(i2);

        // Incidencia 3: abierta, sin vigilante asignado
        Incidencia i3 = new Incidencia(
                "INC003", "41234567C", "Los Soles",
                "Calle Perales, nº15", Incidencia.TIPO_ACCIDENTE,
                "Ha habido un choque entre dos vehículos que circulaban en esta "
                + "calle y están bloqueando la circulación",
                LocalDateTime.of(2026, 5, 8, 17, 5)
        );
        listaIncidencias.add(i3);
    }

}
