package dao;

import java.io.IOException;
/**
 * 
 * Archivo: DaoInterface.java
 * 
 * Objetivo: Proporcionar una interfaz para unificar las operaciones que se realizan
 * por medio del patrï¿½n de diseï¿½o DAO, generalizando la transmiciï¿½n en una clase genï¿½rica.
 * 
 * @author.
 * @version 1.0 
 *  @param <Model> Modelo
 * 
 */
import java.sql.SQLException;

import java.util.List;

import net.sf.jasperreports.engine.JRException;
import report.Report;

public interface DaoInterface<Model> {

	/**
	 * add
	 * 
	 * Agrega los valores de los atributos de la clase DTO generica con la que se
	 * está trabajando.
	 * 
	 * @param dto
	 * @return Instancia de tipo de dato primitivo
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @author JorgeJy2
	 */
	Object add(Model dto) throws SQLException, ClassNotFoundException;

	/**
	 * update
	 * 
	 * Actualiza los valores de los atributos de la clase DTO generica con la que se
	 * está trabajando.
	 * 
	 * @param dto
	 * @return booleano (True sí la operación es correcta, False sí no se pudo
	 *         realizar la operación)
	 * @throws SQLException
	 * @throws ClssNotFoundException
	 */
	boolean update(Model dto) throws SQLException, ClassNotFoundException;

	/**
	 * delete
	 * 
	 * Elimina el registro que contenga la clave recibida.
	 * 
	 * @param key
	 * @return booleano (True sí la operación es correcta, False sí no se pudo
	 *         realizar la operación)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	boolean delete(int key) throws SQLException, ClassNotFoundException;

	/**
	 * get
	 * 
	 * Obtiene una instancia con los valores del registro que contenga la clave
	 * recibida.
	 * 
	 * 
	 * @param key
	 * @return instancia de DTO generico con el que se está trabajando
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	Model get(int key) throws SQLException, ClassNotFoundException;

	/**
	 * get all
	 * 
	 * Obtiene una istancia de la interface {@link List} con todos los registros
	 * almacenados de tipo Dto generico.
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<Model> getAll() throws SQLException, ClassNotFoundException;

	List<Model> getFilter(String parameter, String value) throws Exception ;

	void generateReport(Report format) throws ClassNotFoundException, SQLException, JRException, IOException;

}
