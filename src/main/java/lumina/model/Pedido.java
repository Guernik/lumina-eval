package lumina.model;

public final class Pedido {

	private final long numero_pedido;
	
	private final Cliente cliente;
	
	private final DetallePedido detalle_pedido;


	
	public Pedido(long numero_pedido, Cliente cliente, DetallePedido detalle_pedido) {	
		// Cliente y DetallePedido son inmutables, por lo que no necesito copiarlas para garantizar mi inmutabilidad
		this.numero_pedido = numero_pedido;
		this.cliente = cliente;
		this.detalle_pedido = detalle_pedido;
	}



	public final long getNumero_pedido() {
		return numero_pedido;
	}



	public final Cliente getCliente() {
		return cliente;
	}



	public final DetallePedido getDetalle_pedido() {
		return detalle_pedido;
	}
	
	
	
	
	
}
