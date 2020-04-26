package mainRun;

import gui.MainFragment;
import gui.content.pet.PetContainerMainGui;

/**
 * Archivo: Main.java contiene la definición de la clase Main.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class Main {
	// Método main
	public static void main(String[] args) {

		// Material Desing.
		/*
		 * JFrame.setDefaultLookAndFeelDecorated(true);
		 * SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.BusinessBlueSteelSkin"
		 * );
		 */
		new MainFragment(new PetContainerMainGui());

	}// cierre método main
}// cierre clase Main
