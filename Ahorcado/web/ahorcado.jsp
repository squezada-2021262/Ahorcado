<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adivina la Palabra</title>
    <link rel="stylesheet" href="css/css.css">
</head>
<body>
    <h1>Adivina la Palabra</h1>
    <div class="main-container">
        <div class="game-area">
            <div id="temporizador"></div>
            <div class="muneco-container">
                <img id="imagen-muneco" src="img/0.png" alt="Muñeco de intentos">
            </div>
            <div id="palabra-secreta"></div>
            <div id="mensaje"></div>
            <div class="botones-container">
                <button id="btn-iniciar">Iniciar</button>
                <button id="btn-reiniciar">Reiniciar</button>
                <button id="btn-pausar">Pausar</button>
                <button id="btn-salir">Salir</button>
            </div>
        </div>
        <div class="keyboard-area">
            <div class="card-container">
                <div id="keyboard"></div>
            </div>
            <div class="card-container">
                <h3>Pistas</h3>
                <div id="pistas"></div>
            </div>
        </div>
    </div>
    <script src="js/script.js"></script>
</body>
</html>