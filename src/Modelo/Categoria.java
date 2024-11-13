package Modelo;

// Clase que representa una categoría de productos
public class Categoria {
    // Atributos de la clase
    private int id;         // Identificador único de la categoría
    private String nombre;  // Nombre de la categoría (ej. "Electrónica", "Ropa")

    // Constructor que inicializa una nueva categoría
    public Categoria(int id, String nombre) {
        this.id = id;           // Asigna el ID único de la categoría
        this.nombre = nombre;   // Establece el nombre de la categoría
    }

    // Métodos "getter" y "setter" para acceder y modificar los atributos de la categoría
    public int getId() { return id; }           // Obtiene el ID de la categoría
    public void setId(int id) { this.id = id; } // Modifica el ID de la categoría

    public String getNombre() { return nombre; }                    // Obtiene el nombre de la categoría
    public void setNombre(String nombre) { this.nombre = nombre; }  // Modifica el nombre de la categoría

    // Método que representa la categoría como una cadena de texto para fácil visualización
    @Override
    public String toString() {
        return "Categoria{id=" + id + ", nombre='" + nombre + "'}";
    }
}
