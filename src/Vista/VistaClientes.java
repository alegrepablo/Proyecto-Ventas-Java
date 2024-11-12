package Vista;

import Modelo.Cliente;
import java.util.List;
import java.util.Scanner;

public class VistaClientes {
    public void mostrarMenu() {
        System.out.println("Menú Clientes:");
        System.out.println("1. Agregar Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Actualizar Cliente");
        System.out.println("4. Eliminar Cliente");
        System.out.println("5. Volver");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /*public void mostrarClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes disponibles.");
        } else {
            System.out.println("Clientes:");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }*/
    public void mostrarClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes disponibles.");
        } else {
            // Determinar el ancho de las columnas
            int idWidth = "ID".length();
            int nombreWidth = "Nombre".length();
            int correoWidth = "Correo".length();

            // Calcular el ancho de cada columna en función del contenido
            for (Cliente cliente : clientes) {
                idWidth = Math.max(idWidth, String.valueOf(cliente.getId()).length());
                nombreWidth = Math.max(nombreWidth, cliente.getNombre().length());
                correoWidth = Math.max(correoWidth, cliente.getCorreo().length());
            }

            // Crear el formato de las columnas
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

    public Cliente obtenerDatosCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Correo del cliente: ");
        String correo = scanner.nextLine();
        return new Cliente(0, nombre, correo); // id se generará automáticamente
    }

    public int obtenerIdCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del cliente: ");
        return scanner.nextInt();
    }

    public void mostrarClienteActualizado() {
        System.out.println("Cliente actualizado correctamente.");
    }

    public void mostrarClienteEliminado() {
        System.out.println("Cliente eliminado correctamente.");
    }
}
