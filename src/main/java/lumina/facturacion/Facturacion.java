package lumina.facturacion;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lumina.model.Pedido;

public class Facturacion {
	
	private static final int THREAD_CANT = 10;  // Obtener de configuración o similar
	
	private ExecutorService billing_executor = Executors.newFixedThreadPool(THREAD_CANT);
	
	
	public void facturar(List<Pedido> lista_pedidos) {
		
		for (Pedido pedido : lista_pedidos) {
			this.billing_executor.submit(() -> {});
		}
		
		
		
	}
	

}
