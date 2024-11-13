package DAO;

import Modelo.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Conexión a la base de datos
public class CategoriaDAO {
    private  Connection conn;

    // Constructor que recibe la conexión a la base de datos
    public CategoriaDAO(Connection conexion) {
        this.conn = conexion;
    }

    // Método para listar todas las categorías
    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>(); // Lista para almacenar las categorías
        String sql = "SELECT * FROM categorias";        // Consulta SQL para obtener todas las categorías

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {   // Ejecuta la consulta y almacena el resultado

            // Itera sobre los resultados de la consulta
            while (rs.next()) {
                int id = rs.getInt("id");               // Obtiene el ID de la categoría
                String nombre = rs.getString("nombre"); // Obtiene el nombre de la categoría
                Categoria categoria = new Categoria(id, nombre);   // Crea un objeto Categoria
                categorias.add(categoria);                         // Agrega la categoría a la lista
            }
        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());    // Maneja errores de SQL
        }

        return categorias;  // Retorna la lista de categorías
    }

    // Método para buscar una categoría por su ID
    public Categoria obtenerCategoriaPorId(int idCategoria) {
        Categoria categoria = null; // Inicializa la categoría como null
        String sql = "SELECT * FROM categorias WHERE id = ?";   // Consulta SQL para buscar por ID

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCategoria);   // Establece el parámetro ID en la consulta
            ResultSet rs = pstmt.executeQuery();         // Ejecuta la consulta y obtiene el resultado

            if (rs.next()) {
                String nombre = rs.getString("nombre");  // Obtiene el nombre de la categoría
                categoria = new Categoria(idCategoria, nombre);     // Crea un objeto Categoria
            } else {
                System.out.println("No se encontró una categoría con ID: " + idCategoria);  // Mensaje si no existe
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener categoría: " + e.getMessage());    // Maneja errores de SQL
        }

        return categoria;   // Retorna la categoría encontrada o null si no existe
    }
}

