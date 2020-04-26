package report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

/**
 * Archivo: FormatReport.java contiene la definición de la clase abstracta
 * FormatReport que implementa de Report.
 * 
 * @author
 * @version 1.0
 *
 */
public class FormatReport implements Report {

	// declaració de atributos
	protected JasperPrint jasperPrint;
	protected String name;
	protected Connection conexion;
	protected JRPdfExporter exportar;
	protected JasperPrint jasperPrintWindow;
	protected SimplePdfExporterConfiguration conf;

	/**
	 * Método setConexion
	 * 
	 * @param conexion objeto de tipo Connection
	 */
	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}// cierre método setConexion

	
	/**
	 * Método obtenerInforme
	 * 
	 * @throws excepcion JRException
	 */
	public void getReport(String report) throws JRException {
		jasperPrint = JasperFillManager.fillReport("reports/" + report, null, conexion);
	}// cierre método obtenerInforme

	/**
	 * Método compilarInforme
	 * 
	 * @throws excepcion JRException
	 */
	public void complieReport(String reportOuyput) throws JRException {
		exportar = new JRPdfExporter();
		exportar.setExporterInput(new SimpleExporterInput(jasperPrint));
		exportar.setExporterOutput(new SimpleOutputStreamExporterOutput("reports/"+reportOuyput));
		conf = new SimplePdfExporterConfiguration();
		exportar.setConfiguration(conf);
		exportar.exportReport();
	}// cierre método compilarInforme

	/**
	 * Método MuestraInforme
	 * 
	 * @throws excepcion JRException
	 */
	public void showReport(String reportOuyput) throws IOException {
		File path = new File("reports/"+reportOuyput);
		Desktop.getDesktop().open(path);
	}// cierre método MuestraInforme
	
}// cierre clase FormatReport
