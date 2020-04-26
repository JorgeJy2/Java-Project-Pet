package gui.content.veterinarian;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import gui.resource.ResourcesGui;
import gui.resource.ResourcesGui.TEXTS;
import model.dto.DtoVeterinarian;

/**
 * Archivo: VeterinarianGuiView.java contiene la definición de la clase
 * VeterinarianGuiView que extiende de JPanel
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class VeterinarianGuiView extends JPanel {

	// declaración de atributos
	private static final long serialVersionUID = 1L;

	private static final String TITLE = "Veterinarios registrados";
	private static final String FILTER = "Filtrar veterinarios";

	// GUI
	private JTable table;

	private JPanel pTitle;
	private JPanel pfilter;

	private JLabel lbTitle;
	private JLabel lbFilter;

	private JComboBox<DtoVeterinarian.FIELDS> cbxFilter;

	private JTextField txtFilter;

	private JButton btnFilter;
	private JButton btnRestoreFilter;
	private JButton btnReport;

	private DefaultTableModel tableModel;

	private JScrollPane scrollPaneTable;

	// constructor sin parámetros
	public VeterinarianGuiView() {
		createGui();
	}// cierre constructor

	// Método que crea la vista
	private void createGui() {

		this.setLayout(new BorderLayout(ResourcesGui.DIMENS.getDistanteComponent(),
				ResourcesGui.DIMENS.getDistanteComponent()));
		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		this.setBorder(ResourcesGui.BORDER.getBorderConteinerMain());

		pTitle = new JPanel();
		pTitle.setBackground(ResourcesGui.COLOR.getSecondColor());
		pTitle.setLayout(new GridLayout(0, 1));
		lbTitle = new JLabel(TITLE);
		lbTitle.setFont(ResourcesGui.FONT.geFontTitle());
		pTitle.add(lbTitle);

		// ================== FILTER start ==================

		pfilter = new JPanel();
		pfilter.setLayout(new GridLayout(1, 0, ResourcesGui.DIMENS.getDistanteComponent(),
				ResourcesGui.DIMENS.getDistanteComponent()));
		pfilter.setBackground(ResourcesGui.COLOR.getSecondColor());

		lbFilter = new JLabel(FILTER);
		pTitle.add(lbFilter);

		cbxFilter = new JComboBox<DtoVeterinarian.FIELDS>(DtoVeterinarian.FIELDS.values());
		pfilter.add(cbxFilter);

		txtFilter = new JTextField();
		txtFilter.setFont(ResourcesGui.FONT.getFontText());
		txtFilter.setBorder(ResourcesGui.BORDER.getBorderTxt());

		pfilter.add(txtFilter);

		btnFilter = new JButton(TEXTS.getTextFilter());
		btnFilter.setBackground(ResourcesGui.COLOR.getPrimaryColor());
		btnFilter.setBorder(ResourcesGui.BORDER.getBorderBtnAcept());
		btnFilter.setForeground(ResourcesGui.COLOR.getSecondColor());
		pfilter.add(btnFilter);

		btnRestoreFilter = new JButton(TEXTS.getTextRestoreFilter());
		btnRestoreFilter.setBackground(ResourcesGui.COLOR.getPrimaryColor());
		btnRestoreFilter.setBorder(ResourcesGui.BORDER.getBorderBtnAcept());
		btnRestoreFilter.setForeground(ResourcesGui.COLOR.getSecondColor());
		pfilter.add(btnRestoreFilter);

		pTitle.add(pfilter);

		pTitle.add(pfilter);
		// ================== FILTER end ==================

		this.add(pTitle, BorderLayout.PAGE_START);
		// Data to be displayed in the JTable

		tableModel = new DefaultTableModel(DtoVeterinarian.FIELDS.values(), 0);

		table = new JTable(tableModel);

		table.setRowHeight(30);
		table.setShowGrid(false);
		table.setBackground(ResourcesGui.COLOR.getSecondColor());
		table.setSelectionBackground(ResourcesGui.COLOR.getPrimaryColor());
		table.setFont(ResourcesGui.FONT.getFontText());

		JTableHeader header = table.getTableHeader();
		header.setBackground(ResourcesGui.COLOR.getPrimaryColor());
		header.setForeground(ResourcesGui.COLOR.getSecondColor());
		header.setFont(ResourcesGui.FONT.getFontText());

		scrollPaneTable = new JScrollPane();
		scrollPaneTable.setViewportView(table);
		this.add(scrollPaneTable, BorderLayout.CENTER);

		btnReport = new JButton(TEXTS.getTextMakeReport());
		btnReport.setBackground(ResourcesGui.COLOR.getPrimaryColor());
		btnReport.setBorder(ResourcesGui.BORDER.getBorderBtnAcept());
		btnReport.setForeground(ResourcesGui.COLOR.getSecondColor());

		this.add(btnReport, BorderLayout.PAGE_END);
	}// cierre Método createGui

	/**
	 * Método setModelTable
	 * 
	 * @param data valor de tipo String
	 */
	public void setModelTable(String[][] data) {
		DefaultTableModel modelo = new DefaultTableModel(data, DtoVeterinarian.FIELDS.values());
		table.setModel(modelo);
		table.setRowHeight(30);
	}// cierre setModelTable

	// getters
	public JTable getTable() {
		return table;
	}

	public String getCbxFilter() {
		return cbxFilter.getSelectedItem().toString();
	}

	public JTextField getTxtFilter() {
		return txtFilter;
	}

	public JButton getBtnFilter() {
		return btnFilter;
	}

	public JButton getBtnRestoreFilter() {
		return btnRestoreFilter;
	}

	public JButton getBtnReport() {
		return btnReport;
	}

	public JScrollPane getScrollPaneTable() {
		return scrollPaneTable;
	}
}// cierre clase VeterinarianGuiView