package Modelo;

public class Venta {
    private int id;
    private int idCliente;
    private String fecha;
    private double total;

    // Constructor
    public Venta(int id, int idCliente, String fecha, double total) {
        this.id = id;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.total = total;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "Venta{id=" + id + ", idCliente=" + idCliente + ", fecha='" + fecha + "', total=" + total + '}';
    }
}
