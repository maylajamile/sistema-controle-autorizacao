package br.com.nexdom.procedimentos.service;

import br.com.nexdom.procedimentos.BaseTest;
import br.com.nexdom.procedimentos.model.Autorizacao;
import br.com.nexdom.procedimentos.util.JPAUtil;
import org.junit.jupiter.api.*;
import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutorizacaoServiceTest extends BaseTest {

    @Override
    protected void popularBanco() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(new Autorizacao(null, 20, true, 4567, "M"));
        em.persist(new Autorizacao(null, 10, false, 1234, "M"));
        em.persist(new Autorizacao(null, 10, true, 6789, "M"));
        em.getTransaction().commit();
        em.close();
    }

    @Test
    void deveRetornarTrueQuandoProcedimentoExiste() {
        AutorizacaoService service = new AutorizacaoService();
        assertTrue(service.existeProcedimento(1234));
    }

    @Test
    void deveRetornarFalseQuandoProcedimentoNaoExiste() {
        AutorizacaoService service = new AutorizacaoService();
        assertFalse(service.existeProcedimento(9999));
    }

    @Test
    void deveRetornarTrueQuandoAutorizacaoPermitida() {
        AutorizacaoService service = new AutorizacaoService();
        LocalDate nascimento = LocalDate.now().minusYears(20);
        assertTrue(service.verificarAutorizacao(4567, nascimento, "M"));
    }

    @Test
    void deveRetornarFalseQuandoAutorizacaoNegada() {
        AutorizacaoService service = new AutorizacaoService();
        LocalDate nascimento = LocalDate.now().minusYears(10);
        assertFalse(service.verificarAutorizacao(1234, nascimento, "M"));
    }

    @Test
    void deveRetornarFalseQuandoSexoNaoConfere() {
        AutorizacaoService service = new AutorizacaoService();
        LocalDate nascimento = LocalDate.now().minusYears(10);
        assertFalse(service.verificarAutorizacao(6789, nascimento, "F"));
    }
}
