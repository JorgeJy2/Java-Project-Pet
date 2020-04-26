package gui.content.veterinarian;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import controller.content.ControllerVeterinarian;
import gui.resource.ResourcesGui;
import gui.resource.ResourcesGui.DIMENS;

/**
 * Archivo: VeterinarianContainerMainGui.java contiene la definición de la clase
 * VeterinarianContainerMainGui que extiende de JPanel
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class VeterinarianContainerMainGui extends JPanel {
	// declaración de atributos
	private static final long serialVersionUID = 1L;

	private VeterinarianGuiView veterinarianGuiView;
	private VeterinarianGui veterinarianGui;

	// constructor sin parámetros
	public VeterinarianContainerMainGui() {
		createGui();
		new ControllerVeterinarian(this);
	}// cierre constructor

	// Método que crea la vista
	private void createGui() {
		this.veterinarianGuiView = new VeterinarianGuiView();
		this.veterinarianGui = new VeterinarianGui();
		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		this.setLayout(new GridLayout(1, 2));
		this.setMaximumSize(new Dimension(DIMENS.getMinVerticalFrame(), DIMENS.getMinHorizontalFrame()));
		this.add(this.veterinarianGui);
		this.add(this.veterinarianGuiView);
	}// cierre método createGui

	// getters
	public VeterinarianGuiView getVeterinarianView() {
		return veterinarianGuiView;
	}

	public VeterinarianGui getVeterinarianGui() {
		return veterinarianGui;
	}

}// cierre clase VeterinarianContainerMainGui
