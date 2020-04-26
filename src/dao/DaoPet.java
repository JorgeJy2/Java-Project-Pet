package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.PoolConnection;
import model.dto.DtoPet;
import net.sf.jasperreports.engine.JRException;
import report.Report;

/**
 * Archivo: DaoPet.java contiene la definici�n de la clase DaoPet que implementa
 * DaoInterface.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */

public class DaoPet implements DaoInterface<DtoPet> {
	// declaraci�n de atributos
	private static final String _ADD = "INSERT INTO mascota(nombre,fecha_nacimiento,raza,peso,color) VALUES (?,?,?,?,?) RETURNING id";
	private static final String _UPDATE = "UPDATE mascota SET nombre = ? , fecha_nacimiento  = ?, raza  = ?,peso  = ?, color = ? WHERE id = ?";
	private static final String _DELETE = "DELETE FROM mascota WHERE id = ?";
	private static final String _SELECT_BASE = "SELECT id,nombre,fecha_nacimiento,raza,peso,color FROM mascota ";
	private static final String _GET_ONE = "WHERE id = ?";
	private static final String _GET_ALL = _SELECT_BASE + "ORDER BY id DESC";
	private static final String REPORT = "pet.jasper";
	private static final String NAME_REPORT_OUPUT = "reporte_mascotas.pdf";

