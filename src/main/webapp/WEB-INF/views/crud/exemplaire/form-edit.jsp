<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="mg.itu.spring.entity.Exemplaire" %>
<%
    Exemplaire ex = (Exemplaire) request.getAttribute("exemplaire");
%>
<html>
<head><title>Modifier Exemplaire</title></head>
<body>
<h2>Modifier Exemplaire</h2>

<form action="<%= request.getContextPath() %>/exemplaire/update" method="post">
    <input type="hidden" name="id" value="<%= ex.getId() %>" />
    <label>Numéro : </label><%= ex.getNumero() %><br/>
    <label>Livre : </label><%= ex.getLivre().getTitre() %><br/><br/>

    <label>Référence :</label><br/>
    <input type="text" name="ref" value="<%= ex.getRef() != null ? ex.getRef() : "" %>" /><br/>

    <label>Date indispo :</label><br/>
    <input type="date" name="dateIndispo" value="<%= ex.getDateIndispo() != null ? ex.getDateIndispo() : "" %>" /><br/><br/>

    <button type="submit">Mettre à jour</button>
    <a href="<%= request.getContextPath() %>/exemplaire">Annuler</a>
</form>
</body>
</html>