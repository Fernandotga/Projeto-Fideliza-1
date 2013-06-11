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

        <a class="btn btn-primary" href="<c:url value="/oferta/criar"/>"><fmt:message key="app.novo" /></a>

        <c:forEach items="${ofertaList}" var="oferta">
            <div style="border-bottom: 1px solid #E5E5E5; padding-bottom: 0px">
                <h4>${oferta.descricaoItem}</h4>
            </div>
            <fmt:formatNumber value="${oferta.preco}" minFractionDigits="2"/> <br />
            <fmt:message key="app.expira.em" /> <fmt:formatDate value="${oferta.dataFinalOferta}" pattern="dd/MM/yyyy" /> <br />

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
    </fieldset>
</body>