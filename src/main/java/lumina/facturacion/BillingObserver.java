package lumina.facturacion;

import java.util.List;

import lumina.model.documentos_comerciales.factura.Factura;

public interface BillingObserver {
	public void notifyBillingDone(List<Factura> facturas_list);
}
