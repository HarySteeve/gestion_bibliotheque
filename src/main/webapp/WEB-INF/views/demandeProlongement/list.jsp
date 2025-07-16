<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="mg.itu.spring.entity.DemandeProlongement" %>
<%@ page import="mg.itu.spring.entity.Pret" %>
<%@ page import="mg.itu.spring.entity.Bibliothecaire" %>
<%
    List<DemandeProlongement> demandes = (List<DemandeProlongement>) request.getAttribute("demandes");
    Bibliothecaire b = (Bibliothecaire) session.getAttribute("bibliothecaire");
    if (b == null) {
        response.sendRedirect(request.getContextPath() + "/");
        return;
    }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des demandes de prolongement</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #aaa;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .action-btn {
            padding: 5px 10px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }

        .action-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <h2>Liste des demandes de prolongement</h2>

    <% if (demandes != null && !demandes.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Date Prêt</th>
                    <th>Nom Adhérant</th>
                    <th>Date demande</th>
                    <th>Début prolongement</th>
                    <th>Fin prolongement</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% for (DemandeProlongement demande : demandes) {
                    Pret pret = demande.getPret();
                %>
                    <tr>
                        <td><%= demande.getId() %></td>
                        <td><%= pret.getDatePris() %></td>
                        <td><%= pret.getAdherant().getNom() %> <%= pret.getAdherant().getPrenom() %></td>
                        <td><%= demande.getDateDemande() %></td>
                        <td><%= demande.getDebutProlongement() != null ? demande.getDebutProlongement() : "-" %></td>
                        <td><%= demande.getFinProlongement() != null ? demande.getFinProlongement() : "-" %></td>
                        <td>
                            <% if(demande.getDebutProlongement() == null) { %>
                                <a class="action-btn" href="<%= request.getContextPath() %>/prolongement/valider/<%= demande.getId() %>">Valider</a>
                            <% } else { %>
                                <span style="color: gray;">Validee</span>
                            <% } %>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>Aucune demande de prolongement en attente.</p>
    <% } %>
    <a href="${pageContext.request.contextPath}/bibliothecaire/index">Retour</a>
</body>
</html>
