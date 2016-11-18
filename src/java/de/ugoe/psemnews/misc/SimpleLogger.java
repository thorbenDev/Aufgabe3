package de.ugoe.psemnews.misc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

/**
 * Maximal-einfache Logging-Funktion für die Übungsaufgabe. Alle Logeinträge
 * werden schlicht auf der Konsole ausgegeben. In einer echten Anwendung sollte
 * stattdessen z. B. Log4j2 o. ä. benutzt werden, wo u. a. auch Log-Dateien
 * konfiguriert werden können.
 */
public class SimpleLogger {

	private final String className;
	
	public SimpleLogger(String className) {
		super();
		this.className = className;
	}

	public void log(Level level, String message) {
		log(level,message,null);
	}
	
	public void log(Level level, String message, Throwable t) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.err.println(
				sdf.format(new Date()) + ' ' +  
				level + ' ' + 
				className + ' ' + 
				message);
		if (t != null) {
			t.printStackTrace();
		}
	}
	
}
