package de.ugoe.psemnews.model.dao;

import java.util.List;

import de.ugoe.psemnews.model.entities.Article;
import de.ugoe.psemnews.model.entities.Category;

/**
 * Schnittstelle für ein Datenzugriffsobjekt, das nachrichtenbezogene
 * Funktionen bereitstellt.
 */
public interface NewsDAO {

	/**
	 * Artikel anhand einer gegebenen Artikel-ID abrufen.
	 * @param articleId Artikel-ID (Primärschlüssel) als <tt>int</tt>
	 * @return Gefundener Artikel als {@link Article}-Bean oder <tt>null</tt>, falls
	 *         zur ID kein Artikel gefunden wird.
	 */
	public Article getArticleById(int articleId);
	
	/**
	 * Liste aller Artikel abrufen, die einer bestimmten Kategorie zugeordnet sind.
	 * @param categoryId Kategorie-ID (Primärschlüssel) als <tt>int</tt>
	 * @return Liste der gefundenen Artikel. Falls keine Artikel zur angegebenen
	 *         Kategorie-ID gefunden werden, wird eine leere Liste zurückgeliefert,
	 *         niemals <tt>null</tt>.
	 */
	public List<Article> getArticlesByCategoryId(int categoryId);
	
	/**
	 * Liste mit begrenzter Anzahl von Artikeln abrufen, die einer bestimmten 
	 * Kategorie zugeordnet sind.
	 * @param categoryId Kategorie-ID (Primärschlüssel) als <tt>int</tt>
	 * @param maxResults Maximale Anzahl benötigter Artikel. Bei Werten kleiner 1
	 *                   wird die Anzahlbeschränkung außer Kraft gesetzt und es 
	 *                   werden alle Artikel der Kategorie zurückgeliefert.
	 * @return Liste der gefundenen Artikel. Falls keine Artikel zur angegebenen
	 *         Kategorie-ID gefunden werden, wird eine leere Liste zurückgeliefert,
	 *         niemals <tt>null</tt>.
	 */
	public List<Article> getArticlesByCategoryId(int categoryId, int maxResults);
	
	
	/**
	 * Nachrichtenkategorie anhand einer gegebenen Kategorie-ID abrufen.
	 * @param categoryId Kategorie-ID (Primärschlüssel) als <tt>int</tt>
	 * @return Gefundene Kategorie als {@link Category}-Bean oder <tt>null</tt>,
	 *         falls zur gegebenen ID nichts gefunden wird.
	 */
	public Category getCategoryById(int categoryId);
	
	/**
	 * Liste aller vorhandenen Nachrichtenkategorien abrufen. Kann z. B. benutzt
	 * werden, um dem Benutzer eine Auswahl der vorhandenen Kategorien anzubieten.
	 * @return Liste aller Nachrichtenkategorien. Sind keine Kategorien vorhanden,
	 *         wird eine leere Liste zurückgeliefert, niemals <tt>null</tt>.
	 */
	public List<Category> getAllCategories();
	
}
