package Modelo;

// Clase que representa los detalles específicos de una venta
public class DetalleVenta {
    // Atributos que describen cada línea de una venta
    private int id;         // Identificador único del detalle de venta en la base de datos
    private int idVenta;    // ID de la venta a la que pertenece el detalle
    private int idProducto; // ID del producto vendido en esta linea
    private int cantidad;   // Cantidad de productos vendidos
    private double subtotal;// Precio total de esta línea (cantidad * precio unitario)

    // Constructor para crear un nuevo detalle de venta
    public DetalleVenta(int id, int idVenta, int idProducto, int cantidad, double subtotal) {
        this.id = id;                   // Asigna el ID único del detalle
        this.idVenta = idVenta;         // Establece el ID de la venta a la que pertenece
        this.idProducto = idProducto;   // Asigna el ID del producto vendido
        this.cantidad = cantidad;       // Establece la cantidad vendida
        this.subtotal = subtotal;       // Define el subtotal de esta línea
    }

    // Métodos para acceder y modificar los datos del detalle de venta
    public int getId() { return id; }           // Obtiene el ID del detalle de venta
    public void setId(int id) { this.id = id; } // Modifica el ID del detalle de venta

    public int getIdVenta() { return idVenta; }                     // Obtiene el ID de la venta
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; } // Modifica el ID de la venta

    public int getIdProducto() { return idProducto; }                           // Obtiene el ID del producto
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; } // Modifica el ID del producto

    public int getCantidad() { return cantidad; }                        // Obtiene la cantidad vendida
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }  // Modifica la cantidad vendida

    public double getSubtotal() { return subtotal; }                        // Obtiene el subtotal de esta línea
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }  // Modifica el subtotal de esta línea

    // Método para convertir el detalle de venta como una cadena de texto para fácil visualización
    @Override
    public String toString() {
        return "DetalleVenta{id=" + id + ", idVenta=" + idVenta + ", idProducto=" + idProducto +
                ", cantidad=" + cantidad + ", subtotal=" + subtotal + '}';
    }
}
