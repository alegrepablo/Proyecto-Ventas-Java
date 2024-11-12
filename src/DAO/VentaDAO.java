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

    public VentaDAO(Connection connection) {
        this.connection = connection;
    }

    public void realizarVenta(int id_cliente, Date fecha, double total) throws SQLException {
        String sql = "INSERT INTO ventas (id_cliente, fecha, total) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id_cliente);
            stmt.setDate(2, fecha);
            stmt.setDouble(3, total);
            stmt.executeUpdate();
        }
    }


    /*public String listarVentas() throws SQLException {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT * FROM ventas";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id"))
                        .append(", Cliente ID: ").append(rs.getInt("id_cliente"))
                        .append(", Fecha: ").append(rs.getDate("fecha")) // Usa getDate para obtener la fecha
                        .append(", Precio Total: ").append(rs.getDouble("total"))
                        .append("\n");
            }
        }
        return sb.toString();
    }*/

    public List<Venta> listarVentas() throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM ventas";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int idCliente = rs.getInt("id_cliente");
                Date fecha = rs.getDate("fecha");
                double total = rs.getDouble("total");

                // Crea una nueva instancia de Venta y añade a la lista
                ventas.add(new Venta(id, idCliente, fecha.toString(), total));
            }
        }
        return ventas;
    }

}
