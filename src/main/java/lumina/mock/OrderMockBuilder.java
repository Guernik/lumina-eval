package lumina.mock;

import java.util.ArrayList;
import java.util.List;

import lumina.model.Cliente;
import lumina.model.pedido.Pedido;
import lumina.model.pedido.ProductoCantidad;

public class OrderMockBuilder {
	
	
	
	public static Pedido create1000Ars10DifferentProductsIvaRespOrder() {
		
		Cliente cliente = ClientMockFactory.createResponsableInscriptoClient();
		
		List<ProductoCantidad> detalle = new ArrayList<>();
		
		
		// Agrego 10 productos distintos, cada uno con cantidad = 1
		
		for (int i=0;i<10;i++) {			
			ProductoCantidad prod_cant = new ProductoCantidad(ProductMockFactory.create100ArsProduct(), 1);
			detalle.add(prod_cant);				
		}	
		
		Pedido pedido = new Pedido(1000l, cliente, detalle);
		return pedido;
	}
	
	
	public static Pedido create1000Ars1DifferentProductsIvaRespOrder() {
		
		Cliente cliente = ClientMockFactory.createResponsableInscriptoClient();
		
		List<ProductoCantidad> detalle = new ArrayList<>();
		
		
		// Agrego 1 producto, con cantidad 10	
					
		ProductoCantidad prod_cant = new ProductoCantidad(ProductMockFactory.create100ArsProduct(), 10);
		detalle.add(prod_cant);				
		
		
		Pedido pedido = new Pedido(1000l, cliente, detalle);
		return pedido;
	}
	
	
	public static Pedido create101ArsMonotributoOrder() {
		Cliente cliente = ClientMockFactory.createMonotributoClient();
		
		List<ProductoCantidad> detalle = new ArrayList<>();
		
		detalle.add(new ProductoCantidad(ProductMockFactory.create50con50ArsProduct(), 2));		
		
		Pedido pedido = new Pedido(1000l, cliente, detalle);
		return pedido;
		
	}
	
	public static Pedido create101ArsIvaRespOrder() {
		Cliente cliente = ClientMockFactory.createResponsableInscriptoClient();
		
		List<ProductoCantidad> detalle = new ArrayList<>();
		
		detalle.add(new ProductoCantidad(ProductMockFactory.create50con50ArsProduct(), 2));		
		
		Pedido pedido = new Pedido(1000l, cliente, detalle);
		return pedido;
		
	}
	
	public static Pedido create101ArsIvaNoRespOrder() {
		Cliente cliente = ClientMockFactory.createIvaNoRespClient();
		
		List<ProductoCantidad> detalle = new ArrayList<>();
		
		detalle.add(new ProductoCantidad(ProductMockFactory.create50con50ArsProduct(), 2));		
		
		Pedido pedido = new Pedido(1000l, cliente, detalle);
		return pedido;
		
	}
	
	
	
	
	
}
