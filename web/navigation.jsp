<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
		language="java" trimDirectiveWhitespaces="true" %>
		
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        
        <!-- nav header -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/Aufgabe3/NewsControllerServlet">it.magazin</a>
        </div>
        
        <!-- nav items -->
        <div class="collapse navbar-collapse" id="navigation">
            <ul class="nav navbar-nav">
<!--                <li <?php if($active=="startseite") echo "class='active'"; ?>><a href="startseite.php">Startseite</a></li>
                <li <?php if($active=="telekom") echo "class='active'"; ?>><a href="telekommunikation.php">Telekommunikation</a></li>
                <li <?php if($active=="infotech") echo "class='active'"; ?>><a href="informationstechnologie.php">Informationstechnologie</a></li>
                <li><a href="#">Medien</a></li>
                <li><a href="#">Politik</a></li>-->

                <c:forEach var="currentCategory" items="${requestScope.allCategories}">
				<%-- Für jede einzelne Kategorie Folgendes ausführen: --%>
				<li>
					<a href="NewsControllerServlet?category=${currentCategory.categoryId}">
						<c:out value="${currentCategory.name}"/> (ID ${currentCategory.categoryId})
					</a>
				</li>
			</c:forEach>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Impressum</a></li>
            </ul>
        </div>
        
    </div>
</nav>