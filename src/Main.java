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

public class Main {
    public static void main(String[] args) {
        // Configuración de la conexión a la base de datos
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("DB_URL");
        String usuario = dotenv.get("DB_USUARIO");
        String contrasena = dotenv.get("DB_CONTRASENA");

        try (Connection conn = DriverManager.getConnection(url, usuario, contrasena)) {
            System.out.println("Conexión exitosa a la base de datos MySQL.");

            // Inicialización de componentes para gestión de productos
            VistaProductos vistaProductos = new VistaProductos();
            ProductoDAO productoDAO = new ProductoDAO(conn);
            CategoriaDAO categoriaDAO = new CategoriaDAO(conn);
            ControladorProducto controladorProducto = new ControladorProducto(vistaProductos, productoDAO, categoriaDAO);

            // Inicialización de componentes para gestión de clientes
            VistaClientes vistaClientes = new VistaClientes();
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            ControladorCliente controladorCliente = new ControladorCliente(vistaClientes, clienteDAO);

            // Inicialización de componentes para gestión de ventas
            VistaVentas vistaVentas = new VistaVentas();
            VentaDAO ventaDAO = new VentaDAO(conn);
            ControladorVenta controladorVenta = new ControladorVenta(vistaVentas, ventaDAO, productoDAO, clienteDAO);

            // Bucle principal del menú
            boolean salir = false;
            while (!salir) {
                // Diseño del menú principal con bordes
                System.out.println("\n+--------------------- Menú Principal ---------------------+");
                System.out.printf("| %-54s |\n", "1. Realizar Venta");
                System.out.printf("| %-54s |\n", "2. Mostrar Ventas");
                System.out.printf("| %-54s |\n", "3. Gestionar Productos");
                System.out.printf("| %-54s |\n", "4. Gestionar Clientes");
                System.out.printf("| %-54s |\n", "5. Salir");
                System.out.println("+---------------------------------------------------------+");
                System.out.print("Seleccione una opción: ");
                // Lectura de la opción del usuario
                int opcion = new Scanner(System.in).nextInt();
                // Manejo de las diferentes opciones del menú
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
            // Manejo de errores de conexión a la base de datos
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }
}
