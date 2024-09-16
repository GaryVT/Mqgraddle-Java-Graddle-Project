package evidencia;

import java.util.List;

//lista de producto mqINIventa con lista de productos

public class Venta {
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	public Venta(int id, List<Producto> productos) {
		super();
		this.id = id;
		this.productos = productos;
	}
	private int id;
    private List<Producto> productos;

}
