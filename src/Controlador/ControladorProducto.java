package Controlador;

import DAO.CategoriaDAO;
import DAO.ProductoDAO;
import Modelo.Categoria;
import Modelo.Producto;
import Vista.VistaProductos;

import java.util.List;
import java.util.Scanner;

public class ControladorProducto {
    // Atributos principales: vista para interactuar, DAO de productos y DAO de categorías
    private VistaProductos vistaProductos;
    private ProductoDAO productoDAO;
    private CategoriaDAO categoriaDAO;

    // Constructor que inicializa la vista, el DAO de productos y el DAO de categorías
    public ControladorProducto(VistaProductos vistaProductos, ProductoDAO productoDAO, CategoriaDAO categoriaDAO) {
        this.vistaProductos = vistaProductos;
        this.productoDAO = productoDAO;
        this.categoriaDAO = categoriaDAO;
    }

    // Método principal que controla el flujo de las operaciones
    public void iniciar() {
        boolean salir = false;  // Bandera para salir del menú
        while (!salir) {    // Bucle que se ejecuta hasta que el usuario elija salir
            vistaProductos.mostrarMenu();   // Muestra el menú de opciones al usuario
            int opcion = new Scanner(System.in).nextInt();  // Captura la opción ingresada por el usuario
            switch (opcion) {
                case 1: // Agregar un producto
                    List<Categoria> categorias = categoriaDAO.listarCategorias();   // Lista las categorías disponibles
                    vistaProductos.mostrarCategorias(categorias);   // Muestra las categorías al usuario
                    Producto nuevoProducto = vistaProductos.obtenerDatosProducto(); // Solicita los datos del producto
                    productoDAO.agregarProducto(nuevoProducto); // Agrega el producto a través del DAO
                    vistaProductos.mostrarMensaje("Producto agregado correctamente.");  // Confirma al usuario
                    break;
                case 2: // Listar productos
                    List<Producto> productos = productoDAO.listarProductos();   // Obtiene la lista de productos desde el DAO
                    vistaProductos.mostrarProductos(productos); // Muestra la lista de productos a través de la vista
                    break;
                case 3: // Actualizar producto
                    int idActualizar = vistaProductos.obtenerIdProducto();  // Solicita el ID del producto a actualizar
                    Producto productoActualizar = vistaProductos.obtenerDatosProducto();   // Solicita los nuevos datos del producto
                    productoActualizar.setId(idActualizar); // Asocia el ID al producto
                    productoDAO.actualizarProducto(productoActualizar); // Actualiza el producto en el DAO
                    vistaProductos.mostrarProductoActualizado();    // Confirma al usuario
                    break;
                case 4: // Eliminar producto
                    int idEliminar = vistaProductos.obtenerIdProducto();    // Solicita el ID del producto a eliminar
                    productoDAO.eliminarProducto(idEliminar);   // Elimina el producto a través del DAO
                    vistaProductos.mostrarProductoEliminado();  // Confirma al usuario
                    break;
                case 5: // Salir del menú
                    salir = true;   // Cambia la bandera para salir del bucle
                    break;
                default:    // Opción inválida
                    vistaProductos.mostrarMensaje("Opción no válida."); // Informa al usuario
            }
        }
    }
}
