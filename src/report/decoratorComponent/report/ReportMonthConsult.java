package report.decoratorComponent.report;

import java.io.IOException;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import report.Report;
import report.decoratorComponent.DecoradorReport;

/**
 * Archivo: ReportMonthConsult.java contiene la definición de la clase
 * ReportFilterConsult que extiende de DecoradorReport.
 * 
 * @author
 * @version 1.0
 *
 */
public class ReportMonthConsult extends DecoradorReport {
	// declaración de atributos
	private static final String REPORT_PEOPLE_FILTER = "consult-month.jasper";
	private static final String REPORT_PEOPLE_FILTER_OUTPUT = "reporte_consulta_fecha.pdf";

	private Map<String, Object> parameters;

	/**
	 * Constructor con parámetros
	 * 
	 * @param controller objeto de tipo ControllerConsult
	 * @param report     objeto de tipo Report
	 */
	public ReportMonthConsult(Report report) {
		super(report);

	}// cierre constructor

	// Método obtenerParametros
	public void setParams(String field, String seach) {
	}// cierre método obtenerParametros

	/**
	 * Método obtenerInforme
	 * 
	 * @throws JRException
	 */
	@Override
	public void getReport(String report) throws JRException {
		jasperPrint = JasperFillManager.fillReport("reports/" + REPORT_PEOPLE_FILTER, parameters, conexion);
	}// cierre método obtenerInforme

	/**
	 * Método compilarInforme
	 * 
	 * @throws excepcion JRException
	 */
	@Override
	public void complieReport(String outputReport) throws JRException {

		super.complieReport(REPORT_PEOPLE_FILTER_OUTPUT);
	}// cierre método compilarInforme

	/**
	 * Método MuestraInforme
	 * 
	 * @throws excepcion IOException
	 */
	@Override
	public void showReport(String outputReport) throws IOException {
		super.showReport(REPORT_PEOPLE_FILTER_OUTPUT);

	}// cierre Método MuestraInforme

}// cierre clase ReportFilterConsult
