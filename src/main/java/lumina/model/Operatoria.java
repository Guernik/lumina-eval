package lumina.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Pojo para almacenar la operatoria diara.
 * 
 * TODO: en los requerimientos no hay manera de distingir entre facturas y notas de credito.
 * Por lo que para las notas de credito el monto será negativo. 
 * En realidad se debería consultar con el cliente
 * @author Emilio Nahuel
 *
 */
public final class Operatoria {
	
	private final Long numero_cliente;
	private final ETipoDocumento tipo_documento;
	private final String letra;
	private final String numero_documento;
	private final LocalDate fecha_emision;
	private final Money monto;
	public Operatoria(Long cliente, ETipoDocumento tipo_documento, String letra,String numero_documento, LocalDate fecha_emision,
			Money monto) {
		this.numero_cliente = cliente;
		this.tipo_documento = tipo_documento;
		this.letra = letra;
		this.numero_documento = numero_documento;
		this.fecha_emision = fecha_emision;
		this.monto = monto;
	}
	public Long getNumeroCliente() {
		return numero_cliente;
	}
	public ETipoDocumento getTipoDocumento() {
		return tipo_documento;
	}
	public String getLetra() {
		return letra;
	}
	public LocalDate getFechaEmision() {
		return fecha_emision;
	}
	public Money getMonto() {
		return monto;
	}	
	public String getNumeroDocumento() {
		return numero_documento;
	}

	@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(numero_cliente);
			sb.append("-");
			sb.append(tipo_documento.toString());
			sb.append("-");
			sb.append(letra);
			sb.append("-");
			sb.append(numero_documento);
			sb.append("-");
			sb.append(fecha_emision.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			sb.append("-");
			sb.append(monto.getCurrency().toString());
			sb.append(monto.getAmount().toString());
			sb.append(".-");			
			
			return sb.toString();
		}
	
	
	

}
