package report.decoratorComponent;

import report.FormatReport;
import report.Report;

/**
 * Archivo: DecoradorReport.java contiene la definici�n de la clase abstracta
 * DecoradorReport que extiende de FormatReport e implementa de Report.
 * 
 * @author
 * @version 1.0
 *
 */
public abstract class DecoradorReport extends FormatReport {
	// declaraci�n de atributo
	private Report report;

	/**
	 * Constructor con par�metro
	 * 
	 * @param report
	 */
	public DecoradorReport(Report report) {
		this.report = report;
	}// cierre constructor

	// manejadores getter y setter
	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

}// cierre clase DecoratorReporte
