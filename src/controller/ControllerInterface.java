package controller;

/**
 * Archivo: ControllerInterface.java contiene la definici�n de la interface
 * ControllerInterface que establece los M�todos que deben implementar las
 * clases que extiendan de esta.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public interface ControllerInterface {
	// M�todos de la interfaz
	/**
	 * M�todo saveRegistry
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean saveRegistry();

	/**
	 * M�todo filter
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean filter();

	/**
	 * M�todo updateRegistry
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean updateRegistry();

	/**
	 * M�todo deleteRegistry
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean deleteRegistry();

	/**
	 * M�todo getDataOfView
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean getDataOfView();

	/**
	 * M�todo setDataOfView
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean setDataOfView(boolean newRegistry);

	/**
	 * M�todo reloadData
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean reloadData();

	/**
	 * M�todo addListener
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean addListener();
}// cierre interface ControllerInterface
