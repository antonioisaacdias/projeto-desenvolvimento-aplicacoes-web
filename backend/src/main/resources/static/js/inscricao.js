const API_URL = "/api/inscricoes";

const form = document.getElementById("formInscricao");
const mensagem = document.getElementById("mensagem");

// 🔥 IMPORTANTE: As chaves (nome, email, telefone) devem ser IGUAIS ao Java
function obterDados() {
    return {
        nome: document.getElementById("name").value,
        email: document.getElementById("email").value,
        telefone: document.getElementById("phone").value
    };
}

function mostrarMensagem(texto, tipo) {
    mensagem.innerHTML = `<div class="alert alert-${tipo}">${texto}</div>`;
}

async function enviarInscricao(dados) {
    // Note que usamos o POST que você configurou no Controller
    return await fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dados)
    });
}

form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const dados = obterDados();

    try {
        const response = await enviarInscricao(dados);
        const textoResposta = await response.text();

        if (response.ok) {
            mostrarMensagem("Inscrição realizada com sucesso!", "success");
            form.reset();
        } else {
            // Exibe a mensagem exata enviada pelo Java (ex: "Email já cadastrado!")
            mostrarMensagem(textoResposta || "Erro ao enviar inscrição", "danger");
        }

    } catch (error) {
        console.error("Erro de conexão:", error);
        mostrarMensagem("Erro ao conectar com o servidor. Verifique se o Backend está ligado.", "danger");
    }
});