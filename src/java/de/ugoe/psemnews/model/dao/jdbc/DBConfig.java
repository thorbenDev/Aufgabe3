package de.ugoe.psemnews.model.dao.jdbc;

import java.util.Properties;
import java.util.logging.Level;

import de.ugoe.psemnews.misc.SimpleLogger;

/**
 * Singleton, das die Datenbankkonfiguration verwaltet.
 * Die Daten werden nur 1x aus einer externen Konfigurationsdatei eingelesen
 * und dann während der gesamten Programmlaufzeit im Speicher bereitgehalten.
 */
public class DBConfig {

	private static final SimpleLogger LOGGER = new SimpleLogger(DBConfig.class.getName());
	private static final String CONFIG_FILENAME = "dbconfig.properties";
	private static final DBConfig INSTANCE = new DBConfig();
	
	private String dbPath = "";
	private String jdbcConnectionString = "";
	
	private DBConfig() {
		LOGGER.log(Level.FINEST, "Lade Datenbankkonfiguration...");
		Properties p = new Properties();
		try {
			p.load(DBConfig.class.getResourceAsStream(CONFIG_FILENAME));
		} catch (Exception e) {
			LOGGER.log (Level.SEVERE, 
					"Schwerer Fehler! Konnte Datenbankeinstellungen nicht " + 
					"aus der Datei \"" + CONFIG_FILENAME + "\" laden. Es trat " + 
					"eine Exception auf: " + e.getMessage() + System.lineSeparator() + 
					"Es kann daher keine DB-Verbindung hergestellt werden.", e);
		}
		dbPath = p.getProperty("dbPath");
		jdbcConnectionString = "jdbc:derby:" + dbPath + ";create=false";
		LOGGER.log(Level.CONFIG, "Pfad zur Datenbank: \"" + dbPath + "\"");
	}

	
	/**
	 * Datenbank-Pfad, in dem sich die Apache-Derby-Datenbank befindet, zurückliefern.
	 * @return Datenbank-Pfad als <tt>String</tt>
	 */
	public String getDbPath() {
		return dbPath;
	}

	/**
	 * JDBC-Connection-String für 
	 * {@link java.sql.DriverManager#getConnection(String, String, String)}
	 * zurückliefern.
	 * @return JDBC-Connection-String
	 */
	public String getJdbcConnectionString() {
		return jdbcConnectionString;
	}
	
	
	/**
	 * Datenbank-Konfigurationsobjekt abrufen.
	 * @return Datenbank-Konfiguration
	 */
	public static DBConfig getInstance() {
		return INSTANCE;
	}
	
}
