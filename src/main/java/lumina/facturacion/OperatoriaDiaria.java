package lumina.facturacion;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import lumina.model.Operatoria;

/**
 * Singleton de operatoria diaria
 * @author Emilio Nahuel
 *
 */
public class OperatoriaDiaria {
	
	/**
	 * Eager initialization
	 * _instance queda inicializado al momento del class loading, garantizando así
	 * que el singleton es thread safe (sin necesidad sincronizar el getInstance)
	 */
	private static final  OperatoriaDiaria _instance = new OperatoriaDiaria();
	
	private final BlockingQueue<Operatoria> daily_operations;
	
	private OperatoriaDiaria() {
		this.daily_operations = new LinkedBlockingQueue<>();
	}
	
	
	
	public static OperatoriaDiaria getInstance() {
		return _instance;		
	}

	public BlockingQueue<Operatoria> getDailyOperations() {
		return daily_operations;
	}
	
	
	/**
	 * Requerimiento 1.c "Proveer un método que Genere el txt con la operatoria del día (...)"
	 */
	public void generarReporteArba() {
		StringBuilder sb = new StringBuilder();
		LocalDateTime now = LocalDateTime.now();
		sb.append("Reporte_Arba_");
		sb.append(now.format(DateTimeFormatter.ofPattern("dd-MM-YYYY_HH_mm")));
		sb.append(".txt");
		
		String file_name = sb.toString();
		
		PrintWriter print_writer = null;
		try {			
			print_writer = new PrintWriter(file_name);
			OperatoriaDiaria.getInstance()
						.getDailyOperations()
						.stream()
						.map(Operatoria::toString)
						.forEach(print_writer::println);
						
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			print_writer.close(); // Tambien se podría utilizar un try-with-resources para evitar hacer el close acá
		}
		
		
		
	
		
		
		
						
	}
		
	
	
	

}
