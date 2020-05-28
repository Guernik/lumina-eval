package lumina.model.documentos_comerciales.notacredito;

import lumina.model.documentos_comerciales.AbstractDocumentoComercial;
import lumina.model.documentos_comerciales.Cabecera;

public class NotaCredito extends AbstractDocumentoComercial{

	private final PieNotaCredito pie_nota_credito;
	
	public NotaCredito(Cabecera cabecera, PieNotaCredito pie_nota_credito) {
		super(cabecera);
		this.pie_nota_credito = pie_nota_credito;
	}

	public PieNotaCredito getPieNotaCredito() {
		return pie_nota_credito;
	}

}
