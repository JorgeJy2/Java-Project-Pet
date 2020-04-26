package report;

import java.io.IOException;
import java.sql.Connection;
import net.sf.jasperreports.engine.JRException;

/**
 * Archivo: Report.java contiene la definici�n de la interface Report.
 * 
 * @author
 * @version 1.0
 *
 */
public interface Report {
	// M�todos de la interfaz

	public void setConexion(Connection conexion);

	public void getReport(String report) throws JRException;

	public void complieReport(String outputReport) throws JRException;

	public void showReport(String outputReport) throws IOException;

}// cierre interface Report
