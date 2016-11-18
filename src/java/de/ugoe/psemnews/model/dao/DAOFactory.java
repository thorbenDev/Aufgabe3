package de.ugoe.psemnews.model.dao;

/**
 * Schnittstellenbeschreibung für ein Objekt, das Datenzugriffsobjekte
 * (Data Access Objects / DAO) bereitstellen kann - vergleichbar mit 
 * einem Werkzeugkasten.<br>
 * Da die DAOFactory intern die Datenquelle verwaltet, auf die die 
 * einzelnen DAOs zugreifen (z. B. eine Datenbankverbindung oder eine 
 * Textdatei), muss die DAOFactory nach Verwendung per 
 * {@link DAOFactory#close()} geschlossen werden. Dafür eignet sich z. B.
 * die &quot;<i>try-with-resources</i>&quot;-Syntax.<br>
 * In diesem Beispiel hier wird im "Werkzeugkasten" nur ein DAO angeboten.
 * In einer größeren Anwendung könnten aber auch mehrere zur Verfügung
 * gestellt werden, z. B. <tt>CustomerDAO</tt>, <tt>ProductDAO</tt>, 
 * <tt>OrderDAO</tt> usw. Dieses Interface hier beinhaltet keine 
 * Funktionalität, sondern gibt nur die Schnittstelle vor, die ein Objekt
 * nach außen hin anbieten muss, damit es als <tt>DAOFactory</tt> benutzt
 * werden kann. So könnte man einerseits eine <tt>DatenbankDAOFactory</tt>
 * haben, die DAOs erzeugt, welche tatsächlich im Hintergrund mit einer
 * Datenbank arbeiten. Daneben könnte es zum Testen eine 
 * <tt>DummyDatenDAOFactory</tt> geben, deren erzeugte DAOs nicht wirklich
 * eine Datenbank ansprechen, sondern lediglich Zufalls-Fake-Daten 
 * zurückliefern. Beide Systeme könnten mit demselben Code benutzt werden
 * und man muss nur an einer Stelle den Objektnamen ändern, um zwischen
 * beiden zu wechseln.<br>
 */
public interface DAOFactory extends AutoCloseable {

	/**
	 * Datenzugriffsobjekt für nachrichtenbezogene Aufgaben erzeugen.
	 * 
	 * @return Datenzugriffsobjekt für nachrichtenbezogene Aufgaben
	 */
	public NewsDAO getNewsDAO();
	
}
