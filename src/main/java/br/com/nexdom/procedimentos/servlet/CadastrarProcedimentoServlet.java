package br.com.nexdom.procedimentos.servlet;

import br.com.nexdom.procedimentos.service.AutorizacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/cadastrarProcedimento")
public class CadastrarProcedimentoServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(CadastrarProcedimentoServlet.class);
    private final AutorizacaoService service = new AutorizacaoService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            int numero = Integer.parseInt(request.getParameter("numero"));
            LocalDate dataNascimento = LocalDate.parse(request.getParameter("dataNascimento"));
            String sexo = request.getParameter("sexo");

            boolean permitido = service.verificarAutorizacao(numero, dataNascimento, sexo);

            String mensagem = permitido
                    ? "Cadastro realizado com sucesso. Procedimento autorizado!"
                        : "Não foi possível realizar o cadastro. Procedimento não autorizado!";

            String tipoMensagem = permitido ? "sucesso" : "erro";

            request.setAttribute("mensagem", mensagem);
            request.setAttribute("tipoMensagem", tipoMensagem);
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (ServletException | IOException e) {
            log.error("[ERRO] - NÃO FOI POSSIVEL REALIZAR A REQUISIÇÃO. " + e.getMessage());
        }
    }
}
