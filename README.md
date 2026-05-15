# p2_grupo39



# GesSec S.L. - Sistema de Gestión de Seguridad (Práctica 2)

Este proyecto corresponde a la **Práctica 2** de la asignatura **Interacción Persona-Computadora (IPC)**. Consiste en el desarrollo de una interfaz gráfica de escritorio funcional para la operadora de la central de seguridad de GesSec S.L., aplicando los principios de Diseño Centrado en el Usuario (DCU) y el patrón de arquitectura **Modelo-Vista-Controlador (MVC)**.

## 📋 Descripción del Proyecto

El objetivo es implementar una aplicación en Java Swing que permita a una Operadora Senior de Central gestionar incidencias y residentes de urbanizaciones privadas. La aplicación se centra en la eficiencia y facilidad de uso, permitiendo:
- **Autenticación segura** mediante una ventana de Login.
- **Visualización en tiempo real** de alertas e incidencias en un Dashboard principal.
- **Gestión de datos**, incluyendo la búsqueda de residentes y el registro de nuevas incidencias.

## 🏗️ Arquitectura: Modelo-Vista-Controlador (MVC)

Para garantizar la mantenibilidad y la separación de responsabilidades, el proyecto sigue estrictamente el patrón MVC:

- **Modelo (`model`)**: Gestiona la lógica de negocio y los datos (Incidencias, Residentes, Vehículos). No tiene conocimiento de la interfaz gráfica.
- **Vista (`view`)**: Define la interfaz de usuario mediante componentes Swing (JFrame, JPanel, JTable). Es pasiva y notifica eventos al controlador.
- **Controlador (`controller`)**: Actúa como puente. Escucha las acciones de la vista (ActionListener) y actualiza el modelo o cambia de ventana según sea necesario.

## 🚀 Características Principales

1. **Multiventana**: Implementación de navegación fluida entre la pantalla de acceso y el panel de control.
2. **Tablas Dinámicas**: Uso de `JTable` para listar incidencias, permitiendo una visión clara del estado de la seguridad.
3. **Buscador de Residentes**: Funcionalidad para identificar usuarios rápidamente a través de su número de teléfono.
4. **Tratamiento de Errores**: Validación de campos de entrada y mensajes informativos al usuario.

## 🛠️ Requisitos e Instalación

### Requisitos previos
- **Java JDK 21**.
- **IDE**: NetBeans
