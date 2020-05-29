package lumina.demo;

import java.util.ArrayList;
import java.util.List;

import lumina.facturacion.BillingObserver;
import lumina.facturacion.Facturacion;
import lumina.facturacion.OperatoriaDiaria;
import lumina.mock.OrderMockBuilder;
import lumina.model.documentos_comerciales.factura.Factura;
import lumina.model.documentos_comerciales.notacredito.NotaCredito;
import lumina.model.pedido.Pedido;

public class LuminaDemoApplication implements BillingObserver {

	public static void main(String[] args) {
		
		new LuminaDemoApplication().runApplication();
		
	}
	
	
	

	private void runApplication() {
		System.out.println("Iniciando Lumina Demo App...");		
		
		Facturacion facturacion = new Facturacion();
		
		Pedido pedido = OrderMockBuilder.create1000SingleProductIvaRespOrder();
		List<Pedido> lista_pedidos = new ArrayList<>();
		lista_pedidos.add(pedido);		
		/** 
		 * Realizar facturacion
		 */
		facturacion.facturar(lista_pedidos, this);
		
		OperatoriaDiaria.getInstance().generarReporteArba();
		System.out.println("Reporte Arba Generado");
	}




	@Override
	public void notifyBillingDone(List<Factura> facturas_list) {
		System.out.println("Recibida lista de facturas");
		
	}

	@Override
	public void notifyCancelBillDone(List<NotaCredito> lista_notas) {
		// TODO Auto-generated method stub
		
	}

	

}
