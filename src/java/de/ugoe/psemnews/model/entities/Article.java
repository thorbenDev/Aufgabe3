package de.ugoe.psemnews.model.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Bean eines Artikels (Nachrichtenmeldung) inklusive zugeordneter Kategorie.
 */
public class Article implements Serializable {

	/**
	 * Maximale Textlänge, die aus dem Nachrichtenvolltext in den Teaser
	 * übernommen wird.
	 */
	public static final int MAX_TEASER_LENGTH = 120;
	private static final String TRUNCATION_SUFFIX = " [...]";
	
	private static final long serialVersionUID = 1L;
	
	
	private int articleId = -1;
	private String title = "";
	private String text = "";
	private String location = "";
	private String author = "";
	private Date pubDate = new Date(0);
	private Category category = new Category();
	
	/**
	 * Leere Artikel-Bean erzeugen.
	 * (Erzeugt implizit auch eine leere {@link Category}-Bean.)
	 */
	public Article() {
	}

	/**
	 * ID (Primärschlüssel) der Nachrichtenmeldung abrufen.
	 * 
	 * @return ID als <tt>int</tt>
	 */
	public int getArticleId() {
		return articleId;
	}

	/**
	 * ID (Primärschlüssel) der Nachrichtenmeldung setzen.
	 * 
	 * @param articleId ID (Primärschlüssel) der Nachrichtenmeldung als <tt>int</tt>
	 */
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	/**
	 * Artikelüberschrift abrufen.
	 * 
	 * @return Artikelüberschrift als <tt>String</tt>
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Artikelüberschrift setzen.
	 * 
	 * @param title Artikelüberschrift als <tt>String</tt>
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Artikeltext abrufen.
	 * 
	 * @return Artikeltext als <tt>String</tt>
	 */
	public String getText() {
		return text;
	}

	/**
	 * Artikeltext festlegen.
	 * 
	 * @param text Artikeltext als <tt>String</tt>
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Ort der Nachrichtenmeldung abrufen.
	 * 
	 * @return Ort der Nachrichtenmeldung als <tt>String</tt>
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Ort der Nachrichtenmeldung setzen.
	 * 
	 * @param location Ort der Nachrichtenmeldung als <tt>String</tt>
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Kürzel des Artikelautors abrufen.
	 * 
	 * @return Kürzel des Artikelautors als <tt>String</tt>, z. B. <tt>&quot;st&quot;</tt>
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Kürzel des Artikelautors setzen.
	 * 
	 * @param author Kürzel des Artikelautors als <tt>String</tt>, 
	 * 				 z. B. <tt>&quot;st&quot;</tt>
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Datum der Nachrichtenmeldung abrufen. 
	 * (&quot;pubDate&quot; als Abkürzung für &quot;publication date&quot;.)
	 * 
	 * @return Datum der Nachrichtenmeldung als {@link Date}-Objekt
	 */
	public Date getPubDate() {
		return pubDate;
	}

	/**
	 * Datum der Nachrichtenmeldung setzen. 
	 * 
	 * @param pubDate Datum der Nachrichtenmeldung als {@link Date}-Objekt
	 */
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	/**
	 * Kategorie der Nachrichtenmeldung abrufen.
	 * 
	 * @return Kategorie der Nachrichtenmeldung als {@link Category}-Bean
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Kategorie der Nachrichtenmeldung setzen.
	 * 
	 * @param category Kategorie der Nachrichtenmeldung als {@link Category}-Bean
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/**
	 * Teaser-Text abrufen. Der Text wird dabei auf maximal 
	 * {@link Article#MAX_TEASER_LENGTH} Zeichen gekürzt. Falls der Volltext
	 * bereits kurz genug ist, wird er im Original zurückgegeben. Ansonsten
	 * wird die Kürzung durch <i>[...]</i> am Ende gekennzeichnet. 
	 * 
	 * @return Gekürzter Teaser-Text als <tt>String</tt>. Niemals <tt>null</tt>.
	 */
	public String getTeaser() {
		if (text == null || text.trim().isEmpty()) return "";
		if (text.length() <= MAX_TEASER_LENGTH) return text;
		String teaser = text.substring(0, MAX_TEASER_LENGTH);
		int lastIndexOfSpace = teaser.lastIndexOf(' ');
		if (lastIndexOfSpace > 0) 
			return teaser.substring(0, lastIndexOfSpace) + TRUNCATION_SUFFIX;
		int lastIndexOfHyphen = teaser.lastIndexOf('-');
		if (lastIndexOfHyphen > 0) 
			return teaser.substring(0, lastIndexOfHyphen) + TRUNCATION_SUFFIX;
		return teaser + TRUNCATION_SUFFIX;
	}

	/**
	 * String-Repräsentation des gesamten Artikelobjekts zurückgegeben 
	 * (für Debugging-Zwecke).
	 */
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", title=\"" + title
				+ "\", text=\"" + text + "\", location=\"" + location + 
				"\", author=\"" + author + "\", pubDate=" + pubDate + 
				", category=" + category + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + articleId;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((pubDate == null) ? 0 : pubDate.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Article))
			return false;
		Article other = (Article) obj;
		if (articleId != other.articleId)
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (pubDate == null) {
			if (other.pubDate != null)
				return false;
		} else if (!pubDate.equals(other.pubDate))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
