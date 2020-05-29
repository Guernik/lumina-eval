package lumina.model;

/**
 * Immutable
 * Clase Cliente
 * @author emilio
 *
 */

public final class Cliente {
	
	private final Long numero_cliente;
	private final String domicilio;
	private final ECondicionImpositiva condicion_impositiva;
	private final ETipoDocumento tipo_documento;
	
	/**
	 * Documento modelado como String ya que los pasaportes y algunos documentos antiguos pueden
	 * contener caracteres alfanuméricos
	 */
	private final String documento;
	
	public Cliente(Long numero_cliente, String domicilio, ECondicionImpositiva condicion_impositiva,
			ETipoDocumento tipo_documento, String documento) {
		this.numero_cliente = numero_cliente;
		this.domicilio = domicilio;
		this.condicion_impositiva = condicion_impositiva;
		this.tipo_documento = tipo_documento;
		this.documento = documento;
	}	

	
	public Long getNumero_cliente() {
		return numero_cliente;
	}


	public String getDomicilio() {
		return domicilio;
	}


	public ECondicionImpositiva getCondicion_impositiva() {
		return condicion_impositiva;
	}


	public ETipoDocumento getTipo_documento() {
		return tipo_documento;
	}


	public String getDocumento() {
		return documento;
	}


	
	
	
	
	
	
	
	
	

}