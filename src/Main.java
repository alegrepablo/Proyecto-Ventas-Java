import Controlador.ControladorCliente;
import Controlador.ControladorProducto;
import Controlador.ControladorVenta;
import DAO.CategoriaDAO;
import DAO.ClienteDAO;
import DAO.ProductoDAO;
import DAO.VentaDAO;
import Vista.VistaClientes;
import Vista.VistaProductos;
import Vista.VistaVentas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import io.github.cdimascio.dotenv.Dotenv;
import java.lang.Integer;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("DB_URL");
        String usuario = dotenv.get("DB_USUARIO");
        String contrasena = dotenv.get("DB_CONTRASENA");

        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena)) {
            System.out.println("Conexión exitosa a la base de datos MySQL.");

            // Instancia vista y DAO
            VistaProductos vistaProductos = new VistaProductos();
            ProductoDAO productoDAO = new ProductoDAO(conn);
            CategoriaDAO categoriaDAO = new CategoriaDAO(conn);
            ControladorProducto controladorProducto = new ControladorProducto(vistaProductos, productoDAO, categoriaDAO);

            VistaClientes vistaClientes = new VistaClientes();
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            ControladorCliente controladorCliente = new ControladorCliente(vistaClientes, clienteDAO);

            VistaVentas vistaVentas = new VistaVentas();
            VentaDAO ventaDAO = new VentaDAO(conn);
            ControladorVenta controladorVenta = new ControladorVenta(vistaVentas, ventaDAO, productoDAO, clienteDAO);

            // Menú principal
            boolean salir = false;
            while (!salir) {
                /*System.out.println("\nMenú Principal:");
                System.out.println("1. Realizar Venta");
                System.out.println("2. Mostrar Ventas");
                System.out.println("3. Gestionar Productos");
                System.out.println("4. Gestionar Clientes");
                System.out.println("5. Salir");*/
                System.out.println("\n+--------------------- Menú Principal ---------------------+");
                System.out.printf("| %-54s |\n", "1. Realizar Venta");
                System.out.printf("| %-54s |\n", "2. Mostrar Ventas");
                System.out.printf("| %-54s |\n", "3. Gestionar Productos");
                System.out.printf("| %-54s |\n", "4. Gestionar Clientes");
                System.out.printf("| %-54s |\n", "5. Salir");
                System.out.println("+---------------------------------------------------------+");
                System.out.print("Seleccione una opción: ");
                int opcion = new Scanner(System.in).nextInt();
                switch (opcion) {
                    case 1:
                        controladorVenta.realizarVenta();
                        break;
                    case 2:
                        controladorVenta.mostrarVentas();
                        break;
                    case 3:
                        controladorProducto.iniciar();
                        break;
                    case 4:
                        controladorCliente.iniciar();
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}
