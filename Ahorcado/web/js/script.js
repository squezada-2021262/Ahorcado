const palabraSecretaElemento = document.getElementById('palabra-secreta');
const mensajeElemento = document.getElementById('mensaje');
const imagenMuneco = document.getElementById('imagen-muneco');
const keyboardContainer = document.getElementById('keyboard');
const pistasContainer = document.getElementById('pistas');
const btnIniciar = document.getElementById('btn-iniciar');
const btnReiniciar = document.getElementById('btn-reiniciar');
const btnPausar = document.getElementById('btn-pausar');
const btnSalir = document.getElementById('btn-salir');
const temporizadorElemento = document.getElementById('temporizador');

let palabraActual = '';
let letrasAdivinadas = [];
let intentosFallidos = 0;
const maxIntentos = 6;
let pistasActuales = [];
let intervaloTemporizador;
let tiempoRestante = 120;
let estaPausado = false;

async function obtenerPalabraAleatoria() {
    try {
        // Forzar una nueva palabra al iniciar o reiniciar el juego
        const response = await fetch('/Ahorcado/Controlador?menu=Juego&accion=Jugar', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!response.ok) {
            throw new Error('Error al iniciar el juego');
        }
        // Obtener la palabra de la sesión
        const palabraResponse = await fetch('/Ahorcado/Controlador?menu=Juego&accion=ObtenerPalabra', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!palabraResponse.ok) {
            throw new Error('Error al obtener la palabra');
        }
        const data = await palabraResponse.json();
        if (data.error) {
            throw new Error(data.error);
        }
        console.log('Palabra recibida:', data.palabra); // Depuración
        return {
            palabra: data.palabra.toUpperCase(),
            pistas: [data.pista1, data.pista2, data.pista3]
        };
    } catch (error) {
        console.error('Error:', error);
        mensajeElemento.innerText = 'Error al cargar la palabra. Intenta de nuevo.';
        return null;
    }
}

async function iniciarJuego() {
    const palabraElegida = await obtenerPalabraAleatoria();
    if (!palabraElegida) {
        return;
    }
    
    palabraActual = palabraElegida.palabra;
    pistasActuales = palabraElegida.pistas;
    letrasAdivinadas = [];
    intentosFallidos = 0;
    tiempoRestante = 120;
    estaPausado = false;
    btnPausar.innerText = 'Pausar';
    
    mensajeElemento.innerText = '';
    imagenMuneco.src = `img/0.png`; 
    
    dibujarPalabra();
    dibujarPistas();
    generarTeclado();
    iniciarTemporizador();
}

function iniciarTemporizador() {
    temporizadorElemento.style.display = 'block';
    clearInterval(intervaloTemporizador);

    intervaloTemporizador = setInterval(() => {
        if (!estaPausado) {
            tiempoRestante--;
            temporizadorElemento.innerText = `Tiempo: ${Math.floor(tiempoRestante / 60)}:${(tiempoRestante % 60).toString().padStart(2, '0')}`;
            
            if (tiempoRestante <= 0) {
                clearInterval(intervaloTemporizador);
                seAcaboElTiempo();
            }
        }
    }, 1000);
}

function manejarPausa() {
    estaPausado = !estaPausado;
    if (estaPausado) {
        btnPausar.innerText = 'Reanudar';
        deshabilitarTeclado();
    } else {
        btnPausar.innerText = 'Pausar';
        habilitarTeclado();
    }
}

function seAcaboElTiempo() {
    deshabilitarTeclado();
    clearInterval(intervaloTemporizador);
    mensajeElemento.innerHTML = '¡Se te acabó el tiempo! La palabra era: ' + palabraActual + '<br><br><button id="btn-reiniciar-mensaje" class="btn-reiniciar-mensaje">Reiniciar Juego</button>';
    
    const btnReiniciarMensaje = document.getElementById('btn-reiniciar-mensaje');
    btnReiniciarMensaje.addEventListener('click', () => {
        iniciarJuego();
    });
}

function manejarLetra(letra) {
    if (letrasAdivinadas.includes(letra) || intentosFallidos >= maxIntentos || estaPausado) {
        return;
    }
    
    const btnLetra = document.getElementById(`btn-${letra}`);
    if (btnLetra) {
        btnLetra.disabled = true;
    }

    if (palabraActual.includes(letra)) {
        letrasAdivinadas.push(letra);
        mensajeElemento.innerText = '¡Correcto!';
    } else {
        intentosFallidos++;
        if (intentosFallidos <= maxIntentos) {
            imagenMuneco.src = `img/${intentosFallidos}.png`;
        }
        mensajeElemento.innerText = '¡Incorrecto!';
    }
    
    dibujarPalabra();
    verificarEstadoJuego();
}

function dibujarPalabra() {
    let palabraMostrada = '';
    for (const letra of palabraActual) {
        if (letrasAdivinadas.includes(letra)) {
            palabraMostrada += letra + ' ';
        } else {
            palabraMostrada += '_ ';
        }
    }
    palabraSecretaElemento.innerText = palabraMostrada.trim();
}

function dibujarPistas() {
    pistasContainer.innerHTML = '';
    const lista = document.createElement('ul');
    pistasActuales.forEach(pista => {
        const li = document.createElement('li');
        li.innerText = pista;
        lista.appendChild(li);
    });
    pistasContainer.appendChild(lista);
}

function generarTeclado() {
    keyboardContainer.innerHTML = '';
    const alfabeto = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    for (const letra of alfabeto) {
        const btn = document.createElement('button');
        btn.innerText = letra;
        btn.id = `btn-${letra}`;
        btn.onclick = () => manejarLetra(letra);
        keyboardContainer.appendChild(btn);
    }
}

function verificarEstadoJuego() {
    const palabraCompleta = palabraActual.split('').every(letra => letrasAdivinadas.includes(letra));
    if (palabraCompleta) {
        mensajeElemento.innerText = `¡Felicidades! Has adivinado la palabra: ${palabraActual}`;
        deshabilitarTeclado();
        clearInterval(intervaloTemporizador);
        
        setTimeout(() => {
            const nombreAnimalFormateado = palabraActual.charAt(0).toUpperCase() + palabraActual.slice(1).toLowerCase();
            imagenMuneco.src = `img/${nombreAnimalFormateado}.png`;
        }, 1000);
        
    } else if (intentosFallidos >= maxIntentos) {
        mensajeElemento.innerText = `¡Te quedaste sin intentos! La palabra era: ${palabraActual}`;
        deshabilitarTeclado();
        clearInterval(intervaloTemporizador);
        
        setTimeout(() => {
            const nombreAnimalFormateado = palabraActual.charAt(0).toUpperCase() + palabraActual.slice(1).toLowerCase();
            imagenMuneco.src = `img/${nombreAnimalFormateado}.png`;
        }, 1000);
    }
}

function deshabilitarTeclado() {
    const botones = keyboardContainer.getElementsByTagName('button');
    for (const btn of botones) {
        btn.disabled = true;
    }
}

function habilitarTeclado() {
    const botones = keyboardContainer.getElementsByTagName('button');
    for (const btn of botones) {
        btn.disabled = false;
    }
}

btnIniciar.addEventListener('click', iniciarJuego);
btnReiniciar.addEventListener('click', iniciarJuego);
btnPausar.addEventListener('click', manejarPausa);
btnSalir.addEventListener('click', () => {
    window.location.href = 'index.jsp';
});

iniciarJuego();