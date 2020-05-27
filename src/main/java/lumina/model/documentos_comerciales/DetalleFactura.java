package lumina.model.documentos_comerciales;

import lumina.model.Money;
import lumina.model.Producto;

public final class DetalleFactura {
	
	private final Producto producto;
	private final Money precio_unitario;
	private final Float porcentaje_iva;
	private final Integer cantidad;
	private final Money precio_venta;	// precio neto + iva
	private final Money precio_neto; 	// precio*cantidad
	private final Money monto_iva;		// % iva segun categoría
	
	
	
	public DetalleFactura(Producto producto, Money precio_unitario, Float porcentaje_iva, Integer cantidad,
			Money precio_venta, Money precio_neto, Money monto_iva) {
		super();
		this.producto = producto;
		this.precio_unitario = precio_unitario;
		this.porcentaje_iva = porcentaje_iva;
		this.cantidad = cantidad;
		this.precio_venta = precio_venta;
		this.precio_neto = precio_neto;
		this.monto_iva = monto_iva;
	}
	
	
	public final Producto getProducto() {
		return producto;
	}
	public final Money getPrecio_unitario() {
		return precio_unitario;
	}
	public final Float getPorcentaje_iva() {
		return porcentaje_iva;
	}
	public final Integer getCantidad() {
		return cantidad;
	}
	public final Money getPrecio_venta() {
		return precio_venta;
	}
	public final Money getPrecio_neto() {
		return precio_neto;
	}
	public final Money getMonto_iva() {
		return monto_iva;
	}
	
	
	
	
	

}
