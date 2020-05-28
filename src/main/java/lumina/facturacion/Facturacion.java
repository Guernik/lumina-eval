package lumina.facturacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import lumina.model.Pedido;
import lumina.model.documentos_comerciales.factura.Factura;

public class Facturacion {
	
	private static final int THREAD_CANT = 10;  // Obtener de configuración o similar
	
	private static final AtomicLong _billing_number = new AtomicLong();
	
	
	private ExecutorService billing_executor = Executors.newFixedThreadPool(THREAD_CANT);
	
	private List<CompletableFuture<Factura>> completables = new ArrayList<>();
	
	
	
	
	
	
	public void facturar(List<Pedido> lista_pedidos, BillingObserver observer) {
		
		for (Pedido pedido : lista_pedidos) {
			
			long next_billing_number = _billing_number.incrementAndGet();			
			
		    CompletableFuture<Factura> completable = new CompletableFuture<>();
		    completables.add(completable);			
			
		    this.billing_executor.submit(new ProcesarFacturacion(pedido,next_billing_number,completable));
						
		}
		
		@SuppressWarnings("unchecked")
		CompletableFuture<Factura>[] completables_array = completables.toArray(new CompletableFuture[0]);
		
		CompletableFuture.allOf(completables_array).thenAccept(f -> {
			observer.notifyBillingDone();
		});
		
	}
	

}
