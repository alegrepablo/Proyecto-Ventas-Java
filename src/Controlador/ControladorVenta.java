package Controlador;

import DAO.ClienteDAO;
import DAO.ProductoDAO;
import DAO.VentaDAO;
import Modelo.Venta;
import Vista.VistaVentas;
import java.util.InputMismatchException;


import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class ControladorVenta {
    // Atributos principales: vista para interactuar y DAOs necesarios para las ventas
    private VistaVentas vista;
    private VentaDAO ventaDAO;
    private ProductoDAO productoDAO;
    private ClienteDAO clienteDAO;

    // Constructor que inicializa la vista y los DAOs
    public ControladorVenta(VistaVentas vista, VentaDAO ventaDAO, ProductoDAO productoDAO, ClienteDAO clienteDAO) {
        this.vista = vista;
        this.ventaDAO = ventaDAO;
        this.productoDAO = productoDAO;
        this.clienteDAO = clienteDAO;
    }

    // Método para realizar una venta
    public void realizarVenta() {
        Scanner scanner = new Scanner(System.in);
        int idCliente = -1;
        int idProducto = -1;
        int cantidad = -1;

        // Validación del ID del cliente
        while (true) {
            System.out.print("Ingrese el ID del cliente: ");
            try {
                idCliente = scanner.nextInt();
                if (clienteDAO.existeCliente(idCliente)) {     // Verifica si el cliente existe
                    break;
                } else {
                    System.out.println("El ID de cliente no existe. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.next(); // Limpia la entrada inválida
            }
        }

        // Validación del ID del producto
        while (true) {
            System.out.print("Ingrese el ID del producto: ");
            try {
                idProducto = scanner.nextInt();
                if (productoDAO.existeProducto(idProducto)) {    // Verifica si el producto existe
                    break;
                } else {
                    System.out.println("El ID de producto no existe. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.next(); // Limpia la entrada inválida
            }
        }

        // Validacion de la cantidad
        while (true) {
            System.out.print("Ingrese la cantidad: ");
            try {
                cantidad = scanner.nextInt();
                if (cantidad > 0) { //La cantidad debe ser positiva
                    break;
                } else {
                    System.out.println("La cantidad debe ser un número positivo. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.next(); // Limpia la entrada inválida
            }
        }

        // Realizar la venta
        try {
            // Obtener el precio del producto
            double precioProducto = productoDAO.obtenerPrecio(idProducto);  // Obtiene el precio del producto
            double precioTotal = precioProducto * cantidad;                 // Calcula el precio total

            // Generar la fecha actual
            Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());

            // Registra la venta en el DAO
            ventaDAO.realizarVenta(idCliente, fechaActual, precioTotal);

            // Actualizar el stock del producto
            productoDAO.actualizarStock(idProducto, cantidad);

            vista.mostrarMensaje("Venta realizada con éxito.");
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al realizar la venta: " + e.getMessage());
        }
    }


    // Métodos para validar la entrada
    private int obtenerIdValido(Scanner scanner, String tipo) {
        int id;
        while (true) {
            System.out.print("Ingrese el ID del " + tipo + ": ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                if (id > 0) break;
                System.out.println("ID inválido. Debe ser un número entero positivo.");
            } else {
                System.out.println("Entrada inválida. Ingrese un número entero.");
                scanner.next();
            }
        }
        return id;
    }

    private int obtenerCantidadValida(Scanner scanner) {
        int cantidad;
        while (true) {
            System.out.print("Ingrese la cantidad: ");
            if (scanner.hasNextInt()) {
                cantidad = scanner.nextInt();
                if (cantidad > 0) break;
                System.out.println("Cantidad inválida. Debe ser un número entero positivo.");
            } else {
                System.out.println("Entrada inválida. Ingrese un número entero.");
                scanner.next();
            }
        }
        return cantidad;
    }


    public void mostrarVentas() {
        try {
            List<Venta> ventas = ventaDAO.listarVentas();  // Obtiene la lista de ventas
            vista.mostrarVentas(ventas);                   // Pasa la lista a la vista
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al mostrar las ventas: " + e.getMessage());
        }
    }

}

