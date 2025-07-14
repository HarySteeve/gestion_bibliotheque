<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List, mg.itu.spring.entity.Livre" %>
<%
    List<Livre> livres = (List<Livre>) request.getAttribute("livres");
%>
<html>
<head><title>Ajouter Exemplaires</title></head>
<body>
<h2>Ajouter des exemplaires</h2>

<form action="<%= request.getContextPath() %>/exemplaire/save" method="post">
    <label>Livre :</label><br/>
    <select name="idLivre">
        <% for (Livre l : livres) { %>
            <option value="<%= l.getId() %>"><%= l.getTitre() %></option>
        <% } %>
    </select><br/>

    <label>Quantité :</label><br/>
    <input type="number" name="quantite" min="1" /><br/><br/>

    <button type="submit">Ajouter</button>
    <a href="<%= request.getContextPath() %>/exemplaire">Annuler</a>
</form>
</body>
</html>