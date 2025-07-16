<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="java.util.*" %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    Integer idAdherant = (Integer) request.getAttribute("idAdherant");
    String titreLivre = (String) request.getAttribute("titreLivre");
    String reference = (String) request.getAttribute("reference");
    String dateReservee = (String) request.getParameter("dateReservee");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Demande de reservation</title>
</head>
<body>
    <h2>Demande de reservation</h2>

    <% if (errorMessage != null) { %>
        <div style="color:red; font-weight: bold">
            Erreur: <%= errorMessage %>
        </div>
    <% } %>

    <%
        String successMessage = (String) request.getAttribute("successMessage");
        if (successMessage != null) {
    %>
        <div style="color:green; font-weight:bold;">
            <%= successMessage %>
        </div>
    <% } %>


    <form action="${pageContext.request.contextPath}/demande/reservation/form" method="post">
        <!-- idAdherant transmis en hidden -->
        <input type="hidden" name="idAdherant" value="<%= idAdherant != null ? idAdherant : "1" %>" />

        <label for="titreLivre">Titre du livre</label>
        <div class="input">
            <input type="text" name="titreLivre" value="<%= (titreLivre != null) ? titreLivre : "" %>">
        </div>
        <br>

        <label for="numeroExemplaire">Reference</label>
        <div class="input">
            <input type="text" name="reference" id="numeroExemplaire" value="<%= (reference != null) ? reference : "" %>">
        </div>
        <br>

        <label for="dateReservee">Date reservee</label>
        <div class="input">
            <input type="date" name="dateReservee" id="dateReservee"
                value="<%= (dateReservee != null) ? dateReservee : "" %>">
        </div>
        <br>

        <div class="button">
            <button type="submit">Reserver</button>
        </div>
        
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/adherant/index">Retour</a>
</body>
</html>
