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
import model.dto.DtoConsult;
import model.dto.DtoConsult.STATUS;
import net.sf.jasperreports.engine.JRException;
import report.Report;

/**
 * Archivo: DaoConsult.java contiene la definición de la clase DaoConsult que
 * implementa DaoInterface.
 * 
 * @author JorgeJy2
 * @version 1.0
 *
 */
public class DaoConsult implements DaoInterface<DtoConsult> {
	// declaración de atributos

	private static final String _BASE_SELECT = "SELECT c.id,id_mascota,m.nombre,id_veterinario,v.nombre,fecha,costo,descripcion,estatus FROM consulta c INNER JOIN mascota m ON c.id_mascota = m.id INNER JOIN veterinario v ON c.id_veterinario = v.id ";
	private static final String _ADD = "INSERT INTO consulta (id_mascota,id_veterinario,fecha,costo,descripcion,estatus ) VALUES (?,?,?,?,?,?) RETURNING id";
	private static final String _GET_ALL = _BASE_SELECT + "ORDER BY c.id DESC";
	private static final String _GET_ONE = _BASE_SELECT + "WHERE c.id = ?";
	private static final String _UPDATE = "UPDATE consulta SET fecha = ?,costo = ?,descripcion  = ?,estatus =  ?, id_mascota = ?,id_veterinario = ?  WHERE id = ?";
	private static final String _DELETE = "DELETE FROM consulta WHERE id = ?";

	private static final String REPORT = "consult.jasper";
	private static final String NAME_REPORT_OUPUT = "reporte_consultas.pdf";

