<head>
    <title><fmt:message key="app.title"/> | <fmt:message key="app.listar" /> </title>
</head>
<body>
    <fieldset>
        <div id="legend">
            <legend class=""> 
                <fmt:message key="oferta.oferta"/> 
                <small> <fmt:message key="app.listar"/> </small>
            </legend>  
        </div>  

        <div style="border-bottom: 1px solid gray; padding-bottom: 5px">
            <a class="btn btn-primary" href="<c:url value="/oferta/criar"/>"><fmt:message key="app.novo" /></a>
        </div>

        <table cellpadding="1" cellspacing="1" class="footable" data-page-navigation="#pagination" data-page-size="2">
            <c:forEach items="${ofertaList}" var="oferta">
                <tr>
                    <td>
                        <div class="control-group">
                            <div class="controls">
                                <h4>${oferta.descricaoItem}</h4>
                            </div>

                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-tags"></i></span>                   
                                </div>
                                <fmt:formatNumber value="${oferta.preco}" minFractionDigits="2"/>
                            </div>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-calendar"></i></span>                   
                                </div>
                                <fmt:message key="app.expira.em" /> <fmt:formatDate value="${oferta.dataFinalOferta}" pattern="dd/MM/yyyy" /> 
                            </div>
                        </div>

                        <div class="control-group">
                            <div class="controls">
                                <div class="inline">
                                    <form action="<c:url value="/oferta/${oferta.id}" />" method="GET">
                                        <button class="btn btn-success" style="margin-right: 5px;" type="submit">
                                            <i class="icon-white icon-check"></i>
                                            <fmt:message key="app.exibir" />
                                        </button>
                                    </form>
                                </div>
                                <div class="inline">
                                    <form action="<c:url value="/oferta/${oferta.id}/editar"/>" method="GET">
                                        <button class="btn btn-success" style="margin-right: 5px;" type="submit">
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