<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="java.util.List, mg.itu.spring.entity.Exemplaire" %>
<%
    List<Exemplaire> exs = (List<Exemplaire>) request.getAttribute("exemplaires");
%>
<html>
<head><title>Liste des exemplaires</title></head>
<body>
<h2>Liste des exemplaires</h2>
<a href="<%= request.getContextPath() %>/exemplaire/add">Ajouter exemplaires</a><br/><br/>

<table border="1">
    <tr>
        <th>ID</th><th>Livre</th><th>Numéro</th><th>Ref</th><th>Date indispo</th><th>Actions</th>
    </tr>
    <% for (Exemplaire e : exs) { %>
    <tr>
        <td><%= e.getId() %></td>
        <td><%= e.getLivre().getTitre() %></td>
        <td><%= e.getNumero() %></td>
        <td><%= e.getRef() != null ? e.getRef() : "" %></td>
        <td><%= e.getDateIndispo() != null ? e.getDateIndispo() : "" %></td>
        <td>
            <a href="<%= request.getContextPath() %>/exemplaire/edit/<%= e.getId() %>">Modifier</a> |
            <%-- <a href="<%= request.getContextPath() %>/exemplaire/delete/<%= e.getId() %>" onclick="return confirm('Supprimer ?')">Supprimer</a> --%>
        </td>
    </tr>
    <% } %>
</table>
<a href="${pageContext.request.contextPath}/bibliothecaire/index">Retour</a>
</body>
</html>