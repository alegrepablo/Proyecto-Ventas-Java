package DAO;

import Modelo.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ClienteDAO {
    private Connection connection; // Conexión a la base de datos

    // Constructor que recibe la conexión a la base de datos
    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para agregar un nuevo cliente
    public void agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, correo) VALUES (?, ?)"; // Consulta SQL para insertar un cliente
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, cliente.getNombre()); // Establece el nombre del cliente en la consulta
            statement.setString(2, cliente.getCorreo()); // Establece el correo del cliente en la consulta
            statement.executeUpdate(); // Ejecuta la inserción en la base de datos

            // Obtiene el ID generado automáticamente para el nuevo cliente
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                cliente.setId(generatedKeys.getInt(1)); // Asigna el ID generado al cliente
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja errores de SQL
        }
    }

    // Método para listar todos los clientes
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>(); // Lista para almacenar los clientes
        String sql = "SELECT * FROM clientes"; // Consulta SQL para obtener todos los clientes
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) { // Ejecuta la consulta y almacena el resultado
            while (resultSet.next()) {
                Cliente cliente = new Cliente(
                        resultSet.getInt("id"), // Obtiene el ID del cliente
                        resultSet.getString("nombre"), // Obtiene el nombre del cliente
                        resultSet.getString("correo")); // Obtiene el correo del cliente
                clientes.add(cliente); // Agrega el cliente a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja errores de SQL
        }
        return clientes; // Retorna la lista de clientes
    }

    // Método para actualizar un cliente existente
    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, correo = ? WHERE id = ?"; // Consulta SQL para actualizar un cliente
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNombre()); // Establece el nuevo nombre del cliente
            statement.setString(2, cliente.getCorreo()); // Establece el nuevo correo del cliente
            statement.setInt(3, cliente.getId()); // Establece el ID del cliente a actualizar
            statement.executeUpdate(); // Ejecuta la actualización en la base de datos
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja errores de SQL
        }
    }

    // Método para eliminar un cliente por su ID
    public void eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?"; // Consulta SQL para eliminar un cliente por ID
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id); // Establece el ID del cliente a eliminar
            statement.executeUpdate(); // Ejecuta la eliminación en la base de datos
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja errores de SQL
        }
    }

    // Método para verificar si existe un cliente por su ID
    public boolean existeCliente(int idCliente) {
        String sql = "SELECT COUNT(*) FROM clientes WHERE id = ?"; // Consulta SQL para contar clientes con un ID específico
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCliente); // Establece el ID del cliente a verificar
            ResultSet resultSet = statement.executeQuery(); // Ejecuta la consulta
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Devuelve true si el ID existe
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Maneja errores de SQL
        }
        return false; // Devuelve false si el ID no existe o si ocurre un error
    }
}

