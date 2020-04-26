package model.list.filter;

import java.util.List;
import java.util.stream.Collectors;
import model.dto.DtoVeterinarian;
import model.list.ListVeterinarian;

/**
 * Archivo: FilterVeterinarian.java contiene la definici�n de la clase FilterVeterinarian
 * que extiende de ListVeterinarian.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class FilterVeterinarian extends ListVeterinarian {
	// declaraci�n de atributo
	private String value;

	/**
	 * Constructor con par�metro
	 * 
	 * @param value valor de tipo String
	 */
	public FilterVeterinarian(String value) {
		this.value = value;
	}// cierre constructor

	/**
	 * M�todo getList
	 * 
	 * @return retorna un objeto de tipo lista.
	 */
	@Override
	public List<DtoVeterinarian> getList() {
		return super.getList().stream().filter((DtoVeterinarian veterinarian) -> {
			return veterinarian.getName().equals(value);
		}).collect(Collectors.toList());
	}// cierre m�todo getList
}// cierre clase FilterVeterinarian
