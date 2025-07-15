<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="java.util.List" %>
<%@ page import="mg.itu.spring.entity.Pret" %>
<%
    List<Pret> prets = (List<Pret>) request.getAttribute("prets");
    String erreur = (String) request.getAttribute("error");
%>
<html>
<head><title>Demande de prolongement</title></head>
<body>
    <h2>Faire une demande de prolongement</h2>

    <% if (erreur != null) { %>
        <p style="color:red;"><%= erreur %></p>
    <% } %>

    <form action="<%= request.getContextPath() %>/prolongement/submit" method="post">
        <label>Choisir un prêt :</label><br/>
        <select name="idPret">
            <% for (Pret p : prets) { %>
                <option value="<%= p.getId() %>">
                    Prêt du <%= p.getDatePris() %> - Exemplaire ID <%= p.getExemplaire().getId() %>
                </option>
            <% } %>
        </select><br/><br/>

        <button type="submit">Valider</button>
    </form>
</body>
</html>
