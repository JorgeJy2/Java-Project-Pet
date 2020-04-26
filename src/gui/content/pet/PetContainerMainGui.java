package gui.content.pet;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

import controller.pet.ControllerPet;
import gui.resource.ResourcesGui;
import gui.resource.ResourcesGui.DIMENS;

/**
 * Archivo: PetContainerMainGui.java contiene la definición de la clase
 * PetContainerMainGui que extiende de JPanel.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class PetContainerMainGui extends JPanel {
	// declaraciï¿½n de atributos
	
	private static final long serialVersionUID = 1L;

	private PetGuiView petGuiView;
	private PetGui petGui;

	/**
	 * Constructor sin parámetros
	 */
	public PetContainerMainGui() {
		createGui();
		new ControllerPet(this);
	}// cierre constructor

	// mï¿½todo que crea la vista
	private void createGui() {
		this.petGuiView = new PetGuiView();
		this.petGui = new PetGui();
		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		this.setLayout(new GridLayout(1, 2));
		this.setMaximumSize(new Dimension(DIMENS.getMinVerticalFrame(), DIMENS.getMinHorizontalFrame()));
		this.add(this.petGui);
		this.add(this.petGuiView);
		
	}// cierre método createGui

	/**
	 * Método getCarGuiView
	 * 
	 * @return retorna un objeto CarGuiView
	 */
	public PetGuiView getPetGuiView() {
		return petGuiView;
	}// cierre mï¿½todo getCarGuiView

	/**
	 * Método getCarGui
	 * 
	 * @return retorna un objeto CarGui
	 */
	public PetGui getPetGui() {
		return petGui;
	}// cierre método getCarGui

}// cierre clase CarContainerMainGui
