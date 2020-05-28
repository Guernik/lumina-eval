package lumina.mock;

import java.math.BigDecimal;

import lumina.model.ECurrency;
import lumina.model.Money;
import lumina.model.producto.Producto;

public class ProductMockFactory {
	
	public static Producto create100ArsProduct() {
		Producto producto = new Producto("PROD-001-ARS",
								"Produco a ARS 100.-",
								new Money(ECurrency.ARS,new BigDecimal("100.00")));
		return producto;
	}
	
	
	public static Producto create50con50ArsProduct() {
		Producto producto = new Producto("PROD-002-ARS",
								"Produco a ARS 50.50.- ",
								new Money(ECurrency.ARS,new BigDecimal("50.50")));
		return producto;
	}
	

}
