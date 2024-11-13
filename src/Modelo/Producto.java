package Modelo;

// Clase que representa un producto en el sistema de ventas de productos
public class Producto {
    // Atributos que describen las características de un producto
    private int id;         // Identificador único del producto en la base de datos
    private String nombre;  // Nombre del producto
    private double precio;  // Precio unitario del producto
    private int stock;      // Cantidad disponible en inventario
    private String talle;   // Talla del producto
    private int idCategoria;// ID de la categoría a la que pertenece el producto

    // Constructor para crear un nuevo producto con todos sus atributos
    public Producto(int id, String nombre, double precio, int stock, String talle, int idCategoria) {
        this.id = id;                   // Asigna el ID único del producto
        this.nombre = nombre;           // Establece el nombre del producto
        this.precio = precio;           // Define el precio del producto
        this.stock = stock;             // Define el stock disponible
        this.talle = talle;             // Establece la talla o tamaño del producto
        this.idCategoria = idCategoria; // Asigna el ID de la categoría del producto
    }

    // Métodos para acceder y modificar los datos del producto
    public int getId() { return id; }           // Obtiene el ID del producto
    public void setId(int id) { this.id = id; } // Modifica el ID del producto

    public String getNombre() { return nombre; }                    // Obtiene el nombre del producto
    public void setNombre(String nombre) { this.nombre = nombre; }  // Modifica el nombre del producto

    public double getPrecio() { return precio; }                    // Obtiene el precio del producto
    public void setPrecio(double precio) { this.precio = precio; }  // Modifica el precio del producto

    public int getStock() { return stock; }                 // Obtiene el stock disponible
    public void setStock(int stock) { this.stock = stock; } // Modifica el stock disponible

    public String getTalle() { return talle; }                  // Obtiene la talla o tamaño del producto
    public void setTalle(String talle) { this.talle = talle; }  // Modifica la talla del producto

    public int getIdCategoria() { return idCategoria; }                             // Obtiene el ID de la categoría
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; } // Modifica el ID de la categoría

    // Método para convertir el producto como una cadena de texto pata facil visualizacion
    @Override
    public String toString() {
        return "Modelo.Producto{id=" + id + ", nombre='" + nombre + "', precio=" + precio +
                ", stock=" + stock + ", talle='" + talle + "', idCategoria=" + idCategoria + '}';
    }
}
