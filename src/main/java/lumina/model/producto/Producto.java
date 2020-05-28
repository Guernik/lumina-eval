package lumina.model.producto;

import lumina.model.Money;

/**
 * Immutable
 * Clase Producto
 * Modela un determinado producto
 * @author emilio
 *
 */
public final class Producto {
	
	private final String codigo;
	private final String nombre;
	private final Money precio;
	
	
	
	
	public Producto(String codigo, String nombre, Money precio) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
	}

	
	
	public final String getCodigo() {
		return codigo;
	}




	public final String getNombre() {
		return nombre;
	}




	public final Money getPrecio() {
		return precio;
	}
	
	

}
