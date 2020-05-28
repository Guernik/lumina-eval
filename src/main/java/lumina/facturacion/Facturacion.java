package lumina.facturacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import lumina.model.documentos_comerciales.factura.Factura;
import lumina.model.pedido.Pedido;

public class Facturacion {
	
	private static final int THREAD_CANT = 10;  // Obtener de configuración o similar
	
	private static final AtomicLong _billing_number = new AtomicLong();
	
	
	private ExecutorService billing_executor = Executors.newFixedThreadPool(THREAD_CANT);
	private ExecutorService observer_notifier = Executors.newFixedThreadPool(1);
	
	
	/**
	 * Requerimiento 1.a "Recibir una colección de pedidos, realizar facturación"
	 * @param lista_pedidos
	 * @param billing_observer
	 */
	
	public void facturar(List<Pedido> lista_pedidos, BillingObserver billing_observer) {
		
		/**
		 * Lista de futures donde se iran guardando los resultados
		 * de procesar cada una de las facturas
		 */
		List<Future<Factura>> future_list = new ArrayList<>();
		
		
		for (Pedido pedido : lista_pedidos) {
			
			long next_billing_number = _billing_number.incrementAndGet();			

			
			Future<Factura> future = this.billing_executor.submit(new ProcesarFacturacion(pedido, next_billing_number));
		    
			future_list.add(future);
		 						
		}
		
		List<Factura> facturas_list = new ArrayList<>();
		
		this.observer_notifier.submit( () -> {
			for (Future<Factura> future_bill : future_list) {
				try {
					facturas_list.add (future_bill.get());
				} catch (InterruptedException | ExecutionException e) {

					e.printStackTrace();
				}
			}
			billing_observer.notifyBillingDone(facturas_list);
			
		});
		
//		@SuppressWarnings("unchecked")
//		CompletableFuture<Factura>[] completables_array = completables.toArray(new CompletableFuture[0]);
//		
//		CompletableFuture.allOf(completables_array).thenAccept(f -> {
//			// Obtener lista de facturas
//			
//			
//			observer.notifyBillingDone();
//		});
		
	}
	

}
