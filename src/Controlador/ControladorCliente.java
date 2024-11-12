package Controlador;

import DAO.ClienteDAO;
import Modelo.Cliente;
import Vista.VistaClientes;

import java.util.List;
import java.util.Scanner;

public class ControladorCliente {
    private VistaClientes vista;
    private ClienteDAO clienteDAO;

    public ControladorCliente(VistaClientes vista, ClienteDAO clienteDAO) {
        this.vista = vista;
        this.clienteDAO = clienteDAO;
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            vista.mostrarMenu();
            int opcion = new Scanner(System.in).nextInt();
            switch (opcion) {
                case 1:
                    Cliente nuevoCliente = vista.obtenerDatosCliente();
                    clienteDAO.agregarCliente(nuevoCliente);
                    vista.mostrarMensaje("Cliente agregado correctamente.");
                    break;
                case 2:
                    List<Cliente> clientes = clienteDAO.listarClientes();
                    vista.mostrarClientes(clientes);
                    break;
                case 3:
                    int idActualizar = vista.obtenerIdCliente();
                    Cliente clienteActualizar = vista.obtenerDatosCliente();
                    clienteActualizar.setId(idActualizar);
                    clienteDAO.actualizarCliente(clienteActualizar);
                    vista.mostrarClienteActualizado();
                    break;
                case 4:
                    int idEliminar = vista.obtenerIdCliente();
                    clienteDAO.eliminarCliente(idEliminar);
                    vista.mostrarClienteEliminado();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        }
    }
}
