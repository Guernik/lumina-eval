package lumina.model.documentos_comerciales.notacredito;

import java.time.LocalDate;

import lumina.model.Cliente;
import lumina.model.documentos_comerciales.AbstractCabecera;

public class CabeceraNotaCredito extends AbstractCabecera{

	
	private final Long numero_nota_credito;
	
	public CabeceraNotaCredito(LocalDate fecha_emision, Long numero_nota_credito,
							String codigo_emision,String letra,
							Cliente cliente) 
	{
		super(fecha_emision, codigo_emision, letra, cliente);
		this.numero_nota_credito = numero_nota_credito;	
	}

	
	
	public Long getNumero_nota_credito() {
		return numero_nota_credito;
	}

}
