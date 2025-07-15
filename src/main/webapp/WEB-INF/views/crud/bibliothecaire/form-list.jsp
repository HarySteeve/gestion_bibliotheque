<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="java.util.List" %>
<%@ page import="mg.itu.spring.entity.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <%
        Bibliothecaire b = (Bibliothecaire) request.getAttribute("bibliothecaire");
        boolean isEdit = (b != null);
    %>
    <form action="<%= request.getContextPath() + (isEdit ? "/bibliothecaire/update" : "/bibliothecaire/add") %>" method="post">
        <input type="hidden" name="id" value="<%= isEdit ? b.getId() : "" %>">
        <p>
            Nom : <input type="text" name="nom" value="<%= isEdit ? b.getNom() : "" %>">
        </p>
        <p>
            Prenom : <input type="text" name="prenom" value="<%= isEdit ? b.getPrenom() : "" %>">
        </p>
        <p>
            Mot de passe : <input type="text" name="mdp" value="<%= isEdit ? b.getMdp() : "" %>">
        </p>
        <p>
            Email : <input type="email" name="email" value="<%= isEdit ? b.getEmail() : "" %>">
        </p>
        <p>
            Nom utilisateur : <input type="text" name="username" value="<%= isEdit ? b.getUsername() : "" %>">
        </p>
        <p>
            Date embauche : <input type="date" name="dateEmbauche" value="<%= isEdit ? b.getDateEmbauche() : "" %>">
        </p>
        <% if(isEdit) { %>
            <p>
                Date renvoie : <input type="date" name="dateRenvoie" value="<%= isEdit ? b.getDateRenvoie() : "" %>">
            </p>
        <% } %>
        <p>
            <input type="submit" value="Soumettre">
        </p>
    </form>

    <h2>Liste des Bibliothecaires</h2>
    <ul>
        <%
            List<Bibliothecaire> bibliothecairesActifs = (List<Bibliothecaire>) request.getAttribute("bibliothecairesActifs");
            List<Bibliothecaire> bibliothecairesVires = (List<Bibliothecaire>) request.getAttribute("bibliothecairesVires");
        %>
            <h3>Bibiothcaires actifs</h3>
        <%
            if(bibliothecairesActifs == null || bibliothecairesActifs.isEmpty()) {
        %>
                <li>Aucunes bibliothecaires actifs</li>

        <%  } else {
                for (Bibliothecaire bibliothecaire : bibliothecairesActifs) {
        %>
                    <li>
                        <%= bibliothecaire.getId() %> - <%= bibliothecaire.getUsername() %> - Date embauche: <%= bibliothecaire.getDateEmbauche() %>
                        [<a href="<%= request.getContextPath() %>/bibliothecaire/edit?id=<%= bibliothecaire.getId() %>">Modifier</a>]
                        [<a href="<%= request.getContextPath() %>/bibliothecaire/virer?id=<%= bibliothecaire.getId() %>">Virer</a>]
                    </li>
        <%
                }
            }
        %>
            <h3>Bibiothcaires vires</h3>
        <%            
            if(bibliothecairesVires == null || bibliothecairesVires.isEmpty()) {
        %>
                <li>Aucunes bibliothecaires virees</li>
        
        <%  } else {
                for (Bibliothecaire bibliothecaire : bibliothecairesVires) {
        %>
                    <li>
                        <%= bibliothecaire.getId() %> - <%= bibliothecaire.getUsername() %> - Date embauche: <%= bibliothecaire.getDateEmbauche() %> - Date renvoie: <%= bibliothecaire.getDateRenvoie() %>
                        [<a href="bibliothecaire/edit?id=<%= bibliothecaire.getId() %>">Modifier</a>]
                    </li>
        <% 
                } 
            }
        %>
    </ul>
    <br>
    <a href="${pageContext.request.contextPath}/bibliothecaire/index">Retour</a>
</body>
</html>