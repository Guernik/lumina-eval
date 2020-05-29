package lumina.model.documentos_comerciales.notacredito;

public class NotaCredito {

	private final PieNotaCredito pie_nota_credito;
	private CabeceraNotaCredito cabecera;
	
	//TODO Falta el motivo?
	
	public NotaCredito(CabeceraNotaCredito cabecera, PieNotaCredito pie_nota_credito) {
		
		this.cabecera = cabecera;
		this.pie_nota_credito = pie_nota_credito;
	}

	public PieNotaCredito getPieNotaCredito() {
		return pie_nota_credito;
	}

	public CabeceraNotaCredito getCabecera() {
		return cabecera;
	}

}
