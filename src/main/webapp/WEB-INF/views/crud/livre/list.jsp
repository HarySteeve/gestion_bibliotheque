<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.itu.spring.entity.Livre" %>

<%
    List<Livre> livres = (List<Livre>) request.getAttribute("livres");
%>

<html>
<head>
    <title>Liste des livres</title>
</head>
<body>
<h2>Liste des livres</h2>
<a href="<%= request.getContextPath() %>/livre/add">Ajouter un livre</a><br/><br/>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Titre</th>
        <th>IBN</th>
        <th>Édition</th>
        <th>Tag</th>
        <th>Auteur</th>
        <th>Actions</th>
    </tr>
    <% for (Livre l : livres) { %>
    <tr>
        <td><%= l.getId() %></td>
        <td><%= l.getTitre() %></td>
        <td><%= l.getIbn() %></td>
        <td><%= l.getEdition() %></td>
        <td><%= l.getTag() %></td>
        <td><%= l.getAuteur().getNom() %> <%= l.getAuteur().getPrenom() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/livre/edit/<%= l.getId() %>">Modifier</a> |
            <%-- <a href="<%= request.getContextPath() %>/livre/delete/<%= l.getId() %>" onclick="return confirm('Supprimer ?')">Supprimer</a> --%>
        </td>
    </tr>
    <% } %>
</table>
<a href="${pageContext.request.contextPath}/bibliothecaire/index">Retour</a>
</body>
</html>
