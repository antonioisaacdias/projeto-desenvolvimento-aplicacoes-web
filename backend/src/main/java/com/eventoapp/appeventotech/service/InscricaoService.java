package com.eventoapp.appeventotech.service;

import com.eventoapp.appeventotech.model.Inscricao;
import com.eventoapp.appeventotech.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importante para salvar dados

import java.util.List;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository repository;

    /**
     * Realiza a inscrição com validações prévias.
     * @Transactional garante a integridade da operação no banco.
     */
    @Transactional
    public Inscricao realizarInscricao(Inscricao inscricao) {

        // 🔥 Validação manual de email duplicado
        if (repository.existsByEmail(inscricao.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado!");
        }

        // 🔥 Validação manual de telefone duplicado
        if (repository.existsByTelefone(inscricao.getTelefone())) {
            throw new IllegalArgumentException("Telefone já cadastrado!");
        }

        return repository.save(inscricao);
    }

    /**
     * Retorna a lista de todos os inscritos.
     * Utilizado pelo método GET do seu Controller.
     */
    public List<Inscricao> listarTodas() {
        return repository.findAll();
    }
}