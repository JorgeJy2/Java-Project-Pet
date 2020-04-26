package model.list;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.DaoInterface;
import dao.DaoConsult;
import dao.SaveErrosDao;
import model.dto.DtoConsult;
import model.list.interador.DaoInteractor;
import model.list.interador.Interator;

import report.Report;

/**
 * Archivo: ListConsult.java contiene la definición de la clase ListConsult que
 * implementa de la interfaz Listable.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class ListConsult implements Listable<DtoConsult> {
	// declaración de atributos

	private static final String EMPY_LIST_REPORT = "No existen datos para hacer el reporte de consultas, intente registrar nuevas consultas.";

	private static ListConsult instance;

	private DaoInterface<DtoConsult> _dao;
	private List<DtoConsult> _consults;

	// constructor sin parametros
	private ListConsult() {
		_dao = new DaoConsult();
		_consults = new ArrayList<DtoConsult>();
	}// cierre constructor

	/**
	 * Instancia de la clase ListConsult
	 * 
	 * @return retorna la instancia
	 */
	public static ListConsult getInstance() {

		if (instance == null)
			instance = new ListConsult();

		return instance;
	}// cierre instancia

	/**
	 * Método loadList
	 * 
	 * @throws excepcion de clase y base de datos
	 */
	@Override
	public void loadList() throws ClassNotFoundException, SQLException {

		try {
			this._consults = _dao.getAll();
		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
	}// cierre método loadList

	/**
	 * Método add
	 * 
	 * @param dto objeto de tipo DtoConsult
	 * @return retorna un valor de tipo booleano
	 * @throws excepcion de tipo clase y base de datos
	 */
	@Override
	public boolean add(DtoConsult dto) throws ClassNotFoundException, SQLException {

		try {
			int id_added = (int) _dao.add(dto);
			if (id_added != -1) {
				dto.setId(id_added);
				_consults.add(0, dto);
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
	}// cierre método add

	/**
	 * Método getOne
	 * 
	 * @param position valor de tipo entero
	 * @return retorna un objeto de tipo DtoConsult
	 */
	@Override
	public DtoConsult getOne(int position) {
		return _consults.get(position);
	}// cierre método getOne

	/**
	 * Método delete
	 * 
	 * @param position valor de tipo entero
	 * @return retorna un valor de tipo booleano
	 * @throws exceopcion de tipo clase y base de datos
	 */
	@Override
	public boolean delete(int position) throws ClassNotFoundException, SQLException {
		try {
			if (_dao.delete(_consults.get(position).getId())) {
				_consults.remove(position);
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
	}// cierre método delete

	/**
	 * Método update
	 * 
	 * @param dtoConsult objeto de tipo DtoConsult
	 * @param position   valor de tipo entero
	 * @return retorna una valor de tipo booleano
	 * @throws excepcion de tipo clase y base de datos
	 */
	@Override
	public boolean update(DtoConsult dtoConsult, int position) throws ClassNotFoundException, SQLException {
		try {
			if (_dao.update(dtoConsult)) {
				_consults.set(position, dtoConsult);
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
	}// cierre método update

	/**
	 * Método getList
	 * 
	 * @return retorna un objeto de tipo List
	 */
	@Override
	public List<DtoConsult> getList() {
		return _consults;
	}// cierre método getList

	/**
	 * Método getAll
	 * 
	 * @return retorna un objeto de tipo Interator
	 */
	@Override
	public Interator<DtoConsult> getAll() {
		return new DaoInteractor<DtoConsult>(_consults);
	}// cierre método getAll

	/**
	 * Método sizeDtos
	 * 
	 * @return retorna un valor de tipo entero
	 */
	@Override
	public int sizeDtos() {
		return _consults.size();
	}// cierre método sizeDtos

	/**
	 * Método loadListFilter
	 * 
	 * @param parameter valor de tipo String
	 * @param value     valor de tipo String
	 * @throws excepcion de tipo clase y base de datos
	 */
	@Override
	public void loadListFilter(String parameter, String value) throws Exception {
		try {
			_consults = _dao.getFilter(parameter, value);
		} catch (Exception e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		}
	}// cierre método loadListFilter

	/**
	 * Método getReport
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
			_dao.generateReport(format);
			return true;
		}
	}// cierre método getReport

}// cierre clase ListConsult
