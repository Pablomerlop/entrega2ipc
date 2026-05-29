# p2_grupo39



# GesSec S.L. - Sistema de Gestión de Seguridad (Práctica 2)

**Universidad de Valladolid** | Escuela de Ingeniería Informática
**Asignatura:** Interacción Persona-Computadora (Curso 2025/2026)
**Grado:** Ingeniería Informática + InDat

---

## Introducción
Este repositorio contiene la implementación de un prototipo interactivo para el rol de "Operadora Senior de Central" de la empresa GesSec S.L., dedicada a la gestión de seguridad en entornos residenciales. El proyecto es el resultado del diseño previo de prototipos de baja fidelidad y tests de usabilidad.

### Objetivos del Proyecto
* Desarrollar componentes sencillos de GUI en Java con la biblioteca Java Swing, utilizando el entorno NetBeans.
* Aplicar el patrón arquitectónico MVC al diseño de una aplicación interactiva.
* Implementar una aplicación interactiva que emule el comportamiento de algunas de las interfaces de usuario previamente diseñadas.
* Aplicar la gestión de múltiples ventanas mediante máquina de estados.

---

## Funcionalidades

### Menú Principal
* Acceso conjunto al resto de funcionalidades del sistema a través de este menú.
* Visualización del número total de incidencias abiertas.
* Ventana inicial por defecto al arrancar la aplicación.

### Creación Manual de Incidencias
* **Identificador:** Cadena de texto entre 1 y 10 caracteres que no debe coincidir con otra incidencia del sistema.
* **Estado:** Abierta o cerrada.
* **DNI del residente:** 8 números seguidos de una letra (Obligatorio).
* **Urbanización:** Nombre entre 1 y 50 caracteres (Obligatorio).
* **Dirección:** Entre 1 y 50 caracteres (No obligatorio).
* **Tipo:** Alarma de intrusión, alarma de humo, accidente, problema de causa natural, asistencia general u otro (Obligatorio, valor por defecto: "otro").
* **Descripción:** Texto libre sin límite de caracteres (No obligatorio).
* **Fecha y hora:** Campo obligatorio.
* **Solución (solo si está cerrada):** Descripción en texto libre sin límite.
* **Asignación de Vigilante:** Opción de asignar un vigilante al abrir o dejarlo para más tarde.

### Gestión de Incidencias
* Visualizar la información completa de cualquier incidencia.
* Asignar un vigilante disponible (se asume disponibilidad salvo que estén asignados a otra incidencia abierta).
* Modificar la información de una incidencia abierta (siempre que no esté ya asignada a un vigilante).
* Cambiar el estado de la incidencia (abierta/cerrada).
* Filtrar la visualización por estado: abiertas, cerradas o todas.

---

## Detalles Técnicos
* **Interfaz:** Al menos 3 ventanas navegables, aplicando las mejoras descubiertas en el test de usabilidad de la Práctica 1. Los cambios realizados en una ventana se reflejan dinámicamente en las demás.
* **Tecnología:** Compatible con Apache NetBeans 28 y JDK 21.
* **Persistencia:** Los datos se guardan temporalmente en memoria y se pierden al cerrar la aplicación.

### Datos Iniciales (Mock Data)
El modelo se inicializa con los siguientes datos simulados:

**Vigilantes**
| Nombre | Teléfono |
| :--- | :--- |
| José Miras | 600102030 |
| Laura Prisma | 692375610 |
| Carlos Mirón | 612345678 |
| Juan Guerrero | 698765432 |
| Francisco Cárceles | 611223344 |

**Incidencias Pre-cargadas**
* **Incidencia 1:** DNI "12000000A", Urbanización "Los Pinos" (Calle Páramo, n°4). Tipo: "otro". Descripción: "Una farola de la calle no funciona correctamente...". Fecha: 01/05/2026 03:20. Estado: cerrada (Solución: "Un cable de la farola estaba pelado..."). Vigilante: Carlos Mirón.
* **Incidencia 2:** DNI "07777777B", Urbanización "Los Limoneros". Tipo: "alarma de humo". Descripción: "Se puede ver humo extendiéndose...". Fecha: 07/05/2026 15:15. Estado: abierta. Vigilante: José Miras.
* **Incidencia 3:** DNI "41234567C", Urbanización "Los Soles" (Calle Perales, n°15). Tipo: "accidente". Descripción: "Ha habido un choque entre dos vehículos...". Fecha: 08/05/2026 17:05. Estado: abierta.

---

## Evaluación y Entrega
El peso en la nota final de esta práctica es del 10%. El proyecto se empaquetará en un archivo ZIP nombrado `p2_grupox.zip` (donde "X" es el número del grupo).

**Criterios de Calificación:**
* **Usabilidad (35%):** Atributos de usabilidad y uso correcto de componentes de IU.
* **Funcionalidades y código (50%):** Implementación de funcionalidades, uso del patrón MVC, gestión de ventanas y Javadoc.
* **Estética (15%):** Acabado visual de la IU.

> Nota: La entrega incluye una defensa presencial obligatoria por parte de todos los miembros del grupo. La inasistencia no justificada supone un 0 en la nota.

### Calendario de Entregas (Campus Virtual)
* **Grupo L1:** 26/05/26 a las 23:59
* **Grupo L2:** 26/05/26 a las 23:59
* **Grupo L3:** 25/05/26 a las 23:59
* **Grupo L4:** 28/05/26 a las 23:59
* **Grupo L5:** 29/05/26 a las 23:59
* **Grupo L6:** 29/05/26 a las 23:59
##  Requisitos e Instalación

### Requisitos previos
- **Java JDK 21**.
- **IDE**: NetBeans
