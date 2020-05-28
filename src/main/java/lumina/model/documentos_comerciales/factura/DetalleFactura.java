package lumina.model.documentos_comerciales.factura;

import java.math.BigDecimal;

import lumina.model.Money;
import lumina.model.producto.Producto;


/**
 * TODO documentar
 * @author emilio
 *
 */
public final class DetalleFactura {
	
	private final Producto producto;
	private final Money precio_unitario;
	private final Float porcentaje_iva;
	private final Integer cantidad;
	private final Money precio_venta;	// precio neto + iva
	private final Money precio_neto; 	// precio*cantidad
	private final BigDecimal monto_iva;		// % iva segun categoría
	
	
	
	public DetalleFactura(Producto producto, Money precio_unitario, Float porcentaje_iva, Integer cantidad,
			Money precio_venta, Money precio_neto, BigDecimal monto_iva) {
		super();
		this.producto = producto;
		this.precio_unitario = precio_unitario;
		this.porcentaje_iva = porcentaje_iva;
		this.cantidad = cantidad;
		this.precio_venta = precio_venta;
		this.precio_neto = precio_neto;
		this.monto_iva = monto_iva;
	}
	
	public static class Builder {
		private Producto producto;
		private Money precio_unitario;
		private Float porcentaje_iva;
		private Integer cantidad;
		private Money precio_venta;	// precio neto + iva
		private Money precio_neto; 	// precio*cantidad
		private BigDecimal monto_iva;		// % iva segun categoría
		
		public Builder producto(Producto producto) {
			this.producto = producto;
			return this;
		}
		public Builder precioUnitario(Money precio_unitario) {
			this.precio_unitario = precio_unitario;
			return this;
		}
		public Builder porcentajeIva(Float porcentaje_iva ) {
			this.porcentaje_iva = porcentaje_iva;
			return this;
		}
		public Builder cantidad(Integer cantidad ) {
			this.cantidad = cantidad;
			return this;
		}
		public Builder precioVenta(Money precio_venta ) {
			this.precio_venta = precio_venta;
			return this;
		}
		public Builder precioNeto(Money precio_neto ) {
			this.precio_neto = precio_neto;
			return this;
		}
		public Builder montoIva(BigDecimal monto_iva ) {
			this.monto_iva = monto_iva;
			return this;
		}
		public DetalleFactura build() {
			return new DetalleFactura(producto,
					precio_unitario,
					porcentaje_iva,
					cantidad,
					precio_venta,
					precio_neto,
					monto_iva);
		}
		
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
	public final Money getPrecioVenta() {
		return precio_venta;
	}
	public final Money getPrecioNeto() {
		return precio_neto;
	}
	public final BigDecimal getMontoIva() {
		return monto_iva;
	}
	
	
	
	
	
	
	
	

}
