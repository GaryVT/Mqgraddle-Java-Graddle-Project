package evidencia;

//productos para lista de productos
//historial, distribuidor, proA, proB, appVentas, 
	

public class Pedido {
    public Pedido(String pedidoId, String descripcion, int cantidad) {
		super();
		this.pedidoId = pedidoId;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}
	public String getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(String pedidoId) {
		this.pedidoId = pedidoId;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	private String pedidoId;
    private String descripcion;
    private int cantidad;

}
