package report.decoratorComponent.report;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.dto.DtoConsult;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import report.Report;
import report.decoratorComponent.DecoradorReport;

/**
 * Archivo: ReportFilterConsult.java contiene la definici�n de la clase
 * ReportFilterConsult que extiende de DecoradorReport.
 * 
 * @author
 * @version 1.0
 *
 */
public class ReportFilterConsult extends DecoradorReport {
	// declaraci�n de atributos
	private static final String REPORT_PEOPLE_FILTER = "filter_consult.jasper";
	private static final String REPORT_PEOPLE_FILTER_OUTPUT = "reporte_consulta_filtro.pdf";

	private Map<String, Object> parameters;

	/**
	 * Constructor con par�metros
	 * 
	 * @param controller objeto de tipo ControllerConsult
	 * @param report     objeto de tipo Report
	 */
	public ReportFilterConsult(Report report) {
		super(report);

	}// cierre constructor

	// M�todo obtenerParametros
	public void setParams(String field, String seach) {
		parameters = new HashMap<String, Object>();
		parameters.put("search", seach);
		parameters.put("field", field);

		DtoConsult.FIELDS fieldSelect = DtoConsult.FIELDS.valueOf(field);
		String querySelect = "WHERE ";
		if (seach.length() <= 0) {
			querySelect = "";
		} else {
			switch (fieldSelect) {
			case mascota:
				querySelect += "UPPER(m.nombre) LIKE '%" + seach.toUpperCase() + "%'";
				break;
			case veterinario:
				querySelect += "UPPER(v.nombre) LIKE '%" + seach.toUpperCase() + "%'";
				break;
			case fecha:
				querySelect += field + "  = '" + seach + "'";
				break;
			case descripcion:
				querySelect += "UPPER(" + field + ") LIKE '%" + seach.toUpperCase() + "%'";
				break;
			case costo:
				querySelect += field + " = " + seach + "";
				break;
			case estatus:
				querySelect += field + "  = '" + seach + "'";
				break;
			}
		}
		parameters.put("query-select", querySelect);
	}// cierre m�todo obtenerParametros

	/**
	 * M�todo obtenerInforme
	 * 
	 * @throws JRException
	 */
	@Override
	public void getReport(String report) throws JRException {
		jasperPrint = JasperFillManager.fillReport("reports/" + REPORT_PEOPLE_FILTER, parameters, conexion);
	}// cierre m�todo obtenerInforme

	/**
	 * M�todo compilarInforme
	 * 
	 * @throws excepcion JRException
	 */
	@Override
	public void complieReport(String outputReport) throws JRException {

		super.complieReport(REPORT_PEOPLE_FILTER_OUTPUT);
	}// cierre m�todo compilarInforme

	/**
	 * M�todo MuestraInforme
	 * 
	 * @throws excepcion IOException
	 */
	@Override
	public void showReport(String outputReport) throws IOException {
		super.showReport(REPORT_PEOPLE_FILTER_OUTPUT);

	}// cierre M�todo MuestraInforme

}// cierre clase ReportFilterConsult
