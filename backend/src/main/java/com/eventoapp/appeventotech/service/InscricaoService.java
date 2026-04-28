package com.eventoapp.appeventotech.service;

import com.eventoapp.appeventotech.model.Inscricao;
import com.eventoapp.appeventotech.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository repository;

    public Inscricao realizarInscricao(Inscricao inscricao) {

        // 🔥 valida email duplicado
        if (repository.existsByEmail(inscricao.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado!");

        }

        // 🔥 valida telefone duplicado
        if (repository.existsByTelefone(inscricao.getTelefone())) {
            throw new IllegalArgumentException("Telefone já cadastrado!");
        }

        return repository.save(inscricao);

    }



}