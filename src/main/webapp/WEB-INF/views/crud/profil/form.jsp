<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="mg.itu.spring.entity.Profil" %>
<%
    Profil profil = (Profil) request.getAttribute("profil");
%>
<html>
<head>
    <title>Formulaire Profil</title>
</head>
<body>
    <h2>Formulaire Profil</h2>
    <form action="<%= request.getContextPath() %>/profil/save" method="post">
        <% if (profil != null && profil.getId() != null) { %>
            <input type="hidden" name="id" value="<%= profil.getId() %>" />
        <% } %>

        <label>Description:</label><br/>
        <input type="text" name="description" value="<%= profil != null ? profil.getDescription() : "" %>" /><br/>

        <label>Nombre max de livres:</label><br/>
        <input type="number" name="nbLivreMax" value="<%= profil != null ? profil.getNbLivreMax() : 0 %>" /><br/>

        <label>Durée de prêt (jours):</label><br/>
        <input type="number" name="dureePret" value="<%= profil != null ? profil.getDureePret() : 0 %>" /><br/>

        <label>Nombre de prolongements:</label><br/>
        <input type="number" name="nbProlongement" value="<%= profil != null ? profil.getNbProlongement() : 0 %>" /><br/>

        <label>Max réservations:</label><br/>
        <input type="number" name="nbReservationMax" value="<%= profil != null ? profil.getNbReservationMax() : 0 %>" /><br/>

        <label>Duree max de réservations (jours):</label><br/>
        <input type="number" name="dureeJourReservationngementMax" value="<%= profil != null ? profil.getDureeJourReservationMax() : 0 %>" /><br/>

        <button type="submit">Enregistrer</button>
        <a href="<%= request.getContextPath() %>/profil">Annuler</a>
    </form>
</body>
</html>
