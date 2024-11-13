package DAO;

import Modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Scanner;

public class ProductoDAO {
    private Connection connection; // Conexión a la base de datos

    // Constructor que recibe la conexión a la base de datos
    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para verificar si existe una categoría en la base de datos
    public boolean existeCategoria(int idCategoria) {
        String sql = "SELECT COUNT(*) FROM categorias WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCategoria); // Establece el ID de la categoría a verificar
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true si existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna false si no existe o si ocurre un error
    }

    // Método para verificar si existe un producto en la base de datos
    public boolean existeProducto(int idProducto) {
        String sql = "SELECT COUNT(*) FROM productos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idProducto); // Establece el ID del producto a verificar
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Devuelve true si el ID existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Devuelve false si el ID no existe o si ocurre un error
    }

    // Método para agregar un nuevo producto
    public void agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, precio, stock, talle, id_categoria) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setInt(3, producto.getStock());
            statement.setString(4, producto.getTalle());
            statement.setInt(5, producto.getIdCategoria());
            statement.executeUpdate();

            // Obtener el ID generado automáticamente para el producto
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                producto.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener el precio de un producto específico
    public double obtenerPrecio(int idProducto) throws SQLException {
        String sql = "SELECT precio FROM productos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProducto); // Establece el ID del producto para obtener su precio
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("precio"); // Retorna el precio del producto
            }
        }
        return 0.0; // Retorna 0.0 si no se encuentra el producto
    }

    // Método para listar todos los productos
    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>(); // Lista para almacenar los productos
        String sql = "SELECT * FROM productos";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Producto producto = new Producto(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getDouble("precio"),
                        resultSet.getInt("stock"),
                        resultSet.getString("talle"),
                        resultSet.getInt("id_categoria"));
                productos.add(producto); // Agrega el producto a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos; // Retorna la lista de productos
    }

    // Método para actualizar un producto
    public void actualizarProducto(Producto producto) {
        Scanner scanner = new Scanner(System.in);

        // Validación de ID del producto
        System.out.print("Ingrese el ID del producto a actualizar: ");
        int idProducto = scanner.nextInt();
        producto.setId(idProducto); // Asigna el ID ingresado al producto

        // Validación y asignación de valores para el producto
        System.out.print("Ingrese el nombre del producto: ");
        producto.setNombre(scanner.next());

        System.out.print("Ingrese el precio del producto: ");
        producto.setPrecio(scanner.nextDouble());

        System.out.print("Ingrese el stock del producto: ");
        producto.setStock(scanner.nextInt());

        System.out.print("Ingrese el talle del producto: ");
        producto.setTalle(scanner.next());

        System.out.print("Ingrese el ID de la categoría: ");
        producto.setIdCategoria(scanner.nextInt());

        // Actualizar el producto en la base de datos
        String sql = "UPDATE productos SET nombre = ?, precio = ?, stock = ?, talle = ?, id_categoria = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setInt(3, producto.getStock());
            statement.setString(4, producto.getTalle());
            statement.setInt(5, producto.getIdCategoria());
            statement.setInt(6, producto.getId());
            statement.executeUpdate();
            System.out.println("Producto actualizado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    // Método para eliminar un producto
    public void eliminarProducto(int idEliminar) {
        Scanner scanner = new Scanner(System.in);

        // Validación de ID del producto
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int idProducto = scanner.nextInt();

        // Eliminar el producto en la base de datos
        String sql = "DELETE FROM productos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idProducto);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No se encontró un producto con el ID especificado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
    }

    // Método para actualizar el stock de un producto específico
    public void actualizarStock(int idProducto, int nuevoStock) throws SQLException {
        String sql = "UPDATE productos SET stock = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, nuevoStock);
            stmt.setInt(2, idProducto);
            stmt.executeUpdate(); // Ejecuta la actualización del stock
        }
    }

    // Método para obtener el stock de un producto específico
    public int obtenerStock(int idProducto) throws SQLException {
        String sql = "SELECT stock FROM productos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProducto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("stock"); // Retorna el stock del producto
            } else {
                throw new SQLException("Producto no encontrado.");
            }
        }
    }
}
