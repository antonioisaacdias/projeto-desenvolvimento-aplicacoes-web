package com.eventoapp.appeventotech.controller;

import com.eventoapp.appeventotech.model.Inscricao;
import com.eventoapp.appeventotech.service.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// IMPORTANTE: tratar erros de integridade (unique constraints)

@RestController
@RequestMapping("/api/inscricoes")
@CrossOrigin("*") // Permite acesso de diferentes origens (Frontend)
public class InscricaoController {

    @Autowired
    private InscricaoService service;

    /**
     * Lista todas as inscrições.
     * Resolve o erro 405 ao acessar a URL diretamente pelo navegador.
     */
    @GetMapping
    public ResponseEntity<List<Inscricao>> listar() {
        List<Inscricao> lista = service.listarTodas(); // Certifique-se de que este método existe no seu Service
        return ResponseEntity.ok(lista);
    }

    /**
     * Realiza uma nova inscrição.
     * Requer um corpo JSON via método POST.
     */
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Inscricao inscricao) {
        try {
            // ✅ Fluxo de sucesso: retorna Status 201 (Created)
            Inscricao novaInscricao = service.realizarInscricao(inscricao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaInscricao);

        } catch (IllegalArgumentException e) {
            // 🔥 Erro de validação manual vindo do Service
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (DataIntegrityViolationException e) {
            // 🔥 Tratamento de erro de banco (campos únicos como Email ou Telefone)
            String mensagem = "Dado já cadastrado no sistema!";

            // Pega a causa real do erro para identificar o campo duplicado
            String causaReal = e.getMostSpecificCause().getMessage().toLowerCase();

            if (causaReal.contains("email")) {
                mensagem = "Este e-mail já está em uso!";
            } else if (causaReal.contains("telefone")) {
                mensagem = "Este telefone já está em uso!";
            }

            return ResponseEntity.badRequest().body(mensagem);

        } catch (Exception e) {
            // 🔥 Fallback para erros inesperados (Evita o erro 500 vazio)
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno no servidor: " + e.getMessage());
        }
    }
}