	/**
	 * M�todo add
	 * 
	 * @param dto objeto de tipo DtoPet
	 * @return retorna un objeto
	 * @exception Excepciones de base de datos y de clase
	 */
	@Override
	public Object add(DtoPet dto) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_ADD);
		preparedStatement.setString(1, dto.getName());
		preparedStatement.setDate(2, dto.getDateBirth());
		preparedStatement.setString(3, dto.getRace());
		preparedStatement.setDouble(4, dto.getWeight());
		preparedStatement.setString(5, dto.getColor());

		ResultSet resultSet = preparedStatement.executeQuery();

		int idAdded = -1;

		while (resultSet.next()) {
			idAdded = resultSet.getInt(1);
		}

		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();
		return idAdded;
	}// cierre m�todo add

	/**
	 * M�todo update
	 * 
	 * @param dto objeto de tipo DtoPet
	 * @return retorna un valor de tipo booleano
	 * @exception Excepcion de tipo clase y de base de datos
	 */

	@Override
	public boolean update(DtoPet dto) throws SQLException, ClassNotFoundException {

		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_UPDATE);

		preparedStatement.setString(1, dto.getName());
		preparedStatement.setDate(2, dto.getDateBirth());
		preparedStatement.setString(3, dto.getRace());
		preparedStatement.setDouble(4, dto.getWeight());
		preparedStatement.setString(5, dto.getColor());
		preparedStatement.setInt(6, dto.getId());

		int resultUpdate = preparedStatement.executeUpdate();

		preparedStatement.close();
		connectionPostgresql.close();

		return (resultUpdate > 0);
	}// cierre Método update

	/**
	 * M�todo delete
	 * 
	 * @param key de tipo objeto
	 * @return retorna valor de tipo booleano
	 * @exception Excepcion de tipo clase y de base de datos
	 */
	@Override
	public boolean delete(int key) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_DELETE);

		preparedStatement.setInt(1,key);

		int result = preparedStatement.executeUpdate();

		preparedStatement.close();
		connectionPostgresql.close();

		return (result > 0);
	}// cierre Método delete

	/**
	 * M�todo get
	 * 
	 * @param key de tipo objeto
	 * @return retorna un objeto de tipo DtoPet
	 * @exception excepcion de tipo clase y base de datos
	 */
	@Override
	public DtoPet get(int key) throws SQLException, ClassNotFoundException {

		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_SELECT_BASE + _GET_ONE);

		preparedStatement.setInt(1,key);

		ResultSet resultSet = preparedStatement.executeQuery();

		DtoPet resultDao = new DtoPet();

		while (resultSet.next()) {
			resultDao.setId(resultSet.getInt(1));
			resultDao.setName(resultSet.getString(2));
			resultDao.setDateBirth(resultSet.getDate(3));
			resultDao.setRace(resultSet.getString(4));
			resultDao.setWeight(resultSet.getDouble(5));
			resultDao.setColor(resultSet.getString(6));
		}

		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return resultDao;
	}// cierre Método get

	/**
	 * M�todo getAll
	 * 
	 * @return retorna un objeto de tipo Lista
	 * @exception excepcion de tipo clase y base de datos
	 */
	@Override
	public List<DtoPet> getAll() throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_GET_ALL);
		ResultSet resultSet = preparedStatement.executeQuery();

		List<DtoPet> list = new ArrayList<DtoPet>();

		while (resultSet.next()) {
			DtoPet resultDao = new DtoPet();
			resultDao.setId(resultSet.getInt(1));
			resultDao.setName(resultSet.getString(2));
			resultDao.setDateBirth(resultSet.getDate(3));
			resultDao.setRace(resultSet.getString(4));
			resultDao.setWeight(resultSet.getDouble(5));
			resultDao.setColor(resultSet.getString(6));
			list.add(resultDao);
		}

		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return list;
	}// cierre M�todo getAll

	/**
	 * M�todo getFilter
	 * 
	 * @param parameter valor de tipo String
	 * @param value     valor de tipo String
	 * @return retorna una lista
	 * @throws Exception 
	 * @exception excepcion de clase y de base de datos
	 */
	@Override
	public List<DtoPet> getFilter(String parameter, String value) throws Exception {
		
		DtoPet.FIELDS field = DtoPet.FIELDS.valueOf(parameter);
		String querySelect = _SELECT_BASE + " WHERE ";

		if (value.length() <= 0) {
			querySelect = _GET_ALL;
		} else {
			switch (field) {
			case nombre:
				querySelect += "UPPER(" + parameter + ") LIKE '%" + value.toUpperCase() + "%'";
				break;
			case fecha_nacimiento:
				try {
					Date.valueOf(value);
				} catch (Exception e) {
					throw new Exception("El formato de fecha ingresado no es el correcto");
				}
				querySelect += parameter + " = '" + value + "'";
				break;
			case raza:
				querySelect += "UPPER(" + parameter + ") LIKE '%" + value.toUpperCase() + "%'";
				break;
			case peso:
				try {
					Double.parseDouble(value);
				} catch (Exception e) {
					throw new Exception("S�lo se permiten datos decimales en peso.");
				}
				querySelect += parameter + " = " + value + "";
				break;
			case color:
				querySelect += "UPPER(" + parameter + ") LIKE '%" + value.toUpperCase() + "%'";
				break;
			}

			querySelect += " ORDER BY id DESC";
		}
	
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = null;

		preparedStatement = connectionPostgresql.prepareStatement(querySelect);

		ResultSet resultSet = preparedStatement.executeQuery();
		List<DtoPet> list = new ArrayList<DtoPet>();
		while (resultSet.next()) {
			DtoPet resultDao = new DtoPet();
			resultDao.setId(resultSet.getInt(1));
			resultDao.setName(resultSet.getString(2));
			resultDao.setDateBirth(resultSet.getDate(3));
			resultDao.setRace(resultSet.getString(4));
			resultDao.setWeight(resultSet.getDouble(5));
			resultDao.setColor(resultSet.getString(6));
			list.add(resultDao);
		}
		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return list;
	}// cierre m�todo getFilter

	/**
	 * M�todo generateReport
	 * 
	 * @param format objeto de tipo FormatReport
	 * @throws ClassNotFoundException excepcion de clase
	 * @throws SQLException           excepcion de base de datos
	 * @throws JRException
	 * @throws IOException
	 */
	public void generateReport(Report format) throws ClassNotFoundException, SQLException, JRException, IOException {
		Report reportPeople = format;
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		reportPeople.setConexion(connectionPostgresql);
		reportPeople.getReport(REPORT);
		reportPeople.complieReport(NAME_REPORT_OUPUT);
		reportPeople.showReport(NAME_REPORT_OUPUT);

		connectionPostgresql.close();
	}// cierre m�todo generateReport

}// cierre clase DaoPet
