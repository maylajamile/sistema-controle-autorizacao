$(document).ready(function () {
    $('#numero').on('blur', function () {
        const numero = $(this).val();
        const inputField = $(this).closest('.input-field');

        if (numero.length === 0) {
            inputField.find('#mensagemErro').remove();
            return;
        }

        $.get('verificarProcedimento', { numero: numero })
            .done(function (data) {
                if (!data.existe) {
                    if (inputField.find('#mensagemErro').length === 0) {
                        const mensagemErro = $('<div id="mensagemErro" style="color: red; font-size: 0.9em; margin-top: 5px;">Número de procedimento não encontrado</div>');
                        inputField.append(mensagemErro);
                    }
                } else {
                    inputField.find('#mensagemErro').remove();
                }
            })
            .fail(function () {
                inputField.find('#mensagemErro').remove();
            });
    });
});

$(document).ready(function() {
    setTimeout(function() {
        $('.mensagem').fadeOut('slow');
    }, 5000);
});