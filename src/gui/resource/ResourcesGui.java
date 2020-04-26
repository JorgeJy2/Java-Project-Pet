package gui.resource;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * Archivo: ResourcesGui.java contiene la definición de la clase ResourcesGui.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class ResourcesGui {
	/**
	 * Clase estatica COLOR
	 * 
	 * @author
	 * @version 1.0
	 *
	 */
	public static final class COLOR {
		// declaración de atributos
		private static final Color SECOND_COLOR = Color.white;
		private static final Color PRIMARY_COLOR = new Color(255, 87, 51);
		private static final Color ACENT_COLOR = Color.gray;
		private static final Color WARING_COLOR = new Color(235, 49, 49);

		// getters
		public static Color getSecondColor() {
			return SECOND_COLOR;
		}

		public static Color getPrimaryColor() {
			return PRIMARY_COLOR;
		}

		public static Color getAcentColor() {
			return ACENT_COLOR;
		}

		public static Color getWaringColor() {
			return WARING_COLOR;
		}
	}// cierre clase COLOR

	/**
	 * Clase estatica BORDER
	 * 
	 * @author
	 * @version 1.0
	 *
	 */
	public static final class BORDER {
		// declaración de atributos
		private static final Border BORDER_CONTEINER_MAIN = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		private static final Border BORDER_FORM = BorderFactory.createEmptyBorder(20, 0, 20, 10);
		private static final Border BORDER_TITLE = BorderFactory.createEmptyBorder(9, 9, 9, 0);
		private static final Border BORDER_TXT = BorderFactory.createMatteBorder(0, 0, 1, 0,
				ResourcesGui.COLOR.getAcentColor());

		private static final Border BORDER_BTN_ACEPT = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(ResourcesGui.COLOR.getSecondColor(), 2),
				BorderFactory.createLineBorder(ResourcesGui.COLOR.getPrimaryColor(), 5));

		private static final Border BORDER_BTN_CANCEL = BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(ResourcesGui.COLOR.getAcentColor(), 2),
				BorderFactory.createLineBorder(ResourcesGui.COLOR.getSecondColor(), 5));

		// getters
		public static Border getBorderBtnAcept() {
			return BORDER_BTN_ACEPT;
		}

		public static Border getBorderBtnCancel() {
			return BORDER_BTN_CANCEL;
		}

		public static Border getBorderTxt() {
			return BORDER_TXT;
		}

		public static Border getBorderConteinerMain() {
			return BORDER_CONTEINER_MAIN;
		}

		public static Border getBorderTitle() {
			return BORDER_TITLE;
		}

		public static Border getBorderForm() {
			return BORDER_FORM;
		}

	}// cierre clase BORDER

	/**
	 * Clase estatica FONT
	 * 
	 * @author
	 * @version 1.0
	 *
	 */
	public static final class FONT {
		// declaraciÃ³n de atributos
		private static final Font FONT_TEXT = new Font("Segoe UI", 0, 15);
		private static final Font FONT_TITLE = new Font("Segoe UI", 0, 19);

		// getters
		public static Font getFontText() {
			return FONT_TEXT;
		}

		public static Font geFontTitle() {
			return FONT_TITLE;
		}
	}// cierre clase FONT

	/**
	 * Clase DIMENS
	 * 
	 * @author
	 * @version 1.0
	 *
	 */
	public static final class DIMENS {
		// declaraciÃ³n de atributo
		private static final int DISTANCE_COMPONENTS = 10;

		private static final int BORDER_BTNS_H = 10;
		private static final int BORDER_BTNS_V = 5;

		private static final int BORDER_FORM_H = 20;
		private static final int BORDER_FORM_V = 5;

		private static final int GRID_FORM_ROWS = 0;
		private static final int GRID_FROM_COLS = 2;

		private static final int GRID_BTN_ROWS = 1;
		private static final int GRID_BTN_COLS = 2;

		private static final int MIN_VERTICAL_FRAME = 100;
		private static final int MIN_HORIZONTAL_FRAME = 100;

		public static int getDistanceComponents() {
			return DISTANCE_COMPONENTS;
		}

		public static int getBorderBtnsH() {
			return BORDER_BTNS_H;
		}

		public static int getBorderBtnsV() {
			return BORDER_BTNS_V;
		}

		public static int getBorderFormH() {
			return BORDER_FORM_H;
		}

		public static int getBorderFormV() {
			return BORDER_FORM_V;
		}

		public static int getGridFormRows() {
			return GRID_FORM_ROWS;
		}

		public static int getGridFromCols() {
			return GRID_FROM_COLS;
		}

		public static int getGridBtnRows() {
			return GRID_BTN_ROWS;
		}

		public static int getGridBtnCols() {
			return GRID_BTN_COLS;
		}

		// getter
		public static int getDistanteComponent() {
			return DISTANCE_COMPONENTS;
		}

		public static int getMinVerticalFrame() {
			return MIN_VERTICAL_FRAME;
		}

		public static int getMinHorizontalFrame() {
			return MIN_HORIZONTAL_FRAME;
		}

	}// cierre clase DIMENS

	public static final class GRAPHIC {
		private static final String MAIN_SRC_IMG = "imgs/";

		private static final String IMG_PET = MAIN_SRC_IMG + "pet_64.png";
		private static final String IMG_VETERINARIAN = MAIN_SRC_IMG + "veterinarian_64.png";
		private static final String IMG_QUERY_PET = MAIN_SRC_IMG + "query-pet_64.png";

		private static final String IMG_DELETE = MAIN_SRC_IMG + "borrar.png";
		private static final String IMG_REPORT = MAIN_SRC_IMG + "report.png";

		public static String getImgPet() {
			return IMG_PET;
		}

		public static String getImgVeterinarian() {
			return IMG_VETERINARIAN;
		}

		public static String getImgQueryPet() {
			return IMG_QUERY_PET;
		}

		public static String getImgDelete() {
			return IMG_DELETE;
		}

		public static String getImgReport() {
			return IMG_REPORT;
		}
	}

	public static final class TEXTS {
		private static final String TEXT_ADD = "AGREGAR";
		private static final String TEXT_CANCEL = "CANCELAR";
		private static final String TEXT_EDIT = "EDITAR";
		private static final String TEXT_DELETE = "ELIMINAR";
		private static final String TEXT_REPORT = "REPORTE";
		private static final String TEXT_FILTER = "FILTRAR";
		private static final String TEXT_RESTORE_FILTER = "RESTAURAR FILTRO";
		private static final String TEXT_MAKE_REPORT = "REALIZAR REPORTE";
		private static final String TEXT_SPECIAL_REPORT = "REALIZAR ESTADÍSTICAS";
		private static final String TEXT_MAKE_REPORT_FILTER = "REALIZAR REPORTE CON FILTRO";
		
		public static String getTextAdd() {
			return TEXT_ADD;
		}

		public static String getTextCancel() {
			return TEXT_CANCEL;
		}

		public static String getTextDelete() {
			return TEXT_DELETE;
		}

		public static String getTextReport() {
			return TEXT_REPORT;
		}

		public static String getTextEdit() {
			return TEXT_EDIT;
		}

		public static String getTextFilter() {
			return TEXT_FILTER;
		}

		public static String getTextRestoreFilter() {
			return TEXT_RESTORE_FILTER;
		}

		public static String getTextMakeReport() {
			return TEXT_MAKE_REPORT;
		}

		public static String getTextMakeReportFilter() {
			return TEXT_MAKE_REPORT_FILTER;
		}

		public static String getTextSpecialReport() {
			return TEXT_SPECIAL_REPORT;
		}
		
	}

}// cierre clase ResourcesGui
