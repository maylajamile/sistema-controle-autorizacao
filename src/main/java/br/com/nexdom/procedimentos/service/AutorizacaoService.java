package br.com.nexdom.procedimentos.service;

import br.com.nexdom.procedimentos.dao.AutorizacaoDAO;
import java.time.LocalDate;
import java.time.Period;
import br.com.nexdom.procedimentos.util.JPAUtil;
import javax.persistence.EntityManager;

public class AutorizacaoService {

    public boolean verificarAutorizacao(int numero, LocalDate dataNascimento, String sexo) {
        // Para calcular a idade usando o mês e ano
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();

        EntityManager em = JPAUtil.getEntityManager();
        try {
            AutorizacaoDAO dao = new AutorizacaoDAO(em);
            return dao.isProcedimentoPermitido(numero, idade, sexo);
        } finally {
            em.close();
        }
    }

    public boolean existeProcedimento(int numero) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            AutorizacaoDAO dao = new AutorizacaoDAO(em);
            return dao.existeProcedimento(numero);
        } finally {
            em.close();
        }
    }
}

