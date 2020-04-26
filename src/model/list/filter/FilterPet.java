package model.list.filter;

import java.util.List;
import java.util.stream.Collectors;

import model.dto.DtoPet;
import model.list.ListPet;

/**
 * Archivo: FilterPet.java contiene la definici�n de la clase FilterPet que
 * extiende de ListPet.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class FilterPet extends ListPet {
	// declaraci�n de atributo
	private String value;

	/**
	 * Contructor con par�metro
	 * 
	 * @param value valor de tipo String
	 */
	public FilterPet(String value) {
		this.value = value;
	}// cierre constructor

	/**
	 * M�todo getList
	 * 
	 * @return retorna un objeto de tipo List
	 */
	@Override
	public List<DtoPet> getList() {
		return super.getList().stream().filter((DtoPet pet) -> {
			return pet.getName().equals(value);
		}).collect(Collectors.toList());
	}// cierre M�todo getList
}// Cierre clase FilterCar
