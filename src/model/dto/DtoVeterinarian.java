package model.dto;

import java.sql.Date;


/**
 * Archivo: DtoVeterinarian.java
 * 
 * Objetivo: Representa la estructura de una persona con profesión veterinaria en el mundo real.
 * 
 * @author  JorgeJy2
 * @version 1.0
 *
 */

public class DtoVeterinarian {

	public enum SEX {
		Masculino,
		Femenino
	}
	
	public static enum FIELDS  {
		nombre,correo,cedula,sexo,fecha_nacimiento
	};

	// Atributos de clase
	private int id;
	private String name;
	private String email;
	private String identification;
	private SEX sex;
	private Date dateBirth;

	// Constructores

	// Sin parametros

	public DtoVeterinarian() {
		id = 1;
		name = "NO NAME";
		email = "NO email";
		identification = "NO IDENTIFICATION";
		sex = SEX.Femenino;
		}// cierre constructor

	
	/**
	 * Constructor con parametros
	 * 
	 * @param id
	 * @param name
	 * @param dateBirth
	 * @param email
	 * @param identification
	 * @param sex
	 * @param date
	 */
	public DtoVeterinarian(int id,String name, Date dateBirth, String email, String identification, String sex, String date) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.identification = identification;
		this.sex = SEX.Femenino;
		this.dateBirth = dateBirth;
	}

	// Manejadores getters y setters


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public SEX getSex() {
		return sex;
	}

	public void setSex(SEX sex) {
		this.sex = sex;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date datenew) {
		this.dateBirth = datenew;
	}

	/**
	 * toString
	 * 
	 * Proporciona una vista del estado de la clase.
	 * 
	 * @return valores de los atributos que almacena la instancia.
	 */
	
	@Override
	public String toString() {
		return "DtoPeople [id=" + id + ", name=" + name + ", email=" + email + ", identification=" + identification
				+ ", sex=" + sex + ", date=" + dateBirth + "]";
	}
	

	
}// Cierre clase DtoVeterinarian
