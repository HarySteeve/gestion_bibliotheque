<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="java.util.*" %>
<%@ page import="mg.itu.spring.entity.Pret" %>
<%
    List<Pret> listePrets = (List<Pret>) request.getAttribute("listePrets");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des prêts</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        a.button {
            padding: 6px 12px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }

        a.button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Liste des prets</h2>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Adhérant</th>
                <th>Exemplaire</th>
                <th>Type de prêt</th>
                <th>Date pris</th>
                <th>rendu obligatoire</th>
                <th>Date rendu</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (listePrets != null && !listePrets.isEmpty()) {
                    for (Pret pret : listePrets) {
            %>
                        <tr>
                            <td><%= pret.getId() %></td>
                            <td><%= pret.getAdherant().getNom() %> <%= pret.getAdherant().getPrenom() %></td>
                            <td><%= pret.getExemplaire().getRef() %></td>
                            <td><%= pret.getTypePret().getLibelle() %></td>
                            <td><%= pret.getDatePris() %></td>
                            <td><%= pret.getDatePris().plusDays(pret.getAdherant().getProfil().getDureePret()) %></td>
                            <td><%= pret.getDateRendu() != null ? pret.getDateRendu() : "-" %></td>
                            <td>
                                <a class="button" href="${pageContext.request.contextPath}/pret/form/modifier?id=<%= pret.getId() %>">Modifier</a>
                            </td>
                        </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="7" style="text-align: center;">Aucun prêt trouvé.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/livre/preter">Retour</a>
</body>
</html>
