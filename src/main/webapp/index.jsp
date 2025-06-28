<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <title>Cadastrar Procedimento</title>
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>" defer>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<c:url value='/js/script.js'/>" defer></script>
</head>
<body>
    <c:if test="${not empty mensagem}">
        <div class="mensagem ${tipoMensagem}">
            ${mensagem}
        </div>
    </c:if>
    <div class="container">
        <h2>Cadastrar Procedimento</h2>
        <div class="underline"></div>

        <form action="cadastrarProcedimento" method="post">
            <div class="input-field">
                <label for="numero">Número do Procedimento</label>
                <i class="fa fa-file-medical"></i>
                <input type="text" name="numero" id="numero" required>
            </div>

            <div class="input-field">
                <label for="dataNascimento">Data de Nascimento</label>
                <i class="fa fa-calendar"></i>
                <input type="date" name="dataNascimento" id="dataNascimento" required>
            </div>

            <div class="input-field">
                <label for="sexo">Sexo</label>
                <i class="fa fa-venus-mars"></i>
                <select name="sexo" id="sexo" required>
                    <option value="" disabled selected>Selecione</option>
                    <option value="M">Masculino</option>
                    <option value="F">Feminino</option>
                </select>
            </div>

            <button type="submit" class="submit-button">Cadastrar</button>
        </form>
    </div>
</body>
</html>
