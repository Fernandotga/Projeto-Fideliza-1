<head>
    <title><fmt:message key="app.title"/> | <fmt:message key="app.listar" /> </title>
</head>
<body>
    <fieldset>
        <div id="legend">
            <legend class=""> 
                <fmt:message key="fidelidade.fidelidade"/> 
                <small> <fmt:message key="app.listar"/> </small>
            </legend>  
        </div>  

        <div style="border-bottom: 1px solid gray; padding-bottom: 5px;">
             <a class="btn btn-success btn-small" href="<c:url value="/fidelidade/criar"/>"><fmt:message key="app.novo" /></a>
        </div>

        <table cellpadding="1" cellspacing="1" class="footable" data-page-navigation="#pagination" data-page-size="1">
            <c:forEach items="${fidelidadeList}" var="fidelidade">
                <tr>
                    <td>
                        <div class="control-group">
                            <div class="controls">
                                <h4>${fidelidade.recompensa}</h4>
                            </div>

                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-chevron-up"></i></span>                   
                                </div>
                                <fmt:message key="fidelidade.maximo.pontos" /> <fmt:formatNumber value="${fidelidade.maximoPontos}" minFractionDigits="0"/>
                            </div>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-calendar"></i></span>                   
                                </div>
                                <fmt:message key="app.valido.por" /> <fmt:formatNumber value="${fidelidade.validadeDias}" minFractionDigits="0"/> Dias.
                            </div>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-off"></i></span>                   
                                </div>
                                <c:if test="${fidelidade.ativo}">Fidelidade Ativa</c:if>
                                <c:if test="${!fidelidade.ativo}">Fidelidade Inativa</c:if>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="controls">
                                    <div class="inline">
                                        <form action="<c:url value="/fidelidade/${fidelidade.id}" />" method="GET">
                                        <button class="btn btn-success btn-small" style="margin-right: 5px;" type="submit">
                                            <i class="icon-white icon-check"></i>
                                            <fmt:message key="app.exibir" />
                                        </button>
                                    </form>
                                </div>
                                <div class="inline">
                                    <form action="<c:url value="/fidelidade/${fidelidade.id}/editar"/>" method="GET">
                                        <button class="btn btn-success btn-small" style="margin-right: 5px;" type="submit">
                                            <i class="icon-white icon-edit"></i>
                                            <fmt:message key="app.editar" />
                                        </button>
                                    </form>
                                </div>
                                <br class="clearBoth" />
                            </div>
                        </div> 
                    </c:forEach>
                </td>
            </tr>
        </table>
    </fieldset>
    <div style="border-top: 1px solid gray; padding-bottom: 0px">
        <ul id="pagination" class="footable-nav"><span>Paginas:</span></ul>
    </div>
</body>