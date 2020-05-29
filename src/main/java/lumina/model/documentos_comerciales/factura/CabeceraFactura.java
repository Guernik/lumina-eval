package lumina.model.documentos_comerciales.factura;

import java.time.LocalDate;

import lumina.model.Cliente;
import lumina.model.documentos_comerciales.AbstractCabecera;

/**
 * Inmutable
 * @author Emilio Nahuel
 *
 */
public final class CabeceraFactura extends AbstractCabecera{

	
	private final Long numero_factura;
	
	public CabeceraFactura(LocalDate fecha_emision,Long numero_factura,
							String codigo_emision,String letra,
							Cliente cliente) 
	{
		super(fecha_emision, codigo_emision, letra, cliente);
		this.numero_factura = new Long(numero_factura);	
	}

	
	
	public Long getNumeroFactura() {
		return numero_factura.longValue();
	}

}
