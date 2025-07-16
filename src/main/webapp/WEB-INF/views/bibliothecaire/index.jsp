<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="mg.itu.spring.entity.Bibliothecaire" %>
<%
    Bibliothecaire b = (Bibliothecaire) session.getAttribute("bibliothecaire");
    if (b == null) {
        response.sendRedirect(request.getContextPath() + "/");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil Bibliothécaire</title>
</head>
<body>
    <h2>Bienvenue Bibliothécaire</h2>
    <p>Nom : <strong><%= b.getNom() %></strong></p>
    <p>Prénom : <strong><%= b.getPrenom() %></strong></p>

    <hr/>

    <h3>CRUD</h3>
    <ul>
        <li><a href="${pageContext.request.contextPath}/profil">profil</a></li>
        <li><a href="${pageContext.request.contextPath}/adherant">adherant</a></li>
        <li><a href="${pageContext.request.contextPath}/livre">livre</a></li>
        <li><a href="${pageContext.request.contextPath}/exemplaire">exemplaire</a></li>
        <li><a href="${pageContext.request.contextPath}/bibliothecaire">bibliothecaire</a></li>
    </ul>

    <h3>Fonctionnalite</h3>
    <ul>
        <li><a href="${pageContext.request.contextPath}/reservation/liste">validation reservation</a></li>
        <li><a href="${pageContext.request.contextPath}/livre/preter">preter un livre</a></li>
        <li><a href="${pageContext.request.contextPath}/reabonnement/add">reabonnement</a></li>
        <li><a href="${pageContext.request.contextPath}/prolongement/list">Validation prolongement</a></li>
    </ul>

    <hr/>
    <a href="<%= request.getContextPath() %>/logout">Se déconnecter</a>
</body>
</html>