	/**
	 * 
	 * Método add
	 * 
	 * @param dto objeto de tipo DtoConsult
	 * @return retorna un objeto
	 * @exception Excepciones de base de datos y de clase
	 */
	@Override
	public Object add(DtoConsult dto) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_ADD);
		preparedStatement.setInt(1, dto.getIdPet());
		preparedStatement.setInt(2, dto.getIdVeterinarian());
		preparedStatement.setDate(3, dto.getDate());
		preparedStatement.setDouble(4, dto.getCost());
		preparedStatement.setString(5, dto.getDescription());
		preparedStatement.setObject(6, dto.getStatus(), java.sql.Types.OTHER);
		ResultSet resultSet = preparedStatement.executeQuery();

		int resultId = -1;
		while (resultSet.next()) {
			resultId = resultSet.getInt(1);
		}

		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return resultId;
	}// cierre MÃ©todo add

	/**
	 * Método update
	 * 
	 * @param dto objeto de tipo DtoConsult
	 * @return retorna un valor de tipo booleano
	 * @exception Excepcion de tipo clase y de base de datos
	 */
	@Override
	public boolean update(DtoConsult dto) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_UPDATE);
		preparedStatement.setDate(1, dto.getDate());
		preparedStatement.setDouble(2, dto.getCost());
		preparedStatement.setString(3, dto.getDescription());

		preparedStatement.setObject(4, dto.getStatus(), java.sql.Types.OTHER);
		preparedStatement.setInt(5, dto.getIdPet());
		preparedStatement.setInt(6, dto.getIdVeterinarian());
		preparedStatement.setInt(7, dto.getId());

		int resultUpdate = preparedStatement.executeUpdate();

		preparedStatement.close();
		connectionPostgresql.close();
		return (resultUpdate > 0);
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
		preparedStatement.setInt(1, key);
		int resultDelete = preparedStatement.executeUpdate();
		preparedStatement.close();
		connectionPostgresql.close();
		return (resultDelete > 0);
	}// cierre método delete

	/**
	 * Método get
	 * 
	 * @param key de tipo objeto
	 * @return retorna un objeto de tipo DtoConsult
	 * @exception excepcion de tipo clase y base de datos
	 */
	@Override
	public DtoConsult get(int key) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_GET_ONE);

		preparedStatement.setInt(1,key);

		ResultSet resultSet = preparedStatement.executeQuery();

		DtoConsult dto = new DtoConsult();
		while (resultSet.next()) {
			dto.setId(resultSet.getInt(1));
			dto.setIdPet(resultSet.getInt(2));
			dto.setPetName(resultSet.getString(3));
			dto.setIdVeterinarian(resultSet.getInt(4));
			dto.setVeterinarianEmail(resultSet.getString(5));
			dto.setDate(resultSet.getDate(6));
			dto.setCost(resultSet.getDouble(7));
			dto.setDescription(resultSet.getString(8));
			dto.setStatus(STATUS.valueOf(resultSet.getString(9)));
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
	public List<DtoConsult> getAll() throws SQLException, ClassNotFoundException {

		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_GET_ALL);

		ResultSet resultSet = preparedStatement.executeQuery();

		List<DtoConsult> list = new ArrayList<DtoConsult>();

		while (resultSet.next()) {

			DtoConsult dto = new DtoConsult();
			dto.setId(resultSet.getInt(1));
			dto.setIdPet(resultSet.getInt(2));
			dto.setPetName(resultSet.getString(3));
			dto.setIdVeterinarian(resultSet.getInt(4));
			dto.setVeterinarianEmail(resultSet.getString(5));
			dto.setDate(resultSet.getDate(6));
			dto.setCost(resultSet.getDouble(7));
			dto.setDescription(resultSet.getString(8));
			dto.setStatus(STATUS.valueOf(resultSet.getString(9)));

			list.add(dto);
		}

		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return list;
	}// cierre MÃ©todo getAll

	/**
	 * Método getFilter
	 * 
	 * @param parameter valor de tipo String
	 * @param value     valor de tipo String
	 * @return retorna una lista
	 * @exception excepcion de clase y de base de datos
	 */
	public List<DtoConsult> getFilter(String parameter, String value) throws Exception {
		DtoConsult.FIELDS field = DtoConsult.FIELDS.valueOf(parameter);
		String querySelect = _BASE_SELECT + " WHERE ";

		if (value.length() <= 0) {
			querySelect = _GET_ALL;
		} else {
			switch (field) {
			case mascota:
				querySelect +="UPPER(m.nombre) LIKE '%" + value.toUpperCase() + "%'";
				break;
			case veterinario:
				querySelect +="UPPER(v.nombre) LIKE '%" + value.toUpperCase() + "%'";
				break;
			case fecha:
				try {
					Date.valueOf(value);
				} catch (Exception e) {
					throw new Exception("El formato de fecha ingresado no es el correcto");
				}
				querySelect += parameter + "  = '" + value + "'";
				break;
			case descripcion:
				querySelect += "UPPER(" + parameter + ") LIKE '%" + value.toUpperCase() + "%'";
				break;
			case costo:
				try {
					Double.parseDouble(value);
				} catch (Exception e) {
					throw new Exception("Sólo se permiten datos decimales en costo.");
				}
				querySelect += parameter + " = " + value + "";
				break;
			case estatus:
				
				String temp = value.toLowerCase();
				
				System.out.println(temp);
				
				if("primer_visita".indexOf(temp) > -1) {
					value = "Primer_visita";
				}else if("revision".indexOf(temp) > -1) {
					value = "Revision";
				}else  if("terminado".indexOf(temp) > -1) {
					value = "Terminado";
				} 			
				
				try {
					DtoConsult.STATUS.valueOf(value);
				} catch (Exception e) {
					throw new Exception("Se debe ingresar Primer_visita,Revison o Terminado en estatus");
				}
				querySelect += parameter + "  = '" + value + "'";
				break;
			}
			querySelect += " ORDER BY id DESC";
		}
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(querySelect);
		ResultSet resultSet = preparedStatement.executeQuery();

		List<DtoConsult> list = new ArrayList<DtoConsult>();

		while (resultSet.next()) {

			DtoConsult dto = new DtoConsult();
			dto.setId(resultSet.getInt(1));
			dto.setIdPet(resultSet.getInt(2));
			dto.setPetName(resultSet.getString(3));
			dto.setIdVeterinarian(resultSet.getInt(4));
			dto.setVeterinarianEmail(resultSet.getString(5));
			dto.setDate(resultSet.getDate(6));
			dto.setCost(resultSet.getDouble(7));
			dto.setDescription(resultSet.getString(8));
			dto.setStatus(STATUS.valueOf(resultSet.getString(9)));
			list.add(dto);

		}
		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return list;
	}// cierre método getFilter

	@Override
	public void generateReport(Report format) throws ClassNotFoundException, SQLException, JRException, IOException {
		Report reportPeople = format;

		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		reportPeople.setConexion(connectionPostgresql);
		reportPeople.getReport(REPORT);
		reportPeople.complieReport(NAME_REPORT_OUPUT);
		reportPeople.showReport(NAME_REPORT_OUPUT);

		connectionPostgresql.close();
	}
}
