package Vista;

import Modelo.Cliente;
import java.util.List;
import java.util.Scanner;

// Clase para manejar la interfaz de usuario relacionada con clientes
public class VistaClientes {
    // Muestra las opciones del menú de clientes
    public void mostrarMenu() {
        System.out.println("Menú Clientes:");
        System.out.println("1. Agregar Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Actualizar Cliente");
        System.out.println("4. Eliminar Cliente");
        System.out.println("5. Volver");
    }

    // Muestra un mensaje general al usuario
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Muestra la lista de clientes en formato de tabla
    public void mostrarClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes disponibles.");
        } else {
            // Calcula el ancho de las columnas según el contenido
            int idWidth = "ID".length();
            int nombreWidth = "Nombre".length();
            int correoWidth = "Correo".length();

            // Ajusta el ancho según los datos más largos
            for (Cliente cliente : clientes) {
                idWidth = Math.max(idWidth, String.valueOf(cliente.getId()).length());
                nombreWidth = Math.max(nombreWidth, cliente.getNombre().length());
                correoWidth = Math.max(correoWidth, cliente.getCorreo().length());
            }

            // Crea y muestra la tabla con formato
            String format = "| %-" + idWidth + "s | %-" + nombreWidth + "s | %-" + correoWidth + "s |\n";
            String line = "+-" + "-".repeat(idWidth) + "-+-" + "-".repeat(nombreWidth) + "-+-" + "-".repeat(correoWidth) + "-+";

            // Imprimir cabecera
            System.out.println(line);
            System.out.printf(format, "ID", "Nombre", "Correo");
            System.out.println(line);

            // Imprimir los datos de los clientes
            for (Cliente cliente : clientes) {
                System.out.printf(format, cliente.getId(), cliente.getNombre(), cliente.getCorreo());
            }

            // Imprimir línea de cierre
            System.out.println(line);
        }
    }

    // Obtiene los datos de un nuevo cliente desde el teclado
    public Cliente obtenerDatosCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo del cliente: ");
        String correo = scanner.nextLine();
        return new Cliente(0, nombre, correo); // id se generará automáticamente
    }

    // Solicita y obtiene el ID de un cliente
    public int obtenerIdCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del cliente: ");
        return scanner.nextInt();
    }

    // Muestra mensaje de confirmación de actualización
    public void mostrarClienteActualizado() {
        System.out.println("Cliente actualizado correctamente.");
    }

    // Muestra mensaje de confirmación de eliminación
    public void mostrarClienteEliminado() {
        System.out.println("Cliente eliminado correctamente.");
    }
}
