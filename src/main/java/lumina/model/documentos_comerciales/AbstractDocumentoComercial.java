package lumina.model.documentos_comerciales;

public class AbstractDocumentoComercial {
	
	
	private final Cabecera cabecera;	
	private final Pie pie;

	public AbstractDocumentoComercial(Cabecera cabecera, Pie pie) {	
		this.cabecera = cabecera;
		this.pie = pie;
	}
	
	

	public final Cabecera getCabecera() {
		return cabecera;
	}

	public final Pie getPie() {
		return pie;
	}
	
	
	
	
	
	

}
