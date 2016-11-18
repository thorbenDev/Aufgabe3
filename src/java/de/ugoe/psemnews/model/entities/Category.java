package de.ugoe.psemnews.model.entities;

import java.io.Serializable;

/**
 * Bean einer Nachrichtenkategorie.
 */
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int categoryId;
	private String name;
	
	/**
	 * Neues Nachrichtenkategorie-Objekt erzeugen.
	 * 
	 * @param categoryId ID der Kategorie (Primärschlüssel)
	 * @param name Kategoriebezeichnung
	 */
	public Category(int categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}
	
	/**
	 * Leeres Nachrichtenkategorie-Objekt erzeugen.
	 */
	public Category() {
		this.categoryId = -1;
		this.name = "";
	}

	/**
	 * ID (Primärschlüssel) der Kategorie abrufen.
	 * 
	 * @return ID (Primärschlüssel) der Kategorie als <tt>int</tt>
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * ID (Primärschlüssel) der Kategorie setzen.
	 * 
	 * @param categoryId ID (Primärschlüssel) der Kategorie als <tt>int</tt>
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Bezeichnung der Kategorie abrufen.
	 * 
	 * @return Bezeichnung der Kategorie als <tt>String</tt>
	 */
	public String getName() {
		return name;
	}

	/**
	 * Bezeichnung der Kategorie setzen.
	 * 
	 * @param name Bezeichnung der Kategorie als <tt>String</tt>
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * String-Repräsentation des Kategorie-Objekts zurückliefern
	 * (für Debugging-Zwecke).
	 */
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=\"" + name + "\"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Category))
			return false;
		Category other = (Category) obj;
		if (categoryId != other.categoryId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
