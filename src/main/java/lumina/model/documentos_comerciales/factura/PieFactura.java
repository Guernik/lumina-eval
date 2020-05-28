package lumina.model.documentos_comerciales.factura;

import java.math.BigDecimal;

import lumina.model.Money;

/**
 * Inmutable
 * @author emilio
 *
 */
public final class PieFactura {
	
	private final Money total;
	private final BigDecimal total_iva;

	public PieFactura(Money total, BigDecimal total_iva) {	
		this.total = total;
		this.total_iva = total_iva;
	}

	
	
	public final Money getTotal() {
		return total;
	}

	public BigDecimal getTotal_iva() {
		return total_iva;
	}
	
	
	
	
	
	

}
