package evidencia;


public class Producto {
	
    
//mqIniVenta
    
    public String getProductoId() {
		return productoId;
	}
	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}
	public String getProductoNombre() {
		return productoNombre;
	}
	public void setProductoNombre(String productoNombre) {
		this.productoNombre = productoNombre;
	}
	public int getProductoCantidad() {
		return productoCantidad;
	}
	public void setProductoCantidad(int productoCantidad) {
		this.productoCantidad = productoCantidad;
	}
	public String getProveedorId() {
		return proveedorId;
	}
	public void setProveedorId(String proveedorId) {
		this.proveedorId = proveedorId;
	}
	
	public Producto(String productoId, String productoNombre, int productoCantidad, String proveedorId) {
		super();
		this.productoId = productoId;
		this.productoNombre = productoNombre;
		this.productoCantidad = productoCantidad;
		this.proveedorId = proveedorId;
	}
	private String productoId;
    private String productoNombre;
    private int productoCantidad;
    private String proveedorId;

}
