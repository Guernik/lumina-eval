package lumina.model;

import java.math.BigDecimal;

/**
 * Immutable
 * Clase Money
 * Modela una cantidad monetaria con un tipo de divisa
 * @author emilio
 *
 */
public final class Money {
	
	private final ECurrency currency;
	private final BigDecimal amount;
	
	
	public Money(ECurrency currency, BigDecimal amount) {
		this.currency = currency;
		this.amount = amount;
	}
	
	
	public final ECurrency getCurrency() {
		return currency;
	}


	public final BigDecimal getAmount() {
		return amount;
	}
	

}
