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

        // salva no banco
        Inscricao salva = repository.save(inscricao);

        // 🔥 SEM EMAIL (removido totalmente)

        return salva;
    }
}