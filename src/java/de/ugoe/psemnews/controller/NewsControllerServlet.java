package de.ugoe.psemnews.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.ugoe.psemnews.model.dao.DAOFactory;
import de.ugoe.psemnews.model.dao.NewsDAO;
import de.ugoe.psemnews.model.dao.jdbc.JDBCDAOFactory;
import de.ugoe.psemnews.model.entities.Article;
import de.ugoe.psemnews.model.entities.Category;

/**
 * Beispiel-Controller
 */
@WebServlet(description = "NewsControllerServlet", urlPatterns = { "/NewsControllerServlet" })
public class NewsControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Diese Methode wird aufgerufen, wenn der Webbrowser eines Users eine GET-Anfrage
	 * auf die o.g. Adresse (siehe "urlPatterns") richtet.
	 */
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		/* Zunächst prüfen, ob der Benutzer schon eine Kategorie gewählt hat. */
		int categoryId = -1;
		String strCategoryParameter = request.getParameter("category");
		/* Folgendes nur ausführen, wenn ein Wert für den Parameter "category" gesendet wurde: */
		if ((strCategoryParameter != null) && (!strCategoryParameter.trim().isEmpty())) {
			try {
				/* Versuchen, aus dem String-Parameter eine Ganzzahl herauszulesen: */
				categoryId = Integer.parseInt(strCategoryParameter);
			} catch (Exception e) {
				/* Fehler! Der Benutzer hat keine gültige Zahl angegeben. */
				/* Fehlermeldung ausgeben: */
				forwardToErrorView(request, response, 
						"Ihre Kategorie-Angabe war ungültig, da es sich nicht um eine Ganzzahl " +
				        "handelte. Beim Verarbeiten des Werts trat der folgende Fehler auf: <<" +
					    e + ">>");
				/* Und den Controller verlassen! */
				return;
			}
			/* Wenn wir hier ankommen, wurde tatsächlich irgendeine Kategorie-ID angegeben.
			 * Aber ein int kann ja auch negativ oder 0 sein, was für eine Kategorie-ID nicht
			 * erlaubt wäre (erlaubt: 1 oder größer). Der Controller prüft daher, ob die 
			 * Eingabe gültig ist. */
			if (categoryId < 1) {
				forwardToErrorView(request, response, 
						"Werte kleiner 1 sind als Kategorie-ID nicht erlaubt!");
				return;
			}
		}
		
                //Artikel ID lesen Anfang
                		/* Zunächst prüfen, ob der Benutzer schon eine Kategorie gewählt hat. */
		int articleId = -1;
		String strArticleParameter = request.getParameter("article");
		/* Folgendes nur ausführen, wenn ein Wert für den Parameter "article" gesendet wurde: */
		if ((strArticleParameter != null) && (!strArticleParameter.trim().isEmpty())) {
			try {
				/* Versuchen, aus dem String-Parameter eine Ganzzahl herauszulesen: */
				articleId = Integer.parseInt(strArticleParameter);
			} catch (Exception e) {
				/* Fehler! Der Benutzer hat keine gültige Zahl angegeben. */
				/* Fehlermeldung ausgeben: */
				forwardToErrorView(request, response, 
						"Ihre Kategorie-Angabe war ungültig, da es sich nicht um eine Ganzzahl " +
				        "handelte. Beim Verarbeiten des Werts trat der folgende Fehler auf: <<" +
					    e + ">>");
				/* Und den Controller verlassen! */
				return;
			}
			/* Wenn wir hier ankommen, wurde tatsächlich irgendeine Kategorie-ID angegeben.
			 * Aber ein int kann ja auch negativ oder 0 sein, was für eine Kategorie-ID nicht
			 * erlaubt wäre (erlaubt: 1 oder größer). Der Controller prüft daher, ob die 
			 * Eingabe gültig ist. */
			if (articleId < 1) {
				forwardToErrorView(request, response, 
						"Werte kleiner 1 sind als Artikel-ID nicht erlaubt!");
				return;
			}
		}
                //Artikel ID lesen Ende
                
		/* Nachdem der Kategorie-ID-Parameter verarbeitet wurde, jetzt die Daten aus dem Model
		 * abrufen, die in der View später angezeigt werden sollen. */
		
		/* Daten, die später an die View übergeben werden sollen. */
		List<Category> allCategories = null;
		Category selectedCategory = null;
		List<Article> articlesInCategory = null;
                Article selectedArticle = null;
		
		/* Abfrage des Models: */
		try (DAOFactory df = new JDBCDAOFactory()) {
			
			/* Datenzugriffsobjekt (Data Access Object = DAO) für Nachrichteninhalte abrufen: */
			NewsDAO newsDAO = df.getNewsDAO();
			
			/* Über das DAO eine Liste aller vorhandenen Kategorien aus der Datenbank abfragen.
			 * Mit Hilfe dieser Liste kann sich der User später die Kategorie aussuchen, die
			 * er einsehen möchte. */
			allCategories = newsDAO.getAllCategories();
			
			/* Falls der Benutzer bereits eine Kategorie ausgewählt hat (siehe oben für die
			 * Verarbeitung des Kategorie-Parameters), sollen ...
			 * - Informationen über die gewählte Kategorie aus der DB abgerufen werden, denn
			 *   bisher liegt nur die nichtssagende ID vor.
			 * - alle Artikel aus der DB abgerufen werden, die dieser Kategorie zugeordnet sind. */
			if (categoryId >= 1) {
				/* u.a. Name der Kategorie aus der DB abrufen, indem nach der ID gesucht wird: */
				selectedCategory = newsDAO.getCategoryById(categoryId);
				/* Artikel aus dieser Kategorie abrufen: */
				articlesInCategory = newsDAO.getArticlesByCategoryId(categoryId);
			}
                        if (articleId >= 1) {
				/* u.a. Name der Kategorie aus der DB abrufen, indem nach der ID gesucht wird: */
				selectedArticle = newsDAO.getArticleById(articleId);
			}
			
		} catch (Exception e) {
			
			/* Wenn die Ausführung hierhin gelangt, ist beim Ansprechen des Models bzw. der
			 * Datenbank irgendetwas schiefgegangen. Daher veranlassen, dass es gleich zur
			 * Fehlermeldungs-View weitergeht: */
			forwardToErrorView(request, response, 
					"Der Datenabruf aus der Datenbank ist wegen folgender Exception " +
				    "fehlgeschlagen: <<" + e + ">>");
			/* Controller verlassen. */
			return;
			
		}
		
		/* Wenn die Ausführung hier ankommt, wurden alle notwendigen Daten abgerufen und
		 * können nun im Request-Scope hinterlegt werden. Der Request-Scope kann bildlich
		 * als "Regal" betrachtet werden, in das der Controller Daten hineinlegen kann.
		 * Jedes "Regalfach" hat einen eindeutigen Namen. Die View kann die vom Controller
		 * dort bereitgelegten Daten später unter Angabe dieses Namens abholen. */
		
		/*                     v Name des "Regalfachs" v    v Was kommt rein? v  */
		request.setAttribute ("allCategories",              allCategories     );
		request.setAttribute ("selectedCategory",           selectedCategory  );
		request.setAttribute ("articlesInSelectedCategory", articlesInCategory);
		
		/* Nachdem die Daten im RequestScope bereitgelegt wurden, jetzt zur View weiterleiten: */
		RequestDispatcher rd;
                if(articleId >= 1)  {
                                        request.setAttribute ("selectedArticle",           selectedArticle  );
                                        rd = request.getRequestDispatcher("viewArticle.jsp");
                }
                else                {   rd = request.getRequestDispatcher("startseite.jsp");
                }
                rd.forward(request, response);
		
	}

	
	private void forwardToErrorView(HttpServletRequest request, HttpServletResponse response, 
			String errorMessage) throws ServletException, IOException {
		/* Fehlermeldung im Request-Scope hinterlegen, damit die View sie anzeigen kann: */
		request.setAttribute("errorMessage", errorMessage);
		/* Der Controller ist jetzt fertig. Die Verarbeitung der Anfrage an die View weiterreichen: */
		RequestDispatcher rd = request.getRequestDispatcher("startseite.jsp");
        rd.forward(request, response);
	}
	
}
