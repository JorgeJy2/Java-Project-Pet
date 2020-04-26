package gui.content.pet;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.resource.ResourcesGui;
import gui.resource.ResourcesGui.DIMENS;
import gui.resource.ResourcesGui.TEXTS;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

/**
 * Archivo:PetGui.java contiene la definición de la clase PetGui que extiende de
 * JPanel
 * 
 * @author JorgeJy2
 * @version 1.0
 */
public class PetGui extends JPanel {
	// declaración de atributos
	private static final long serialVersionUID = 1L;

	private static final String TEXT_NAME = "Nombre *";
	private static final String TEXT_DATE_NAC = "Fecha de nacimiento*";
	private static final String TEXT_COLOR = "Color *";
	private static final String TEXT_RACE = "Raza*";
	private static final String TEXT_WEIGHT = "Peso (Kilos)*";

	private static final String TITLE = "Registrar mascota";

	private JPanel pTitle;

	// declaración de componentes
	private JLabel lbTitle;
	private JLabel lbName;
	private JLabel lbDateNac;
	private JLabel lbColor;
	private JLabel lbRace;
	private JLabel lbWeigh;

	private JTextField txtName;
	private JTextField txtColor;
	private JTextField txtRace;
	private JTextField txtWeight;

	private JButton btnAdd;
	private JButton btnCancel;
	private JButton btnDelete;

	private JPanel contentButtons;
	private JPanel contentForm;
	private JPanel contentMain;

	private SqlDateModel dateModel;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	// constructor sin parámetros
	public PetGui() {
		createGui();
	}// Cierre constructor

	// método que crea la vista
	private void createGui() {

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
		lbDateNac = new JLabel(TEXT_DATE_NAC, JLabel.RIGHT);
		lbColor = new JLabel(TEXT_COLOR, JLabel.RIGHT);
		lbRace = new JLabel(TEXT_RACE, JLabel.RIGHT);
		lbWeigh = new JLabel(TEXT_WEIGHT, JLabel.RIGHT);

		lbName.setFont(ResourcesGui.FONT.getFontText());
		lbDateNac.setFont(ResourcesGui.FONT.getFontText());
		lbColor.setFont(ResourcesGui.FONT.getFontText());
		lbRace.setFont(ResourcesGui.FONT.getFontText());
		lbWeigh.setFont(ResourcesGui.FONT.getFontText());

		txtName = new JTextField();
		txtColor = new JTextField();
		txtRace = new JTextField();
		txtWeight = new JTextField();

		dateModel = new SqlDateModel();
		datePanel = new JDatePanelImpl(dateModel);
		datePicker = new JDatePickerImpl(datePanel);

		txtName.setBorder(ResourcesGui.BORDER.getBorderTxt());
		txtColor.setBorder(ResourcesGui.BORDER.getBorderTxt());
		txtRace.setBorder(ResourcesGui.BORDER.getBorderTxt());
		txtWeight.setBorder(ResourcesGui.BORDER.getBorderTxt());

		txtName.setForeground(ResourcesGui.COLOR.getAcentColor());
		txtColor.setForeground(ResourcesGui.COLOR.getAcentColor());
		txtRace.setForeground(ResourcesGui.COLOR.getAcentColor());
		txtWeight.setForeground(ResourcesGui.COLOR.getAcentColor());

		datePicker.setBackground(ResourcesGui.COLOR.getSecondColor());
		datePanel.setBackground(ResourcesGui.COLOR.getSecondColor());

		txtName.setFont(ResourcesGui.FONT.getFontText());
		txtColor.setFont(ResourcesGui.FONT.getFontText());
		txtRace.setFont(ResourcesGui.FONT.getFontText());
		txtWeight.setFont(ResourcesGui.FONT.getFontText());
		datePicker.setFont(ResourcesGui.FONT.getFontText());

		datePicker.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		contentForm.add(lbName);
		contentForm.add(txtName);
		contentForm.add(lbDateNac);
		contentForm.add(datePicker);

		contentForm.add(lbRace);
		contentForm.add(txtRace);
		contentForm.add(lbWeigh);
		contentForm.add(txtWeight);
		contentForm.add(lbColor);
		contentForm.add(txtColor);
		

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

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtRace() {
		return txtRace;
	}

	public void setTxtRace(JTextField txtRace) {
		this.txtRace = txtRace;
	}

	public JTextField getTxtWeight() {
		return txtWeight;
	}

	public void setTxtWeight(JTextField txtWeight) {
		this.txtWeight = txtWeight;
	}

	public JTextField getTxtColor() {
		return txtColor;
	}

	public void setTxtColor(JTextField txtColor) {
		this.txtColor = txtColor;
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

}// cierre clase PetGui
