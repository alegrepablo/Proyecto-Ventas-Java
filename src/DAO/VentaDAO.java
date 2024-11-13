package DAO;

import Modelo.Venta;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    private Connection connection;

    // Constructor que recibe la conexión a la base de datos
    public VentaDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para registrar una venta en la base de datos
    public void realizarVenta(int id_cliente, Date fecha, double total) throws SQLException {
        String sql = "INSERT INTO ventas (id_cliente, fecha, total) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // Configura el ID del cliente, la fecha y el total de la venta
            stmt.setInt(1, id_cliente);
            stmt.setDate(2, fecha);
            stmt.setDouble(3, total);
            // Ejecuta la inserción en la base de datos
            stmt.executeUpdate();
        }
    }

    // Método para obtener la lista de todas las ventas
    public List<Venta> listarVentas() throws SQLException {
        List<Venta> ventas = new ArrayList<>(); // Lista para almacenar las ventas
        String sql = "SELECT * FROM ventas"; // Consulta SQL para obtener todas las ventas
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { // Ejecuta la consulta y obtiene el resultado
            while (rs.next()) {
                int id = rs.getInt("id"); // Obtiene el ID de la venta
                int idCliente = rs.getInt("id_cliente"); // Obtiene el ID del cliente
                Date fecha = rs.getDate("fecha"); // Obtiene la fecha de la venta
                double total = rs.getDouble("total"); // Obtiene el total de la venta

                // Crea una nueva instancia de Venta y añade a la lista
                ventas.add(new Venta(id, idCliente, fecha.toString(), total));
            }
        }
        return ventas; // Retorna la lista de ventas
    }

}
