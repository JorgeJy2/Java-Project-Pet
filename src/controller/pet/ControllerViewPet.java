package controller.pet;

import java.awt.Point;
import java.awt.event.ActionEvent;

import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

import java.sql.SQLException;

import gui.content.pet.PetGuiView;
import gui.dialogs.Messages;
import gui.resource.ResourcesGui.TEXTS;
import model.dto.DtoPet;

import model.list.ListPet;
import model.list.Listable;
import model.list.interador.Interator;
import report.FormatReport;
import report.decoratorComponent.report.ReportFilterPet;

/**
 * Archivo: ControllerViewPet.java contiene la definición de la clase
 * ControllerViewPet.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class ControllerViewPet {
	// declaración de atributos
	private PetGuiView guiView;
	private Listable<DtoPet> list;

	private boolean makeNormalReport = true;

	/**
	 * Constructor con parámetros
	 * 
	 * @param guiView objeto de tipo PetGuiView
	 * @param list    objeto de tipo Listable
	 */
	public ControllerViewPet(PetGuiView guiView, Listable<DtoPet> list) {
		this.guiView = guiView;
		this.list = list;
		guiView.getBtnRestoreFilter().setVisible(false);
		setActionListener();
	}// cierre constructor

	/**
	 * Constructor con parámetros
	 * 
	 * @param petGuiView objeto de tipo PetGuiView
	 */
	public ControllerViewPet(PetGuiView petGuiView) {
		this.guiView = petGuiView;
		this.list = ListPet.getInstance();

		if (list.sizeDtos() < 0) {
			loadAll();
		}
		setActionListener();
	}// cierre constructor

	/**
	 * Método setKeyTable
	 * 
	 * @param arg0 objeto de tipo KeyListener
	 */
	public void setKeyTable(KeyListener arg0) {
		guiView.getTable().addKeyListener(arg0);
	}// cierre método setKeyTable

	/**
	 * Método seledtOneRowTable selecciona una fila de la tabla
	 */
	public void selectOneRowTable() {
		guiView.getTable().setRowSelectionInterval(0, 0);
		guiView.getScrollPaneTable().getViewport().setViewPosition(new Point(0, 0));
	}// cierre método selectOneRowTable

	/**
	 * Método filterTable Filtra la tabla
	 */
	public void filterTable() {
		try {
			list.loadListFilter(guiView.getCbxFilter(), guiView.getTxtFilter().getText());
			guiView.setModelTable(makeDataToList());
			filter(true);
		} catch (Exception e) {
			Messages.showError(e.getLocalizedMessage());
			filter(false);
		}
	}// cierre método filterTable

	public String[][] makeDataToList() {
		String[][] data = new String[list.sizeDtos()][5];
		Interator<DtoPet> inte = list.getAll();
		while (inte.hasNext()) {
			int pointerCar = inte.now();
			DtoPet dto = inte.next();
			data[pointerCar][0] = dto.getName();
			data[pointerCar][1] = dto.getDateBirth().toString();
			data[pointerCar][2] = dto.getRace();
			data[pointerCar][3] = dto.getWeight() + "";
			data[pointerCar][4] = dto.getColor();
		}
		return data;
	}

	/**
	 * Método updateTable Actualiza tabla
	 * 
	 * @param dto      objeto de tipo DtoPet
	 * @param position variable de tipo entero
	 */
	public void updateTable(DtoPet dto, int position) {
		guiView.getTable().setValueAt(dto.getName(), position, 0);
		guiView.getTable().setValueAt(dto.getDateBirth().toString(), position, 1);
		guiView.getTable().setValueAt(dto.getRace(), position, 2);
		guiView.getTable().setValueAt(dto.getWeight(), position, 3);
		guiView.getTable().setValueAt(dto.getColor(), position, 4);
	}// cierre método updateTable

	/**
	 * Método getSelectedRow Obtiene la selección de una fila
	 * 
	 * @return retorna un valor entero
	 */
	public int getSelectedRow() {
		return guiView.getTable().getSelectedRow();
	}// cierre método getSelectedRow

	/**
	 * método getLengthSelectedRows Obtiene la longitud de las filas
	 * 
	 * @return retorna un valor entero
	 */
	public int getLengthSelectedRows() {
		return guiView.getTable().getSelectedRows().length;
	}// cierre método getLengthSelectedRows

	/**
	 * Método reloadData Recarga los datos
	 */
	public void reloadData() {
		guiView.setModelTable(makeDataToList());
	}// cierre método reloadData

	/**
	 * MÃ©todo setActionListener
	 */
	public void setActionListener() {
		// Implementación lambda.
		guiView.getBtnFilter().addActionListener((ActionEvent e) -> {
			filterTable();
		});

		guiView.getBtnRestoreFilter().addActionListener((ActionEvent e) -> {
			filter(false);
		});

		guiView.getBtnReport().addActionListener((ActionEvent e) -> {
			searchReport();
		});

	}// cierre método setActionListener

	/**
	 * Método clearSelection Limpia la selección.
	 */
	public void clearSelection() {
		guiView.getTable().clearSelection();
	}// cierre método clearSelection

	/**
	 * Método setClicTable Escucha cuando se da clic a la tabla
	 * 
	 * @param mousAdapter
	 */
	public void setClicTable(MouseAdapter mousAdapter) {
		guiView.getTable().addMouseListener(mousAdapter);
	}// cierre setClicTable

	/**
	 * Método searchReport() Carga dos tipos de reporte "simple o avanzado".
	 */
	private void searchReport() {
		Thread threarReport = new Thread(() -> {
			FormatReport format = null;
			if (makeNormalReport()) {
				format = new FormatReport();
			} else {
				ReportFilterPet filterReport = new ReportFilterPet(new FormatReport());
				filterReport.setParams(guiView.getCbxFilter(), guiView.getTxtFilter().getText().toUpperCase());
				format = filterReport;
			}
			if (format != null) {
				try {
					list.getReport(format);
				} catch (Exception e1) {
					Messages.showError(e1.getLocalizedMessage());
				}
			}

		});
		threarReport.start();
	}// cierre método searchReport

	private void filter(boolean isFilter) {
		makeNormalReport = !isFilter;
		if (isFilter) {
			guiView.getBtnRestoreFilter().setVisible(true);
			guiView.getBtnReport().setText(TEXTS.getTextMakeReportFilter());
		} else {
			guiView.getTxtFilter().setText("");
			guiView.getBtnRestoreFilter().setVisible(false);
			guiView.getBtnReport().setText(TEXTS.getTextMakeReport());
			loadAll();

		}
	}

	private void loadAll() {
		try {
			list.loadList();
			reloadData();
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());

		}
	}

	private boolean makeNormalReport() {
		return makeNormalReport;
	}

}// cierre clase ControllerViewPet
