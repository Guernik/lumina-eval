package lumina.facturacion;

import java.time.LocalDate;
import java.util.concurrent.Callable;

import lumina.model.Money;
import lumina.model.documentos_comerciales.factura.Factura;
import lumina.model.documentos_comerciales.notacredito.CabeceraNotaCredito;
import lumina.model.documentos_comerciales.notacredito.NotaCredito;
import lumina.model.documentos_comerciales.notacredito.PieNotaCredito;

public class ProcesarCancelacion implements Callable<NotaCredito> {

	Factura factura;
	
	public ProcesarCancelacion(Factura factura) {
		this.factura = factura;
	}

	@Override
	public NotaCredito call() throws Exception {
		
		CabeceraNotaCredito cabecera= construirCabecera();
		
		PieNotaCredito pie = construirPie();		
		
		NotaCredito nota_credito = new NotaCredito(cabecera, pie);
		
		
		return nota_credito;
	}

	
	

	private CabeceraNotaCredito construirCabecera() {
		return new CabeceraNotaCredito(
				LocalDate.now(),				
				null, //TODO numero nota credito
				null, //TODO
				factura.getCabeceraFactura().getCliente().getCondicion_impositiva().letra(),
				factura.getCabeceraFactura().getCliente());		
	}

	
	private PieNotaCredito construirPie() {
		return new PieNotaCredito(new Money(factura.getPieFactura().getTotal().getCurrency(),factura.getPieFactura().getTotal().getAmount()));
		
	}

}
