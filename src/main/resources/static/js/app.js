// ================================
// TEMA CLARO / ESCURO
// ================================
const body = document.getElementById('body');
const themeBtn = document.getElementById('theme-btn');
let dark = true;

function toggleTheme() {
    dark = !dark;
    body.className = dark ? 'dark' : 'light';
    themeBtn.textContent = dark ? '☀ claro' : '☾ escuro';
    localStorage.setItem('theme', dark ? 'dark' : 'light');
    updateLampColor(lastState?.ledOn ?? false);
}

// Recupera tema salvo
const savedTheme = localStorage.getItem('theme');
if (savedTheme) {
    dark = savedTheme === 'dark';
    body.className = savedTheme;
    themeBtn.textContent = dark ? '☀ claro' : '☾ escuro';
}

// ================================
// POLLING — atualiza a cada 3s
// ================================
let lastState = null;

async function fetchState() {
    try {
        const res = await fetch('/api/estado');
        if (!res.ok) return;
        const state = await res.json();
        lastState = state;
        updateUI(state);
    } catch (e) {
        console.error('Erro ao buscar estado:', e);
    }
}

function updateUI(state) {
    // Lâmpada
    updateLampColor(state.ledOn);

    // Métricas
    const lumVal = document.getElementById('lum-val');
    const ledVal = document.getElementById('led-val');
    if (lumVal) lumVal.textContent = state.luminosity ?? 0;
    if (ledVal) {
        ledVal.textContent = state.ledOn ? 'Ligado' : 'Desligado';
        ledVal.className = 'metric-value ' + (state.ledOn ? 'on' : 'off');
    }
}

function updateLampColor(on) {
    const lampBody = document.getElementById('lamp-body');
    const lampGlow = document.getElementById('lamp-glow');
    const lampFil  = document.getElementById('lamp-fil');
    const lampLbl  = document.getElementById('lamp-label');

    if (!lampBody) return;

    if (on) {
        lampBody.setAttribute('fill', '#fffde0');
        lampGlow.setAttribute('opacity', '1');
        lampFil.setAttribute('stroke', '#ffeb3b');
        if (lampLbl) lampLbl.textContent = 'Ligado';
    } else {
        lampBody.setAttribute('fill', dark ? '#555' : '#ccc');
        lampGlow.setAttribute('opacity', '0');
        lampFil.setAttribute('stroke', '#aaa');
        if (lampLbl) lampLbl.textContent = 'Desligado';
    }
}

// Inicia polling
fetchState();
setInterval(fetchState, 3000);
