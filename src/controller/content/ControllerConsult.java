package controller.content;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.ControllerWindow;
import controller.validator.ValidatorText;
import gui.content.consult.ConsultContainerMainGui;
import gui.content.consult.ConsultGui;
import gui.content.consult.ConsultGuiView;
import gui.dialogs.Messages;
import gui.resource.ResourcesGui.TEXTS;
import model.dto.DtoVeterinarian;
import model.dto.DtoConsult.STATUS;
import model.dto.DtoItemCombobx;
import model.dto.DtoConsult;
import model.dto.DtoPet;
import model.list.ListVeterinarian;
import model.list.ListPet;
import model.list.ListConsult;
import model.list.interador.Interator;

import report.FormatReport;
import report.Report;
import report.decoratorComponent.report.ReportFilterConsult;
import report.decoratorComponent.report.ReportMonthConsult;

/**
 * Archivo: ControllerConsult.java contiene la definición de la clase
 * ControllerConsult que extiende de ControllerWindow.
 * 
 * @author JorgeJy2
 * 
 * @version 1.0
 *
 */
public class ControllerConsult extends ControllerWindow {
	// declaración de atributos
	// Messages

	private static final String ERROR_SAVE = "No se pudo agregar, consulta el archivo de errores";
	private static final String ERROR_DELETE = "No se pudo eliminar, consulta el archivo de errores";

	// GUI
	private ConsultGui _consultGui;
	private ConsultGuiView _consultGuiView;

	// Model
	private ListConsult _listConsult;
	private ListPet _listPet;
	private ListVeterinarian _listVeterinarian;

	private DtoConsult dtoConsult;
	private int actualTicketSelect;

	private boolean makeNormalReport = true;

	/**
	 * Constructor con parámetro
	 * 
	 * @param containerMainGui objeto de tipo ConsultContainerMainGui
	 */
	public ControllerConsult(ConsultContainerMainGui containerMainGui) {

		this._consultGui = containerMainGui.getConsultGui();
		this._consultGuiView = containerMainGui.getConsultGuiView();

		_listConsult = ListConsult.getInstance();

		_listPet = ListPet.getInstance();
		_listVeterinarian = ListVeterinarian.getInstance();

		addListener();

		try {
			_listConsult.loadList();
			reloadData();

			_listPet.loadList();
			loadPeetInList();

			_listVeterinarian.loadList();
			loadVeterinarianInList();

			if (_listPet.sizeDtos() <= 0) {
				Messages.showError(
						"No se han registrado mascotas aún, registre la mascota para realizar una consulta.");
			}

			if (_listVeterinarian.sizeDtos() <= 0) {
				Messages.showError(
						"No se han registrado veterinarios aún, registre un veterinario para realizar una consulta.");
			}

		} catch (ClassNotFoundException | SQLException e) {
			Messages.showMessage(e.getLocalizedMessage());
		}

		dtoConsult = new DtoConsult();
		chengeVisibleBtnDelete(false);

		_consultGuiView.getBtnRestoreFilter().setVisible(false);

	}// cierre constructor

