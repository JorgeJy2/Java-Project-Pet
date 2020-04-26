package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

/**
 * Archivo: ControllerWindow.java contiene la definici�n de la clase abstracta
 * ControllerWindow que extiende de KeyAdapter e implementa de ActionListener,
 * ControllerInterface.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public abstract class ControllerWindow extends KeyAdapter implements ActionListener, ControllerInterface {

	// M�todo actionPerformed
	@Override
	public void actionPerformed(ActionEvent e) {

	}// cierre m�todo actionPerformed

}// cierre clase ControllerWindow
