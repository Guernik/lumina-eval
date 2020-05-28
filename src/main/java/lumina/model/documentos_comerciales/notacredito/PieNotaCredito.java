package lumina.model.documentos_comerciales.notacredito;

import lumina.model.Money;

/**
 * Inmutable
 * @author emilio
 *
 */
public final class PieNotaCredito {
	
	private final Money total;


	public PieNotaCredito(Money total) {	
		this.total = total;		
	}
	
	public final Money getTotal() {
		return total;
	}

	
	
	
	
	
	

}
