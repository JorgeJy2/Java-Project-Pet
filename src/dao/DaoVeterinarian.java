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
import model.dto.DtoVeterinarian;
import model.dto.DtoVeterinarian.SEX;
import net.sf.jasperreports.engine.JRException;
import report.Report;

/**
 * Archivo: DaoVeterinarian.java contiene la definición de la clase
 * DaoVeterinarian que implementa DaoInterface.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class DaoVeterinarian implements DaoInterface<DtoVeterinarian> {
	// declaración de atributos
	private static final String _ADD = "INSERT INTO veterinario (nombre,correo,cedula,sexo,fecha_nacimiento) VALUES (?,?,?,?,?) RETURNING id";
	private static final String _UPDATE = "UPDATE veterinario SET nombre=?,correo=?,cedula =?,sexo=?,fecha_nacimiento=? WHERE id= ?";
	private static final String _DELETE = "DELETE FROM veterinario WHERE id=?";
	private static final String _SELECT_BASE = "SELECT id,nombre,correo,cedula,sexo,fecha_nacimiento FROM veterinario ";
	private static final String _GET_ONE = _SELECT_BASE + "WHERE id=?";
	private static final String _GET_ALL = _SELECT_BASE + "ORDER BY id DESC";

	private static final String REPORT = "veterinarian.jasper";
	private static final String NAME_REPORT_OUPUT = "reporte_veterinarios.pdf";

	// Métodos implementados de la interface DaoInterface
	/**
	 * Método add
	 * 
	 * @param dto objeto de tipo DtoVeterinarian
	 * @return retorna un objeto
	 * @exception Excepciones de base de datos y de clase
	 */
	@Override
	public Object add(DtoVeterinarian dto) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_ADD);
		preparedStatement.setString(1, dto.getName());
		preparedStatement.setString(2, dto.getEmail());
		preparedStatement.setString(3, dto.getIdentification());
		preparedStatement.setObject(4, dto.getSex(), java.sql.Types.OTHER);
		preparedStatement.setDate(5, dto.getDateBirth());

		ResultSet result = preparedStatement.executeQuery();
		int idResult = -1;

		while (result.next()) {
			idResult = result.getInt(1);
		}

		result.close();
		preparedStatement.close();
		connectionPostgresql.close();
		return idResult;
	}// cierre método add

	/**
	 * Método update
	 * 
	 * @param dto objeto de tipo DtoVeterinarian
	 * @return retorna un valor de tipo booleano
	 * @exception Excepcion de tipo clase y de base de datos
	 */
	@Override
	public boolean update(DtoVeterinarian dto) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_UPDATE);

		preparedStatement.setString(1, dto.getName());
		preparedStatement.setString(2, dto.getEmail());
		preparedStatement.setString(3, dto.getIdentification());
		preparedStatement.setObject(4, dto.getSex(), java.sql.Types.OTHER);
		preparedStatement.setTimestamp(5, java.sql.Timestamp.from(java.time.Instant.now()));
		preparedStatement.setInt(6, dto.getId());

		int tuplasChange = preparedStatement.executeUpdate();
		preparedStatement.close();
		connectionPostgresql.close();

		return (tuplasChange > 0);
	}// cierre MÃ©todo update

	/**
	 * Método delete
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
	}// cierre método delete

	/**
	 * Método get
	 * 
	 * @param key de tipo objeto
	 * @return retorna un objeto de tipo DtoVeterinarian
	 * @exception excepcion de tipo clase y base de datos
	 */
	@Override
	public DtoVeterinarian get(int key) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_GET_ONE);

		preparedStatement.setInt(1,key);

		ResultSet resultSet = preparedStatement.executeQuery();

		DtoVeterinarian dto = new DtoVeterinarian();

		while (resultSet.next()) {
			dto.setName(resultSet.getString(1));
			dto.setEmail(resultSet.getString(2));
			dto.setIdentification(resultSet.getString(3));
			dto.setSex(SEX.valueOf(resultSet.getString(4)));
			dto.setDateBirth(resultSet.getDate(5));
		}

		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();
		return dto;
	}// cierre método get

	/**
	 * Método getAll
	 * 
	 * @return retorna un objeto de tipo Lista
	 * @exception excepcion de tipo clase y base de datos
	 */
	@Override
	public List<DtoVeterinarian> getAll() throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_GET_ALL);

		ResultSet tableResultSet = preparedStatement.executeQuery();

		List<DtoVeterinarian> listPeople = new ArrayList<DtoVeterinarian>();

		while (tableResultSet.next()) {

			DtoVeterinarian dto = new DtoVeterinarian();

			dto.setId(tableResultSet.getInt(1));
			dto.setName(tableResultSet.getString(2));
			dto.setEmail(tableResultSet.getString(3));
			dto.setIdentification(tableResultSet.getString(4));
			dto.setSex(SEX.valueOf(tableResultSet.getString(5)));
			dto.setDateBirth(tableResultSet.getDate(6));

			listPeople.add(dto);
		}

		tableResultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return listPeople;
	}// cierre MÃ©todo getAll

	/**
	 * Método getFilter
	 * 
	 * @param parameter valor de tipo String
	 * @param value     valor de tipo String
	 * @return retorna una lista
	 * @exception excepcion de clase y de base de datos
	 */
	public List<DtoVeterinarian> getFilter(String parameter, String value) throws Exception{
		DtoVeterinarian.FIELDS field = DtoVeterinarian.FIELDS.valueOf(parameter);
		String querySelect = _SELECT_BASE + " WHERE ";

		if (value.length() <= 0) {
			querySelect = _GET_ALL;
		} else {
			switch (field) {
			case nombre:
				querySelect += "UPPER(" + parameter + ") LIKE '%" + value.toUpperCase() + "%'";
				break;
			case correo:
				querySelect += "UPPER(" + parameter + ") LIKE '%" + value.toUpperCase() + "%'";
				break;
			case cedula:
				querySelect += "UPPER(" + parameter + ") LIKE '%" + value.toUpperCase() + "%'";
				break;
			case sexo:
				String temp = value.toLowerCase();
				if("masculino".indexOf(temp) > -1) {
					value = "Masculino";
				}else if("fememino".indexOf(temp) > -1) {
					value = "Femenino";
				}				
				try {	
					DtoVeterinarian.SEX.valueOf(value);
				} catch (Exception e) {
					throw new Exception("Se debe ingresar Femenino/Masculino en sexo");
				}
				querySelect += parameter + "  = '" + value + "'";
				break;
			case fecha_nacimiento:
				try {
					Date.valueOf(value);
				} catch (Exception e) {
					throw new Exception("El formato de fecha ingresado no es el correcto");
				}
				querySelect += parameter + " = '" + value + "'";
				break;
			}
			querySelect += " ORDER BY id DESC";
		}
		
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(querySelect);
		List<DtoVeterinarian> list = new ArrayList<DtoVeterinarian>();
		ResultSet tableResultSet = preparedStatement.executeQuery();

		while (tableResultSet.next()) {

			DtoVeterinarian dto = new DtoVeterinarian();
			dto.setId(tableResultSet.getInt(1));
			dto.setName(tableResultSet.getString(2));
			dto.setEmail(tableResultSet.getString(3));
			dto.setIdentification(tableResultSet.getString(4));
			dto.setSex(SEX.valueOf(tableResultSet.getString(5)));
			dto.setDateBirth(tableResultSet.getDate(6));

			list.add(dto);

		}
		tableResultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();
		return list;
	}// cierre método getFilter

	/**
	 * Método generateReport
	 * 
	 * @param format objeto de tipo FormatReport
	 * @throws ClassNotFoundException excepcion de clase
	 * @throws SQLException           excepcion de base de datos
	 * @throws IOException
	 * @throws JRException
	 */
	public void generateReport(Report format) throws ClassNotFoundException, SQLException, IOException, JRException {

		Report reportPeople = format;
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		reportPeople.setConexion(connectionPostgresql);
		reportPeople.getReport(REPORT);
		reportPeople.complieReport(NAME_REPORT_OUPUT);
		reportPeople.showReport(NAME_REPORT_OUPUT);

		connectionPostgresql.close();
	}// cierre método generateReport
}// cierre clase DaoVeterinarian
