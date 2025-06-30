package br.com.nexdom.procedimentos.servlet;

import br.com.nexdom.procedimentos.BaseTest;
import br.com.nexdom.procedimentos.model.Autorizacao;
import br.com.nexdom.procedimentos.util.JPAUtil;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VerificarProcedimentoServletTest extends BaseTest {

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
    void deveRetornarJsonComExisteTrueQuandoProcedimentoExiste() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Mockito.when(request.getParameter("numero")).thenReturn("1234");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Mockito.when(response.getWriter()).thenReturn(writer);

        VerificarProcedimentoServlet servlet = new VerificarProcedimentoServlet();
        servlet.doGet(request, response);

        writer.flush();

        String jsonResposta = stringWriter.toString();
        Map<?, ?> mapResposta = new Gson().fromJson(jsonResposta, Map.class);

        assertTrue((Boolean) mapResposta.get("existe"));
    }

    @Test
    void deveRetornarJsonComExisteFalseQuandoProcedimentoNaoExiste() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Mockito.when(request.getParameter("numero")).thenReturn("9999");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Mockito.when(response.getWriter()).thenReturn(writer);

        VerificarProcedimentoServlet servlet = new VerificarProcedimentoServlet();
        servlet.doGet(request, response);

        writer.flush();

        String jsonResposta = stringWriter.toString();
        Map<?, ?> mapResposta = new Gson().fromJson(jsonResposta, Map.class);

        assertFalse((Boolean) mapResposta.get("existe"));
    }

    @Test
    void deveRetornarJsonComExisteFalseQuandoParametroNumeroInvalido() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Mockito.when(request.getParameter("numero")).thenReturn("abc");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Mockito.when(response.getWriter()).thenReturn(writer);

        VerificarProcedimentoServlet servlet = new VerificarProcedimentoServlet();
        servlet.doGet(request, response);

        writer.flush();

        String jsonResposta = stringWriter.toString();
        Map<?, ?> mapResposta = new Gson().fromJson(jsonResposta, Map.class);

        assertFalse((Boolean) mapResposta.get("existe"));
    }

    @Test
    void deveRetornarJsonComExisteFalseQuandoParametroNumeroNaoForInformado() throws Exception {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        Mockito.when(request.getParameter("numero")).thenReturn(null);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        Mockito.when(response.getWriter()).thenReturn(writer);

        VerificarProcedimentoServlet servlet = new VerificarProcedimentoServlet();
        servlet.doGet(request, response);

        writer.flush();

        String jsonResposta = stringWriter.toString();
        Map<?, ?> mapResposta = new Gson().fromJson(jsonResposta, Map.class);

        assertFalse((Boolean) mapResposta.get("existe"));
    }
}
