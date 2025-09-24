<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="css/css.css">
</head>
<body>
    <div class="login-container">
        <h2>Iniciar Sesión</h2>
        <form id="login-form" action="/Ahorcado/Validar" method="post">
            <input type="hidden" name="accion" value="Login">
            <input type="text" name="usuario" placeholder="Usuario" required>
            <input type="password" name="contrasena" placeholder="Contraseña" required>
            <button type="submit">Entrar</button>
            <% if (request.getAttribute("errorMessage") != null) { %>
                <p id="error-message" style="display: block; color: red;">
                    <%= request.getAttribute("errorMessage") %>
                </p>
            <% } %>
        </form>
    </div>
</body>
</html>