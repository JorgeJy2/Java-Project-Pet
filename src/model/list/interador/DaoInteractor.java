package model.list.interador;

import java.util.List;

/**
 * Archivo: DaoInterface.java contiene la definici�n de la clase DaoInterface
 * que implementa de Interator.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class DaoInteractor<Dto> implements Interator<Dto> {
	// declaraci�n de atributos
	private List<Dto> list;
	private int index = 0;

	/**
	 * Constructor con par�metros
	 * 
	 * @param list objeto de tipo List
	 */
	public DaoInteractor(List<Dto> list) {
		this.list = list;
	}// cierre constructor

	// Implementaci�n de los m�todos de la interface Interator
	@Override
	public Dto first() {
		index = 0;
		return list.get(index);
	}

	@Override
	public Dto next() {
		return hasNext() ? list.get(index++) : null;
	}

	@Override
	public boolean hasNext() {
		return index < list.size();
	}

	@Override
	public boolean hasBefore() {
		return index < -1;
	}

	@Override
	public Dto before() {
		return hasBefore() ? list.get(index--) : null;
	}

	@Override
	public int now() {
		return index;
	}

}// cierre clase DaoInteractor
