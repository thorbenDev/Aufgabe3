<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
		language="java" trimDirectiveWhitespaces="true" %>
		
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="de">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Fehler</title>
	</head>
	<body>
		<h1>Fehler</h1>
		<p>Beim Verarbeiten Ihrer Anfrage trat der folgende Fehler auf:</p>
		<p style="color:red;">
			<%-- Fehlermeldung ausgeben, die der Controller bereitgelegt hat: --%>
			<c:out value="${requestScope.errorMessage}"/>
		</p>
	</body>
</html>
