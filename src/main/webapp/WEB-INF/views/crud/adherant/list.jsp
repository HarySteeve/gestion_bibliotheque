<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="java.util.List" %>
<%@ page import="mg.itu.spring.entity.Adherant" %>

<%
    List<Adherant> adherants = (List<Adherant>) request.getAttribute("adherants");
%>

<html>
<head>
    <title>Liste des Adhérants</title>
</head>
<body>
<h2>Liste des Adhérants</h2>
<a href="<%= request.getContextPath() %>/adherant/add">Ajouter un adhérant</a><br/><br/>

<table border="1">
    <tr>
        <%-- <th>ID</th> --%>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Profil</th>
        <th>Dtn</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    <% for (Adherant a : adherants) { %>
    <tr>
        <%-- <td><%= a.getId() %></td> --%>
        <td><%= a.getNom() %></td>
        <td><%= a.getPrenom() %></td>   
        <td><%= a.getProfil().getDescription() %></td>
        <td><%= a.getDtn() %></td>
        <td><%= a.getEmail() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/adherant/edit/<%= a.getId() %>">Modifier</a> |
            <a href="<%= request.getContextPath() %>/adherant/API/detail/<%= a.getId() %>">Detail</a>
        </td>
    </tr>
    <% } %>
</table>
<a href="${pageContext.request.contextPath}/bibliothecaire/index">Retour</a>
</body>
</html>
