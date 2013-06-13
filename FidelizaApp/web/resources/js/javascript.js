$(document).ready(function() {
    $("#cadastroEmpresa").submit(function() {
        //
        if ($("#senha").val() !== $("#senhaRepetir").val()) {
            $("#error").text("Senhas diferentes. Favor conferir!");
            return false;
        } else {
            return true;
        }
    });

    //Foco no primeiro campo visivel
    $("input:text:visible:first").focus();

    $("#cnpj").blur(function() {
        //valida o CNPJ digitado
        if (!ValidarCNPJ($("#cnpj").val())) {
            $("#error").removeClass("hidden");
            $("#cnpj").focus();
        }
    });

//    Executar metodo no V|Raptor
//    data-action="${linkTo[EmpresaController].addTelefone}"
//    $('#addTelefone').click(function() {
//        $('#tab_telefone').load($(this).attr('data-action'));
//    });
});


function ValidarCNPJ(cnpj) {

    cnpj = cnpj.replace(/[^\d]+/g, '');

//    if (cnpj === '') {
//        $("#error").text("CNPJ Rejeitado! Campo vazio!");
//        return false;
//    }

    if ((cnpj.length !== 14) && (cnpj !== '')) {
        $("#error").text("CNPJ Rejeitado! Campo nao tem os 14 digitos necessarios!");
        return false;
    }


    // Elimina CNPJs invalidos conhecidos
    if (cnpj === "00000000000000" ||
            cnpj === "11111111111111" ||
            cnpj === "22222222222222" ||
            cnpj === "33333333333333" ||
            cnpj === "44444444444444" ||
            cnpj === "55555555555555" ||
            cnpj === "66666666666666" ||
            cnpj === "77777777777777" ||
            cnpj === "88888888888888" ||
            cnpj === "99999999999999") {
        $("#error").text("CNPJ Rejeitado! Numeros repetidos e invalidos!");
        return false;
    }

    // Valida DVs
    tamanho = cnpj.length - 2;
    numeros = cnpj.substring(0, tamanho);
    digitos = cnpj.substring(tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if ((resultado !== digitos.charAt(0)) && (cnpj !== '')) {
        $("#error").text("CNPJ Rejeitado! Favor confira os numeros digitados");
        return false;
    }

    tamanho = tamanho + 1;
    numeros = cnpj.substring(0, tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if ((resultado !== digitos.charAt(1)) && (cnpj !== '')) {
        $("#error").text("CNPJ Rejeitado! Favor confira os numeros digitados");
        return false;
    }
    return true;
}

function confirmar(title, sim, nao, msg, funcao, largura, altura) {
    $('div#mensagem').dialog({
        title: title,
        buttons: [
            {
                text: sim,
                click: function() {
                    $(this).dialog('close');
                    funcao();
                }
            },
            {
                text: nao,
                click: function() {
                    $(this).dialog('close');
                }
            }
        ],
        width: (largura !== undefined) ? largura : 320,
        height: (altura !== undefined) ? altura : 150
    })
            .html(msg)
            .dialog('open');
}
;

function getError(xhr) {
    var start = xhr.responseText.indexOf('(') + 1,
            end = xhr.responseText.indexOf(')'),
            error = xhr.responseText.substring(start, end);

    return error;
}
;

function mensagem(title, msg, largura, altura) {
    $('div#mensagem').dialog({
        'title': title,
        'buttons': {
            'ok': function() {
                $(this).dialog('close');
            }
        },
        width: (largura !== undefined) ? largura : 320,
        height: (altura !== undefined) ? altura : 150
    })
            .html(msg)
            .dialog('open');
}
;

function DoPrinting() {
    if (!window.print) {
        alert("Use um navegador mais moderno!");
        return;
    }
    window.print();
}

