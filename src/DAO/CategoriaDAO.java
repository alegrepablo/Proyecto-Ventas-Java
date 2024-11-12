package DAO;

import Modelo.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private  Connection conn;

    public CategoriaDAO(Connection conexion) {
        this.conn = conexion;
    }

    // Método para listar todas las categorías
    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Categoria categoria = new Categoria(id, nombre);
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }

        return categorias;
    }

    // Método para buscar una categoría por su ID
    public Categoria obtenerCategoriaPorId(int idCategoria) {
        Categoria categoria = null;
        String sql = "SELECT * FROM categorias WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCategoria);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                categoria = new Categoria(idCategoria, nombre);
            } else {
                System.out.println("No se encontró una categoría con ID: " + idCategoria);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener categoría: " + e.getMessage());
        }

        return categoria;
    }
}

