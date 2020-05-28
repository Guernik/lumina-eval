package lumina.mock;

import lumina.model.Cliente;
import lumina.model.ECondicionImpositiva;
import lumina.model.ETipoDocumento;

public class ClientMockFactory {
	
	
	public static Cliente createResponsableInscriptoClient() {
		Cliente cliente = new Cliente(1l,
							"Paseo Colon 650",
							ECondicionImpositiva.RESPONSABLE_INSCRIPTO,
							ETipoDocumento.DNI,
							"30.000.000");
		return cliente;
	}
	
	public static Cliente createMonotributoClient() {
		Cliente cliente = new Cliente(1l,
							"Paseo Colon 650",
							ECondicionImpositiva.MONOTRIBUTO,
							ETipoDocumento.DNI,
							"30.000.000");
		return cliente;
	}
	
	public static Cliente createIvaNoRespClient() {
		Cliente cliente = new Cliente(1l,
							"Paseo Colon 650",
							ECondicionImpositiva.NO_RESPONSABLE,
							ETipoDocumento.DNI,
							"30.000.000");
		return cliente;
	}
	
	
	
	
	

}
