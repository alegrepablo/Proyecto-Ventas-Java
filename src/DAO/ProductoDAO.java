package DAO;

import Modelo.Producto;
import java.util.InputMismatchException;
import com.mysql.cj.jdbc.DatabaseMetaData;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Scanner;

public class ProductoDAO {
    private Connection connection;

    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean existeCategoria(int idCategoria) {
        DatabaseMetaData MySQLConnection = null;
        try (Connection conn = MySQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM categorias WHERE id = ?")) {
            stmt.setInt(1, idCategoria);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true si existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna false si no existe o si ocurre un error
    }

    public boolean existeProducto(int idProducto) {
        String sql = "SELECT COUNT(*) FROM productos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idProducto);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Devuelve true si el ID existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Devuelve false si el ID no existe o si ocurre un error
    }


    public void agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, precio, stock, talle, id_categoria) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setInt(3, producto.getStock());
            statement.setString(4, producto.getTalle());
            statement.setInt(5, producto.getIdCategoria());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                producto.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double obtenerPrecio(int idProducto) throws SQLException {
        String sql = "SELECT precio FROM productos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProducto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("precio");
            }
        }
        return 0.0;
    }

    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
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
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    /*public void actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, stock = ?, talle = ?, id_categoria = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.setInt(3, producto.getStock());
            statement.setString(4, producto.getTalle());
            statement.setInt(5, producto.getIdCategoria());
            statement.setInt(6, producto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public void actualizarProducto(Producto producto) {
        Scanner scanner = new Scanner(System.in);

        // Validar ID de producto
        int idProducto = -1;
        while (true) {
            System.out.print("Ingrese el ID del producto a actualizar: ");
            try {
                idProducto = scanner.nextInt();
                if (idProducto > 0) {
                    break;  // ID válido
                } else {
                    System.out.println("El ID debe ser un número entero positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.next();  // Descarta la entrada no válida
            }
        }

        // Validar nombre del producto
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.next();  // Usar next() para evitar leer espacio en blanco al principio

        // Validar precio del producto
        double precio = -1;
        while (true) {
            System.out.print("Ingrese el precio del producto: ");
            try {
                precio = scanner.nextDouble();
                if (precio > 0) {
                    break;
                } else {
                    System.out.println("El precio debe ser un número positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
                scanner.next();  // Descarta la entrada no válida
            }
        }

        // Validar stock del producto
        int stock = -1;
        while (true) {
            System.out.print("Ingrese el stock del producto: ");
            try {
                stock = scanner.nextInt();
                if (stock >= 0) {
                    break;
                } else {
                    System.out.println("El stock debe ser un número entero no negativo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.next();  // Descarta la entrada no válida
            }
        }

        // Validar talle del producto
        System.out.print("Ingrese el talle del producto: ");
        String talle = scanner.next();

        // Validar ID de la categoría
        int idCategoria = -1;
        while (true) {
            System.out.print("Ingrese el ID de la categoría: ");
            try {
                idCategoria = scanner.nextInt();
                if (idCategoria > 0) {
                    break;
                } else {
                    System.out.println("El ID de la categoría debe ser un número entero positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.next();  // Descarta la entrada no válida
            }
        }

        // Asignamos los valores validados al objeto Producto
        producto.setId(idProducto);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setTalle(talle);
        producto.setIdCategoria(idCategoria);

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


    /*public void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public void eliminarProducto(int idEliminar) {
        Scanner scanner = new Scanner(System.in);

        // Validar ID de producto
        int idProducto = -1;
        while (true) {
            System.out.print("Ingrese el ID del producto a eliminar: ");
            try {
                idProducto = scanner.nextInt();
                if (idProducto > 0) {
                    break;  // ID válido
                } else {
                    System.out.println("El ID debe ser un número entero positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                scanner.next();  // Descarta la entrada no válida
            }
        }

        // Eliminar el producto de la base de datos
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


    public void actualizarStock(int idProducto, int nuevoStock) throws SQLException {
        String sql = "UPDATE productos SET stock = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, nuevoStock);
            stmt.setInt(2, idProducto);
            stmt.executeUpdate();
        }
    }

    public int obtenerStock(int idProducto) throws SQLException {
        String sql = "SELECT stock FROM productos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProducto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("stock");
            } else {
                throw new SQLException("Producto no encontrado.");
            }
        }
    }


}
