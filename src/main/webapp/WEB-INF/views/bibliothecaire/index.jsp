<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>BOBOLOTHEQUE</h1>
    <h3>CRUD</h3>
    <ul>
        <li><a href="${pageContext.request.contextPath}/profil">profil</a></li>
        <li><a href="${pageContext.request.contextPath}/adherant">adherant</a></li>
        <li><a href="${pageContext.request.contextPath}/livre">livre</a></li>
        <li><a href="${pageContext.request.contextPath}/exemplaire">exemplaire</a></li>
        <li><a href="${pageContext.request.contextPath}/bibliothecaire">bibliothecaire</a></li>
    </ul>
    <h3>Fonctionnalite</h3>
    <ul>
        <li><a href="${pageContext.request.contextPath}/livre/preter">preter un livre</a></li>
        <li><a href="${pageContext.request.contextPath}/reabonnement/add">reabonnement</a></li>
    </ul>
    <h3>Fonctionnalite adherant</h3>
    <ul>
        <li><a href="${pageContext.request.contextPath}/reservation/demande">Demande de reservation</a></li>
    <h3>Login</h3>
    <ul>
        <li><a href="login">login</a></li>
    </ul>
</body>
</html>