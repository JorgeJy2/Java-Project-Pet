package connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Archivo: PoolConnection.java contiene la definici�n de la clase
 * PoolConnection.
 * 
 * @version 1.0
 *
 */
public class PoolConnection {
	// declaraci�n de atributos
	
	private static final String _DRIVER = "org.postgresql.Driver";
	private static final String _JDBC = "jdbc:postgresql://";
	
	private static final String _HOST = "127.0.0.1:5432";
	private static final String _DB_NAME = "veterinaria";
	private static final String _USER = "postgres";
	private static final String _PASSWORD = "123456789";

	private static final String _URL = _JDBC + _HOST + "/" + _DB_NAME;
	private static BasicDataSource basicDataSource;
	private static Connection connectionToPool;
	private static PoolConnection instance;

	// Constructor sin par�metros
	private PoolConnection() {
		initConfiguration();
	}

	/**
	 * M�todo getInstancePool(). Genera la instancia de la clase PoolConnection.
	 * 
	 * @return retorna la instancia de la clase PoolConnection.
	 */
	public static PoolConnection getInstancePool() {
		if (instance == null) {
			instance = new PoolConnection();
		}
		return instance;
	}

	/**
	 * M�todo initConfiguraci�n. Descripci�n: Contiene las conexiones de
	 * PoolConnection.
	 */
	private static void initConfiguration() {

		if (basicDataSource == null) {
			basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName(_DRIVER);
			basicDataSource.setUsername(_USER);
			basicDataSource.setPassword(_PASSWORD);
			basicDataSource.setUrl(_URL);
			basicDataSource.setMaxTotal(5);// Maximo de conexiones activas que
											// se asignan desde este pool,
											// negativo = sin limites
			basicDataSource.setMaxIdle(0);// Minimo de conexiones que permanecen
											// inactivas en el grupo sin crear
											// otras adicionales, cero para no
											// crear ninguna
			basicDataSource.setMaxWaitMillis(0);// numero de milesegundos que
												// esperara el pool (cuando no
												// hay conexiones disponibles)
		}
	}

	/**
	 * M�todo getConnectionToPoll().Obtiene la conexi�n para PoolConnection.
	 * 
	 * @return retorna connectionToPool
	 * @throws SQLException Excepci�n de base de datos.
	 */
	public Connection getConnectionToPoll() throws SQLException {
		return connectionToPool = basicDataSource.getConnection();

	}

	/**
	 * M�todo closePoolConnection(). Cierra la conexi�n PoolConnection.
	 * 
	 * @throws SQLException Excepci�n de base de datos.
	 */
	public void closePoolConnection() throws SQLException {
		basicDataSource.close();
	}

}// cierre clase PoolConnection
