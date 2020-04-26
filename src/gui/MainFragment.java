package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ControllerWindowAdapter;
import gui.content.consult.ConsultContainerMainGui;
import gui.content.pet.PetContainerMainGui;
import gui.content.veterinarian.VeterinarianContainerMainGui;
import gui.resource.ResourcesGui;
import gui.resource.ResourcesGui.COLOR;
import gui.resource.ResourcesGui.DIMENS;
import gui.resource.ResourcesGui.GRAPHIC;

/**
 * Archivo: MainFragment.java contien la definición de la clase MainFragment que
 * extiende de JFrame.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class MainFragment extends JFrame {
	// declaración de atributos

	private static final long serialVersionUID = 1L;
	// declaración de componentes
	private JPanel contentPanel;
	private JPanel panelChange;

	private JPanel panelMenu;
	private JPanel conteint;

	/**
	 * Constructor con parámetro
	 * 
	 * @param contentPanel objeto de tipo JPanel
	 */
	public MainFragment(JPanel contentPanel) {
		this.contentPanel = contentPanel;
		this.setLocationRelativeTo(null);
		createGui();
	}// cierre constructor

	// Método que crea la vista
	private void createGui() {
		this.setLayout(new BorderLayout());

		// ================== TITLE end ==================

		panelMenu = new JPanel();
		panelMenu.setLayout(new BorderLayout());
		panelMenu.setBorder(ResourcesGui.BORDER.getBorderConteinerMain());

		JButton btnPet = new JButton();
		btnPet.setIcon(new ImageIcon(GRAPHIC.getImgPet()));
		JButton btnVeterinarian = new JButton("");
		btnVeterinarian.setIcon(new ImageIcon(GRAPHIC.getImgVeterinarian()));
		JButton btnConsult = new JButton("");
		btnConsult.setIcon(new ImageIcon(GRAPHIC.getImgQueryPet()));

		btnPet.addActionListener((ActionEvent arg0) -> {
			changePanel(new PetContainerMainGui());
		});

		btnVeterinarian.addActionListener((ActionEvent arg0) -> {

			changePanel(new VeterinarianContainerMainGui());
		});

		btnConsult.addActionListener((ActionEvent arg0) -> {
			changePanel(new ConsultContainerMainGui());
		});

		btnPet.setBackground(ResourcesGui.COLOR.getSecondColor());
		btnPet.setBorder(ResourcesGui.BORDER.getBorderBtnAcept());
		btnPet.setForeground(ResourcesGui.COLOR.getSecondColor());

		btnVeterinarian.setBackground(ResourcesGui.COLOR.getSecondColor());
		btnVeterinarian.setBorder(ResourcesGui.BORDER.getBorderBtnAcept());
		btnVeterinarian.setForeground(ResourcesGui.COLOR.getSecondColor());

		btnConsult.setBackground(ResourcesGui.COLOR.getSecondColor());
		btnConsult.setBorder(ResourcesGui.BORDER.getBorderBtnAcept());
		btnConsult.setForeground(ResourcesGui.COLOR.getSecondColor());

		JPanel menuTitle = new JPanel();
		menuTitle.setLayout(new GridLayout(0, 1));
		JLabel titleSubTitle = new JLabel("HAPPY PETS (Veterinario)");
		titleSubTitle.setFont(ResourcesGui.FONT.geFontTitle());
		menuTitle.add(titleSubTitle);

		panelMenu.add(menuTitle, BorderLayout.PAGE_START);

		JPanel panelOption = new JPanel();
		panelOption.setLayout(new GridLayout(1, 0, 30, 10));
		panelOption.add(btnPet);
		panelOption.add(btnVeterinarian);
		panelOption.add(btnConsult);

		panelMenu.add(panelOption, BorderLayout.CENTER);

		conteint = new JPanel();
		conteint.setLayout(new BorderLayout());
		conteint.add(panelMenu, BorderLayout.PAGE_START);
		conteint.setBackground(COLOR.getSecondColor());

		panelChange = new JPanel();
		panelChange.setLayout(new BorderLayout());
		panelChange.add(contentPanel, BorderLayout.CENTER);
		conteint.add(panelChange, BorderLayout.CENTER);

		this.setMinimumSize(new Dimension(DIMENS.getMinVerticalFrame(), DIMENS.getMinHorizontalFrame()));
		this.add(conteint, BorderLayout.CENTER);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		this.addWindowListener(new ControllerWindowAdapter());

	}// cierre método createGui

	/**
	 * Método changePanel
	 * 
	 * @param panel objeto de tipo JPanel
	 */
	private void changePanel(JPanel panel) {
		panelChange.removeAll();
		panelChange.add(panel);
		panelChange.revalidate();
		panelChange.repaint();
	}// cierre método changePanel

}// cierre clase MainFragment
