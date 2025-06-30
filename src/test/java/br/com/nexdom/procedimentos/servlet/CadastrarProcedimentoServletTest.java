package br.com.nexdom.procedimentos.servlet;

import br.com.nexdom.procedimentos.BaseTest;
import br.com.nexdom.procedimentos.model.Autorizacao;
import br.com.nexdom.procedimentos.util.JPAUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CadastrarProcedimentoServletTest extends BaseTest {

    @Override
    protected void popularBanco() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(new Autorizacao(null, 20, true, 4567, "M"));
        em.persist(new Autorizacao(null, 10, false, 1234, "M"));
        em.getTransaction().commit();
        em.close();
    }

    @Test
    void deveCadastrarProcedimentoAutorizado() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("numero")).thenReturn("4567");
        when(request.getParameter("dataNascimento")).thenReturn(LocalDate.now().minusYears(20).toString());
        when(request.getParameter("sexo")).thenReturn("M");
        when(request.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);

        CadastrarProcedimentoServlet servlet = new CadastrarProcedimentoServlet();
        servlet.doPost(request, response);

        verify(request).setAttribute("mensagem", "Cadastro realizado com sucesso. Procedimento autorizado!");
        verify(request).setAttribute("tipoMensagem", "sucesso");
        verify(dispatcher).forward(request, response);
    }

    @Test
    void naoDeveCadastrarProcedimentoNaoAutorizado() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("numero")).thenReturn("1234");
        when(request.getParameter("dataNascimento")).thenReturn(LocalDate.now().minusYears(10).toString());
        when(request.getParameter("sexo")).thenReturn("M");
        when(request.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);

        CadastrarProcedimentoServlet servlet = new CadastrarProcedimentoServlet();
        servlet.doPost(request, response);

        verify(request).setAttribute("mensagem", "Não foi possível realizar o cadastro. Procedimento não autorizado!");
        verify(request).setAttribute("tipoMensagem", "erro");
        verify(dispatcher).forward(request, response);
    }

}
