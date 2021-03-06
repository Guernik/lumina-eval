package lumina.model.pedido;

import java.math.BigDecimal;

import lumina.model.Money;
import lumina.model.producto.Producto;

/**
 * Inmutable
 * @author emilio
 *
 */

public class ProductoCantidad {
	
	private final Producto producto;
	private final Integer cantidad;
	
	
	public ProductoCantidad(Producto producto, Integer cantidad) {	
		/*
		 * Producto es inmutable, no necesito copiarlo
		 */
		this.producto = producto;
		this.cantidad = new Integer(cantidad);
	}


	public Producto getProducto() {
		return producto;
	}


	public int getCantidad() {
		return cantidad.intValue();
	}
	
	public Money totalNeto () {		
		BigDecimal total = producto.getPrecio().getAmount().multiply(BigDecimal.valueOf(getCantidad()));
		return new Money(producto.getPrecio().getCurrency(),total);		
	}
	
	
	

}
