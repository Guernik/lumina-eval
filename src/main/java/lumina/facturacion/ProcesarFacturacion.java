package lumina.facturacion;

import java.time.LocalDate;
import java.util.concurrent.Callable;

import lumina.model.Pedido;
import lumina.model.documentos_comerciales.Cabecera;
import lumina.model.documentos_comerciales.Factura;



public class ProcesarFacturacion implements Callable<Factura>{

	
	private final Pedido pedido;
	
	
	
	public ProcesarFacturacion(Pedido pedido) {	
		this.pedido = pedido;
	}



	@Override
	public Factura call() throws Exception {
		
		// construir Cabecera
		
		Cabecera cabecera = new Cabecera(
				LocalDate.now(),
				null, //TODO
				null, //TODO
				pedido.getCliente().getCondicion_impositiva().letra(),
				pedido.getCliente()); 
		
		// construir Detalle
		pedido.getDetalle_pedido()
		
		// construir Pie
		
		// construir Factura
		
		
		return null;
	}

}