	/**
	 * Método saveRegistry guarda los valores de la consulta
	 * 
	 * @return retorna valor booleano
	 */
	@Override
	public boolean saveRegistry() {
		try {
			if (isValidForm()) {
				if (getDataOfView()) {
					if (_listConsult.add(dtoConsult)) {
						_listConsult.loadList();
						reloadData();
						_consultGuiView.selectOneRowTable();
						// limpia el formulario para un nuevo registro
						setDataOfView(true);
						return true;
					} else {
						Messages.showError(ERROR_SAVE);
					}
				} else {
					Messages.showError("No sé pudo obtener datos de la vista");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());
		}
		return false;
	}// cierre método saveRegistry

	/**
	 * Método filter Filtra la lista de las consultas
	 * 
	 * @return retorna valor booleano
	 */
	@Override
	public boolean filter() {
		try {
			_listConsult.loadListFilter(_consultGuiView.getCbxFilter(), _consultGuiView.getTxtFilter().getText());
			reloadData();
			filter(true);
		} catch (Exception e) {
			Messages.showError(e.getLocalizedMessage());
			filter(false);
		}
		return false;
	}// cierre método filter

	/**
	 * MÃ©todo updateRegistry Actualiza los datos de Consulta
	 * 
	 * @return retorna valor booleano
	 */

	private void loadPeetInList() {

		Interator<DtoPet> interator = _listPet.getAll();
		while (interator.hasNext()) {
			DtoPet pet = interator.next();
			_consultGui.getCbxPet().addItem(new DtoItemCombobx(pet.getId(), pet.getName()));
		}

	}

	private void loadVeterinarianInList() {

		Interator<DtoVeterinarian> interator = _listVeterinarian.getAll();
		while (interator.hasNext()) {
			DtoVeterinarian dto = interator.next();
			_consultGui.getCbxVeterinarian().addItem(new DtoItemCombobx(dto.getId(), dto.getName()));
		}

	}

	@Override
	public boolean updateRegistry() {
		try {
			if (isValidForm()) {

				if (getDataOfView()) {
					if (_listConsult.update(dtoConsult, actualTicketSelect)) {
						_listConsult.loadList();
						reloadData();
						setDataOfView(false);
						return true;
					} else {
						System.out.println("no se pudo editar");
					}
				}

			}

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
			Messages.showError(e.getLocalizedMessage());
		}

		return false;

	}// cierre método updateRegistry

	/**
	 * Método deleteRegistry Elimina un registro de Consulta
	 * 
	 * @return retorna un valor de tipo booleano
	 */
	@Override
	public boolean deleteRegistry() {
		if (dtoConsult != null) {
			try {
				if (_listConsult.delete(actualTicketSelect)) {
					_listConsult.loadList();
					reloadData();
					setDataOfView(true);
					dtoConsult = new DtoConsult();
					chengeVisibleBtnDelete(false);
				} else {
					Messages.showError(ERROR_DELETE);
				}
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showError(e.getLocalizedMessage());
			}
		}
		return false;
	}// cierre método deleteRegistry

	// getter y setter
	@Override
	public boolean getDataOfView() {

		try {
			dtoConsult.setIdVeterinarian(
					((DtoItemCombobx) _consultGui.getCbxVeterinarian().getSelectedItem()).getId());
			dtoConsult.setIdPet(((DtoItemCombobx) _consultGui.getCbxPet().getSelectedItem()).getId());
			dtoConsult.setDate(_consultGui.getDate());
			double cost = Double.parseDouble(_consultGui.getTxtCost().getText());
			dtoConsult.setCost(cost);

			dtoConsult.setDescription(_consultGui.getTxtDescription().getText());
			dtoConsult.setStatus((STATUS) this._consultGui.getCbxStatus().getSelectedItem());

			System.out.println(dtoConsult);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ocurrio un error al obtener los datos de la vista");
			System.out.println(e);
		}

		return false;
	}

	@Override
	public boolean setDataOfView(boolean newRegistry) {
		if (newRegistry) {
			dtoConsult = new DtoConsult();
			if (_listVeterinarian.sizeDtos() > 0) {
				_consultGui.getCbxVeterinarian().setSelectedIndex(0);
				;
			}
			if (_listPet.sizeDtos() > 0) {
				_consultGui.getCbxPet().setSelectedIndex(0);
				;
			}
			_consultGui.getDatePicker().getModel().setValue(null);
			_consultGui.getTxtCost().setText("");
			_consultGui.getTxtDescription().setText("");
			_consultGui.getCbxStatus().setSelectedIndex(0);
			chengeVisibleBtnDelete(false);
			_consultGui.getBtnAdd().setText(TEXTS.getTextAdd());
		} else {

			Interator<DtoPet> interatorPet = _listPet.getAll();
			while (interatorPet.hasNext()) {
				DtoPet dtoPet = interatorPet.next();
				if (dtoConsult.getIdPet() == dtoPet.getId()) {
					_consultGui.getCbxPet().setSelectedIndex(interatorPet.now() - 1);
				}
			}

			Interator<DtoVeterinarian> interatorVeterinarian = _listVeterinarian.getAll();
			while (interatorVeterinarian.hasNext()) {
				DtoVeterinarian veterinarian = interatorVeterinarian.next();
				if (dtoConsult.getIdVeterinarian() == veterinarian.getId()) {
					_consultGui.getCbxVeterinarian().setSelectedIndex(interatorVeterinarian.now() - 1);
				}
			}
			_consultGui.setDate(dtoConsult.getDate());
			_consultGui.getTxtCost().setText(dtoConsult.getCost() + " ");
			_consultGui.getCbxStatus().setSelectedItem(dtoConsult.getStatus());
			_consultGui.getTxtDescription().setText(dtoConsult.getDescription());

			_consultGui.getBtnAdd().setText(TEXTS.getTextEdit());
		}

		return true;
	}

	/**
	 * Método reloadData Recarga los datos a la vista
	 * 
	 * @return retorna un valor booleano
	 */
	@Override
	public boolean reloadData() {

		String[][] data = new String[_listConsult.sizeDtos()][6];
		Interator<DtoConsult> interator = _listConsult.getAll();
		while (interator.hasNext()) {
			int pointer = interator.now();
			DtoConsult dtoConsult = interator.next();
			data[pointer][0] = dtoConsult.getPetName();
			data[pointer][1] = dtoConsult.getVeterinarianEmail();
			data[pointer][2] = dtoConsult.getDate().toString();
			data[pointer][3] = dtoConsult.getDescription();
			data[pointer][4] = dtoConsult.getCost() + "";
			data[pointer][5] = dtoConsult.getStatus().toString();
		}
		_consultGuiView.setModelTable(data);
		return true;
	}// cierre método reloadData

	/**
	 * Método actionPerformed Realiza las acciones de cada evento de los botones.
	 * 
	 * @param e objeto de tipo ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == _consultGui.getBtnAdd()) {
			if (_consultGui.getBtnAdd().getText().equals(TEXTS.getTextEdit())) {
				if (updateRegistry()) {
					Messages.showMessage("Acutalizado");
				}
			} else {
				if (saveRegistry()) {
					setDataOfView(true);
					Messages.showMessage("Guardado");
				}
			}
		} else if (e.getSource() == _consultGui.getBtnCancel()) {
			chengeVisibleBtnDelete(false);
			_consultGui.resetBtnAdd();
			_consultGuiView.getTable().clearSelection();
			setDataOfView(true);

		} else if (e.getSource() == _consultGui.getBtnDelete()) {
			deleteRegistry();
		} else if (e.getSource() == _consultGuiView.getBtnFilter()) {
			filter();
		} else if (e.getSource() == _consultGuiView.getBtnRestoreFilter()) {
			filter(false);
		} else if (e.getSource() == _consultGuiView.getBtnReport()) {
			searchReport(false);
		} else if(e.getSource() == _consultGuiView.getBtnSpecialReport()) {
			if(_listConsult.sizeDtos() > 0) {				
				searchReport(true);
			} else {
				Messages.showError("Es necesario contar con consultas registradas para realizar las estadísticas.");
			}
		} 
	}// cierre método actionPerformed

	/**
	 * Método addListener Escuha los eventos de los botones.
	 * 
	 * @return retorna un valor booleano.
	 */
	@Override
	public boolean addListener() {
		try {
			// Butons
			_consultGui.getBtnAdd().addActionListener(this);
			_consultGui.getBtnCancel().addActionListener(this);
			_consultGui.getBtnDelete().addActionListener(this);

			_consultGuiView.getBtnFilter().addActionListener(this);
			_consultGuiView.getBtnReport().addActionListener(this);
			_consultGuiView.getBtnRestoreFilter().addActionListener(this);
			_consultGuiView.getBtnSpecialReport().addActionListener(this);

			// Table
			_consultGuiView.getTable().addKeyListener(this);
			_consultGuiView.getTable().addMouseListener(new MauseClickedOnTableTicke(this));

			return true;
		} catch (Exception e) {
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}// cierre método addListener

	// GUI

	/**
	 * Archivo: .java contiene la definición de la clase ControllerConsult que
	 * extiende de MouseAdapter
	 * 
	 * @author
	 * @version 1.0
	 *
	 */
	private class MauseClickedOnTableTicke extends MouseAdapter {
		// declaración de atributo
		private ControllerConsult controllerConsult;

		/**
		 * Constructor con parámetro
		 * 
		 * @param controllerConsult objeto de tipo ControllerConsult
		 */
		public MauseClickedOnTableTicke(ControllerConsult controllerConsult) {
			this.controllerConsult = controllerConsult;
		}// cierre constructor

		/**
		 * Método mouseClicked Controla los eventos del mouse
		 * 
		 * @param evnt objeto de tipo MouseEvent
		 */
		public void mouseClicked(MouseEvent evnt) {
			if (evnt.getClickCount() == 1) {
				controllerConsult.chengeVisibleBtnDelete(true);
				int contador = controllerConsult._consultGuiView.getTable().getSelectedRow();
				controllerConsult.actualTicketSelect = contador;
				controllerConsult.dtoConsult = _listConsult.getOne(contador);
				this.controllerConsult.setDataOfView(false);
			}
		}
	}

	private void searchReport(boolean special) {
		Thread threarReport = new Thread(() -> {
			Report format = null;
			
			if(special) {
				ReportMonthConsult filterConsult = new ReportMonthConsult(new FormatReport());
				format = filterConsult;
			} else  {
				if (makeNormalReport()) {
					format = new FormatReport();
				} else {
					  ReportFilterConsult filterConsult = new ReportFilterConsult(new
					  FormatReport()); filterConsult.setParams(_consultGuiView.getCbxFilter(),
					  _consultGuiView.getTxtFilter().getText()); format = filterConsult;
				}
			}
			
			if (format != null) {
				try {
					_listConsult.getReport(format);
				} catch (Exception e) {
					Messages.showError(e.getLocalizedMessage());
				}
			}
		});

		threarReport.start();

	}

	public String getParametro() {
		String[] reportOption = { "En espera", "Pagado", "Perdido" };
		String index = (String) JOptionPane.showInputDialog(new JFrame(), "¿Qué reporte deseas ver?",
				"Formato de Reporte", JOptionPane.QUESTION_MESSAGE, null, reportOption, reportOption[0]);

		return index;
	}

	private void chengeVisibleBtnDelete(boolean status) {
		_consultGui.getBtnDelete().setVisible(status);
	}

	/**
	 * Método isValidForm Valida si la información es correcta
	 * 
	 * @return retorna valor booleano
	 */

	private boolean isValidForm() {
		if (_consultGui.getCbxPet().getSelectedItem() == null) {
			Messages.showError("Se debe seleccionar una mascota.");
			return false;
		}

		if (_consultGui.getCbxVeterinarian().getSelectedItem() == null) {
			Messages.showError("Se debe seleccionar una veterinario.");
			return false;
		}

		if (_consultGui.getDatePicker().getModel().getValue() == null) {
			Messages.showError("Se debe seleccionar una fecha.");
			return false;
		}

		String cost = _consultGui.getTxtCost().getText();
		try {
			Double.parseDouble(cost);
		} catch (NumberFormatException e) {
			Messages.showError("El campo costo sólo permite números decimales");
			return false;
		}

		if (_consultGui.getCbxStatus().getSelectedItem() == null) {
			Messages.showError("Se debe seleccionar un estatus.");
			return false;
		}

		String description = _consultGui.getTxtDescription().getText();

		if (!(description.length() >= ValidatorText.getMinLengthDescription()
				&& description.length() <= ValidatorText.getMaxLengthDescription())) {
			Messages.showError(
					"El campo descripción debe tener una longitud mínima de " + ValidatorText.getMinLengthDescription()
							+ " y máxima de " + ValidatorText.getMaxLengthDescription() + ".");
			return false;
		}

		if (!ValidatorText.validateFieldLetter(description)) {
			Messages.showError("El campo descripción sólo debe contener texto.");
			return false;
		}
		return true;
	}

	private void filter(boolean isFilter) {
		makeNormalReport = !isFilter;
		if (isFilter) {
			_consultGuiView.getBtnRestoreFilter().setVisible(true);
			_consultGuiView.getBtnReport().setText(TEXTS.getTextMakeReportFilter());
		} else {
			_consultGuiView.getTxtFilter().setText("");
			_consultGuiView.getBtnRestoreFilter().setVisible(false);
			_consultGuiView.getBtnReport().setText(TEXTS.getTextMakeReport());
			loadAll();

		}
	}

	private void loadAll() {
		try {
			_listConsult.loadList();
			reloadData();
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());

		}
	}

	private boolean makeNormalReport() {
		return makeNormalReport;
	}

}// cierre clase ControllerConsult
