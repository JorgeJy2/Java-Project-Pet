package gui.content.consult;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.resource.ResourcesGui;
import gui.resource.ResourcesGui.DIMENS;
import gui.resource.ResourcesGui.TEXTS;
import model.dto.DtoConsult.STATUS;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;


/**
 * Archivo: ConsultGui.java contiene la definición de la clase ConsultGui que
 * extiende de JPanel.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */

public class ConsultGui extends JPanel {
	// declaración de atributos
	private static final long serialVersionUID = 1L;

	private static final String TEXT_PET = "Seleciona a la mascota *";
	private static final String TEXT_VETERINARIAN = "Seleciona a el veterinario *";
	private static final String TEXT_DATE = "Fecha";
	private static final String TEXT_COST = "Costo *";
	private static final String TEXT_STATUS = "Estatus";
	private static final String TEXT_DESCRIPTION = "Descripción *";

	private static final String TITLE = "Registrar consulta";

	private JComboBox<Object> cbxPet;
	private JComboBox<Object> cbxVeterinarian;

	private JComboBox<STATUS> cbxStatus;


	private JPanel pTitle;

	// declaración de componentes
	private JLabel lbTitle;
	private JLabel lbPet;
	private JLabel lbVeterinarin;
	private JLabel lbDate;
	private JLabel lbCost;
	private JLabel lbStatus;
	private JLabel lbDescription;

	private SqlDateModel dateModel;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	private JTextField txtCost;
	private JTextField txtDescription;

	/*
	 * private JComboBox<String> cbxPeople; private JComboBox<String> cbxCar;
	 */
	private JButton btnAdd;
	private JButton btnCancel;
	private JButton btnDelete;

	private JPanel contentButtons;
	private JPanel contentMain;
	private JPanel contentForm;

	// constructor sin parámetros
	public ConsultGui() {
		createGui();
	}// cierre constructor

