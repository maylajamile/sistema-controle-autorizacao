package br.com.nexdom.procedimentos.dao;

import br.com.nexdom.procedimentos.model.Autorizacao;
import javax.persistence.EntityManager;
import java.util.List;

public class AutorizacaoDAO {

    private final EntityManager em;

    public AutorizacaoDAO(EntityManager em) {
        this.em = em;
    }

    public boolean existeProcedimento(int numero) {
        String jpql = "SELECT a FROM Autorizacao a WHERE a.procedimento = :numero";
        List<Autorizacao> resultados = em.createQuery(jpql, Autorizacao.class)
                .setParameter("numero", numero)
                .setMaxResults(1)
                .getResultList();

        return !resultados.isEmpty();
    }

    public boolean isProcedimentoPermitido(int numero, int idade, String sexo) {
        String jpql = "SELECT a FROM Autorizacao a WHERE a.procedimento = :numero " +
                "AND a.idade = :idade AND a.sexo = :sexo AND a.permitido = true";
        List<Autorizacao> resultados = em.createQuery(jpql, Autorizacao.class)
                .setParameter("numero", numero)
                .setParameter("idade", idade)
                .setParameter("sexo", sexo)
                .setMaxResults(1)
                .getResultList();

        return !resultados.isEmpty();
    }
}
