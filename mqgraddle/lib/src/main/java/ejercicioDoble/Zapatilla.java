package ejercicioDoble;

public class Zapatilla {
    private String tipo;
    private String nombre;

    public Zapatilla(String tipo, String nombre) {
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
    /* un tienda de zapatillas que recibe multiples mensajes (a traves del metodo publish/suscribe)
     *  y que luego de recibir todas las zapatillas, estas se envian a una cola destinada para cada 
     *  tipo de zapatilla (de correr o de vestir). Para que lo entiendas, primero recibe mensaje con el 
     *  metodo publish/suscribe y luego los clasifico segun el tipo de zapatiila
     *  
     * Receptores de zapatillas de correr y vestir
     * JmsTiendaZapatillas
     * JmsPreductorTopicArtemis
     */
}