<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="java.util.List" %>
<%@ page import="mg.itu.spring.entity.Livre" %>

<%
    List<Livre> livres = (List<Livre>) request.getAttribute("livres");
%>

<html>
<head>
    <title>Liste des livres</title>
</head>
<body>
<h2>Liste des livres</h2>
<a href="<%= request.getContextPath() %>/livre/add">Ajouter un livre</a><br/><br/>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Titre</th>
        <th>IBN</th>
        <th>Édition</th>
        <th>Tag</th>
        <th>Auteur</th>
        <th>Actions</th>
    </tr>
    <% for (Livre l : livres) { %>
    <tr>
        <td><%= l.getId() %></td>
        <td><%= l.getTitre() %></td>
        <td><%= l.getIbn() %></td>
        <td><%= l.getEdition() %></td>
        <td><%= l.getTag() %></td>
        <td><%= l.getAuteur().getNom() %> <%= l.getAuteur().getPrenom() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/livre/edit/<%= l.getId() %>">Modifier</a> |
            <a href="<%= request.getContextPath() %>/livre/API/detail/<%= l.getId() %>">Detail</a>
        </td>
    </tr>
    <% } %>
</table>
<a href="${pageContext.request.contextPath}/bibliothecaire/index">Retour</a>


<!-- <script>
  async function afficherDetails(idLivre) {
    const container = document.getElementById('details-container');
    container.innerHTML = "<p>Chargement...</p>";
    const contextPath = "<%--= request.getContextPath() --%>";

    try {
      const response = await fetch(contextPath + "/livre/API/detail/" + idLivre);
      if (!response.ok) {
        throw new Error("Erreur HTTP : " + response.status);
      }

      const data = await response.json();
      const livre = data.livre;
      const exemplaires = data.exemplaires;

      let html = "";
      html += "<h4>Détail du livre</h4>";
      html += "<p><strong>Titre :</strong> " + livre.titre + "</p>";
      html += "<p><strong>IBN :</strong> " + livre.ibn + "</p>";
      html += "<p><strong>Tag :</strong> " + livre.ibn + "</p>";
      html += "<p><strong>Édition :</strong> " + livre.edition + "</p>";
      html += "<p><strong>Auteur :</strong> " + livre.auteur.nom + " " + livre.auteur.prenom + "</p>";

      html += "<h4>Exemplaires</h4>";
      html += "<ul>";

      if (exemplaires.length === 0) {
        html += "<li>Aucun exemplaire</li>";
      } else {
        for (const ex of exemplaires) {
          const dispo = ex.dateIndispo == null ? "✅ Disponible" : "❌ Indisponible";
          html += "<li>Numéro: " + ex.numero + ", Référence: " + ex.ref + " — " + dispo + "</li>";
        }
      }

      html += "</ul>";

      container.innerHTML = html;

    } catch (error) {
      container.innerHTML = "<p style='color:red;'>Erreur : " + error.message + "</p>";
    }
  }
</script> -->

</body>
</html>
