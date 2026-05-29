# Sistema de Gestión de Seguridad - GesSec S.L.

## Descripción del Proyecto
Proyecto desarrollado para la asignatura de **Interacción Persona-Computadora (IPC)** durante el curso 2025/2026. La aplicación consiste en una interfaz de escritorio implementada en **Java Swing** que permite a la operadora de la central de seguridad de GesSec S.L. realizar el seguimiento y gestión operativa de incidencias.

El diseño sigue el patrón de arquitectura **Modelo-Vista-Controlador (MVC)**, garantizando la separación de la lógica de negocio, la capa de persistencia en memoria y la interfaz de usuario.

## Características Técnicas
* **Arquitectura:** Modelo-Vista-Controlador (MVC).
* **Gestión de Navegación:** Implementada mediante una máquina de estados controlada por `GestorVistas`.
* **Persistencia:** Datos almacenados en memoria (sin persistencia externa).
* **Entorno de desarrollo:** Apache NetBeans 28, JDK 21.

## Funcionalidades Implementadas
* **Dashboard:** Visualización del contador de incidencias abiertas y acceso a los módulos operativos.
* **Registro de Incidencias:** Formulario de alta con validaciones de formato (DNI, ID, longitud de campos y tipos de incidencia).
* **Gestión Operativa:**
    * Filtrado de incidencias por estado (Abierta/Cerrada/Todas).
    * Asignación de vigilantes disponibles.
    * Edición de detalles y registro de soluciones aplicadas para el cierre de incidencias.

## Estructura del Repositorio
* `src/modelo/`: Clases de dominio y lógica del sistema.
* `src/vista/`: Interfaz gráfica basada en formularios `JFrame` (Swing).
* `src/controlador/`: Lógica de control y gestión de eventos (ActionListener).
* `src/principal/`: Clase de entrada (`Main`) para la ejecución de la aplicación.
