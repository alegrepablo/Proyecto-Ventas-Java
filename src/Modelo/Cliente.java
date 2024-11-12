package Modelo;

public class Cliente {
    private int id;
    private String nombre;
    private String correo;

    // Constructor
    public Cliente(int id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nombre='" + nombre + "', correo='" + correo + "'}";
    }

}
