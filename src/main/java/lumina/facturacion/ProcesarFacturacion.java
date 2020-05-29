package lumina.facturacion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import lumina.exceptions.FootTotalCalculationException;
import lumina.model.ECurrency;
import lumina.model.Money;
import lumina.model.documentos_comerciales.factura.CabeceraFactura;
import lumina.model.documentos_comerciales.factura.DetalleFactura;
import lumina.model.documentos_comerciales.factura.Factura;
import lumina.model.documentos_comerciales.factura.PieFactura;
import lumina.model.pedido.Pedido;
import lumina.model.pedido.ProductoCantidad;



public class ProcesarFacturacion implements Callable<Factura>{

	
	private final Pedido pedido;
	private long next_billing_number;
	
	
	
	
	public ProcesarFacturacion(Pedido pedido, long next_billing_number) {	
		this.pedido = pedido;
		this.next_billing_number = next_billing_number;
	
	}



	@Override
	public Factura call() throws Exception {
		// construir Cabecera
		
		CabeceraFactura cabecera = construirCabecera();
		
		List<DetalleFactura> detalles = construirCuerpoFactura();		
		
		PieFactura pie = construirPieFactura(detalles);		
		
		Factura factura = new Factura(cabecera, pie, detalles);
		return factura;
		
	}



	private PieFactura construirPieFactura(List<DetalleFactura> detalles) {
		Optional<BigDecimal> maybe_total = detalles.stream()
				.map(DetalleFactura::getPrecioVenta)
				.map(Money::getAmount)
				.reduce(BigDecimal::add);
		Optional<BigDecimal> maybe_total_iva = detalles.stream()
		.map(DetalleFactura::getMontoIva)				
		.reduce(BigDecimal::add);
		
		BigDecimal total_pie_factura = maybe_total.orElseThrow(() -> new FootTotalCalculationException());
		BigDecimal total_iva = maybe_total_iva.orElseThrow(() -> new FootTotalCalculationException());
		
		
		PieFactura pie = new PieFactura(new Money(ECurrency.ARS,total_pie_factura), total_iva);
		return pie;

	}



	private List<DetalleFactura> construirCuerpoFactura() {
		DetalleFactura.Builder builder;
		List<DetalleFactura> detalles = new ArrayList<>();

		for (ProductoCantidad item : pedido.getProductos()) {
			builder = new DetalleFactura.Builder();

			float iva = pedido.getCliente().getCondicion_impositiva().porcentaje();
			BigDecimal precio = item.getProducto().getPrecio().getAmount(); 
			
		
			Money precio_neto = calcularPrecioNeto(item);
			Money precio_venta = calcularPrecioVenta(precio_neto, iva);
			
			BigDecimal monto_iva = montoIva(precio, iva);
			
			builder.producto(item.getProducto()); // TODO probablemente aquí debería ir el nombre/codigo del producto
			builder.precioUnitario(item.getProducto().getPrecio());
			builder.porcentajeIva(iva);
			builder.cantidad(item.getCantidad());
			builder.precioVenta(precio_venta);
			builder.precioNeto(precio_neto);
			builder.montoIva(monto_iva);
			DetalleFactura detalle = builder.build();
			
			detalles.add(detalle);
			
		}
		return detalles;
	}



	private CabeceraFactura construirCabecera() {
		return new CabeceraFactura(
				LocalDate.now(),
				next_billing_number,
				null, //TODO
				pedido.getCliente().getCondicion_impositiva().letra(),
				pedido.getCliente());
	}
	
	/**
	 * Suma el iva a un precio dado
	 * @param precio
	 * @param iva valor decimal del porcentaje de iva, ej: 0.21f
	 * @return
	 */
	private BigDecimal sumarIva(BigDecimal precio, float iva) {
		BigDecimal monto_iva = montoIva(precio,iva);		
		return precio.add(monto_iva);
	}
	
	/**
	 * 
	 * @param precio
	 * @param iva
	 * @return
	 */
	private BigDecimal montoIva(BigDecimal precio, float iva) {
		BigDecimal iva_convertido = new BigDecimal(Float.toString(iva));
		return precio.multiply(iva_convertido);
	}
	
	/**
	 * Devuelve multiplicacion entre precio y cantidad en tipo BigDecimal
	 * @param precio
	 * @param cantidad
	 * @return
	 */
	private BigDecimal precioNeto(BigDecimal precio, Integer cantidad ) {
		return precio.multiply(BigDecimal.valueOf(cantidad));
	}
	
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	private Money calcularPrecioNeto(ProductoCantidad item) {
		Money precio = item.getProducto().getPrecio();
		BigDecimal precio_neto = precioNeto(precio.getAmount(),item.getCantidad());
		return new Money(ECurrency.ARS, precio_neto);
	}
	
	/**
	 * 
	 * @param precio_neto
	 * @param iva
	 * @return
	 */
	private Money calcularPrecioVenta(Money precio_neto,float iva) {
		
		BigDecimal precio_venta = sumarIva(precio_neto.getAmount(),
										pedido.getCliente().getCondicion_impositiva().porcentaje());
		
		return new Money(ECurrency.ARS, precio_venta);
	}
	
	




}
