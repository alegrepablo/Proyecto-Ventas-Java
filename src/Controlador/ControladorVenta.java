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
    private VistaVentas vista;
    private VentaDAO ventaDAO;
    private ProductoDAO productoDAO;
    private ClienteDAO clienteDAO;

    public ControladorVenta(VistaVentas vista, VentaDAO ventaDAO, ProductoDAO productoDAO, ClienteDAO clienteDAO) {
        this.vista = vista;
        this.ventaDAO = ventaDAO;
        this.productoDAO = productoDAO;
        this.clienteDAO = clienteDAO;
    }

    /*public void realizarVenta() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del cliente: ");
        int idCliente = scanner.nextInt();
        System.out.print("Ingrese el ID del producto: ");
        int idProducto = scanner.nextInt();
        System.out.print("Ingrese la cantidad: ");
        int cantidad = scanner.nextInt();

        try {
            // Obtener el precio del producto
            double precioProducto = productoDAO.obtenerPrecio(idProducto);
            double precioTotal = precioProducto * cantidad;

            // Generar la fecha actual
            Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());

            // Llamar al método realizarVenta en VentaDAO con la fecha actual
            ventaDAO.realizarVenta(idCliente, fechaActual, precioTotal);
            vista.mostrarMensaje("Venta realizada con éxito.");
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al realizar la venta: " + e.getMessage());
        }
    }*/

    /*public void realizarVenta() {
        Scanner scanner = new Scanner(System.in);
        int idCliente;
        int idProducto;
        int cantidad;

        // Verificar que el ID del cliente sea válido
        while (true) {
            System.out.print("Ingrese el ID del cliente: ");
            idCliente = scanner.nextInt();
            if (clienteDAO.existeCliente(idCliente)) {
                break;
            } else {
                vista.mostrarMensaje("ID de cliente no válido. Por favor, intente nuevamente.");
            }
        }

        // Verificar que el ID del producto sea válido
        while (true) {
            System.out.print("Ingrese el ID del producto: ");
            idProducto = scanner.nextInt();
            if (productoDAO.existeProducto(idProducto)) {
                break;
            } else {
                vista.mostrarMensaje("ID de producto no válido. Por favor, intente nuevamente.");
            }
        }

        // Verificar que la cantidad sea un valor positivo
        while (true) {
            System.out.print("Ingrese la cantidad: ");
            cantidad = scanner.nextInt();
            if (cantidad > 0) {
                break;
            } else {
                vista.mostrarMensaje("La cantidad debe ser mayor que cero. Por favor, intente nuevamente.");
            }
        }

        try {
            // Obtener el precio del producto
            double precioProducto = productoDAO.obtenerPrecio(idProducto);
            double precioTotal = precioProducto * cantidad;

            // Generar la fecha actual
            Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());

            // Llamar al método realizarVenta en VentaDAO con la fecha actual
            ventaDAO.realizarVenta(idCliente, fechaActual, precioTotal);
            vista.mostrarMensaje("Venta realizada con éxito.");
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al realizar la venta: " + e.getMessage());
        }
    }*/

    /*public void realizarVenta() {
        Scanner scanner = new Scanner(System.in);
        int idCliente = obtenerIdClienteValido(scanner);
        int idProducto = obtenerIdProductoValido(scanner);
        int cantidad = obtenerCantidadValida(scanner);

        try {
            // Obtener el precio del producto
            double precioProducto = productoDAO.obtenerPrecio(idProducto);
            double precioTotal = precioProducto * cantidad;

            // Generar la fecha actual
            Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());

            // Llamar al método realizarVenta en VentaDAO con la fecha actual
            ventaDAO.realizarVenta(idCliente, fechaActual, precioTotal);
            vista.mostrarMensaje("Venta realizada con éxito.");
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al realizar la venta: " + e.getMessage());
        }
    }

    private int obtenerIdClienteValido(Scanner scanner) {
        int idCliente = -1;
        boolean esValido = false;

        while (!esValido) {
            try {
                System.out.print("Ingrese el ID del cliente: ");
                idCliente = scanner.nextInt();

                if (clienteDAO.existeCliente(idCliente)) {  // Suponiendo que este método verifica si el cliente existe
                    esValido = true;
                } else {
                    vista.mostrarMensaje("ID de cliente no encontrado. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                vista.mostrarMensaje("Entrada no válida. Ingrese un número entero para el ID del cliente.");
                scanner.next(); // Limpiar la entrada no válida
            }
        }

        return idCliente;
    }

    private int obtenerIdProductoValido(Scanner scanner) {
        int idProducto = -1;
        boolean esValido = false;

        while (!esValido) {
            try {
                System.out.print("Ingrese el ID del producto: ");
                idProducto = scanner.nextInt();

                if (productoDAO.existeProducto(idProducto)) {  // Suponiendo que este método verifica si el producto existe
                    esValido = true;
                } else {
                    vista.mostrarMensaje("ID de producto no encontrado. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                vista.mostrarMensaje("Entrada no válida. Ingrese un número entero para el ID del producto.");
                scanner.next(); // Limpiar la entrada no válida
            }
        }

        return idProducto;
    }

    private int obtenerCantidadValida(Scanner scanner) {
        int cantidad = -1;
        boolean esValida = false;

        while (!esValida) {
            try {
                System.out.print("Ingrese la cantidad: ");
                cantidad = scanner.nextInt();

                if (cantidad > 0) {
                    esValida = true;
                } else {
                    vista.mostrarMensaje("La cantidad debe ser un número positivo. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                vista.mostrarMensaje("Entrada no válida. Ingrese un número entero para la cantidad.");
                scanner.next(); // Limpiar la entrada no válida
            }
        }

        return cantidad;
    }


    /*public void mostrarVentas() {
        try {
            String ventas = ventaDAO.listarVentas();
            vista.mostrarVentas(ventas);
        } catch (SQLException e) {
            vista.mostrarMensaje("Error al mostrar las ventas: " + e.getMessage());
        }
    }*/

    public void realizarVenta() {
        Scanner scanner = new Scanner(System.in);
        int idCliente = -1;
        int idProducto = -1;
        int cantidad = -1;

        // Solicitar ID de cliente
        while (true) {
            System.out.print("Ingrese el ID del cliente: ");
            try {
                idCliente = scanner.nextInt();
                if (clienteDAO.existeCliente(idCliente)) {
                    break;
                } else {
                    System.out.println("El ID de cliente no existe. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.next(); // Descartar entrada inválida
            }
        }

        // Solicitar ID de producto
        while (true) {
            System.out.print("Ingrese el ID del producto: ");
            try {
                idProducto = scanner.nextInt();
                if (productoDAO.existeProducto(idProducto)) {
                    break;
                } else {
                    System.out.println("El ID de producto no existe. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.next(); // Descartar entrada inválida
            }
        }

        // Solicitar cantidad
        while (true) {
            System.out.print("Ingrese la cantidad: ");
            try {
                cantidad = scanner.nextInt();
                if (cantidad > 0) {
                    break;
                } else {
                    System.out.println("La cantidad debe ser un número positivo. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.next(); // Descartar entrada inválida
            }
        }

        // Realizar la venta
        try {
            // Obtener el precio del producto
            double precioProducto = productoDAO.obtenerPrecio(idProducto);
            double precioTotal = precioProducto * cantidad;

            // Generar la fecha actual
            Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());

            // Llamar al método realizarVenta en VentaDAO con la fecha actual
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

