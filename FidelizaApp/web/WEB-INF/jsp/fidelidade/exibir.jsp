<head>
    <title><fmt:message key="app.title"/> | <fmt:message key="app.exibir" /> </title>
</head>
<body>
    <fieldset>
        <div id="legend">
            <legend class=""> 
                <fmt:message key="fidelidade.fidelidade"/> 
                <small> <fmt:message key="app.exibir"/> </small>
            </legend>  
        </div>  

        <div id="imprimir">

            <div class="control-group">
                <div class="controls">
                    <div style="border-bottom: 2px solid #E5E5E5; padding-bottom: 0px">
                        <h3>${entity.recompensa}</h3>
                    </div>
                </div>
            </div>

            <div class="controls">
                <div class="input-prepend">
                    <span class="add-on"><i class="icon-chevron-up"></i></span>                   
                </div>
                <fmt:message key="fidelidade.maximo.pontos" /> <fmt:formatNumber value="${entity.maximoPontos}" minFractionDigits="0"/>
            </div>
            <div class="controls">
                <div class="input-prepend">
                    <span class="add-on"><i class="icon-calendar"></i></span>                   
                </div>
                <fmt:message key="app.valido.por" /> <fmt:formatNumber value="${entity.validadeDias}" minFractionDigits="0"/> Dias.
            </div>       

            <div class="control-group">
                <div class="controls">
                    <div class="inline">
                        <button onClick="DoPrinting();" class="btn btn-primary btn-small" style="margin-right: 5px;" type="submit">
                            <i class="icon-white icon-qrcode"></i>
                            <fmt:message key="app.qrcode" />
                        </button>
                    </div>

                    <div class="inline">
                        <form action="<c:url value="/fidelidade" />" method="GET">
                            <button class="btn btn-success btn-small" style="margin-right: 5px;" type="submit">
                                <i class="icon-white icon-list"></i>
                                <fmt:message key="app.listar" />
                            </button>
                        </form>
                    </div>
                    <div class="inline">
                        <form action="<c:url value="/fidelidade/${entity.id}/editar"/>" method="GET">
                            <button class="btn btn-success btn-small" style="margin-right: 5px;" type="submit">
                                <i class="icon-white icon-edit"></i>
                                <fmt:message key="app.editar" />
                            </button>
                        </form>
                    </div>

                    <div class="inline">
                        <form action="<c:url value="/fidelidade/${entity.id}" />" method="POST">
                            <input type="hidden" name="_method" value="delete"/>
                            <button class="btn btn-danger btn-small" type="submit">
                                <i class="icon-white icon-remove"></i>
                                <fmt:message key="app.excluir" />
                            </button>
                        </form>
                    </div>
                    <br class="clearBoth" />
                    <div id="qrcode">
                        <img class="img-polaroid" id="qrcode" src="${linkTo[FidelidadeController].qrcode}?qrText=${entity.id}"/>
                    </div>
                </div>
            </div>
        </div>
    </fieldset>
</body>