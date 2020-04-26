package controller.pet;

import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import controller.ControllerWindow;
import controller.validator.ValidatorText;
import gui.content.pet.PetContainerMainGui;
import gui.content.pet.PetGui;
import gui.content.pet.PetGuiView;
import gui.dialogs.Messages;
import gui.resource.ResourcesGui.TEXTS;
import model.dto.DtoPet;
import model.list.ListPet;
import model.list.Listable;

/**
 * Archivo: ControllerPet.java contiene la definici�n de la clase ControllerPet
 * y extiende de la clase ControllerWindow.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */

public class ControllerPet extends ControllerWindow {
	// declaraci�n de atributos
	private PetContainerMainGui viewPet;
	private PetGuiView petGuiView;
	private PetGui petGui;

	private DtoPet dtoPet;
	private int indexSelectOnView;

	private Listable<DtoPet> listPet;

	private ControllerViewPet controllerViewPet;

	/**
	 * Constructor ControllerPet que recibe un objeto de PetContainerMainGui.
	 * 
	 * @param viewPet objeto de tipo PetContainerMainGui.
	 */
	public ControllerPet(PetContainerMainGui viewPet) {
		this.viewPet = viewPet;

		// iniciar instancias
		listPet = ListPet.getInstance();
		petGuiView = this.viewPet.getPetGuiView();
		controllerViewPet = new ControllerViewPet(petGuiView, listPet);
		petGui = this.viewPet.getPetGui();
		dtoPet = new DtoPet();

		addListener();

		// revisar s� la lista tiene datos

		if (listPet.sizeDtos() <= 0) {
			try {
				listPet.loadList();
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showMessage(e.getLocalizedMessage());
			}
		}

		reloadData();

		// ocultar el bot�n de borrar

		chengeVisibleBtnDelete(false);

	}// cierre constructor

	/**
	 * M�todo saveRegistry(). Registra o modifica una mascota.
	 * 
	 * @return retorna valor booleano
	 */
	@Override
	public boolean saveRegistry() {
		try {
			if (isValidDataForm()) {
				if (getDataOfView()) {
					if (listPet.add(dtoPet)) {
						reloadData();
						controllerViewPet.selectOneRowTable();
						// limpia el formulario para un nuevo registro
						setDataOfView(true);
						return true;
					} else {
						System.out.println("no se pudo acutalizar");
					}
				} else {
					System.out.println("no se optuvo datos de view");
				}
			}

		} catch (ClassNotFoundException e) {
			Messages.showError(e.getLocalizedMessage());
		} catch (SQLException e) {
			Messages.showError(e.getLocalizedMessage());
		}
		return false;
	}// cierre m�todo saveRegistry

	/**
	 * M�todo filter(). Llama el M�todo filterTable().
	 * 
	 * @return retorna un valor booleano
	 */
	@Override
	public boolean filter() {
		controllerViewPet.filterTable();
		return true;
	}// cierre m�todo filter

