package lumina.test.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lumina.mock.OrderMockBuilder;
import lumina.model.pedido.Pedido;

public class TestOrders {
	
	
	
	
	@BeforeEach
	public void setUp() {
		
	}
	
	
	@Test
	public void ivaRespClientOrderShouldBe1000Ars () {
		
		Pedido pedido  = OrderMockBuilder.create1000Ars1DifferentProductsIvaRespOrder();
		
		BigDecimal cantidad_1000 = new BigDecimal("1000.00");		
		
		assertEquals(0, cantidad_1000.compareTo(pedido.totalPedido()));
		
		
		
		
		
	}
	
	
	

}
