package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import connection.PoolConnection;
import gui.dialogs.Messages;

/**
 * Archivo: ControllerWindowAdapter.java contiene la definici�n de la clase
 * ControllerWindowAdapter y extiende de WindowAdapter.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class ControllerWindowAdapter extends WindowAdapter {
	/**
	 * M�todo windowClosing Cierra la ventana
	 * 
	 * @param arg0 objeto de tipo WindowEvent
	 */
	public void windowClosing(WindowEvent arg0) {
		try {
			PoolConnection.getInstancePool().closePoolConnection();
		} catch (SQLException e) {
			Messages.showError(e.getLocalizedMessage());
		}
		System.exit(0);
	}// cierre m�todo windowClosing
}// cierre clase ControllerWindowAdapter