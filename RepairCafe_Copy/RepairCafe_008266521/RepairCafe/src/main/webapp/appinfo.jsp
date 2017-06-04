<%-- 
    Document   : appinfo
    Created on : 10-Aug-2014, 19:48:42
    Author     : V R
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informatie over RepairCafe</title>
    </head>
    <body>
        <h2>Informatie over RepairCafe</h2>
        <table border="1">
            <caption>Auteurs:</caption>
            <c:forEach var="auteur" items="${requestScope.auteurs}">
                <tr>
                    <c:choose>
                        <c:when test="${auteur eq 'V R (student)'}">
                            <td>Front end:</td>
                        </c:when>
                        <c:otherwise>
                            <td>Back end:</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${auteur}</td>
                </tr>
            </c:forEach>
        </table>
        
        Gebruikte technologieën:
    </body>
</html>
