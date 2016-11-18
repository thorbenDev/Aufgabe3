<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
		language="java" trimDirectiveWhitespaces="true" %>
		
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- include header file -->
<%@include file="head.jsp" %>

<!-- body -->

<!-- content -->
<div class="container">
        
    <!-- left side content -->
    <div class="col-xs-12 col-sm-8 col-md-8 content-leftside">
        
        <!-- breadcrumb bar -->
        <div class="row">
            <ol class="breadcrumb">
                <li><a href="startseite.php">it.magazin</a></li>
                <li><a href="informationstechnologie.php">Informationstechnologie</a>
                </li><li class="active">Aufgerufener Artikel</li>
            </ol>
        </div>
        
        <!-- article -->
        <div class="row">
            <h3><c:out value="${requestScope.selectedArticle.getTitle()}"/></h3>
        </div>
        
        <div class="row">
            <p>
                    <c:out value="${requestScope.selectedArticle.getText()}"/>
            </p>
        </div>
        
        <!-- date -->
        <div class="row text-right">
                <time>26.10.2016</time>    
        </div>
        
    </div>
    
    <!-- right side content -->
    <div class="col-xs-12 col-sm-6 col-md-4 content-rightside">
        
        <!-- search bar -->
        <?php include "searchbar.php"; ?>
        
        <!-- new articles -->
        <div class="row">
            <h3>Lesen Sie auch</h3>
        </div>
        
        <!-- article #1 -->
        <div class="row">
            <h4> Deutschland bei WLAN-Hotspots abgeschlagen </h4>
        	<p> 
            	Obwohl im Vergleich zu vergangenen Jahren Zuw&aumlchse bei &oumlffentlichen
            	WLAN-Angeboten zu verzeichnen sind, hinkt der Ausbau in Deutschland hinterher.
            	Kritiker f&uumlhren als Ursache insb. die lange Zeit komplizierte rechtliche Struktur
            	rund um St&oumlrerhaftung und Abnahmegeb&uumlhren ... 
            </p>
        	<button type=button class="btn btn-default"> Ganzen Artikel lesen &#187;</button>
        </div>
	
	    <!-- article #2 -->
	    <div class="row">
	        <h4> Fiber-to-the-Home vielfach unerschwinglich </h4>
        	<p> 
            	Eine Internetanbindung &uumlber Lichtwellenleiter, die bis ins eigene Zuhause verlegt werden,
            	verspricht hohe &Uumlbertragungsraten und niedrige Latenzen. Aufgrund der hohen Kosten stellt das
            	Verfahren ... 
            </p>
            <a href="#" class="btn btn-default">Ganzen Artikel lesen &#187;</a>
	    </div>
        
    </div>
    
</div>

<!-- include footer file -->
<?php include "foot.php"; ?>
        
   