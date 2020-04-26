package controller.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Archivo: ValidatorText.java contiene la definición de la clase ValidatorText.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class ValidatorText {
	
	// PETS
	private static final int MIN_LENGTH_NAME = 2;
	private static final int MAX_LENGTH_NAME = 50;
	private static final int MIN_LENGTH_COLOR = 3;
	private static final int MAX_LENGTH_COLOR = 30;
	private static final int MIN_LENGTH_RACE= 3;
	private static final int MAX_RACE = 20;
	
	//VETERINARIAN
	private static final int MIN_LENGTH_EMAIL= 5;
	private static final int MAX_LENGTH_EMAIL= 50;
	private static final int MIN_LENGTH_IDENTIFICATION= 5;
	private static final int MAX_LENGTH_IDENTIFICATION= 30;
	
	// CONSULT
	
	private static final int MIN_LENGTH_DESCRIPTION= 5;
	private static final int MAX_LENGTH_DESCRIPTION= 250;

	/**
	 * Método estatico validateFieldLetter
	 * 
	 * @param entrada valor de tipo String
	 * @return retorna un valor de tipo booleano
	 */
	public static boolean validateFieldLetter(String entrada) {
		if (entrada.length() < 1 || entrada.equals(""))
			return false;
		else {
			Pattern patron = Pattern.compile("[^a-zA-Z-ñáéíóúÑÁÉÍÓÚ ]");
			Matcher encaja = patron.matcher(entrada);
			return (!encaja.find());
		}
	}// cierre Método validateFieldLetter

	/**
	 * Método estatico validateAlphanumeric
	 * 
	 * @param entrada valor de tipo String
	 * @return retorna valor booleano
	 */
	public static boolean validateAlphanumeric(String entrada) {
		if (entrada.length() < 1 || entrada.equals(""))
			return false;
		else {
			Pattern patron = Pattern.compile("[^A-Za-z0-9-]");
			Matcher encaja = patron.matcher(entrada);
			return (!encaja.find());
		}
	}// cierre método validateMatricula


	/**
	 * Método validateFielEmail
	 * 
	 * @param email valor de tipo String
	 * @return retorna valor booleano
	 */
	public static boolean validateFielEmail(String email) {
		if (email.length() < 1 || email.equals(""))
			return false;
		else {
			Pattern pattern = Pattern.compile(
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher mather = pattern.matcher(email);
			return (mather.find());
		}
	}// cierre método validateFielEmail


	public static int getMinLengthColor() {
		return MIN_LENGTH_COLOR;
	}

	public static int getMaxLengthColor() {
		return MAX_LENGTH_COLOR;
	}

	public static int getMaxRace() {
		return MAX_RACE;
	}
	
	public static int getMinLengthRace() {
		return MIN_LENGTH_RACE;
	}

	public static int getMinLengthName() {
		return MIN_LENGTH_NAME;
	}

	public static int getMaxLengthName() {
		return MAX_LENGTH_NAME;
	}

	public static int getMinLengthEmail() {
		return MIN_LENGTH_EMAIL;
	}

	public static int getMaxLengthEmail() {
		return MAX_LENGTH_EMAIL;
	}

	public static int getMinLengthIdentification() {
		return MIN_LENGTH_IDENTIFICATION;
	}

	public static int getMaxLengthIdentification() {
		return MAX_LENGTH_IDENTIFICATION;
	}

	public static int getMinLengthDescription() {
		return MIN_LENGTH_DESCRIPTION;
	}

	public static int getMaxLengthDescription() {
		return MAX_LENGTH_DESCRIPTION;
	}
	
	
	
}// cierre clase ValidatorText
