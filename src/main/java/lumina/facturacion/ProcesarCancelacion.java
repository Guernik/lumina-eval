package lumina.facturacion;

import java.util.concurrent.Callable;

import lumina.model.documentos_comerciales.factura.Factura;
import lumina.model.documentos_comerciales.notacredito.NotaCredito;

public class ProcesarCancelacion implements Callable<NotaCredito> {

	Factura factura;
	
	public ProcesarCancelacion(Factura factura) {
		this.factura = factura;
	}

	@Override
	public NotaCredito call() throws Exception {
		return null;
	}

	

}