	/**
	 * M�todo updateRegistry(). Actualiza la tabla de los registros de objeto
	 * DtoPet.
	 * 
	 * @return retorna un valor booleano.
	 * @exception Excepci�n de clase y de base de datos.
	 * 
	 */
	@Override
	public boolean updateRegistry() {
		try {
			if (isValidDataForm()) {
				if (getDataOfView()) {
					if (listPet.update(dtoPet, indexSelectOnView)) {
						controllerViewPet.updateTable(dtoPet, indexSelectOnView);
						setDataOfView(false);
						return true;
					}
				} else {
					System.out.println("no se optuvo datos de view");
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());
		}

		return false;
	}// cierre m�todo update

	/**
	 * M�todo deleteRegistry(). Elimina un registro de la tabla DtoPet.
	 * 
	 * @return retorna un valor booleano
	 * @exception Excepcion de clase y de base de datos.
	 */
	@Override
	public boolean deleteRegistry() {
		try {
			if (listPet.delete(indexSelectOnView)) {
				reloadData();
				setDataOfView(true);
				dtoPet = new DtoPet();
				chengeVisibleBtnDelete(false);
				return true;
			}
		} catch (Exception e) {
			Messages.showError(e.getMessage());
		}
		return false;
	}// cierre m�todo deleteRegistry

	/**
	 * M�todo getDataOfView
	 * 
	 * @return retona un valor booleano.
	 */

	@Override
	public boolean getDataOfView() {
		try {
			// necesita validaciones
			dtoPet.setName(petGui.getTxtName().getText());
			dtoPet.setDateBirth(petGui.getDate());
			dtoPet.setColor(petGui.getTxtColor().getText());
			dtoPet.setRace(petGui.getTxtRace().getText());
			dtoPet.setWeight(Double.parseDouble(petGui.getTxtWeight().getText()));
			return true;
		} catch (Exception e) {

			System.out.println(e);
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}// cierre m�todo getDataOfView

	private boolean isValidDataForm() {

		String name = petGui.getTxtName().getText();
		if (!(name.length() >= ValidatorText.getMinLengthName() && name.length() <= ValidatorText.getMaxLengthName())) {
			Messages.showError("El campo nombre debe tener una longitud m�nima de " + ValidatorText.getMinLengthName()
					+ " y m�xima de " + ValidatorText.getMaxLengthName() + ".");
			return false;
		}
		if (!ValidatorText.validateFieldLetter(name)) {
			Messages.showError("El campo nombre s�lo debe contener texto.");
			return false;
		}

		if (petGui.getDatePicker().getModel().getValue() == null) {
			Messages.showError("Se debe seleccionar una fecha.");
			return false;
		}

		String race = petGui.getTxtRace().getText();

		if (!(race.length() >= ValidatorText.getMinLengthRace() && race.length() <= ValidatorText.getMaxRace())) {
			Messages.showError("El campo raza debe tener una longitud m�nima de " + ValidatorText.getMinLengthRace()
					+ " y m�xima de " + ValidatorText.getMaxRace() + ".");
			return false;
		}

		if (!ValidatorText.validateFieldLetter(race)) {
			Messages.showError("El campo raza s�lo debe contener texto.");
			return false;
		}

		String weightText = petGui.getTxtWeight().getText();
		try {
			Double.parseDouble(weightText);
		} catch (NumberFormatException e) {
			Messages.showError("El campo peso s�lo permite n�meros decimales");
			return false;
		}

		String color = petGui.getTxtColor().getText();
		if (!(color.length() >= ValidatorText.getMinLengthColor()
				&& color.length() <= ValidatorText.getMaxLengthColor())) {
			Messages.showError("El campo color debe tener una longitud m�nima de " + ValidatorText.getMinLengthColor()
					+ " y m�xima de " + ValidatorText.getMaxLengthColor() + ".");
			return false;
		}
		if (!ValidatorText.validateFieldLetter(color)) {
			Messages.showError("El campo color s�lo debe contener texto.");
			return false;
		}

		return true;
	}

	/**
	 * M�todo setDataOfView Env�a los datos a la vista.
	 * 
	 * @return retorna un valor booleano
	 */
	@Override
	public boolean setDataOfView(boolean newRegistry) {
		try {
			if (newRegistry) {
				dtoPet = new DtoPet();
				petGui.getBtnAdd().setText(TEXTS.getTextAdd());
				petGui.getTxtName().setText("");
				petGui.getDatePicker().getModel().setValue(null);
				petGui.getTxtRace().setText("");
				petGui.getTxtWeight().setText("");
				petGui.getTxtColor().setText("");
				chengeVisibleBtnDelete(false);

			} else {
				dtoPet = listPet.getOne(indexSelectOnView);
				petGui.getTxtName().setText(dtoPet.getName());
				petGui.setDate(dtoPet.getDateBirth());
				petGui.getTxtRace().setText(dtoPet.getRace());
				// Agregar valicacion
				petGui.getTxtWeight().setText(dtoPet.getWeight() + "");
				petGui.getTxtColor().setText(dtoPet.getColor());
				petGui.getBtnAdd().setText(TEXTS.getTextEdit());
			}
			return true;
		} catch (Exception e) {
			System.out.println(e);
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}// cierre m�todo setDataOfView

	/**
	 * M�todo reloadData. Recarga los datos en la vista.
	 * 
	 * @return retorna un valor booleano
	 */
	@Override
	public boolean reloadData() {
		controllerViewPet.reloadData();
		return true;
	}// cierre m�todo reloadData

	/**
	 * M�todo addListener Agrega los eventos a los botones
	 * 
	 * @return retorna un valor booleano
	 */
	@Override
	public boolean addListener() {
		try {
			petGui.getBtnAdd().addActionListener(this);
			petGui.getBtnDelete().addActionListener(this);
			petGui.getBtnCancel().addActionListener(this);

			controllerViewPet.setClicTable(new MauseClickedOnTable(this));
			controllerViewPet.setKeyTable(this);

			return true;
		} catch (Exception e) {
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}// cierre m�todo addListener

	/**
	 * M�todo actionPerformed Controla las acciones que se realizan dentro de los
	 * eventos de los botones.
	 * 
	 * @param e objeto de tipo ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == petGui.getBtnCancel()) {
			chengeVisibleBtnDelete(false);
			controllerViewPet.clearSelection();
			setDataOfView(true);
		} else if (e.getSource() == petGui.getBtnAdd()) {
			if (petGui.getBtnAdd().getText().equals(TEXTS.getTextEdit())) {
				if (updateRegistry()) {
					setDataOfView(true);
					Messages.showMessage("Acutalizado");
				}
			} else {
				if (saveRegistry()) {
					Messages.showMessage("Guardado");
				}
			}
		} else if (e.getSource() == petGui.getBtnDelete()) {
			if (deleteRegistry()) {
				Messages.showMessage(" Eliminado");
			}
		}
	}// cierre m�todo actionPerformed

	/**
	 * Archivo:ControllerPet.java contiene la definici�n de la clase
	 * MauseClickedOnTable que extiende de MouseAdapter
	 * 
	 * @author
	 *
	 */
	private class MauseClickedOnTable extends MouseAdapter {
		// declaraci�n de atributos
		private ControllerPet controllerPet;

		/**
		 * Constructor con par�metro
		 * 
		 * @param controllerPet objeto de tipo ControllerPet
		 */
		public MauseClickedOnTable(ControllerPet controllerPet) {
			this.controllerPet = controllerPet;
		}

		/**
		 * M�todo mouseClicked Controla los eventos del mouse
		 * 
		 * @param evnt objeto de tipo MouseEvent
		 */
		public void mouseClicked(MouseEvent evnt) {
			if (evnt.getClickCount() == 1) {
				chengeVisibleBtnDelete(true);
				this.controllerPet.indexSelectOnView = this.controllerPet.controllerViewPet.getSelectedRow();
				dtoPet = listPet.getOne(indexSelectOnView);
				this.controllerPet.setDataOfView(false);
			}
		}// cierre m�todo mouseClicked
	}// cierre de clase MauseClickedOnTable

	/**
	 * M�todo getParametro
	 * 
	 * @return retorna un valor de tipo String
	 */
	public String getParametro() {
		return JOptionPane.showInputDialog(null, "Ingresa P�rametro de Busqueda");
	}// cierre M�todo getParametro

	private void chengeVisibleBtnDelete(boolean status) {
		petGui.getBtnDelete().setVisible(status);
	}

}// cierre clase ControllerPet
