package ejercicio;

public class Producto {
    private String tipo;  // "ropa" o "electrodoméstico"
    private String nombre;

    public Producto() {}

    public Producto(String tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "tipo='" + tipo + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
    
    /*
     * JmsProductor, crea conexion al broker y define proveedores
     * ProveedorA se suscribe a la cola proveedorAQueue, espera mensajes con
     * ProveedorB ...
     */
}