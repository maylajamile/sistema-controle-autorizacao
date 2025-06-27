package br.com.nexdom.procedimentos.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cadastrarProcedimento")
public class CadastrarProcedimentoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String numero = request.getParameter("numero");
        String dataNascimento = request.getParameter("dataNascimento");
        String sexo = request.getParameter("sexo");

        System.out.println(numero + dataNascimento + sexo);
    }
}
