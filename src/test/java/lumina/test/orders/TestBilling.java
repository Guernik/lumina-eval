package lumina.test.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lumina.facturacion.BillingObserver;
import lumina.facturacion.Facturacion;
import lumina.facturacion.OperatoriaDiaria;
import lumina.mock.OrderMockBuilder;
import lumina.model.documentos_comerciales.factura.Factura;
import lumina.model.documentos_comerciales.notacredito.NotaCredito;
import lumina.model.pedido.Pedido;

public class TestBilling {

	
	private Facturacion facturacion;
	BillingObserver<List<Factura>> billing_observer;
	BillingObserver<List<NotaCredito>> anulation_observer;
	BlockingQueue<List<Factura>> bill_queue;
	BlockingQueue<List<NotaCredito>> credit_note_queue;
	
	@BeforeEach
	public void setUp() {
		facturacion = new Facturacion();
		bill_queue = new LinkedBlockingQueue<>();
		credit_note_queue = new LinkedBlockingQueue<>();
		
		
		/**
		 * Simular un observer. El mismo sera notificado
		 * cuando el proceso de todas las facturas termine
		 */
		billing_observer = (facturas) -> {
			try {
				bill_queue.put(facturas);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		};

		/**
		 * Idem, observer para la cancelacion
		 */
		anulation_observer= (ontas_credito) -> {
			try {
				credit_note_queue.put(ontas_credito);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		};
		
	}
	
	
	
	/**
	 * Testear facturacion con cliente iva responsable inscripto
	 * @throws InterruptedException 
	 */
	@Test
	public void totalOfBillingShouldBe1105With50CentsForIvaResponsable() throws InterruptedException {
		Pedido pedido = OrderMockBuilder.create1000SingleProductIvaRespOrder();
		List<Pedido> lista_pedidos = new ArrayList<>();
		lista_pedidos.add(pedido);		
		/** 
		 * Realizar facturacion
		 */
		facturacion.facturar(lista_pedidos, billing_observer); 
		
		/** Esperamos un tiempo prudencial a que se realice el proceso de facturacion */
		List<Factura> facturas = bill_queue.poll(10, TimeUnit.SECONDS);		
		Factura factura = facturas.get(0);		
		
		/** A partir de aqui el observer ya fue notificado de los resultados, y tenemos la factura */		
		BigDecimal valor_factura_esperado = new BigDecimal("1100.50");
		BigDecimal valor_iva_esperado = new BigDecimal("100.50");
		BigDecimal valor_factura_recibido = factura.getPieFactura().getTotal().getAmount();
		BigDecimal valor_iva_recibido =  factura.getPieFactura().getTotal_iva();		
		/**
		 * Lo ideal seria un solo assert por test
		 */
		assertEquals(1, facturas.size());
		assertEquals(0, valor_factura_esperado.compareTo(valor_factura_recibido));
		assertEquals(0, valor_iva_esperado.compareTo(valor_iva_recibido));	

	}
	
	/**
	 * Testear facturacion con cliente monotributo
	 */
	@Test
	public void totalOfBillingShouldBe122With21CentsForMonotributo() throws InterruptedException {
		Pedido pedido = OrderMockBuilder.create101ArsMonotributoOrder();
		List<Pedido> lista_pedidos = new ArrayList<>();
		lista_pedidos.add(pedido);		
		/** 
		 * Realizar facturacion
		 */
		facturacion.facturar(lista_pedidos, billing_observer); 
		
		/** Esperamos un tiempo prudencial a que se realice el proceso de facturacion */
		List<Factura> facturas = bill_queue.poll(10, TimeUnit.SECONDS);		
		Factura factura = facturas.get(0);		
		
		/** A partir de aqui el observer ya fue notificado de los resultados, y tenemos la factura */		
		BigDecimal valor_factura_esperado = new BigDecimal("122.21");
		BigDecimal valor_iva_esperado = new BigDecimal("10.605");
		BigDecimal valor_factura_recibido = factura.getPieFactura().getTotal().getAmount();
		BigDecimal valor_iva_recibido =  factura.getPieFactura().getTotal_iva();		
		/**
		 * Lo ideal seria un solo assert por test
		 */
		assertEquals(1, facturas.size());
		assertEquals(0, valor_factura_esperado.compareTo(valor_factura_recibido));
		assertEquals(0, valor_iva_esperado.compareTo(valor_iva_recibido));	

	}
	
	
	
	
	/**
	 * Testear facturacion con cliente iva no responsable
	 */
	@Test
	public void totalOfBillingShouldBe1105With50CentsForMonotributo() throws InterruptedException {
		Pedido pedido = OrderMockBuilder.create101ArsMonotributoOrder();
		List<Pedido> lista_pedidos = new ArrayList<>();
		lista_pedidos.add(pedido);		
		/** 
		 * Realizar facturacion
		 */
		facturacion.facturar(lista_pedidos, billing_observer); 
		
		/** Esperamos un tiempo prudencial a que se realice el proceso de facturacion */
		List<Factura> facturas = bill_queue.poll(10, TimeUnit.SECONDS);		
		Factura factura = facturas.get(0);		
		
		/** A partir de aqui el observer ya fue notificado de los resultados, y tenemos la factura */		
		BigDecimal valor_factura_esperado = new BigDecimal("122.21");
		BigDecimal valor_iva_esperado = new BigDecimal("10.605");
		BigDecimal valor_factura_recibido = factura.getPieFactura().getTotal().getAmount();
		BigDecimal valor_iva_recibido =  factura.getPieFactura().getTotal_iva();		
		/**
		 * Lo ideal seria un solo assert por test
		 */
		System.out.println(valor_factura_recibido);
		System.out.println(valor_iva_recibido);
		assertEquals(1, facturas.size());
		assertEquals(0, valor_factura_esperado.compareTo(valor_factura_recibido));
		assertEquals(0, valor_iva_esperado.compareTo(valor_iva_recibido));	
		System.out.println("size: " + OperatoriaDiaria.getInstance().getDailyOperations().size());

	}
	
	
	/**
	 * Testear facturacion de 1000 pedidos
	 * @throws InterruptedException 
	 */
	@Test
	public void processOf10_000BillsShouldGenerate10_000Bills() throws InterruptedException {
		
		Pedido pedido;
		List<Pedido> lista_pedidos = new ArrayList<>();
		
		/**
		 * Generar 100 pedidos
		 */
		for (int i = 0; i< 10_000; i++) {
			pedido = OrderMockBuilder.create1000SingleProductIvaRespOrder();
			lista_pedidos.add(pedido);
		}		
		
		/** 
		 * Realizar facturacion
		 */
		facturacion.facturar(lista_pedidos, billing_observer); 
		
		/** Esperamos un tiempo prudencial a que se realice el proceso de facturacion */
		List<Factura> facturas = bill_queue.poll(10, TimeUnit.SECONDS);
		
				
		/** A partir de aqui el observer ya fue notificado de los resultados, y tenemos la lista de facturas */
		
		assertEquals(10_000, facturas.size());	
		System.out.println("size: " + OperatoriaDiaria.getInstance().getDailyOperations().size());

	}
	
	@Test
	public void testBillCancelling() throws InterruptedException {
		System.out.println("size: " + OperatoriaDiaria.getInstance().getDailyOperations().size());
		Pedido pedido = OrderMockBuilder.create101ArsMonotributoOrder();
		List<Pedido> lista_pedidos = new ArrayList<>();
		lista_pedidos.add(pedido);
		/** 
		 * Realizar facturacion
		 */
		facturacion.facturar(lista_pedidos, billing_observer); 
		
		/** Esperamos un tiempo prudencial a que se realice el proceso de facturacion */
		List<Factura> facturas = bill_queue.poll(10, TimeUnit.SECONDS);
				
		facturacion.anularFacturas(facturas, anulation_observer);
		/** Esperamos un tiempo prudencial a que se realice el proceso de cancelacion */
		List<NotaCredito> notas_credito= credit_note_queue.poll(10, TimeUnit.SECONDS);
		
		BigDecimal monto_esperado = new BigDecimal("122.21");
		BigDecimal monto_recibido = notas_credito.get(0).getPieNotaCredito().getTotal().getAmount();
		
		assertEquals(1, notas_credito.size());
		
		assertEquals(0, monto_esperado.compareTo(monto_recibido));
		System.out.println("size: " + OperatoriaDiaria.getInstance().getDailyOperations().size());
		
	}	
	
	
	
}
