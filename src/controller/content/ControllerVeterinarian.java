package controller.content;

import java.awt.Point;
import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controller.ControllerWindow;
import controller.validator.ValidatorText;
import gui.content.veterinarian.VeterinarianContainerMainGui;
import gui.dialogs.Messages;
import gui.resource.ResourcesGui.TEXTS;
import model.dto.DtoVeterinarian;
import model.dto.DtoVeterinarian.SEX;
import model.list.ListVeterinarian;
import model.list.interador.Interator;
import report.FormatReport;
import report.Report;
import report.decoratorComponent.report.ReportFilterVeterinarian;

/**
 * Archivo: ControllerVeterinarian.java contiene la definición de la clase
 * ControllerVeterinarian y extiende de la clase ControllerWindow.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class ControllerVeterinarian extends ControllerWindow {
//declaración de atributos
	private VeterinarianContainerMainGui view;
	private ListVeterinarian listVeterinarian;
	private DtoVeterinarian dtoVeterinarian;

	private int indexSelectOnView;

	private MauseClickedOnTable mauseClickedOnTable;

	private boolean makeNormalReport = true;

	/**
	 * Constructor ControllerPeople con parámetro
	 * 
	 * @param view objeto de tipo VeterinarianContainerMainGui
	 */
	public ControllerVeterinarian(VeterinarianContainerMainGui view) {
		this.view = view;
		listVeterinarian = ListVeterinarian.getInstance();

		addListener();
		if (listVeterinarian.sizeDtos() <= 0) {
			try {
				listVeterinarian.loadList();
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showError(e.getMessage());
			}
		}
		dtoVeterinarian = new DtoVeterinarian();
		reloadData();
		chengeVisibleBtnDelete(false);
		view.getVeterinarianView().getBtnRestoreFilter().setVisible(false);

	}// cierre constructor

	/**
	 * Método reloadDataList Obtiene los datos de la lista.
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean reloadDataList() {
		String[][] data = new String[listVeterinarian.sizeDtos()][5];
		Interator<DtoVeterinarian> inte = listVeterinarian.getAll();
		while (inte.hasNext()) {
			int pointerVeterinarian = inte.now();
			DtoVeterinarian veterinarian = inte.next();
			data[pointerVeterinarian][0] = veterinarian.getName();
			data[pointerVeterinarian][1] = veterinarian.getEmail();
			data[pointerVeterinarian][2] = veterinarian.getIdentification();
			data[pointerVeterinarian][3] = veterinarian.getSex().toString();
			data[pointerVeterinarian][4] = veterinarian.getDateBirth().toString();
		}
		view.getVeterinarianView().setModelTable(data);
		view.getVeterinarianView().getTable().addKeyListener(this);
		view.getVeterinarianView().getTable().addMouseListener(mauseClickedOnTable);
		return true;

	}// cierre Método reloadDataList

	/**
	 * Método saveRegistry Guarda o modifica los datos de DtoVeterinarian.
	 * 
	 * @return retorna un valor booleano
	 */
	@Override
	public boolean saveRegistry() {
		try {
			if (isValidForm()) {
				if (getDataOfView()) {
					if (listVeterinarian.add(dtoVeterinarian)) {
						reloadDataList();
						view.getVeterinarianView().getTable().setRowSelectionInterval(0, 0);
						view.getVeterinarianView().getScrollPaneTable().getViewport().setViewPosition(new Point(0, 0));
						// limpia el formulario para un nuevo registro
						setDataOfView(true);
						return true;
					} else {
						System.out.println("no se pudo acutalizar");
					}
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
	 * Método filter Filtrar los datos de la lista DtoVeterinarian.
	 * 
	 * @return retorna valor booleano
	 */
	@Override
	public boolean filter() {
		try {
			listVeterinarian.loadListFilter(view.getVeterinarianView().getCbxFilter(),
					view.getVeterinarianView().getTxtFilter().getText());
			String[][] data = new String[listVeterinarian.sizeDtos()][5];
			Interator<DtoVeterinarian> inte = listVeterinarian.getAll();
			while (inte.hasNext()) {
				int pointerVeterinarian = inte.now();
				DtoVeterinarian veterinarian = inte.next();
				data[pointerVeterinarian][0] = veterinarian.getName();
				data[pointerVeterinarian][1] = veterinarian.getEmail();
				data[pointerVeterinarian][2] = veterinarian.getIdentification();
				data[pointerVeterinarian][3] = veterinarian.getSex().toString();
				data[pointerVeterinarian][4] = veterinarian.getDateBirth().toString();
			}
			view.getVeterinarianView().setModelTable(data);
			view.getVeterinarianView().getTable().addKeyListener(this);
			view.getVeterinarianView().getTable().addMouseListener(mauseClickedOnTable);
			filter(true);
			return true;
		} catch (Exception e) {
			Messages.showError(e.getLocalizedMessage());
			filter(false);
			return false;
		}
	}// cierre método filter

	/**
	 * Método updateRegistry Actualiza un valor de tipo DtoVeterinarian.
	 * 
	 * @return retorna un valor de tipo booleano.
	 */
	@Override
	public boolean updateRegistry() {

		try {
			if (isValidForm()) {
				if (getDataOfView()) {
					if (listVeterinarian.update(dtoVeterinarian, indexSelectOnView)) {
						reloadData();
						setDataOfView(false);
						return true;
					} else {
						System.out.println("no se pudo editar");
					}
				} else {
					System.out.println("no se optuvo datos de view");
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());
		}
		return false;
	}// cierre métod updateRegistry

	/**
	 * Método deleteRegistry Elimina un registro de la lista DtoVeterinarian.
	 * 
	 * @return retorna un valor booleano.
	 */
	@Override
	public boolean deleteRegistry() {
		try {
			if (listVeterinarian.delete(indexSelectOnView)) {
				reloadData();
				setDataOfView(true);
				dtoVeterinarian = new DtoVeterinarian();
				chengeVisibleBtnDelete(false);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			Messages.showError(e.getMessage());
			return false;
		}
	}// cierre método deleteRegistry

	/**
	 * Método getDataOfView Obtiene los datos de la vista.
	 * 
	 * @return retorna un valor booleano.
	 */
	@Override
	public boolean getDataOfView() {
		try {
			dtoVeterinarian.setName(view.getVeterinarianGui().getTxtName().getText());
			dtoVeterinarian.setEmail(view.getVeterinarianGui().getTxtEmail().getText());
			dtoVeterinarian.setIdentification(view.getVeterinarianGui().getTxtCedula().getText());
			dtoVeterinarian.setSex((SEX) view.getVeterinarianGui().getCbxSex().getSelectedItem());
			dtoVeterinarian.setDateBirth(view.getVeterinarianGui().getDate());
			System.out.println(dtoVeterinarian);
			return true;
		} catch (Exception e) {
			System.out.println("ocurrio un error al oyener los datos");
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}

	/**
	 * Método setDataOfView Envia los datos a la vista.
	 * 
	 * @return retorna un valor booleano.
	 */
	@Override
	public boolean setDataOfView(boolean newRegistry) {
		try {
			if (newRegistry) {
				dtoVeterinarian = new DtoVeterinarian();
				dtoVeterinarian = new DtoVeterinarian();
				view.getVeterinarianGui().getTxtName().setText("");
				view.getVeterinarianGui().getTxtEmail().setText("");
				view.getVeterinarianGui().getTxtCedula().setText("");
				view.getVeterinarianGui().getCbxSex().setSelectedIndex(0);
				view.getVeterinarianGui().getDatePicker().getModel().setValue(null);
				view.getVeterinarianGui().getBtnAdd().setText("Agregar");
				view.getVeterinarianGui().getBtnDelete().setEnabled(false);
				chengeVisibleBtnDelete(false);
			} else {
				dtoVeterinarian = listVeterinarian.getList().get(indexSelectOnView);
				view.getVeterinarianGui().getTxtName().setText(dtoVeterinarian.getName());
				view.getVeterinarianGui().getTxtEmail().setText(dtoVeterinarian.getEmail());
				view.getVeterinarianGui().getTxtCedula().setText(dtoVeterinarian.getIdentification());
				view.getVeterinarianGui().getCbxSex().setSelectedItem(dtoVeterinarian.getSex());
				view.getVeterinarianGui().setDate(dtoVeterinarian.getDateBirth());
				view.getVeterinarianGui().getBtnAdd().setText(TEXTS.getTextEdit());
				view.getVeterinarianGui().getBtnDelete().setEnabled(true);
			}
			return true;
		} catch (Exception e) {
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}// cierre Método setDataOfView

	/**
	 * Método reloadData Recarga los datos a la vista.
	 * 
	 * @return retorna un valor booleano.
	 */
	@Override
	public boolean reloadData() {
		String[][] data = new String[listVeterinarian.sizeDtos()][5];
		Interator<DtoVeterinarian> interator = listVeterinarian.getAll();
		while (interator.hasNext()) {
			int pointerVeterinarian = interator.now();
			DtoVeterinarian veterinarian = interator.next();
			data[pointerVeterinarian][0] = veterinarian.getName();
			data[pointerVeterinarian][1] = veterinarian.getEmail();
			data[pointerVeterinarian][2] = veterinarian.getIdentification();
			data[pointerVeterinarian][3] = veterinarian.getSex().toString();
			data[pointerVeterinarian][4] = veterinarian.getDateBirth().toString();
		}
		view.getVeterinarianView().setModelTable(data);
		view.getVeterinarianView().getTable().addKeyListener(this);
		view.getVeterinarianView().getTable().addMouseListener(mauseClickedOnTable);

		return true;

	}// cierre método reloadData

	/**
	 * Método keyReleased Evento del teclado
	 */
	@Override
	public void keyReleased(KeyEvent d) {
		if (view.getVeterinarianView().getTable().getSelectedRows().length > 0) {
			indexSelectOnView = view.getVeterinarianView().getTable().getSelectedRow();
			dtoVeterinarian = listVeterinarian.getList().get(indexSelectOnView);
			dtoVeterinarian.setName(view.getVeterinarianView().getTable().getValueAt(indexSelectOnView, 0).toString());
			dtoVeterinarian.setEmail(view.getVeterinarianView().getTable().getValueAt(indexSelectOnView, 1).toString());
			dtoVeterinarian
					.setIdentification(view.getVeterinarianView().getTable().getValueAt(indexSelectOnView, 2).toString());
		}
		updateRegistry();
	}// cierre método keyReleased

	/**
	 * Método actionPerformed Realiza las acciones de cada evento de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.getVeterinarianView().getBtnFilter()) {
			filter();
		} else if (e.getSource() == view.getVeterinarianGui().getBtnCancel()) {
			chengeVisibleBtnDelete(false);
			view.getVeterinarianView().getTable().clearSelection();
			setDataOfView(true);
		} else if (e.getSource() == view.getVeterinarianGui().getBtnAdd()) {
			if (view.getVeterinarianGui().getBtnAdd().getText().equals(TEXTS.getTextEdit())) {
				if (updateRegistry()) {
					Messages.showMessage("Acutalizado");
				}
			} else {
				if (saveRegistry()) {
					Messages.showMessage("Guardado");
				}
			}
		} else if (e.getSource() == view.getVeterinarianGui().getBtnDelete()) {
			if (deleteRegistry()) {
				Messages.showMessage("Eliminado");
			}
		} else if (e.getSource() == view.getVeterinarianView().getBtnRestoreFilter()) {
			filter(false);
		} else if (e.getSource() == view.getVeterinarianView().getBtnReport()) {
			searchReport();
		}

	}// cierre método actionPerformed

	/**
	 * Cierre método searchReport carga el reporte "simple o avanzado".
	 */
	private void searchReport() {
		Thread threarReport = new Thread(() -> {
			Report format = null;
			if (makeNormalReport()) {
				format = new FormatReport();
			} else {
				ReportFilterVeterinarian filterVeterinarian =  new ReportFilterVeterinarian(new FormatReport());
				filterVeterinarian.setParams(view.getVeterinarianView().getCbxFilter(),
						view.getVeterinarianView().getTxtFilter().getText());
				format = filterVeterinarian;
				
			}
			if (format != null) {

				try {
					listVeterinarian.getReport(format);
				} catch (Exception e) {
					Messages.showError(e.getLocalizedMessage());
				}
			}

		});

		threarReport.start();

	}

	/**
	 * Método addListener Escuha los eventos de los botones.
	 * 
	 * @return retorna un valor booleano.
	 */
	@Override
	public boolean addListener() {
		try {
			view.getVeterinarianView().getBtnFilter().addActionListener(this);
			view.getVeterinarianView().getBtnReport().addActionListener(this);
			view.getVeterinarianView().getBtnRestoreFilter().addActionListener(this);
			view.getVeterinarianGui().getBtnAdd().addActionListener(this);
			view.getVeterinarianGui().getBtnDelete().addActionListener(this);
			view.getVeterinarianGui().getBtnCancel().addActionListener(this);
			view.getVeterinarianView().getTable().addMouseListener(new MauseClickedOnTable(this));
			return true;
		} catch (Exception e) {
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}// cierre Método addListener

	/**
	 * Método addScrollTable Agrega un scroll a la tabla.
	 */

//Inner Class Event to view
	/**
	 * Archivo: ControllerVeterinarian.java contiene la definición de la clase
	 * MauseClickedOnTable que extiende de MouseAdapter.
	 * 
	 * @author
	 * @version 1.0
	 *
	 */
	private class MauseClickedOnTable extends MouseAdapter {
		// declaración de atributos
		private ControllerVeterinarian controllerVeterinarian;

		/**
		 * Constructor con parámetro
		 * 
		 * @param controllerVeterinarian objeto de tipo ControllerPeople
		 */
		public MauseClickedOnTable(ControllerVeterinarian controllerVeterinarian) {
			this.controllerVeterinarian = controllerVeterinarian;
		}

		/**
		 * Método mouseClicked Controla los eventos del mouse
		 * 
		 * @param evnt objeto de tipo MouseEvent
		 */
		public void mouseClicked(MouseEvent evnt) {
			if (evnt.getClickCount() == 1) {
				chengeVisibleBtnDelete(true);
				this.controllerVeterinarian.indexSelectOnView = this.controllerVeterinarian.view.getVeterinarianView()
						.getTable().getSelectedRow();
				this.controllerVeterinarian.dtoVeterinarian = this.controllerVeterinarian.listVeterinarian.getList()
						.get(indexSelectOnView);
				this.controllerVeterinarian.setDataOfView(false);
			}
		}
	}// cierre clase MauseClickedOnTable

	/**
	 * Método getParametro
	 * 
	 * @return retorna un valor de tipo String
	 */
	public String getParametro() {
		return JOptionPane.showInputDialog(null, "Ingresa Digitos de una placa para búsqueda");
	}

	private void chengeVisibleBtnDelete(boolean status) {
		view.getVeterinarianGui().getBtnDelete().setVisible(status);
	}

	private boolean isValidForm() {

		String name = view.getVeterinarianGui().getTxtName().getText();
		if (!(name.length() >= ValidatorText.getMinLengthName() && name.length() <= ValidatorText.getMaxLengthName())) {
			Messages.showError("El campo nombre debe tener una longitud mínima de " + ValidatorText.getMinLengthName()
					+ " y máxima de " + ValidatorText.getMaxLengthName() + ".");
			return false;
		}

		if (!ValidatorText.validateFieldLetter(name)) {
			Messages.showError("El campo nombre sólo debe contener texto.");
			return false;
		}

		String email = view.getVeterinarianGui().getTxtEmail().getText();
		if (!(email.length() >= ValidatorText.getMinLengthEmail() && email.length() <= ValidatorText.getMaxLengthEmail())) {
			Messages.showError("El campo correo debe tener una longitud mínima de " + ValidatorText.getMinLengthEmail()
					+ " y máxima de " + ValidatorText.getMaxLengthEmail() + ".");
			return false;
		} 
		if (!ValidatorText.validateFielEmail(email)) {
			Messages.showError("El campo correo no tiene un formato correcto.");
			return false;
		}
		String identification = view.getVeterinarianGui().getTxtCedula().getText();
		if (!(identification.length() >= ValidatorText.getMinLengthIdentification()
				&& identification.length() <= ValidatorText.getMaxLengthIdentification())) {
			Messages.showError(
					"El campo cedula debe tener una longitud mínima de " + ValidatorText.getMinLengthIdentification()
							+ " y máxima de " + ValidatorText.getMaxLengthIdentification() + ".");
			return false;
		}

		if (!ValidatorText.validateAlphanumeric(identification)) {
			Messages.showError("El campo cedula no tiene un formato correcto.");
			return false;
		}

		if (view.getVeterinarianGui().getCbxSex().getSelectedItem() == null) {
			Messages.showError("Se debe seleccionar un tipo de sexo.");
			return false;
		}

		if (view.getVeterinarianGui().getDatePicker().getModel().getValue() == null) {
			Messages.showError("Se debe seleccionar una fecha.");
			return false;
		}

		return true;
	}

	private void filter(boolean isFilter) {
		makeNormalReport = !isFilter;
		if (isFilter) {
			view.getVeterinarianView().getBtnRestoreFilter().setVisible(true);
			view.getVeterinarianView().getBtnReport().setText(TEXTS.getTextMakeReportFilter());
		} else {
			view.getVeterinarianView().getTxtFilter().setText("");
			view.getVeterinarianView().getBtnRestoreFilter().setVisible(false);
			view.getVeterinarianView().getBtnReport().setText(TEXTS.getTextMakeReport());
			loadAll();

		}
	}

	private void loadAll() {
		try {
			listVeterinarian.loadList();
			reloadData();
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());

		}
	}

	private boolean makeNormalReport() {
		return makeNormalReport;
	}

}// cierre clase ControllerVeterinarian
