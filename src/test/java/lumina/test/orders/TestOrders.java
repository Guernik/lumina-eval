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
	
	
	
	/**
	 * Testear valor de una orden de 1000 ARS
	 */
	@Test
	public void orderShouldBe1000Ars () {
		
		Pedido pedido  = OrderMockBuilder.create1000Ars1DifferentProductsIvaRespOrder();
		
		BigDecimal cantidad_1000 = new BigDecimal("1000.00");		
		
		assertEquals(0, cantidad_1000.compareTo(pedido.totalPedido()));		
		
	}
	
	/**
	 * Testea que tres ordenes de diferentes productos todas tengan un total de 1000
	 * 
	 */
	@Test
	public void orderWithDifferentProductsShouldHaveSameValue() {
		// Pedido con 1 producto, cantidad 10, valor 100 = 1000 ARS
		Pedido pedido_1_producto_10_veces  = OrderMockBuilder.create1000Ars1DifferentProductsIvaRespOrder();
		
		// Pedido con 10 productos, cantidad 1, valor 100 = 1000ARS
		Pedido pedido_10_productos_1_vez = OrderMockBuilder.create1000Ars10DifferentProductsIvaRespOrder();
		
		// Pedido con 1 productos, cantidad 1, valor 1000 = 1000ARS
		Pedido pedido_1_producto_1_vez = OrderMockBuilder.create1000SingleProductIvaRespOrder();
		
		int compare_1 = pedido_1_producto_10_veces.totalPedido().compareTo(pedido_10_productos_1_vez.totalPedido());
		
		int compare_2 = pedido_1_producto_10_veces.totalPedido().compareTo(pedido_1_producto_1_vez.totalPedido());
		
		int compare_3 = pedido_10_productos_1_vez.totalPedido().compareTo(pedido_1_producto_1_vez.totalPedido());
		
		assertEquals(0, compare_1);
		assertEquals(0, compare_2);
		assertEquals(0, compare_3);
	}
	
	/**
	 * Testear que pedido con valor decimal de 50Ars con 50 centavos tenga el total corrento
	 */
	@Test
	public void orderWithDecimalAmountsShouldHaveCorrectValue () {
		Pedido pedido = OrderMockBuilder.create50Ars50CentsIvaRespOrder();
		
		BigDecimal cantidad_50_50 = new BigDecimal("50.50");
		
		assertEquals(0, cantidad_50_50.compareTo(pedido.totalPedido()));
	}

}
