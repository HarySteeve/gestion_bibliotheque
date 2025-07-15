<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="mg.itu.spring.entity.Livre, mg.itu.spring.entity.Auteur, java.util.List" %>

<%
    Livre livre = (Livre) request.getAttribute("livre");
    List<Auteur> auteurs = (List<Auteur>) request.getAttribute("auteurs");
%>

<html>
<head>
    <title>Formulaire Livre</title>
</head>
<body>
<h2>Formulaire Livre</h2>

<form action="<%= request.getContextPath() %>/livre/save" method="post">
    <% if (livre.getId() != null) { %>
        <input type="hidden" name="id" value="<%= livre.getId() %>" />
    <% } %>

    <label>Titre:</label><br/>
    <input type="text" name="titre" value="<%= livre.getTitre() != null ? livre.getTitre() : "" %>" /><br/>
    
    <label>IBN:</label><br/>
    <input type="text" name="ibn" value="<%= livre.getIbn() != null ? livre.getIbn() : "" %>" /><br/>

    <label>Édition (AAAA-MM-JJ):</label><br/>
    <input type="date" name="edition" value="<%= livre.getEdition() != null ? livre.getEdition() : "" %>" /><br/>

    <label>Tag:</label><br/>
    <input type="text" name="tag" value="<%= livre.getTag() != null ? livre.getTag() : "" %>" /><br/>

    <label>Auteur:</label><br/>
    <select name="auteur.id">
        <% for (Auteur a : auteurs) { %>
            <option value="<%= a.getId() %>" <%= (livre.getAuteur() != null && livre.getAuteur().getId().equals(a.getId())) ? "selected" : "" %>>
                <%= a.getNom() %> <%= a.getPrenom() %>
            </option>
        <% } %>
    </select><br/><br/>

    <button type="submit">Valider</button>
    <a href="<%= request.getContextPath() %>/livre">Annuler</a>
</form>
</body>
</html>
