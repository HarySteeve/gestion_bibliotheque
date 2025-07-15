<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="java.util.List, java.time.LocalDate, mg.itu.spring.entity.Adherant" %>
<%
    List<Adherant> adherants = (List<Adherant>) request.getAttribute("adherants");
    LocalDate today = (LocalDate) request.getAttribute("today");
%>
<html>
<head><title>Réabonnement</title></head>
<body>
<h2>Réabonnement</h2>
<form action="<%= request.getContextPath() %>/reabonnement/save" method="post">
    <label>Adhérant :</label><br/>
    <select name="idAdherant">
        <% for (Adherant a : adherants) { %>
            <option value="<%= a.getId() %>"><%= a.getNom() %> <%= a.getPrenom() %></option>
        <% } %>
    </select><br/>

    <label>Date réabonnement :</label><br/>
    <input type="date" name="dateReabonnement" value="<%= today %>"><br/><br/>

    <button type="submit">Valider</button>
</form>
<a href="${pageContext.request.contextPath}/bibliothecaire/index">Retour</a>
</body>
</html>
