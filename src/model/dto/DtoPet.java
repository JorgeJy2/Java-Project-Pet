package model.dto;

import java.sql.Date;

/**
 * Archivo: DtoPet.java
 * 
 * Objetivo: Representa la estructura de una mascota en el mundo real.
 * 
 * @author  JorgeJy2
 * @version 1.0
 *
 */

public class DtoPet {
	// Atributos de clase
	private int id;

	private String name;
	private String race;
	private double weight;
	private String color;
	private Date dateBirth;
	
	public static enum FIELDS  {
			nombre,fecha_nacimiento,raza,peso,color
	};

	// Constructores
	// Constructor sin parametros
	public DtoPet() {
		name = "NO NAME";
		race = "NO RACE";
		weight = 0.0;
		color = "NO COLOR";
	}// cierre constructor


	/**
	 * Constructor con parametros 
	 * 
	 * @param id
	 * @param name
	 * @param dateBirth
	 * @param race
	 * @param weight
	 * @param color
	 */
	public DtoPet(int id, String name, Date dateBirth, String race, double weight, String color) {
		super();
		this.id = id;
		this.name = name;
		this.dateBirth = dateBirth;
		this.race = race;
		this.weight = weight;
		this.color = color;
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

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
		return "DtoPet [id=" + id + ", name=" + name + ", date=" + dateBirth + ", race=" + race + ", weight=" + weight
				+ ", color=" + color + "]";
	}

}// cierre clase DtoPet
