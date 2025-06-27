package br.com.nexdom.procedimentos.dao;

import br.com.nexdom.procedimentos.model.Autorizacao;
import br.com.nexdom.procedimentos.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class AutorizacaoDAO {

    public boolean existeProcedimento(int numero) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<Autorizacao> resultados = em.createQuery(
                            "SELECT a FROM Autorizacao a WHERE a.procedimento = :numero", Autorizacao.class)
                    .setParameter("numero", numero)
                    .setMaxResults(1)
                    .getResultList();

            return !resultados.isEmpty();
        } finally {
            em.close();
        }
    }
}

