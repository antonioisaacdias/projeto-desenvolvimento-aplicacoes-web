package com.eventoapp.appeventotech.controller;


import com.eventoapp.appeventotech.model.Inscricao;
import com.eventoapp.appeventotech.service.InscricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inscricoes")
@CrossOrigin("*")
public class InscricaoController {

    @Autowired
    private InscricaoService service;

    @PostMapping
    public ResponseEntity<Inscricao> cadastrar(@RequestBody Inscricao inscricao) {
        try {
            Inscricao novaInscricao = service.realizarInscricao(inscricao);
            return ResponseEntity.ok(novaInscricao);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}


