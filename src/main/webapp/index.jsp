<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<html>
<head>
    <title>Connexion - Bibliothèque</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&family=Playfair+Display:wght@400;500&display=swap" rel="stylesheet">
</head>
<body>
    <div class="login-container">
        <div class="login-header">
            <h1>Bibliothèque Universelle</h1>
            <h2>Connexion à votre espace</h2>
        </div>

        <% if (request.getAttribute("error") != null) { %>
            <div class="error-message">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

        <form action="<%= request.getContextPath() %>/login" method="post" class="login-form">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" id="email" name="email" placeholder="exemple@gmail.com">
            </div>

            <div class="form-group">
                <label for="mdp">Mot de passe</label>
                <input type="password" id="mdp" name="mdp" placeholder="••••••••">
            </div>

            <button type="submit" class="submit-btn">Se connecter</button>
        </form>
    </div>
</body>
</html>