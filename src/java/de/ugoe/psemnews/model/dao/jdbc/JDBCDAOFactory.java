package de.ugoe.psemnews.model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import de.ugoe.psemnews.misc.SimpleLogger;
import de.ugoe.psemnews.model.dao.DAOFactory;
import de.ugoe.psemnews.model.dao.NewsDAO;

/**
 * {@link DAOFactory}-Implementierung, die mit einer Apache-Derby-Datenbank arbeitet.
 */
public class JDBCDAOFactory implements DAOFactory {

	private static final SimpleLogger LOGGER = 
			new SimpleLogger(JDBCDAOFactory.class.getCanonicalName());
	
	private final Connection connection;
	private boolean connectionClosed = true;
	
	/**
	 * Neuen &quot;Datenzugriffs-Werkzeugkasten&quot; für eine <i>embedded</i> 
	 * Apache-Derby-Datenbank erstellen. Intern wird automatisch eine Verbindung 
	 * zur Datenbank hergestellt.
	 * @throws SQLException Wirft {@link SQLException}, wenn der Verbindungsaufbau
	 *         zur Datenbank fehlschlägt.
	 */
	public JDBCDAOFactory() throws SQLException {
		
		LOGGER.log(Level.FINEST, "Suche Datenbanktreiber...");
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, 
					"Schwerer Fehler! Kann nicht auf den Derby/JavaDB-Driver zugreifen: " + 
				    e.getMessage(), e);
		}
		
		LOGGER.log(Level.FINEST, "Stelle Verbindung zur Datenbank her...");
		DBConfig dbConfig = DBConfig.getInstance();
		try {
			connection = DriverManager.getConnection(dbConfig.getJdbcConnectionString());
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, 
					"Schwerer Fehler! Konnte keine Verbindung zur Datenbank herstellen. " + 
					"Es trat eine Exception auf: " + e.getMessage(), e);
			throw e;
		}
		connectionClosed = false;
		LOGGER.log(Level.FINEST, "Datenbankverbindung hergestellt.");
		
	}
	
	/**
	 * Datenzugriffsobjekt für Nachrichteninhalte erzeugen. Stellt
	 * Zugriffsmethoden für Nachrichtenartikel und Nachrichtenkategorien bereit.
	 */
	@Override
	public NewsDAO getNewsDAO() {
		if (connectionClosed) throw new IllegalStateException(
				"Nachdem die Datenbankverbindung geschlossen wurde, " + 
				"können keine neuen Datenzugriffsobjekte mehr dazu " + 
				"erstellt werden.");
		return new JDBCNewsDAO(connection);
	}
	
	/**
	 * Datenbankverbindung schließen.
	 */
	@Override
	public void close() throws Exception {
		LOGGER.log(Level.FINEST, "Schließe Datenbankverbindung...");
		connection.close();
		connectionClosed = true;
		LOGGER.log(Level.FINEST, "Datenbankverbindung geschlossen.");
	}

}
