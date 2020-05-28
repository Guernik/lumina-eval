package lumina.model.documentos_comerciales;


/**
 * Se prefirió usar herencia sobre composición ya que los documentos comerciales
 * no son algo que vayan a cambiar su naturaleza (una factura nunca va a ser una nota de crédito).
 * 
 * Esta jerarquía con herencia se aprovecharía más con más tipos de documentos.
 * @author emilio
 *
 */
public class AbstractDocumentoComercial {
	
	
	private final Cabecera cabecera;	
	

	public AbstractDocumentoComercial(Cabecera cabecera) {	
		this.cabecera = cabecera;	
	}
	
	

	public final Cabecera getCabecera() {
		return cabecera;
	}

	
	
	
	
	
	
	

}
