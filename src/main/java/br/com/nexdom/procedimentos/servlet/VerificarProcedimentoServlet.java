package br.com.nexdom.procedimentos.servlet;

import br.com.nexdom.procedimentos.dao.AutorizacaoDAO;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/verificarProcedimento")
public class VerificarProcedimentoServlet extends HttpServlet {

    private final AutorizacaoDAO dao = new AutorizacaoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String numeroStr = request.getParameter("numero");
        boolean existe = false;

        if (numeroStr != null && !numeroStr.isEmpty()) {
            try {
                int numero = Integer.parseInt(numeroStr);
                existe = dao.existeProcedimento(numero);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        Map<String, Boolean> resultado = new HashMap<>();
        resultado.put("existe", existe);

        String json = new Gson().toJson(resultado);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }
}
