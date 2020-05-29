package lumina.model.documentos_comerciales.factura;

import java.util.ArrayList;
import java.util.List;

/**
 * Inmutable
 * @author Emilio Nahuel
 *
 */
public final class Factura {

	private final CabeceraFactura cabecera_factura;
	private final List<DetalleFactura> detalle_factura;
	private final PieFactura pie_factura;

	
	public Factura(CabeceraFactura cabecera, PieFactura pie, List<DetalleFactura> detalle_factura) {
		this.cabecera_factura = cabecera;
		this.detalle_factura = new ArrayList<>(detalle_factura); //Copio la lista para garantizar la inmutabilidad
		this.pie_factura = pie;
		
	}
	
	
	public CabeceraFactura getCabeceraFactura() {
		return cabecera_factura;
	}


	public final List<DetalleFactura> getDetalleFactura() {
		return new ArrayList<>(detalle_factura);
	}


	public PieFactura getPieFactura() {
		return pie_factura;
	}
	
	

}
