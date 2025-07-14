<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="mg.itu.spring.entity.Adherant, mg.itu.spring.entity.Profil, java.util.List" %>

<%
    Adherant adherant = (Adherant) request.getAttribute("adherant");
    List<Profil> profils = (List<Profil>) request.getAttribute("profils");
%>

<html>
<head>
    <title>Formulaire Adhérant</title>
</head>
<body>
<h2>Formulaire Adhérant</h2>

<form action="<%= request.getContextPath() %>/adherant/save" method="post">
    <% if (adherant.getId() != null) { %>
        <input type="hidden" name="id" value="<%= adherant.getId() %>" />
    <% } %>

    <label>Nom:</label><br/>
    <input type="text" name="nom" value="<%= adherant.getNom() != null ? adherant.getNom() : "" %>" /><br/>

    <label>Prénom:</label><br/>
    <input type="text" name="prenom" value="<%= adherant.getPrenom() != null ? adherant.getPrenom() : "" %>" /><br/>
    
    <label>Date de naissance:</label><br/>
    <input type="date" name="dtn" value="<%= adherant.getDtn() != null ? adherant.getDtn() : "" %>" /><br/>
    
    <label>Email:</label><br/>
    <input type="email" name="email" value="<%= adherant.getEmail() != null ? adherant.getEmail() : "" %>" /><br/>

    <label>Mot de passe:</label><br/>
    <input type="password" name="mdp" value="<%= adherant.getMdp() != null ? adherant.getMdp() : "" %>" /><br/>

    <label>Profil:</label><br/>
    <select name="profil.id">
        <% for (Profil p : profils) { %>
            <option value="<%= p.getId() %>" <%= (adherant.getProfil() != null && adherant.getProfil().getId().equals(p.getId())) ? "selected" : "" %>>
                <%= p.getDescription() %>
            </option>
        <% } %>
    </select><br/><br/>

    <button type="submit">Valider</button>
    <a href="<%= request.getContextPath() %>/adherant">Annuler</a>
</form>

</body>
</html>
