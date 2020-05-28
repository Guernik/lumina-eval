package lumina.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Inmutable
 * @author emilio
 *
 */
public final class Pedido {

	private final long numero_pedido;
	
	private final Cliente cliente;
	
	// Detalle del pedido
	private final List<ProductoCantidad> productos;


	
	public Pedido(long numero_pedido, Cliente cliente, List<ProductoCantidad> detalle_pedido) {	
		/**
		 * Cliente es  inmutable, por lo que no necesito copiarlo para garantizar mi inmutabilidad
		 */
		this.numero_pedido = numero_pedido;
		this.cliente = cliente;
		this.productos = new ArrayList<ProductoCantidad>(detalle_pedido); // copiar lista
	}



	public final long getNumero_pedido() {
		return numero_pedido;
	}



	public final Cliente getCliente() {
		return cliente;
	}



	public final List<ProductoCantidad> getProductos() {
		/**
		 * Devolver copia de la lista para mantener la inmutabilidad
		 */
		return new ArrayList<ProductoCantidad>(productos); 
	}
	
	
	
	
	
}
