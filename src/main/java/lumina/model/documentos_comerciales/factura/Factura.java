package lumina.model.documentos_comerciales.factura;

import java.util.ArrayList;
import java.util.List;

import lumina.model.documentos_comerciales.AbstractDocumentoComercial;
import lumina.model.documentos_comerciales.Cabecera;

public final class Factura extends AbstractDocumentoComercial{

	private final List<DetalleFactura> detalle_factura;
	private final PieFactura pie_factura;

	
	public Factura(Cabecera cabecera, PieFactura pie, List<DetalleFactura> detalle_factura) {
		super(cabecera);
		this.detalle_factura = new ArrayList<>(detalle_factura);
		this.pie_factura = pie;
		
	}
	
	
	public final List<DetalleFactura> getDetalleFactura() {
		return new ArrayList<>(detalle_factura);
	}


	public PieFactura getPieFactura() {
		return pie_factura;
	}
	
	

}
