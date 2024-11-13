package Vista;

import Modelo.Venta;
import java.util.List;

// Clase para manejar la interfaz de usuario de ventas
public class VistaVentas {
    // Muestra la lista de ventas en formato tabla
    public void mostrarVentas(List<Venta> ventas) {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            // Dibuja el encabezado de la tabla
            System.out.println("+-------+------------+------------+------------+");
            System.out.printf("| %-5s | %-10s | %-10s | %-10s |%n", "ID", "ID Cliente", "Fecha", "Total");
            System.out.println("+-------+------------+------------+------------+");

            // Muestra cada venta en una fila
            for (Venta venta : ventas) {
                System.out.printf("| %-5d | %-10d | %-10s | %-10.2f |%n",
                        venta.getId(), venta.getIdCliente(), venta.getFecha(), venta.getTotal());
            }

            // Cierra la tabla
            System.out.println("+-------+------------+------------+------------+");
        }
    }

    // Muestra un mensaje general al usuario
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}

