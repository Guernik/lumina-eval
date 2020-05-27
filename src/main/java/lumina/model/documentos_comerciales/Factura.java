package lumina.model.documentos_comerciales;

public class Factura extends AbstractDocumentoComercial{

	private DetalleFactura detalle_factura;

	public final DetalleFactura getDetalle_factura() {
		return detalle_factura;
	}

	
	public Factura(Cabecera cabecera, Pie pie, DetalleFactura detalle_factura) {
		super(cabecera, pie);
		this.detalle_factura = detalle_factura;
	}
	
	
	
	

}
