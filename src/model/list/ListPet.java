package model.list;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoPet;
import dao.DaoInterface;

import dao.SaveErrosDao;

import model.dto.DtoPet;

import model.list.interador.DaoInteractor;
import model.list.interador.Interator;

import report.Report;

/**
 * Archivo: ListPet.java contiene la definición de la clase ListPet que
 * implementa Listable<DtoPet>.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class ListPet implements Listable<DtoPet> {
	// declaración de atrtibutoa
	private static final String EMPY_LIST_REPORT = "No existen datos para hacer el reporte de mascota, intente registrar nuevas mascota.";

	private static ListPet _instance;
	private DaoInterface<DtoPet> _dao;
	private List<DtoPet> _list;

	// constructor sin parámetros
	protected ListPet() {
		_list = new ArrayList<DtoPet>();
		_dao = new DaoPet();
	}// cierre constructor

	/**
	 * Instancia de la clase ListCar
	 * 
	 * @return retorna la instancia
	 */
	public static ListPet getInstance() {
		if (_instance == null) {
			_instance = new ListPet();
		}
		return _instance;
	}// cierre instancia

	/**
	 * Método getList
	 * 
	 * @return retorna la lista de carros
	 */
	@Override
	public List<DtoPet> getList() {
		return _list;
	}// cierre método getList

	/**
	 * Método loadList
	 * 
	 * @throws excepcion de clase y base de datos
	 */
	@Override
	public void loadList() throws ClassNotFoundException, SQLException {
		try {
			_list = _dao.getAll();
		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
	}// cierre método loadList

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
			_list = _dao.getFilter(parameter, value);
		} catch (Exception e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		}
	}// cierre método loadListFilter

	/**
	 * Método add
	 * 
	 * @param dtoPet objeto de tipo DtoPet
	 * @return retorna un valor de tipo booleano
	 * @throws excepcion de tipo clase y base de datos
	 */
	@Override
	public boolean add(DtoPet dtoPet) throws ClassNotFoundException, SQLException {
		try {
			int id_added = (int) _dao.add(dtoPet);
			if (id_added != -1) {
				dtoPet.setId(id_added);
				_list.add(0, dtoPet);
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
	}// cierre MÃ©todo add

	/**
	 * Método getOne
	 * 
	 * @param position valor de tipo entero
	 * @return retorna un objeto de tipo DtoPet
	 */
	@Override
	public DtoPet getOne(int position) {
		return _list.get(position);
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
			if (_dao.delete(_list.get(position).getId())) {
				_list.remove(position);
				return true;
			} else {
				System.out.println("No se pudo borrar....");
			}
			return false;
		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
	}// cierre MÃ©todo delete

	/**
	 * Método update
	 * 
	 * @param dtoPet   objeto de tipo DtoPet
	 * @param position valor de tipo entero
	 * @return retorna una valor de tipo booleano
	 * @throws excepcion de tipo clase y base de datos
	 */
	@Override
	public boolean update(DtoPet dtoPet, int position) throws ClassNotFoundException, SQLException {

		try {
			if (_dao.update(dtoPet)) {
				_list.set(position, dtoPet);
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
	 * Método getAll
	 * 
	 * @return retorna un objeto de tipo Interator
	 */
	@Override
	public Interator<DtoPet> getAll() {
		return new DaoInteractor<DtoPet>(_list);
	}// cierre método Interator

	/**
	 * Método sizeDtos
	 * 
	 * @return retorna un valor de tipo entero
	 */
	@Override
	public int sizeDtos() {
		return _list.size();
	}// cierre método sizeDtos

	/**
	 * método getReport
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
	}// cierre de método getReport

}// cierre de clase ListPet
