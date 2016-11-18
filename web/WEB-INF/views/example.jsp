<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
		language="java" trimDirectiveWhitespaces="true" %>
		
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="de">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Testseite</title>
	</head>
	<body>
		<h1>Testseite zur Nachrichten-WebApp</h1>
		
		<h2>Kategorieauswahl</h2>
		<ul>
			<%-- Alle Kategorien durchlaufen, die der Controller aus dem Model abgerufen
			     und anschließend im Request-Scope für die View bereitgelegt hat: --%>
			<c:forEach var="currentCategory" items="${requestScope.allCategories}">
				<%-- Für jede einzelne Kategorie Folgendes ausführen: --%>
				<li>
					<a href="ExampleControllerServlet?category=${currentCategory.categoryId}">
						<c:out value="${currentCategory.name}"/> (ID ${currentCategory.categoryId})
					</a>
				</li>
			</c:forEach>
			<!-- Die folgende "Kategorie" kam nicht aus Model und Controller, sondern
			     als Fehlerfall zum Ausprobieren angezeigt: -->
			<li>
				<!-- Der Wert hinter category= muss eigentlich eine Zahl sein. -->
				<a href="ExampleControllerServlet?category=huhu">
					Hier klicken, um Fehler herbeizuführen (ungültige Kategorie-ID).
				</a>
			</li>
		</ul>
		
		<%-- Folgendes nur ausgeben, wenn eine Kategorie ausgewählt wurde: --%>
		<c:if test="${requestScope.selectedCategory != null}">
			<h2>
				Nachrichten der Kategorie 
				&quot;<c:out value="${requestScope.selectedCategory.name}"/>&quot;
			</h2>
			<ul>
				<c:forEach var="currentArticle" items="${requestScope.articlesInSelectedCategory}">
					<li>
						<%-- Überschrift des Artikels ausgeben: --%>
						<c:out value="${currentArticle}"/>
						<%-- Datum und ID des Nachrichtenartikels in Klammern anzeigen: --%>
						(<fmt:formatDate value="${currentArticle.pubDate}" 
										 type="date" pattern="dd.MM.yyyy"/>,
						 ID ${currentArticle.articleId})
					</li>
				</c:forEach>
			</ul>
		</c:if>

	</body>
</html>
