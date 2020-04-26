package gui.content.consult;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import controller.content.ControllerConsult;
import gui.resource.ResourcesGui;
import gui.resource.ResourcesGui.DIMENS;

/**
 * Archivo: ConsultContainerMainGui.java contiene la definici�n de la clase
 * ConsultContainerMainGui que extiende de JPanel.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class ConsultContainerMainGui extends JPanel {
	// declaraci�n de atributos
	private static final long serialVersionUID = 1L;

	private ConsultGui consultGui;
	private ConsultGuiView consultGuiView;

	// constructor sin par�metros
	public ConsultContainerMainGui() {
		createGui();

		new ControllerConsult(this);
	}// cierre constructor

	// M�todo que crea la vista
	private void createGui() {
		consultGui = new ConsultGui();
		consultGuiView = new ConsultGuiView();

		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		this.setLayout(new GridLayout(1, 2));
		this.setMaximumSize(new Dimension(DIMENS.getMinVerticalFrame(), DIMENS.getMinHorizontalFrame()));
		this.add(consultGui);
		this.add(consultGuiView);
	}// cierre m�todo createGui

	// getters
	public ConsultGui getConsultGui() {
		return consultGui;
	}

	public ConsultGuiView getConsultGuiView() {
		return consultGuiView;
	}

}// cierre clase ConsultContainerMainGui
