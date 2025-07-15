<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="java.util.List" %>
<%@ page import="mg.itu.spring.entity.Reservation" %>

<%
    List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Liste des Reservations</title>
</head>
<body>
    <h2>rRservations en attentes</h2>

    <table border="1" cellpadding="5">
        <thead>
            <tr>
                <th>ID</th>
                <th>Adherant</th>
                <th>Exemplaire</th>
                <th>Date Demande</th>
                <th>Date Réservée</th>
                <th>Date Validation</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <% for (Reservation res : reservations) { %>
            <tr>
                <td><%= res.getId() %></td>
                <td><%= res.getAdherant().getNom() %></td>
                <td><%= res.getExemplaire().getRef() %></td>
                <td><%= res.getDateDemandeReservation() %></td>
                <td><%= res.getDateReservee() %></td>
                <td><%= res.getDateValidation() != null ? res.getDateValidation() : "-" %></td>
                <td>
                    <% if (res.getDateValidation() == null) { %>
                        <form action="${pageContext.request.contextPath}/reservation/valider" method="post" style="display:inline;">
                            <input type="hidden" name="idReservation" value="<%= res.getId() %>"/>
                            <input type="hidden" name="idBibliothecaire" value="1"/>
                            <button type="submit">Valider</button>
                        </form>
                    <% } else { %>
                        <span style="color: gray;">Validee</span>
                    <% } %>
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>
</body>
</html>
