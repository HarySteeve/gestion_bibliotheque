<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ page import="java.util.*" %>
<%@ page import="mg.itu.spring.entity.*" %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    String successMessage = (String) request.getAttribute("successMessage");
    boolean isEdit = false;
    Pret pret = (Pret) request.getAttribute("pret");
    if (pret != null) {
        isEdit = true;
    } else {
        // Sinon, on regarde la variable transmise par le handler d'exception
        Boolean isEditAttr = (Boolean) request.getAttribute("isEdit");
        if (isEditAttr != null) {
            isEdit = isEditAttr;
        }
    }
    List<TypePret> typePrets = (List<TypePret>) request.getAttribute("typePrets");

    // Données pour réinjection après erreur
    Integer idAdherant = (Integer) request.getAttribute("idAdherant");
    String titreLivre = (String) request.getAttribute("titreLivre");
    Integer numero = (Integer) request.getAttribute("numero");
    Integer idTypePret = (Integer) request.getAttribute("idTypePret");
    String datePris = (String) request.getAttribute("datePris");
    String dateRendu = (String) request.getAttribute("dateRendu");
    String causePenalite = (String) request.getAttribute("causePenalite");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= isEdit ? "Modifier un pret" : "Nouveau prêt" %></title>
</head>
<body>
    <h2><%= isEdit ? "Modifier un pret" : "Formulaire pour prêter un exemplaire" %></h2>
    
    <% if(errorMessage != null) { %>
            <div style = "color:red; font-weight: bold">
                Erreur: <%= errorMessage %>
            </div>
    <%  }  %>

    <% if(successMessage != null) { %>
            <div style="color:green; font-weight:bold;">
                <%= successMessage %>
            </div>
    <%  } %>

    <form action="${pageContext.request.contextPath}/pret/<%= isEdit ? "modifier" : "effectuer" %>" method="post">
        <% if(isEdit) { %>
            <input type="hidden" name="id" value="<%= pret.getId() %>" />
        <% } %>

        <label for="idAdherant">Matricule adherant:</label>
        <div class="input">
            <input type="number" name = "idAdherant" id = "idAdherant" value="<%= isEdit ? pret.getAdherant().getId() : (idAdherant != null ? idAdherant : "") %>">
        </div>
        <br>
        </div>
        <label for="numeroExemplaire">Numero exemplaire</label>
        <div class="input">
            <input type="number" name = "numero" id = "numeroExemplaire" value="<%= isEdit ? pret.getExemplaire().getNumero() : (numero != null ? numero : "") %>">
        </div>
        <br>
        <label for="datePret">Date de pret</label>
        <div class="input">
            <input type="date" name = "datePret" id = "datePret" value="<%= isEdit ? pret.getDatePris() : (numero != null ? numero : "") %>">
        </div>
        <br>
        <label for="typePret">Type de pret</label>
        <div class="select">
            <select name="idTypePret" id="typePret">
                <%  for(TypePret typePret : typePrets) {
                        boolean selected = false;
                        if(isEdit && pret.getTypePret().getId() == typePret.getId()) {
                            selected = true;
                        } else if(idTypePret != null && idTypePret == typePret.getId()) {
                            selected = true;
                        }
                %>
                        <option value="<%= typePret.getId() %>" <%= selected ? "selected" : ""%>>
                            <%= typePret.getLibelle() %>
                        </option>
                <%  } %>
            </select>
        </div>
        <br>
        <% if(isEdit || datePris != null || dateRendu != null || causePenalite != null) { %>
            <label for="datePris">Date pris:</label>
            <div class="input">
                <input type="date" name="datePris" value="<%= isEdit ? pret.getDatePris() : (datePris != null ? datePris : "") %>">
            </div>
            <br>

            <label for="dateRendu">Date rendu:</label>
            <div class="input">
                <input type="date" name="dateRendu" value="<%= isEdit && pret.getDateRendu() != null ? pret.getDateRendu() : (dateRendu != null ? dateRendu : "") %>">
            </div>
            <br>
        <% } %>
        <div class="button">
            <button type="submit"><%= isEdit ? "Mettre a jour" : "Valider" %></button>
        </div>
    </form>
    
    <a href="${pageContext.request.contextPath}/pret/liste">Voir liste prets</a>
    <br>
    <a href="${pageContext.request.contextPath}/bibliothecaire/index">Retour</a>
</body>
</html>