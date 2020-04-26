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
 * Archivo: ControllerPet.java contiene la definición de la clase ControllerPet
 * y extiende de la clase ControllerWindow.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */

public class ControllerPet extends ControllerWindow {
	// declaración de atributos
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

		// revisar sí la lista tiene datos

		if (listPet.sizeDtos() <= 0) {
			try {
				listPet.loadList();
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showMessage(e.getLocalizedMessage());
			}
		}

		reloadData();

		// ocultar el botón de borrar

		chengeVisibleBtnDelete(false);

	}// cierre constructor

	/**
	 * Método saveRegistry(). Registra o modifica una mascota.
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
	}// cierre método saveRegistry

	/**
	 * Método filter(). Llama el Método filterTable().
	 * 
	 * @return retorna un valor booleano
	 */
	@Override
	public boolean filter() {
		controllerViewPet.filterTable();
		return true;
	}// cierre método filter

	/**
	 * Método updateRegistry(). Actualiza la tabla de los registros de objeto
	 * DtoPet.
	 * 
	 * @return retorna un valor booleano.
	 * @exception Excepción de clase y de base de datos.
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
	}// cierre método update

	/**
	 * Método deleteRegistry(). Elimina un registro de la tabla DtoPet.
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
	}// cierre método deleteRegistry

	/**
	 * Método getDataOfView
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
	}// cierre método getDataOfView

	private boolean isValidDataForm() {

		String name = petGui.getTxtName().getText();
		if (!(name.length() >= ValidatorText.getMinLengthName() && name.length() <= ValidatorText.getMaxLengthName())) {
			Messages.showError("El campo nombre debe tener una longitud mínima de " + ValidatorText.getMinLengthName()
					+ " y máxima de " + ValidatorText.getMaxLengthName() + ".");
			return false;
		}
		if (!ValidatorText.validateFieldLetter(name)) {
			Messages.showError("El campo nombre sólo debe contener texto.");
			return false;
		}

		if (petGui.getDatePicker().getModel().getValue() == null) {
			Messages.showError("Se debe seleccionar una fecha.");
			return false;
		}

		String race = petGui.getTxtRace().getText();

		if (!(race.length() >= ValidatorText.getMinLengthRace() && race.length() <= ValidatorText.getMaxRace())) {
			Messages.showError("El campo raza debe tener una longitud mínima de " + ValidatorText.getMinLengthRace()
					+ " y máxima de " + ValidatorText.getMaxRace() + ".");
			return false;
		}

		if (!ValidatorText.validateFieldLetter(race)) {
			Messages.showError("El campo raza sólo debe contener texto.");
			return false;
		}

		String weightText = petGui.getTxtWeight().getText();
		try {
			Double.parseDouble(weightText);
		} catch (NumberFormatException e) {
			Messages.showError("El campo peso sólo permite números decimales");
			return false;
		}

		String color = petGui.getTxtColor().getText();
		if (!(color.length() >= ValidatorText.getMinLengthColor()
				&& color.length() <= ValidatorText.getMaxLengthColor())) {
			Messages.showError("El campo color debe tener una longitud mínima de " + ValidatorText.getMinLengthColor()
					+ " y máxima de " + ValidatorText.getMaxLengthColor() + ".");
			return false;
		}
		if (!ValidatorText.validateFieldLetter(color)) {
			Messages.showError("El campo color sólo debe contener texto.");
			return false;
		}

		return true;
	}

	/**
	 * Método setDataOfView Envía los datos a la vista.
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
	}// cierre método setDataOfView

	/**
	 * Método reloadData. Recarga los datos en la vista.
	 * 
	 * @return retorna un valor booleano
	 */
	@Override
	public boolean reloadData() {
		controllerViewPet.reloadData();
		return true;
	}// cierre método reloadData

	/**
	 * Método addListener Agrega los eventos a los botones
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
	}// cierre método addListener

	/**
	 * Método actionPerformed Controla las acciones que se realizan dentro de los
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
	}// cierre método actionPerformed

	/**
	 * Archivo:ControllerPet.java contiene la definición de la clase
	 * MauseClickedOnTable que extiende de MouseAdapter
	 * 
	 * @author
	 *
	 */
	private class MauseClickedOnTable extends MouseAdapter {
		// declaración de atributos
		private ControllerPet controllerPet;

		/**
		 * Constructor con parámetro
		 * 
		 * @param controllerPet objeto de tipo ControllerPet
		 */
		public MauseClickedOnTable(ControllerPet controllerPet) {
			this.controllerPet = controllerPet;
		}

		/**
		 * Método mouseClicked Controla los eventos del mouse
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
		}// cierre método mouseClicked
	}// cierre de clase MauseClickedOnTable

	/**
	 * Método getParametro
	 * 
	 * @return retorna un valor de tipo String
	 */
	public String getParametro() {
		return JOptionPane.showInputDialog(null, "Ingresa Párametro de Busqueda");
	}// cierre Método getParametro

	private void chengeVisibleBtnDelete(boolean status) {
		petGui.getBtnDelete().setVisible(status);
	}

}// cierre clase ControllerPet
