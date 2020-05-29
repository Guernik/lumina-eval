package lumina.facturacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import lumina.model.documentos_comerciales.factura.Factura;
import lumina.model.documentos_comerciales.notacredito.NotaCredito;
import lumina.model.pedido.Pedido;

public class Facturacion {
	
	private static final int THREAD_CANT = 10;  // Obtener de configuración o similar
	
	private static final AtomicLong _billing_number = new AtomicLong();
	
	
	private ExecutorService billing_executor = Executors.newFixedThreadPool(THREAD_CANT);
	private ExecutorService cancel_bill_executor = Executors.newFixedThreadPool(THREAD_CANT);
	private ExecutorService observer_notifier = Executors.newFixedThreadPool(1);
	// TODO shutdown de executors
	
	
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
			
			// AtomicLong y sus operaciones son  threadsafe
			long next_billing_number = _billing_number.incrementAndGet();			

			
			Future<Factura> future = this.billing_executor.submit(new ProcesarFacturacion(pedido, next_billing_number));
		    
			future_list.add(future);
		 						
		}
		
		List<Factura> facturas_list = new ArrayList<>();
		
		/**
		 * Ejecutar el update del observer en un hilo separado, 
		 * de esta manera este método es totalmente asincrónico
		 */
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
	}
	
	
	/**
	 * Requerimiento 1.b. "Recibir una lista de facturas a anular y Realizar la cancelación de pedidos"
	 * @param facturas
	 * @param billing_observer
	 */
	public void anularFacturas(List<Factura> facturas, BillingObserver billing_observer) {
		/**
		 * Lista de futures donde se iran guardando los resultados
		 * de procesar cada una de las facturas
		 */
		List<Future<NotaCredito>> future_list = new ArrayList<>();
		
		
		for (Factura factura : facturas) {			
			Future<NotaCredito> future = this.cancel_bill_executor.submit(new ProcesarCancelacion(factura));		    
			future_list.add(future);		 						
		}
		
		List<NotaCredito> notas_credito = new ArrayList<>();
		
		/**
		 * Ejecutar el update del observer en un hilo separado, 
		 * de esta manera este método es totalmente asincrónico
		 */
		this.observer_notifier.submit( () -> {
			for (Future<NotaCredito> notacredito : future_list) {
				try {
					notas_credito.add (notacredito.get());
				} catch (InterruptedException | ExecutionException e) {

					e.printStackTrace();
				}
			}
			billing_observer.notifyCancelBillDone(notas_credito);			
		});		
	}	
	

}
