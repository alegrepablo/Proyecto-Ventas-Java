package Controlador;

import DAO.CategoriaDAO;
import DAO.ProductoDAO;
import Modelo.Categoria;
import Modelo.Producto;
import Vista.VistaProductos;

import java.util.List;
import java.util.Scanner;

public class ControladorProducto {
    private VistaProductos vistaProductos;
    private ProductoDAO productoDAO;
    private CategoriaDAO categoriaDAO;

    public ControladorProducto(VistaProductos vistaProductos, ProductoDAO productoDAO, CategoriaDAO categoriaDAO) {
        this.vistaProductos = vistaProductos;
        this.productoDAO = productoDAO;
        this.categoriaDAO = categoriaDAO;
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            vistaProductos.mostrarMenu();
            int opcion = new Scanner(System.in).nextInt();
            switch (opcion) {
                case 1:
                    List<Categoria> categorias = categoriaDAO.listarCategorias();
                    vistaProductos.mostrarCategorias(categorias);
                    Producto nuevoProducto = vistaProductos.obtenerDatosProducto();
                    productoDAO.agregarProducto(nuevoProducto);
                    vistaProductos.mostrarMensaje("Producto agregado correctamente.");
                    break;
                case 2:
                    List<Producto> productos = productoDAO.listarProductos();
                    vistaProductos.mostrarProductos(productos);
                    break;
                case 3:
                    int idActualizar = vistaProductos.obtenerIdProducto();
                    Producto productoActualizar = vistaProductos.obtenerDatosProducto();
                    productoActualizar.setId(idActualizar);
                    productoDAO.actualizarProducto(productoActualizar);
                    vistaProductos.mostrarProductoActualizado();
                    break;
                case 4:
                    int idEliminar = vistaProductos.obtenerIdProducto();
                    productoDAO.eliminarProducto(idEliminar);
                    vistaProductos.mostrarProductoEliminado();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    vistaProductos.mostrarMensaje("Opción no válida.");
            }
        }
    }
}
