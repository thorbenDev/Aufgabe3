<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
		language="java" trimDirectiveWhitespaces="true" %>
		
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<!-- set var for current active page -->
<!-- rausgenommen fÃ¼r Ãnderung auf JSP-->

<!-- include header file -->
<%--<%@include file="head.jsp" %>--%>
<jsp:include page="head.jsp"/>
<!-- body -->
    
<!-- content -->
<div class="container">
  
    <!-- left side content -->
    <!-- ohne KATEGORIE -->
    <div class="col-xs-12 col-sm-6 col-md-8">
 		<c:if test="${requestScope.selectedCategory == null}">
                    <div class="row">
                            <h3>Willkommen!</h3>
                            <p> 
                                Das it.magazin informiert &uuml;ber verschiedene Themen rund um Informationstechnologie, Telekommunikation, Medien und Politik.
                                Dank responsivem Webdesign l&auml;sst sich das Informationsangebot sowohl mit Desktop-PCs als auch mit Tablets oder Smartphones abrufen. 
                            </p>
                            <a href="#" class="btn btn-default">Wir &uuml;ber uns &#187;</a>
                    </div>
		      

        
		<!-- article #1 -->
        <div class="row">
            <h3>Them des Tages</h3>
                <div clasS="col-xs-12 col-sm-12 col-md-6 nopadding">
                    <p>
                        Durch f&uuml;r diese &Uuml;bung erfundene Datens&aumltze wurde ermittelt, dass die Ereignisgesteuerte Prozesskette
                    	(EPK) in diesem Semester die beliebteste Modellierungssprache f&uumlr Prozesse darstellt. Auf den 
                    	Pl&aumltzen 2 und 3 folgen Business Process Modell und Notation (BPMN) sowie das Aktivit&aumltsdiagramm
                    	als Bestandteil der Unified Modeling Language (UML). Petri-Netze fanden dagegen dieses Mal
                    	keinen Anklang. Die Abbildung rechts visualisiert die fiktiven Beliebtheitsanteile der
                    	Modellierungssprachen und wurde mit Hilfe der JavaScript Bibliothek Chart.js im Browser erzeugt.
                    </p>
                    <a href="#" class="btn btn-default">Ganzen Artikel lesen &#187;</a>    
                </div>
                
                <div class="col-xs-12 col-sm-12 col-md-6">
                    <div class="container-fluid">
                        <a href="#"><img class="img-responsive" src="img/graph.jpg" alt="graph"></a>        
                    </div>
                </div>
        </div>
        
		<!-- articel #2 -->
		<div class="row">
            <h3>AuÃerdem interessant</h3>
            <h4>Open Source als Skill weiterhine gefragt</h4>
            <p> 
                Das Open-Source-Betriebssystem Linux hat sich vom Exoten l&aumlngst zu einer der tragenden S&aumlulen einer typischen
                Unternehmens-IT gemausert. Auch freie Bibliotheken wie bspw. jQueary oder Apache Commons sind aus 
                Entwicklungsprojekten heute kaum noch wegzudenken. Viel Know-How ... 
            </p>
            <a href="#" class="btn btn-default">Ganzen Artikel lesen &#187;</a>
            <a href="#" class="btn btn-default">Alle Artikel der Rubrik anzeigen &#187;</a>   
        </div>
</c:if> 
            <!-- mit KATEGORIE -->
<c:if test="${requestScope.selectedCategory != null}">
				<c:forEach var="currentArticle" items="${requestScope.articlesInSelectedCategory}">
                                    <div class="row">
                                            <h3>
                                                <%-- Überschrift des Artikels ausgeben: --%>
						<c:out value="${currentArticle.getTitle()}"/>
                                            </h3>
                                        </div>

                                   <div class="row">
                                                <p> 	
                                                       <c:out value="${currentArticle.getText().substring(0,210)}"/>...            
                                                </p>
                                                <p>
                                                    
                                                </p>
                                    </div>
                                                
<!--         Das ist das Datum      <div class="row text-right">
                                                <time>                                                    <%-- Datum und ID des Nachrichtenartikels in Klammern anzeigen: --%>
                                                    (<fmt:formatDate value="${currentArticle.pubDate}" 
										 type="date" pattern="dd.MM.yyyy"/>,
						 ID ${currentArticle.articleId})</time>    
                                    </div>       -->
                                    <a class="btn btn-default" 
                                       href="NewsControllerServlet?category=${currentArticle.getCategory().getCategoryId()}&article=${currentArticle.articleId}">
                                       Ganzen Artikel lesen »</a>
				</c:forEach>
</c:if>
            
    </div>
            
    <!-- right side content -->
    <div class="col-xs-12 col-sm-6 col-md-4 content-rightside">
        
        <!-- search bar -->
        <jsp:include page="searchbar.html" />
        
        <!-- new articles -->
        <div class="row">
            <h3>Neue Artikel</h3>
		</div>
            
            <!-- #1 -->
		<div class="row">
            <h4>Deutschland bei WLAN-Hotspots abgeschlagen</h4>
            <p> 
                Obwohl im Vergleich zu vergangenen Jahren Zuw&aumlchse bei &oumlffentlichen WLAN-Angeboten
    			zu verzeichenen sind, hinkt der Ausbau in Deutschland hinterher. Kritiker f&uumlhren als 
    			Ursache insb. die lange Zeit komplizierte rechtliche Situation rund um St&oumlrerhaltung und
    			Abmahngeb&uuml;hren ...
    		</p>
            <a href="#" class="btn btn-default">Ganzen Artikel lesen &#187;</a>
        </div>
        
        <div class="row">
            <!-- #2 -->
            <h4>Fiber-to-Home vielfach unerschwinglich</h4>
            <p> 
                Eine Internetanbindung &uuml;ber Lichtwellenleiter, die bis ins eigene Zuhause verlegt werden,
    			verspricht hohe &Uuml;bertragungsraten und niedrige Latenzen. Aufgrund der hohen Kosten stellt das
    			Verfahren ... 
    		</p>
            <a href="#" class="btn btn-default">Ganzen Artikel lesen &#187;</a>
        </div>
        
    </div>
    
</div>

<!-- include footer file -->
<%@include file="foot.html" %>
    