package lumina.facturacion;

public interface BillingObserver<T> {
	public void notifyDone(T facturas_list);
}
