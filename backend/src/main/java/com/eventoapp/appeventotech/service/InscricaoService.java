package com.eventoapp.appeventotech.service;

import com.eventoapp.appeventotech.model.Inscricao;
import com.eventoapp.appeventotech.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository repository;

    @Autowired
    private JavaMailSender mailSender;

    public Inscricao realizarInscricao(Inscricao inscricao) {
        Inscricao salva = (Inscricao) repository.save(inscricao);
        enviarEmailConfirmacao(salva.getEmail(), salva.getNome());
        return salva;
    }

    private void enviarEmailConfirmacao(String emailDestino, String nome) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDestino);
        message.setSubject("Confirmação de Inscrição - Evento Tech");
        message.setText("Olá " + nome + ",\n\nSua inscrição para o Evento Tech foi confirmada com sucesso! \nData: 20 de Novembro\nHorário: 09:00.");
        mailSender.send(message);
    }
}