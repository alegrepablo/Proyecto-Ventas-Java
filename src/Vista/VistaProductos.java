/*package Vista;

import Modelo.Producto;
import java.util.List;
import java.util.Scanner;

public class VistaProductos {
    public void mostrarMenu() {
        System.out.println("Menú Productos:");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Listar Productos");
        System.out.println("3. Actualizar Producto");
        System.out.println("4. Eliminar Producto");
        System.out.println("5. Volver");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarProductos(List<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles.");
        } else {
            System.out.println("Productos:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }

    public Producto obtenerDatosProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Stock del producto: ");
        int stock = scanner.nextInt();
        System.out.print("Talle del producto: ");
        String talle = scanner.next();
        System.out.print("ID de la categoría: ");
        int idCategoria = scanner.nextInt();
        return new Producto(0, nombre, precio, stock, talle, idCategoria); // id se generará automáticamente
    }

    public int obtenerIdProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto: ");
        return scanner.nextInt();
    }

    public void mostrarProductoActualizado() {
        System.out.println("Producto actualizado correctamente.");
    }

    public void mostrarProductoEliminado() {
        System.out.println("Producto eliminado correctamente.");
    }
} */

package Vista;

import DAO.ProductoDAO;
import Modelo.Categoria;
import Modelo.Producto;
import java.util.List;
import java.util.Scanner;

public class VistaProductos {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("Menú Productos:");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Listar Productos");
        System.out.println("3. Actualizar Producto");
        System.out.println("4. Eliminar Producto");
        System.out.println("5. Volver");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /*public void mostrarProductos(List<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles.");
        } else {
            System.out.println("Productos:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }
        }
    }*/
    public void mostrarProductos(List<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles.");
        } else {
            // Determinar el ancho de las columnas
            int idWidth = "ID".length();
            int nombreWidth = "Nombre".length();
            int precioWidth = "Precio".length();
            int stockWidth = "Stock".length();
            int talleWidth = "Talle".length();
            int idCategoriaWidth = "ID Categoría".length();

            // Calcular el ancho de cada columna en función del contenido
            for (Producto producto : productos) {
                idWidth = Math.max(idWidth, String.valueOf(producto.getId()).length());
                nombreWidth = Math.max(nombreWidth, producto.getNombre().length());
                precioWidth = Math.max(precioWidth, String.format("%.2f", producto.getPrecio()).length());
                stockWidth = Math.max(stockWidth, String.valueOf(producto.getStock()).length());
                talleWidth = Math.max(talleWidth, producto.getTalle().length());
                idCategoriaWidth = Math.max(idCategoriaWidth, String.valueOf(producto.getIdCategoria()).length());
            }

            // Crear el formato de las columnas
            String format = "| %-" + idWidth + "s | %-" + nombreWidth + "s | %" + precioWidth + ".2f | %-" + stockWidth + "d | %-" + talleWidth + "s | %-" + idCategoriaWidth + "d |\n";
            String line = "+-" + "-".repeat(idWidth) + "-+-" + "-".repeat(nombreWidth) + "-+-" + "-".repeat(precioWidth) + "-+-" + "-".repeat(stockWidth) + "-+-" + "-".repeat(talleWidth) + "-+-" + "-".repeat(idCategoriaWidth) + "-+";

            // Imprimir cabecera
            System.out.println(line);
            System.out.printf("| %-" + idWidth + "s | %-" + nombreWidth + "s | %-" + precioWidth + "s | %-" + stockWidth + "s | %-" + talleWidth + "s | %-" + idCategoriaWidth + "s |\n", "ID", "Nombre", "Precio", "Stock", "Talle", "ID Categoría");
            System.out.println(line);

            // Imprimir los datos de los productos
            for (Producto producto : productos) {
                System.out.printf(format, producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock(), producto.getTalle(), producto.getIdCategoria());
            }

            // Imprimir línea de cierre
            System.out.println(line);
        }
    }



    public Producto obtenerDatosProducto() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        double precio = obtenerPrecioValido();
        int stock = obtenerStockValido();
        String talle = obtenerTalleValido();

        int idCategoria =obtenerIdCategoriaValido();
        return new Producto(0, nombre, precio, stock, talle, idCategoria);
    }

    private double obtenerPrecioValido() {
        double precio;
        while (true) {
            System.out.print("Precio del producto: ");
            if (scanner.hasNextDouble()) {
                precio = scanner.nextDouble();
                if (precio >= 0) {
                    break;
                } else {
                    System.out.println("El precio debe ser un número positivo.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número para el precio.");
                scanner.next(); // Descarta la entrada no válida
            }
        }
        scanner.nextLine(); // Limpia el buffer
        return precio;
    }

    private int obtenerStockValido() {
        int stock;
        while (true) {
            System.out.print("Stock del producto: ");
            if (scanner.hasNextInt()) {
                stock = scanner.nextInt();
                if (stock >= 0) {
                    break;
                } else {
                    System.out.println("El stock debe ser un número entero positivo.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero para el stock.");
                scanner.next(); // Descarta la entrada no válida
            }
        }
        scanner.nextLine(); // Limpia el buffer
        return stock;
    }

    private String obtenerTalleValido() {
        System.out.print("Talle del producto: ");
        return scanner.next();
    }

    private int obtenerIdCategoriaValido() {
        int idCategoria;
        while (true) {
            System.out.print("ID de la categoría: ");
            if (scanner.hasNextInt()) {
                idCategoria = scanner.nextInt();
                if (idCategoria > 0) {
                    break;
                } else {
                    System.out.println("El ID de la categoría debe ser un número entero positivo.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero para el ID de la categoría.");
                scanner.next(); // Descarta la entrada no válida
            }
        }
        scanner.nextLine(); // Limpia el buffer
        return idCategoria;
    }

    public void mostrarCategorias(List<Categoria> categorias) {
        for (Categoria categoria : categorias) {
            System.out.println("ID: " + categoria.getId() + ", Nombre: " + categoria.getNombre());
        }
    }

    public int obtenerIdProducto() {
        int idProducto;
        while (true) {
            System.out.print("Ingrese el ID del producto: ");
            if (scanner.hasNextInt()) {
                idProducto = scanner.nextInt();
                if (idProducto > 0) {
                    break;
                } else {
                    System.out.println("El ID del producto debe ser un número entero positivo.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero para el ID del producto.");
                scanner.next(); // Descarta la entrada no válida
            }
        }
        scanner.nextLine(); // Limpia el buffer
        return idProducto;
    }

    public void mostrarProductoActualizado() {
        System.out.println("Producto actualizado correctamente.");
    }

    public void mostrarProductoEliminado() {
        System.out.println("Producto eliminado correctamente.");
    }

    public int obtenerOpcionMenu() {
        int opcion;
        while (true) {
            System.out.print("Seleccione una opción: ");
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                if (opcion >= 1 && opcion <= 5) { // Ajusta el rango según las opciones válidas de tu menú
                    break;
                } else {
                    System.out.println("Por favor, ingrese un número entre 1 y 5.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next(); // Descarta la entrada no válida
            }
        }
        scanner.nextLine(); // Limpia el buffer
        return opcion;
    }
}

