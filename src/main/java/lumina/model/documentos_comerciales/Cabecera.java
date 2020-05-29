package lumina.model.documentos_comerciales;

import java.time.LocalDate;

import lumina.model.Cliente;

public final class Cabecera {
	
	private final LocalDate fecha_emision;
	
	private final Long numero_factura;
	
	private final String codigo_emision;
	
	private final String letra;
	
	private final Cliente cliente;

	public Cabecera(LocalDate fecha_emision, Long numero_factura, String codigo_emision, String letra,
			Cliente cliente) {
	
		this.fecha_emision = fecha_emision; // LocalDate ya es immutable
		this.numero_factura = new Long(numero_factura);
		this.codigo_emision = codigo_emision;
		this.letra = letra;
		this.cliente = cliente;
	}

	public final LocalDate getFecha_emision() {
		return fecha_emision; // LocalDate ya es inmutable, por lo que no necesito copiarlo
	}

	public final Long getNumero_factura() {
		return numero_factura.longValue();
	}

	public final String getCodigo_emision() {
		return codigo_emision;
	}

	public final String getLetra() {
		return letra;
	}

	public final Cliente getCliente() {
		return cliente;
	}
	
	
	
	
	

}
