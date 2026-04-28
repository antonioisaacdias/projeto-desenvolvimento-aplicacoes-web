package com.eventoapp.appeventotech.controller;

import com.eventoapp.appeventotech.model.Inscricao;
import com.eventoapp.appeventotech.service.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 🔥 IMPORTANTE: importar exceção do banco
import org.springframework.dao.DataIntegrityViolationException;

@RestController
@RequestMapping("/api/inscricoes")
@CrossOrigin("*")
public class InscricaoController {

    @Autowired
    private InscricaoService service;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Inscricao inscricao) {
        try {
            // ✅ fluxo normal
            return ResponseEntity.ok(service.realizarInscricao(inscricao));

        } catch (IllegalArgumentException e) {
            // 🔥 erro vindo do SERVICE (validação manual)
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (DataIntegrityViolationException e) {
            // 🔥 NOVO: trata erro do banco (unique constraint)

            String mensagem = "Dado já cadastrado!";

            // tenta identificar qual campo deu erro
            if (e.getMessage().toLowerCase().contains("email")) {
                mensagem = "Email já cadastrado!";
            } else if (e.getMessage().toLowerCase().contains("telefone")) {
                mensagem = "Telefone já cadastrado!";
            }

            return ResponseEntity.badRequest().body(mensagem);

        } catch (Exception e) {
            // 🔥 fallback (evita erro 500 sem mensagem)
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro interno no servidor");
        }
    }
}