package lumina.model;

public final class DetallePedido {
	
	private final Integer cantidad;
	private final Producto producto;
	
	
	
	
	public DetallePedido(Integer cantidad, Producto producto) {
	
		this.cantidad = new Integer (cantidad);
		this.producto = producto; // Producto ya es inmutable, no es necesario copiar
	}




	public final Integer getCantidad() {
		return cantidad.intValue();
	}

	public final Producto getProducto() {
		return producto;
	}
	
	
	
	
	

}
