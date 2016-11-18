package de.ugoe.psemnews.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import de.ugoe.psemnews.misc.SimpleLogger;
import de.ugoe.psemnews.model.dao.NewsDAO;
import de.ugoe.psemnews.model.entities.Article;
import de.ugoe.psemnews.model.entities.Category;

/**
 * {@link NewsDAO}-Implementierung, die im Hintergrund mit einer Datenbank
 * zusammenarbeitet. Kann nur über die zugehörige {@link JDBCDAOFactory} erzeugt
 * werden, die sich auch um den Aufbau der Datenbankverbindung kümmert.
 */
public class JDBCNewsDAO implements NewsDAO {

	private static final String SQL_SELECT_CATEGORY_BY_ID = 
			"SELECT K_ID, K_Bezeichnung FROM PSEMNEWS.KATEGORIEN WHERE K_ID = ?";
	
	private static final String SQL_SELECT_CATEGORIES = 
			"SELECT K_ID, K_Bezeichnung FROM PSEMNEWS.KATEGORIEN ORDER BY K_ID ASC";
	
	private static final String SQL_SELECT_ARTICLE = 
			"SELECT B.B_ID, B.B_Titel, B.B_Text, B.B_Ort, B.B_Autor, B.B_Datum, " + 
				   "K.K_ID, K.K_Bezeichnung " +
			"FROM PSEMNEWS.BEITRAEGE B " + 
			"INNER JOIN PSEMNEWS.KATEGORIEN K ON B.B_Kategorie_ID = K.K_ID ";
	
	private static final String SQL_SELECT_ARTICLE_BY_ID = 
			SQL_SELECT_ARTICLE +
			"WHERE B.B_ID = ?";
	
	private static final String SQL_SELECT_ARTICLES_BY_CATEGORY = 
			SQL_SELECT_ARTICLE +
			"WHERE B.B_Kategorie_ID = ? " +
			"ORDER BY B.B_Datum DESC";
	
	private static final SimpleLogger LOGGER = new SimpleLogger(JDBCNewsDAO.class.getCanonicalName());
	
	
	private final Connection connection;
	
	protected JDBCNewsDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Article getArticleById(int articleId) {
		LOGGER.log(Level.FINEST, "Abfrage zu articleId=" + articleId + "...");
		Article result = null;
		try( PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ARTICLE_BY_ID) ) {
			stmt.setInt(1, articleId);
			try( ResultSet rs = stmt.executeQuery() ) {
				if (rs.next()) {
					result = new Article();
					fillArticleFromResultSet(rs, result);
				}
			}
		} catch (SQLException sqlEx) {
			LOGGER.log(Level.SEVERE, "SQLException in getArticleById fuer ID=" + 
					articleId + "! Fehlermeldung: " + sqlEx.getMessage(), sqlEx);
			result = null; // Keine ggf. halbfertigen Datensätze erwünscht.
		}
		LOGGER.log(Level.FINEST, "Artikel zur ID " + articleId +
				" gefunden? " + bool2JaNein(result != null));
		return result;
	}

	@Override
	public List<Article> getArticlesByCategoryId(int categoryId) {
		return getArticlesByCategoryId(categoryId, -1);
	}

	@Override
	public List<Article> getArticlesByCategoryId(int categoryId, int maxResults) {
		if (maxResults > 0) {
			LOGGER.log(Level.FINEST, "Abfrage zu categoryId=" + categoryId + 
					" mit einem Limit von " + maxResults + " Ergebnissen...");
		} else {
			LOGGER.log(Level.FINEST, "Abfrage zu categoryId=" + categoryId + 
					" ohne Ergebnislimit...");
		}
		List<Article> results = new LinkedList<>();
		try( PreparedStatement stmt = connection.prepareStatement( SQL_SELECT_ARTICLES_BY_CATEGORY ) ) {
			stmt.setInt(1, categoryId);
			if( maxResults > 0 ) stmt.setMaxRows( maxResults );
			try( ResultSet rs = stmt.executeQuery() ) {
				while ( rs.next() ) {
					Article entry = new Article();
					fillArticleFromResultSet(rs, entry);
					results.add(entry);
				}
			}
		} catch (SQLException sqlEx) {
			LOGGER.log(Level.SEVERE, "SQLException in getArticlesByCategoryId " + 
					"fuer ID=" + categoryId + " und maxResults=" + maxResults + "! " + 
					"Fehlermeldung: " + sqlEx.getMessage(), sqlEx);
		}
		LOGGER.log(Level.FINEST, results.size() + " Artikel gefunden.");
		return results;
	}

	@Override
	public Category getCategoryById(int categoryId) {
		LOGGER.log(Level.FINEST, "Abfrage fuer categoryId=" + categoryId + "...");
		Category result = null;
		try( PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_CATEGORY_BY_ID) ) {
			stmt.setInt(1, categoryId);
			try( ResultSet rs = stmt.executeQuery() ) {
				if (rs.next()) {
					result = new Category();
					fillCategoryFromResultSet(rs, result);
				}
			}
		} catch (SQLException sqlEx) {
			LOGGER.log(Level.SEVERE, "SQLException in getCategoryById fuer ID=" + 
					categoryId + "! Fehlermeldung: " + sqlEx.getMessage(), sqlEx);
			result = null; // Keine ggf. halbfertigen Datensätze erwünscht.
		}
		LOGGER.log(Level.FINEST, "Kategorie zur ID " + categoryId +
				" gefunden? " + bool2JaNein(result != null));
		return result;
	}

	@Override
	public List<Category> getAllCategories() {
		LOGGER.log(Level.FINEST, "Abfrage aller Kategorien...");
		List<Category> results = new LinkedList<>();
		try( PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_CATEGORIES) ) {
			try( ResultSet rs = stmt.executeQuery() ) {
				while (rs.next()) {
					Category entry = new Category();
					fillCategoryFromResultSet(rs, entry);
					results.add(entry);
				}
			}
		} catch (SQLException sqlEx) {
			LOGGER.log(Level.SEVERE, "SQLException in getAllCategories! " + 
					"Fehlermeldung: " + sqlEx.getMessage(), sqlEx);
		}
		LOGGER.log(Level.FINEST, results.size() + " Kategorien gefunden.");
		return results;
	}
	
	private String bool2JaNein(boolean b) {
		if (b) {
			return "ja";
		} else {
			return "nein";
		}
	}
	
	private void fillArticleFromResultSet(ResultSet rs, Article article) 
			throws SQLException {
		int i=0;
		article.setArticleId(rs.getInt(++i));
		article.setTitle(rs.getString(++i));
		article.setText(rs.getString(++i));
		article.setLocation(rs.getString(++i));
		article.setAuthor(rs.getString(++i));
		article.setPubDate(rs.getDate(++i));
		article.getCategory().setCategoryId(rs.getInt(++i));
		article.getCategory().setName(rs.getString(++i));
	}
	
	private void fillCategoryFromResultSet(ResultSet rs, Category category) 
			throws SQLException {
		int i=0;
		category.setCategoryId(rs.getInt(++i));
		category.setName(rs.getString(++i));
	}
	
}
