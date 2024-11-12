/*package Vista;

public class VistaVentas {
    public void mostrarMenu() {
        System.out.println("Gestión de Ventas:");
        System.out.println("1. Realizar una Venta");
        System.out.println("2. Mostrar Ventas");
    }

    public void mostrarVentas(String ventas) {
        System.out.println("Ventas realizadas:");
        System.out.println(ventas);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}*/

package Vista;

import Modelo.Venta;
import java.util.List;

public class VistaVentas {
    public void mostrarVentas(List<Venta> ventas) {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            System.out.println("+-------+------------+------------+------------+");
            System.out.printf("| %-5s | %-10s | %-10s | %-10s |%n", "ID", "ID Cliente", "Fecha", "Total");
            System.out.println("+-------+------------+------------+------------+");

            for (Venta venta : ventas) {
                System.out.printf("| %-5d | %-10d | %-10s | %-10.2f |%n",
                        venta.getId(), venta.getIdCliente(), venta.getFecha(), venta.getTotal());
            }

            System.out.println("+-------+------------+------------+------------+");
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}

