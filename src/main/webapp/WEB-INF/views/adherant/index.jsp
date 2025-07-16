<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="mg.itu.spring.entity.Adherant" %>
<%
    Adherant a = (Adherant) session.getAttribute("adherant");
    if (a == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<html>
<head><title>Accueil Adhérant</title></head>
<body>
    <h2>Bienvenue <%= a.getNom() %> <%= a.getPrenom() %></h2>
    <p>Page d'accueil réservée aux adhérants</p>
    <a href="<%= request.getContextPath() %>/logout">Se déconnecter</a>
    <li><a href="<%= request.getContextPath() %>/prolongement/form">Faire un prolongement</a></li>
    <a href="<%= request.getContextPath() %>/reservation/demande">Demande de reservation</a>
</body>
</html>
