package Modelo;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private String talle;
    private int idCategoria;

    // Constructor
    public Producto(int id, String nombre, double precio, int stock, String talle, int idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.talle = talle;
        this.idCategoria = idCategoria;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getTalle() { return talle; }
    public void setTalle(String talle) { this.talle = talle; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    @Override
    public String toString() {
        return "Modelo.Producto{id=" + id + ", nombre='" + nombre + "', precio=" + precio +
                ", stock=" + stock + ", talle='" + talle + "', idCategoria=" + idCategoria + '}';
    }
}
