package Controlador;

import DAO.ClienteDAO;
import Modelo.Cliente;
import Vista.VistaClientes;

import java.util.List;
import java.util.Scanner;

public class ControladorCliente {
    // Atributos principales: la vista para interactuar con el usuario y el DAO para acceder a los datos
    private VistaClientes vista;
    private ClienteDAO clienteDAO;

    // Constructor que inicializa la vista y el DAO
    public ControladorCliente(VistaClientes vista, ClienteDAO clienteDAO) {
        this.vista = vista;
        this.clienteDAO = clienteDAO;
    }

    // Método principal que controla el flujo de las operaciones
    public void iniciar() {
        boolean salir = false;  // Bandera para salir del menú
        while (!salir) {    // Bucle que se ejecuta hasta que el usuario elija salir
            vista.mostrarMenu();    // Muestra el menú de opciones al usuario
            int opcion = new Scanner(System.in).nextInt();  // Captura la opción ingresada por el usuario
            switch (opcion) {
                case 1: // Agregar un cliente
                    Cliente nuevoCliente = vista.obtenerDatosCliente(); // Solicita los datos del cliente a la vista
                    clienteDAO.agregarCliente(nuevoCliente);    // Agrega el cliente a través del DAO
                    vista.mostrarMensaje("Cliente agregado correctamente.");    // Confirma al usuario
                    break;
                case 2: // Listar clientes
                    List<Cliente> clientes = clienteDAO.listarClientes();  // Obtiene la lista de clientes desde el DAO
                    vista.mostrarClientes(clientes);    // Muestra la lista de clientes a través de la vista
                    break;
                case 3: // Actualizar cliente
                    int idActualizar = vista.obtenerIdCliente();    // Solicita el ID del cliente a actualizar
                    Cliente clienteActualizar = vista.obtenerDatosCliente();    // Solicita los nuevos datos del cliente
                    clienteActualizar.setId(idActualizar);  // Asocia el ID al cliente
                    clienteDAO.actualizarCliente(clienteActualizar);    // Actualiza el cliente en el DAO
                    vista.mostrarClienteActualizado();  // Confirma al usuario
                    break;
                case 4: // Eliminar cliente
                    int idEliminar = vista.obtenerIdCliente(); // Solicita el ID del cliente a eliminar
                    clienteDAO.eliminarCliente(idEliminar); // Elimina el cliente a través del DAO
                    vista.mostrarClienteEliminado();    // Confirma al usuario
                    break;
                case 5: // Salir del menú
                    salir = true;   // Cambia la bandera para salir del bucle
                    break;
                default:    // Opción inválida
                    vista.mostrarMensaje("Opción no válida.");  // Informa al usuario
            }
        }
    }
}
