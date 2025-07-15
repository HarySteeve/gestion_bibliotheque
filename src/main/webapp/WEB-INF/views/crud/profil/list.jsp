<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="java.util.List" %>
<%@ page import="mg.itu.spring.entity.Profil" %>

<%
    List<Profil> profils = (List<Profil>) request.getAttribute("profils");
%>

<html>
<head>
    <title>Liste des profils</title>
</head>
<body>
    <h2>Liste des profils</h2>

    <a href="<%= request.getContextPath() %>/profil/add">Ajouter un profil</a><br/><br/>

    <table border="1" cellpadding="5">
        <thead>
            <tr>
                <th>ID</th>
                <th>Description</th>
                <th>Max Livres</th>
                <th>Durée Prêt</th>
                <th>Nb prolongements</th>
                <th>Nb réservations</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <% if (profils != null) {
               for (Profil profil : profils) { %>
            <tr>
                <td><%= profil.getId() %></td>
                <td><%= profil.getDescription() %></td>
                <td><%= profil.getNbLivreMax() %></td>
                <td><%= profil.getDureePret() %> j</td>
                <td><%= profil.getNbProlongement() %> </td>
                <td><%= profil.getNbReservationMax() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/profil/edit/<%= profil.getId() %>">Modifier</a> |
                    <a href="<%= request.getContextPath() %>/profil/delete/<%= profil.getId() %>"
                       onclick="return confirm('Supprimer ce profil ?')">Supprimer</a>
                </td>
            </tr>
        <%   }
           } else { %>
            <tr>
                <td colspan="8">Aucun profil trouvé.</td>
            </tr>
        <% } %>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/bibliothecaire/index">Retour</a>
</body>
</html>
