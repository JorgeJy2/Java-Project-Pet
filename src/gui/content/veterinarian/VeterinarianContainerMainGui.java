package gui.content.veterinarian;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import controller.content.ControllerVeterinarian;
import gui.resource.ResourcesGui;
import gui.resource.ResourcesGui.DIMENS;

/**
 * Archivo: VeterinarianContainerMainGui.java contiene la definici�n de la clase
 * VeterinarianContainerMainGui que extiende de JPanel
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class VeterinarianContainerMainGui extends JPanel {
	// declaraci�n de atributos
	private static final long serialVersionUID = 1L;

	private VeterinarianGuiView veterinarianGuiView;
	private VeterinarianGui veterinarianGui;

	// constructor sin par�metros
	public VeterinarianContainerMainGui() {
		createGui();
		new ControllerVeterinarian(this);
	}// cierre constructor

	// M�todo que crea la vista
	private void createGui() {
		this.veterinarianGuiView = new VeterinarianGuiView();
		this.veterinarianGui = new VeterinarianGui();
		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		this.setLayout(new GridLayout(1, 2));
		this.setMaximumSize(new Dimension(DIMENS.getMinVerticalFrame(), DIMENS.getMinHorizontalFrame()));
		this.add(this.veterinarianGui);
		this.add(this.veterinarianGuiView);
	}// cierre m�todo createGui

	// getters
	public VeterinarianGuiView getVeterinarianView() {
		return veterinarianGuiView;
	}

	public VeterinarianGui getVeterinarianGui() {
		return veterinarianGui;
	}

}// cierre clase VeterinarianContainerMainGui
