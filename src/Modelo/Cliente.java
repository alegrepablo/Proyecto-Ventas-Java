package Modelo;

// Clase que representa a un cliente del sistema de ventas de productos
public class Cliente {
    // Atributos que describen las características básicas de un cliente
    private int id;         // Identificador único del cliente en la base de datos
    private String nombre;  // Nombre del cliente
    private String correo;  // Correo electrónico del cliente

    // Constructor para crear un nuevo cliente con sus datos básicos
    public Cliente(int id, String nombre, String correo) {
        this.id = id;           // Asigna el ID único del cliente
        this.nombre = nombre;   // Establece el nombre completo del cliente
        this.correo = correo;   // Establece el correo electrónico del cliente
    }

    // Métodos para obtener y modificar los datos del cliente
    public int getId() { return id; }           // Obtiene el ID del cliente
    public void setId(int id) { this.id = id; } // Modifica el ID del cliente

    public String getNombre() { return nombre; }                    // Obtiene el nombre del cliente
    public void setNombre(String nombre) { this.nombre = nombre; }  // Modifica el nombre del cliente

    public String getCorreo() { return correo; }                    // Obtiene el correo del cliente
    public void setCorreo(String correo) { this.correo = correo; }  // Modifica el correo del cliente

    // Método que representa los datos del cliente como una cadena de texto para fácil visualización
    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nombre='" + nombre + "', correo='" + correo + "'}";
    }

}
