package report.decoratorComponent.report;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.dto.DtoVeterinarian;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import report.Report;
import report.decoratorComponent.DecoradorReport;

/**
 * Archivo: ReportFilterVeterinarian.java contiene la definici�n de la clase
 * ReportFilterVeterinarian que extiende de DecoradorReport.
 * 
 * @author
 * @version 1.0
 *
 */
public class ReportFilterVeterinarian extends DecoradorReport {
	// declaraci�n de atributos
	private static final String REPORT_VETERINARIAN_FILTER = "filter_veterinarian.jasper";
	private static final String REPORT_VETERINARIAN_FILTER_OUTPUT = "reporte_veterinario_filtro.pdf";

	private Map<String, Object> parameters;

	/**
	 * Constructor con par�metros
	 * 
	 * @param controller objeto de tipo ControllerPeople
	 * @param report     objeto de tipo Report
	 */
	public ReportFilterVeterinarian(Report report) {
		super(report);
	}// cierre constructor

	// M�todo obtenerParametros
	public void setParams(String field, String seach) {
		parameters = new HashMap<String, Object>();
		parameters.put("search", seach);
		parameters.put("field", field);

		DtoVeterinarian.FIELDS fieldSelect = DtoVeterinarian.FIELDS.valueOf(field);
		String querySelect = "WHERE ";
		if (seach.length() <= 0) {
			querySelect = "";
		} else {
			switch (fieldSelect) {
			case nombre:
				querySelect += "UPPER(" + field + ") LIKE '%" + seach.toUpperCase() + "%'";
				break;
			case correo:
				querySelect += "UPPER(" + field + ") LIKE '%" + seach.toUpperCase() + "%'";
				break;
			case cedula:
				querySelect += "UPPER(" + field + ") LIKE '%" + seach.toUpperCase() + "%'";
				break;
			case sexo:
				querySelect += field + "  = '" + seach + "'";
				break;
			case fecha_nacimiento:
				querySelect += field + " = '" + seach + "'";
				break;
			}
		}
		parameters.put("query-select", querySelect);
	}// cierre M�todo obtenerParametros

	/**
	 * M�todo obtenerInforme
	 * 
	 * @throws JRException
	 */
	@Override
	public void getReport(String report) throws JRException {
		super.jasperPrint = JasperFillManager.fillReport("reports/" + REPORT_VETERINARIAN_FILTER, parameters, conexion);
	}// cierre m�todo obtenerInforme

	/**
	 * M�ttodo compilarInforme
	 * 
	 * @throws excepcion JRException
	 */
	@Override
	public void complieReport(String outputReport) throws JRException {
		super.complieReport(REPORT_VETERINARIAN_FILTER_OUTPUT);
	}// cierre m�todo compilarInforme

	/**
	 * M�todo MuestraInforme
	 * 
	 * @throws excepcion IOException
	 */
	@Override
	public void showReport(String outputReport) throws IOException {
		super.showReport(REPORT_VETERINARIAN_FILTER_OUTPUT);
	}// cierre m�todo MuestraInforme

}// cierre clase ReportFilterVeterinarian
