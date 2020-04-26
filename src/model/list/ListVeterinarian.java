package model.list;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoInterface;
import dao.DaoVeterinarian;
import dao.SaveErrosDao;
import model.dto.DtoVeterinarian;

import model.list.interador.DaoInteractor;
import model.list.interador.Interator;

import report.Report;

/**
 * Archivo: ListVeterinarian.java contiene la definici�n de la clase
 * ListVeterinarian que implementa la interfaz Listable.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class ListVeterinarian implements Listable<DtoVeterinarian> {
	// declaraci�n de atributos

	private static final String EMPY_LIST_REPORT = "No existen datos para hacer el reporte de veterinarios, intente registrar nuevos veterinarios.";
	private List<DtoVeterinarian> _listVeterinarian;
	private DaoInterface<DtoVeterinarian> _daoVeterinarian;

	private static ListVeterinarian _instance;

	// constructor si par�metros
	protected ListVeterinarian() {
		_listVeterinarian = new ArrayList<DtoVeterinarian>();
		_daoVeterinarian = new DaoVeterinarian();
	}// cierre constructor

	/**
	 * Instancia de la clase ListVeterinarian
	 * 
	 * @return retorna la instancia
	 */
	public static ListVeterinarian getInstance() {
		if (_instance == null) {
			_instance = new ListVeterinarian();
		}
		return _instance;
	}// cierre de instancia

	/**
	 * M�todo getList
	 * 
	 * @return retorna la lista de personas
	 */
	@Override
	public List<DtoVeterinarian> getList() {
		return _listVeterinarian;
	}// cierre m�todo getList

	/**
	 * M�todo loadList
	 * 
	 * @throws excepcion de clase y base de datos
	 */
	@Override
	public void loadList() throws ClassNotFoundException, SQLException {

		try {
			_listVeterinarian = _daoVeterinarian.getAll();
		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
	}// cierre m�todo loadList

	/**
	 * M�todo add
	 * 
	 * @param dtoVeterinarian objeto de tipo DtoVeterinarian
	 * @return retorna un valor de tipo booleano
	 * @throws excepcion de tipo clase y base de datos
	 */
	@Override
	public boolean add(DtoVeterinarian dtoVeterinarian) throws ClassNotFoundException, SQLException {

		try {
			int id_added = (int) _daoVeterinarian.add(dtoVeterinarian);
			if (id_added != -1) {
				dtoVeterinarian.setId(id_added);
				_listVeterinarian.add(0, dtoVeterinarian);
				return true;
			}

			return false;

		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}

	}// cierre m�todo add

	/**
	 * M�todo getOne
	 * 
	 * @param position valor de tipo entero
	 * @return retorna un objeto de tipo DtoPeople
	 */
	@Override
	public DtoVeterinarian getOne(int position) {
		return _listVeterinarian.get(position);
	}// cierre m�todo getOne

	/**
	 * M�todo delete
	 * 
	 * @param position valor de tipo entero
	 * @return retorna un valor de tipo booleano
	 * @throws exceopcion de tipo clase y base de datos
	 */
	@Override
	public boolean delete(int position) throws ClassNotFoundException, SQLException {

		try {
			if (_daoVeterinarian.delete(_listVeterinarian.get(position).getId())) {
				_listVeterinarian.remove(position);
				return true;
			}
			return false;

		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
	}// cierre m�todo delete

	/**
	 * M�todo update
	 * 
	 * @param dtoVeterinarian objeto de tipo DtoVeterinarian
	 * @param position        valor de tipo entero
	 * @return retorna una valor de tipo booleano
	 * @throws excepcion de tipo clase y base de datos
	 */
	@Override
	public boolean update(DtoVeterinarian dtoVeterinarian, int position) throws ClassNotFoundException, SQLException {

		try {
			if (_daoVeterinarian.update(dtoVeterinarian)) {
				_listVeterinarian.set(position, dtoVeterinarian);
				return true;
			}
			return false;

		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
	}// cierre m�todo update

	/**
	 * M�todo getAll
	 * 
	 * @return retorna un objeto de tipo Interator
	 */
	@Override
	public Interator<DtoVeterinarian> getAll() {
		return new DaoInteractor<DtoVeterinarian>(_listVeterinarian);
	}// cierre m�todo getAll

	/**
	 * M�todo sizeDtos
	 * 
	 * @return retorna un valor de tipo entero
	 */
	@Override
	public int sizeDtos() {
		return _listVeterinarian.size();
	}// cierre m�todo sizeDtos

	/**
	 * M�todo loadListFilter
	 * 
	 * @param parameter valor de tipo String
	 * @param value     valor de tipo String
	 * @throws excepcion de tipo clase y base de datos
	 */
	@Override
	public void loadListFilter(String parameter, String value) throws Exception {		
		try {
			_listVeterinarian = _daoVeterinarian.getFilter(parameter, value);
		} catch (Exception e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		}
	}

	/**
	 * M�todo getReport
	 * 
	 * @param format objeto de tipo FormatReport
	 * @return retorna un valor de tipo booleano
	 * @throws Exception
	 */
	@Override
	public boolean getReport(Report format) throws Exception {
		if (sizeDtos() <= 0) {
			throw new Exception(EMPY_LIST_REPORT);
		} else {
			_daoVeterinarian.generateReport(format);
			return true;
		}
	}// cierre m�todo getReport
}// cierre clase ListVeterinarian
