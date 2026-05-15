#Diagrama de Clases (MVC)


```mermaid
classDiagram
    class Main {
        +main(args: String[])
    }

    %% Paquete Model: Lógica de negocio y datos puros
    namespace model {
        class ModeloCentral {
            -listaIncidentes: ArrayList~Incidencia~
            -listaResidentes: ArrayList~Residente~
            +ModeloCentral()
            +getIncidentes(): ArrayList~Incidencia~
            +buscarResidentePorTelefono(telefono: String): Residente
            +agregarIncidencia(inc: Incidencia)
            +cerrarIncidenciaErronea(idIncidencia: String)
        }
        
        class Incidencia {
            -id: String
            -tipo: String
            -descripcion: String
            -estado: String
            -direccion: String
            +getId(): String
            +getTipo(): String
            +getEstado(): String
            +setEstado(estado: String)
            +getDescripcion(): String
        }
        
        class Residente {
            -nombre: String
            -dni: String
            -telefono: String
            -direccion: String
            -urbanizacion: String
            +getNombre(): String
            +getTelefono(): String
            +getDireccion(): String
        }
    }

    %% Paquete View: Interfaz gráfica (Java Swing)
    namespace view {
        class VistaCentral {
            -frame: JFrame
            -panelContenedor: JPanel
            -tablaIncidencias: JTable
            -btnEntrar: JButton
            -btnIdentificarResidente: JButton
            -btnCerrarAlerta: JButton
            -btnBuscarTlf: JButton
            -btnCrearIncidencia: JButton
            -txtTelefono: JTextField
            +VistaCentral()
            +iniciarVista()
            +mostrarPanel(nombrePanel: String)
            +actualizarTabla(datos: Object[][])
            +mostrarMensaje(mensaje: String)
            +addControlador(c: ActionListener)
            +getTelefonoBuscado(): String
            +getFilaSeleccionada(): int
        }
    }

    %% Paquete Controller: Intermediario y gestión de eventos
    namespace controller {
        class ControladorCentral {
            -vista: VistaCentral
            -modelo: ModeloCentral
            +ControladorCentral(v: VistaCentral, m: ModeloCentral)
            +actionPerformed(e: ActionEvent)
            -procesarLogin()
            -cargarDatosTabla()
            -procesarCierreAlerta()
            -procesarBusquedaResidente()
            -procesarNuevaIncidencia()
        }
    }

    %% Relaciones
    Main ..> ModeloCentral : Instancia
    Main ..> VistaCentral : Instancia
    Main ..> ControladorCentral : Instancia
    
    ControladorCentral --> VistaCentral : Manipula
    ControladorCentral --> ModeloCentral : Lee/Modifica
    
    ModeloCentral "1" *-- "0..*" Incidencia : Contiene
    ModeloCentral "1" *-- "0..*" Residente : Contiene


Ventana de Acceso (Login): Pantalla inicial para autenticación
    .Contenido: Campos de texto para credenciales y acceso a la central.

Ventana Principal (Dashboard de Incidencias): El centro de control de la operadora.
    Contenido: Lista global de incidencias activas (incluyendo las alertas automáticas del Escenario 9) y botones de navegación rápida.


Ventana de Gestión de Incidencia/Residente: Ventana modal o secundaria para el detalle.
    Contenido: Buscador de residentes por teléfono (Escenario 8), formulario de creación de incidencias y opciones para cerrar alertas (Escenario 9).  




2. Elementos de cada Ventana y LayoutPara el diseño, utilizaremos gestores de diseño (Layouts) como BorderLayout y GridLayout, tal como se sugiere en el material de apoyo.  
        A. Ventana de LoginLayout: 
        GridLayout(3, 2) o un panel central con FlowLayout.
        Elementos:
            JTextField para el usuario y JPasswordField para la contraseña.
            JButton ("Entrar"): Al pulsar, el Controlador valida los datos contra el Modelo y solicita a la Vista ocultar esta ventana y mostrar el Dashboard.
    
        B. Ventana Principal (Dashboard)
        Layout: BorderLayout.
        Elementos:
            Norte: JLabel con el título "Central de Operaciones - GesSec".
            Centro: JScrollPane que contiene una JTable. Esta tabla muestra ID, Urbanización, Tipo y Estado de las incidencias.  
            Sur (Panel de acciones): JPanel con FlowLayout que contiene:
                JButton ("Identificar Residente"): Abre la ventana de gestión.
                JButton ("Cerrar Alerta"): Para el Escenario 9. Si se selecciona una fila de la tabla, permite marcarla como "Errónea".  
        
        C. Ventana de Gestión (Detalle/Búsqueda)
        Layout: BorderLayout subdividido.
        Elementos:
            Panel Superior: JTextField (para el teléfono) y JButton ("Buscar"). Al interactuar, el controlador busca en la lista de residentes del modelo.
            Panel Central: JLabels informativos que muestran los datos del residente encontrado (Nombre, Dirección, Urbanización).Panel Inferior: Área de texto (JTextArea) para la descripción del problema y JButton ("Crear Incidencia").

    1. Capa model (Lógica y Datos)
Aquí no debe haber nada de Java Swing (ni JFrame, ni JButton). Solo lógica pura.

Incidencia y Residente: Son clases POJO (Plain Old Java Object). Solo tienen constructores, variables privadas y métodos get y set.

ModeloCentral: Es el "cerebro" de los datos. En su constructor, debes crear unos cuantos residentes y alarmas "a fuego" (hardcoded) y meterlos en sus ArrayList para tener datos de prueba. Tendrá métodos como buscarResidentePorTelefono(String tlf) que recorrerá el array de residentes y devolverá el objeto si coincide, o null si no existe.

2. Capa view (Interfaz Gráfica)
VistaCentral: Centraliza todo el Swing. Para cumplir con la restricción de múltiples ventanas sin volveros locos, podéis usar un JFrame principal que contenga un CardLayout. Esto permite apilar los paneles (PanelLogin, PanelDashboard, PanelGestion) como si fueran cartas y mostrar solo uno a la vez cambiando de vista.

Importante: La Vista no toma decisiones. Solo expone métodos para que el controlador extraiga la información (ej. getTelefonoBuscado()) y un método addControlador(ActionListener c) que asigna el controlador a todos los botones.

3. Capa controller (El Intermediario)
ControladorCentral: Implementa ActionListener. Su método actionPerformed(ActionEvent e) es el corazón del programa.

Si el evento viene del btnEntrar, le dice a la vista: vista.mostrarPanel("Dashboard").

Si viene del btnCerrarAlerta (Escenario 9), saca la fila seleccionada de la tabla mediante la vista, le dice al modelo modelo.cerrarIncidenciaErronea(id) y luego le dice a la vista que refresque la tabla.

Si viene del btnBuscarTlf (Escenario 8), coge el texto de la vista, llama al modelo para buscar al residente. Si el modelo devuelve un residente, le dice a la vista que rellene los campos de texto con los datos de esa persona.