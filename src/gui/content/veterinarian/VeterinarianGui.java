package gui.content.veterinarian;

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
import model.dto.DtoVeterinarian.SEX;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

/**
 * Archivo: VeterinarianGui.java contiene la definición de la clase
 * VeterinarianGui que extiende de JPanel.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class VeterinarianGui extends JPanel {
	// declaración de atributos
	private static final long serialVersionUID = 1L;

	private static final String TEXT_NAME = "Nombre *";
	private static final String TEXT_EMAIL = "Correo *";
	private static final String TEXT_CEDULA = "Cedula *";
	private static final String TEXT_SEX = "Sexo *";
	private static final String TEXT_DATE = "Fecha de nacimiento *";

	private static final String TITLE = "Registrar veterinario";

	private JPanel pTitle;

	// declaración de componentes
	private JLabel lbTitle;
	private JLabel lbName;
	private JLabel lbEmail;
	private JLabel lbCedula;
	private JLabel lbSex;
	private JLabel lbDate;

	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtCedula;

	private JButton btnAdd;
	private JButton btnCancel;

	private JButton btnDelete;

	private JPanel contentButtons;
	private JPanel contentForm;
	private JPanel contentMain;

	private JComboBox<SEX> cbxSex;

	private SqlDateModel dateModel;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	// constructor sin parámetros
	public VeterinarianGui() {
		createGui();
	}

	// Método que crea la vista
	private void createGui() {

		cbxSex = new JComboBox<>();
		cbxSex.setModel(new DefaultComboBoxModel<>(SEX.values()));

		this.setLayout(new BorderLayout());
		this.setBackground(ResourcesGui.COLOR.getSecondColor());

		btnDelete = new JButton(TEXTS.getTextDelete());
		btnDelete.setBackground(ResourcesGui.COLOR.getWaringColor());
		btnDelete.setBorder(ResourcesGui.BORDER.getBorderBtnAcept());
		btnDelete.setForeground(ResourcesGui.COLOR.getSecondColor());

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

		lbName = new JLabel(TEXT_NAME, JLabel.RIGHT);
		lbEmail = new JLabel(TEXT_EMAIL, JLabel.RIGHT);
		lbCedula = new JLabel(TEXT_CEDULA, JLabel.RIGHT);
		lbSex = new JLabel(TEXT_SEX, JLabel.RIGHT);
		lbDate = new JLabel(TEXT_DATE, JLabel.RIGHT);

		lbName.setFont(ResourcesGui.FONT.getFontText());
		lbEmail.setFont(ResourcesGui.FONT.getFontText());
		lbCedula.setFont(ResourcesGui.FONT.getFontText());
		lbSex.setFont(ResourcesGui.FONT.getFontText());
		lbDate.setFont(ResourcesGui.FONT.getFontText());

		txtName = new JTextField();
		txtEmail = new JTextField();
		txtCedula = new JTextField();

		dateModel = new SqlDateModel();
		datePanel = new JDatePanelImpl(dateModel);
		datePicker = new JDatePickerImpl(datePanel);

		txtName.setBorder(ResourcesGui.BORDER.getBorderTxt());
		txtEmail.setBorder(ResourcesGui.BORDER.getBorderTxt());
		txtCedula.setBorder(ResourcesGui.BORDER.getBorderTxt());

		txtName.setForeground(ResourcesGui.COLOR.getAcentColor());
		txtEmail.setForeground(ResourcesGui.COLOR.getAcentColor());
		txtCedula.setForeground(ResourcesGui.COLOR.getAcentColor());

		datePicker.setBackground(ResourcesGui.COLOR.getSecondColor());
		datePanel.setBackground(ResourcesGui.COLOR.getSecondColor());

		txtName.setFont(ResourcesGui.FONT.getFontText());
		txtEmail.setFont(ResourcesGui.FONT.getFontText());
		txtCedula.setFont(ResourcesGui.FONT.getFontText());

		datePicker.setFont(ResourcesGui.FONT.getFontText());
		datePicker.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		contentForm.add(lbName);
		contentForm.add(txtName);
		contentForm.add(lbEmail);
		contentForm.add(txtEmail);
		contentForm.add(lbCedula);
		contentForm.add(txtCedula);
		contentForm.add(lbSex);
		contentForm.add(cbxSex);
		contentForm.add(lbDate);
		contentForm.add(datePicker);

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

	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JTextField getTxtCedula() {
		return txtCedula;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JComboBox<SEX> getCbxSex() {
		return cbxSex;
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

}// cierre clase VeterinarianGui
