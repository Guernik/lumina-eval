package lumina.facturacion;

import java.util.List;

import lumina.model.documentos_comerciales.factura.Factura;
import lumina.model.documentos_comerciales.notacredito.NotaCredito;

public interface BillingObserver {
	public void notifyBillingDone(List<Factura> facturas_list);
	
	public void notifyCancelBillDone(List<NotaCredito> lista_notas);
}
