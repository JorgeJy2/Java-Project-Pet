package dao;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Archivo: SaveErrosDao.java contiene la definici�n de la clase SaveErrosDao.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class SaveErrosDao {
	// declaraci�n de atributos
	private static final String PATH = "errors_report/err_dao.txt";

	/**
	 * M�todo saveErrors
	 * 
	 * @param e objeto de tipo Exception
	 */
	public static void saveErrors(Exception e) {
		FileOutputStream fErr;
		try {
			fErr = new FileOutputStream(PATH);
			PrintStream stdErr = new PrintStream(fErr);
			System.setErr(stdErr);
		} catch (Exception ex) {
			System.out.println("Ocurrio un error al querder guardar... ");
			System.out.println(ex.getLocalizedMessage());
		}
		e.printStackTrace();

	}// cierre m�todo saveErrors
}// cierre clase SaveErrorsDao
