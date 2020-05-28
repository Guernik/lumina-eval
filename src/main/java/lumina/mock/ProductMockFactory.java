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
	
	public static Producto create1000ArsProduct() {
		Producto producto = new Producto("PROD-001-ARS",
								"Produco a ARS 1000.-",
								new Money(ECurrency.ARS,new BigDecimal("1000.00")));
		return producto;
	}
	
	
	public static Producto create50Ars50CentsProduct() {
		Producto producto = new Producto("PROD-002-ARS",
								"Produco a ARS 50.50.- ",
								new Money(ECurrency.ARS,new BigDecimal("50.50")));
		return producto;
	}
	

}
