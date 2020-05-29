package lumina.facturacion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.Callable;

import lumina.model.Cliente;
import lumina.model.Money;
import lumina.model.Operatoria;
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
		
		Operatoria operatoria = construirOperatoria(factura.getPieFactura().getTotal());
		
		OperatoriaDiaria.getInstance().getDailyOperations().put(operatoria);
		
		
		return nota_credito;
	}
	
	
	private Operatoria construirOperatoria(Money total) {
		Cliente cliente = factura.getCabeceraFactura().getCliente();
		BigDecimal montoNegativo = total.getAmount().multiply(BigDecimal.valueOf(-1));
		Money nuevoMonto = new Money(total.getCurrency(),montoNegativo);
		
		
		Operatoria op = new Operatoria(cliente.getNumero_cliente(),
									cliente.getTipo_documento(),
									cliente.getCondicion_impositiva().letra(),
									cliente.getDocumento(),
									LocalDate.now(),
									nuevoMonto);
		
		return op;
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
