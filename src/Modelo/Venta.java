package Modelo;

// Clase que representa una venta realizada en el sistema de ventas de productos
public class Venta {
    // Atributos que describen una venta
    private int id;         // Identificador único de la venta en la base de datos
    private int idCliente;  // ID del cliente que realizó la compra
    private String fecha;   // Fecha en que se realizó la venta (formato String)
    private double total;   // Monto total de la venta

    // Constructor para crear una nueva venta con sus datos básicos
    public Venta(int id, int idCliente, String fecha, double total) {
        this.id = id;               // Asigna el ID único de la venta
        this.idCliente = idCliente; // Asigna el ID del cliente asociado
        this.fecha = fecha;         // Establece la fecha de la venta
        this.total = total;         // Establece el monto total de la venta
    }

    // Métodos para acceder y modificar los datos de la venta
    public int getId() { return id; }           // Obtiene el ID de la venta
    public void setId(int id) { this.id = id; } // Modifica el ID de la venta

    public int getIdCliente() { return idCliente; }                         // Obtiene el ID del cliente
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; } // Modifica el ID del cliente

    public String getFecha() { return fecha; }                  // Obtiene la fecha de la venta
    public void setFecha(String fecha) { this.fecha = fecha; }  // Modifica la fecha de la venta

    public double getTotal() { return total; }                  // Obtiene el monto total
    public void setTotal(double total) { this.total = total; }  // Modifica el monto total

    // Método para convertir la venta como una cadena de texto para facil visualizacion
    @Override
    public String toString() {
        return "Venta{id=" + id + ", idCliente=" + idCliente + ", fecha='" + fecha + "', total=" + total + '}';
    }
}
