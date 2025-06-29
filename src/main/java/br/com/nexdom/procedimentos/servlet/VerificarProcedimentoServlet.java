package br.com.nexdom.procedimentos.servlet;

import br.com.nexdom.procedimentos.service.AutorizacaoService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet("/verificarProcedimento")
public class VerificarProcedimentoServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(VerificarProcedimentoServlet.class);
    private final AutorizacaoService service = new AutorizacaoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String numeroStr = request.getParameter("numero");
        boolean existe = false;

        if (Objects.nonNull(numeroStr) && !numeroStr.isEmpty()) {
            try {
                int numero = Integer.parseInt(numeroStr);
                existe = service.existeProcedimento(numero);
            } catch (NumberFormatException e) {
                log.error("[ERRO] - O VALOR numero DEVER SER UM NÚMERO INTEIRO VÁLIDO. " + e.getMessage());
            }
        }

        try {
            Map<String, Boolean> resultado = new HashMap<>();
            resultado.put("existe", existe);

            String json = new Gson().toJson(resultado);
            response.setContentType("application/json");
            response.getWriter().write(json);

        } catch (IOException e) {
            log.error("[ERRO] - NÃO FOI POSSIVEL REALIZAR A REQUISIÇÃO. " + e.getMessage());
        }
    }
}