	// Método que crea la vista
	private void createGui() {

		cbxStatus = new JComboBox<>();
		cbxStatus.setModel(new DefaultComboBoxModel<>(STATUS.values()));

		this.setLayout(new BorderLayout());
		this.setBackground(ResourcesGui.COLOR.getSecondColor());

		contentMain = new JPanel();
		contentMain.setLayout(new BorderLayout());
		contentMain.setBorder(ResourcesGui.BORDER.getBorderConteinerMain());
		contentMain.setBackground(ResourcesGui.COLOR.getSecondColor());

		pTitle = new JPanel();
		pTitle.setBackground(ResourcesGui.COLOR.getSecondColor());
		pTitle.setLayout(new GridLayout(0, 1));
		lbTitle = new JLabel(TITLE);
		lbTitle.setFont(ResourcesGui.FONT.geFontTitle());
		pTitle.add(lbTitle);

		contentMain.add(pTitle, BorderLayout.PAGE_START);

		contentForm = new JPanel();

		contentForm.setLayout(new GridLayout(DIMENS.getGridFormRows(), DIMENS.getGridFromCols(),
				DIMENS.getBorderFormH(), DIMENS.getBorderFormV()));
		contentForm.setBorder(ResourcesGui.BORDER.getBorderForm());
		contentForm.setBackground(ResourcesGui.COLOR.getSecondColor());

		lbPet = new JLabel(TEXT_PET, JLabel.RIGHT);
		lbVeterinarin = new JLabel(TEXT_VETERINARIAN, JLabel.RIGHT);
		lbDate = new JLabel(TEXT_DATE, JLabel.RIGHT);
		lbCost = new JLabel(TEXT_COST, JLabel.RIGHT);
		lbStatus = new JLabel(TEXT_STATUS, JLabel.RIGHT);
		lbDescription = new JLabel(TEXT_DESCRIPTION, JLabel.RIGHT);

		lbPet.setFont(ResourcesGui.FONT.getFontText());
		lbVeterinarin.setFont(ResourcesGui.FONT.getFontText());
		lbDate.setFont(ResourcesGui.FONT.getFontText());
		lbCost.setFont(ResourcesGui.FONT.getFontText());
		lbStatus.setFont(ResourcesGui.FONT.getFontText());
		lbDescription.setFont(ResourcesGui.FONT.getFontText());

		cbxPet = new JComboBox<Object>();
		contentForm.add(cbxPet);

		cbxVeterinarian = new JComboBox<Object>();
		contentForm.add(cbxVeterinarian);

		txtCost = new JTextField();
		txtDescription = new JTextField();

		dateModel = new SqlDateModel();
		datePanel = new JDatePanelImpl(dateModel);
		datePicker = new JDatePickerImpl(datePanel);

		txtCost.setBorder(ResourcesGui.BORDER.getBorderTxt());
		txtDescription.setBorder(ResourcesGui.BORDER.getBorderTxt());

		txtCost.setForeground(ResourcesGui.COLOR.getAcentColor());
		txtDescription.setForeground(ResourcesGui.COLOR.getAcentColor());
		datePicker.setBackground(ResourcesGui.COLOR.getSecondColor());
		datePanel.setBackground(ResourcesGui.COLOR.getSecondColor());

		txtCost.setFont(ResourcesGui.FONT.getFontText());
		txtDescription.setFont(ResourcesGui.FONT.getFontText());

		datePicker.setFont(ResourcesGui.FONT.getFontText());
		datePicker.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		contentForm.add(lbPet);
		contentForm.add(cbxPet);
		contentForm.add(lbVeterinarin);
		contentForm.add(cbxVeterinarian);

		contentForm.add(lbDate);
		contentForm.add(datePicker);

		contentForm.add(lbCost);
		contentForm.add(txtCost);
		contentForm.add(lbStatus);
		contentForm.add(cbxStatus);
		contentForm.add(lbDescription);
		contentForm.add(txtDescription);

		btnDelete = new JButton(TEXTS.getTextDelete());
		btnDelete.setBackground(ResourcesGui.COLOR.getWaringColor());
		btnDelete.setBorder(ResourcesGui.BORDER.getBorderBtnAcept());
		btnDelete.setForeground(ResourcesGui.COLOR.getSecondColor());

			

		contentMain.add(contentForm, BorderLayout.CENTER);

		contentButtons = new JPanel();
		contentButtons.setLayout(new GridLayout(DIMENS.getGridBtnRows(), DIMENS.getGridBtnCols(),
				DIMENS.getBorderBtnsH(), DIMENS.getBorderBtnsV()));

		contentButtons.setBackground(ResourcesGui.COLOR.getSecondColor());

		btnAdd = new JButton(TEXTS.getTextAdd());
		btnCancel = new JButton(TEXTS.getTextCancel());

		btnAdd.setBackground(ResourcesGui.COLOR.getPrimaryColor());
		btnCancel.setBackground(ResourcesGui.COLOR.getSecondColor());

		btnAdd.setBorder(ResourcesGui.BORDER.getBorderBtnAcept());
		btnCancel.setBorder(ResourcesGui.BORDER.getBorderBtnCancel());

		btnAdd.setForeground(ResourcesGui.COLOR.getSecondColor());
		btnCancel.setForeground(ResourcesGui.COLOR.getAcentColor());

		contentButtons.add(btnAdd);
		contentButtons.add(btnCancel);
		contentButtons.add(btnDelete);


		contentMain.add(contentButtons, BorderLayout.PAGE_END);

		this.add(contentMain, BorderLayout.CENTER);

	}// cierre método createGui

	// getters
	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JComboBox<Object> getCbxPet() {
		return cbxPet;
	}

	public JComboBox<Object> getCbxVeterinarian() {
		return cbxVeterinarian;
	}

	public void resetBtnAdd() {
		btnAdd.setText(TEXTS.getTextAdd());
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JTextField getTxtCost() {
		return txtCost;
	}

	public JTextField getTxtDescription() {
		return txtDescription;
	}

	public JComboBox<STATUS> getCbxStatus() {
		return cbxStatus;
	}

	public Date getDate() {
		return (Date) datePicker.getModel().getValue();
	}

	public void setDate(Date date) {
		String dateString = date.toString();
		datePicker.getModel().setYear(Integer.parseInt((String) dateString.subSequence(0, 4)));
		// Se resta uno debido a que java comienza los meses con 0
		datePicker.getModel().setMonth(Integer.parseInt((String) dateString.subSequence(5, 7)) - 1);
		datePicker.getModel().setDay(Integer.parseInt((String) dateString.subSequence(8, 10)));
		datePicker.getModel().setSelected(true);
	}

	public JDatePickerImpl getDatePicker() {
		return datePicker;
	}

}// cierre clase ConsultGui
