package report.decoratorComponent.report;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.dto.DtoPet;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import report.Report;
import report.decoratorComponent.DecoradorReport;

/**
 * Archivo: ReportFilterPet.java contiene la definición de la clase
 * ReportFilterPet que extiende de DecoradorReport.
 * 
 * @author
 * @version 1.0
 *
 */
public class ReportFilterPet extends DecoradorReport {
	// declarar atributos
	private static final String REPORT_PEOPLE_FILTER = "filter_pet.jasper";
	private static final String REPORT_PEOPLE_FILTER_OUPUT = "reporte_mascotas_filtro.pdf";

	private Map<String, Object> parameters;

	/**
	 * Constructor con parámetros
	 * 
	 * @param controller objeto de tipo ControllerCar
	 * @param report     objeto de tipo Report
	 */
	public ReportFilterPet(Report report) {
		super(report);
	}// cierre constructor

	// Método obtenerParametros
	public void setParams(String field, String seach) {
		parameters = new HashMap<String, Object>();
		parameters.put("search", seach);
		parameters.put("field", field);

		String querySelect = "WHERE ";
		DtoPet.FIELDS fieldSelect = DtoPet.FIELDS.valueOf(field);
		if (seach.length() <= 0) {
			querySelect = "";
		} else {
			switch (fieldSelect) {
			case nombre:
				querySelect += "UPPER(" + field + ") LIKE '%" + seach.toUpperCase() + "%'";
				break;
			case fecha_nacimiento:
				querySelect += field + " = '" + seach + "'";
				break;
			case raza:
				querySelect += "UPPER(" + field + ") LIKE '%" + seach.toUpperCase() + "%'";
				break;
			case peso:
				querySelect += field + " = " + seach + "";
				break;
			case color:
				querySelect += "UPPER(" + field + ") LIKE '%" + seach.toUpperCase() + "%'";
				break;
			}
		}
		parameters.put("query-select", querySelect);

	}// cierre método obtenerParametros

	/**
	 * Método obtenerInforme
	 * 
	 * @throws JRException
	 */
	@Override
	public void getReport(String report) throws JRException {
		super.jasperPrint = JasperFillManager.fillReport("reports/" + REPORT_PEOPLE_FILTER, parameters, conexion);
	}// cierre MÃ©todo obtenerInforme

	/**
	 * Método compilarInforme
	 * 
	 * @throws excepcion JRException
	 */
	@Override
	public void complieReport(String outputReport) throws JRException {
		super.complieReport(REPORT_PEOPLE_FILTER_OUPUT);
	}// cierre método compilarInforme

	/**
	 * Método MuestraInforme
	 * 
	 * @throws excepcion IOException
	 */
	@Override
	public void showReport(String outputReport) throws IOException {
		super.showReport(REPORT_PEOPLE_FILTER_OUPUT);
	}// cierre MÃ©todo MuestraInforme

}// cierre clase ReportFilterPet
