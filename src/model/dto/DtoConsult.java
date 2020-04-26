package model.dto;

import java.sql.Date;

/**
 * Archivo: DtoConsult.java
 * 
 * Objetivo: Representa la estructura de un consulta de un veterinario en el
 * mundo real.
 * 
 * @author JorgeJy2
 * @version 1.0 Contiene la composición de las clases:
 */
public class DtoConsult {

	public enum STATUS {
		Primer_visita, Revision, Terminado
	}
	
	public static enum FIELDS  {
		mascota,veterinario,fecha,descripcion,costo,estatus
	};

	// Atributos de clase
	private int id;

	private int idPet;
	private int idVeterinarian;
	private Date date;
	private double cost;
	private String description;
	private STATUS status;
	private String veterinarianEmail;
	private String petName;

	// Constructores

	// Sin parametro
	public DtoConsult() {
		this.id = 1;
		idPet = 1;
		idVeterinarian = 1;
		cost = 10.0;
		description = "NO DESCRIPTION";
		status = STATUS.Primer_visita;
		veterinarianEmail = "No email";
		petName = "No pet";
	}// cierre constructor

	/**
	 * Constructor con parametros
	 * 
	 * @param id
	 * @param idPet
	 * @param idVeterinarian
	 * @param date
	 * @param cost
	 * @param description
	 * @param status
	 * @param veterinarianEmail
	 * @param petName
	 */
	public DtoConsult(int id, int idPet, int idVeterinarian, Date date, double cost, String description,
			STATUS status, String veterinarianEmail, String petName) {
		super();
		this.id = id;
		this.idPet = idPet;
		this.idVeterinarian = idVeterinarian;
		this.date = date;
		this.cost = cost;
		this.description = description;
		this.status = status;
		this.veterinarianEmail = veterinarianEmail;
		this.petName = petName;
	}

	// getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPet() {
		return idPet;
	}

	public void setIdPet(int idPet) {
		this.idPet = idPet;
	}

	public int getIdVeterinarian() {
		return idVeterinarian;
	}

	public void setIdVeterinarian(int idVeterinarian) {
		this.idVeterinarian = idVeterinarian;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public String getVeterinarianEmail() {
		return veterinarianEmail;
	}

	public void setVeterinarianEmail(String veterinarianEmail) {
		this.veterinarianEmail = veterinarianEmail;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
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
		return "DtoTicket [id=" + id + ", idPet=" + idPet + ", idVeterinarian=" + idVeterinarian + ", date=" + date
				+ ", cost=" + cost + ", description=" + description + ", status=" + status + ", veterinarianEmail="
				+ veterinarianEmail + ", petName=" + petName + "]";
	}

}// cierre clase DtoConsult
