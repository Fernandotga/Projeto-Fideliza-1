<head>
    <title><fmt:message key="app.title"/> | <fmt:message key="app.exibir" /> </title>
</head>
<body>
    <c:set var="uri" value="/oferta/${entity.id}" />
    <fieldset>
        <div id="legend">
            <legend class=""> 
                <fmt:message key="oferta.oferta"/> 
                <small> <fmt:message key="app.exibir"/> </small>
            </legend>  
        </div>  

        <div class="control-group">
            <div class="controls">
                <form action="<c:url value="${uri}" />imagem" enctype="multipart/form-data" method="POST">
                    <input type="file" name="file"/>
                    <button class="btn btn-primary" type="submit">
                        <i class="icon-white icon-upload"></i>
                        <fmt:message key="oferta.upload"/>
                    </button>
                </form>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <form action="<c:url value="${uri}" />imagem" method="post">
                    <input type="hidden" name="_method" value="delete"/>
                    <button class="btn btn-danger" type="submit">
                        <i class="icon-white icon-remove-circle"></i>
                        <fmt:message key="oferta.remove.imagem" />
                    </button>
                </form>
                <img class="img-polaroid" src="<c:url value="${uri}" />imagem" width="120" height="130"/>
            </div>
        </div>
        <div style="border-bottom: 1px solid #E5E5E5; padding-bottom: 0px">
            <h2>${entity.descricaoItem}</h2>
        </div>
        <fmt:formatNumber value="${entity.preco}" minFractionDigits="2"/> <br />
        <fmt:message key="app.expira.em" /> <fmt:formatDate value="${entity.dataFinalOferta}" pattern="dd/MM/yyyy" /> <br />

        <div class="control-group">
            <div class="controls">
                <div class="inline">
                    <form action="<c:url value="/oferta" />" method="GET">
                        <button class="btn btn-success" style="margin-right: 5px;" type="submit">
                            <i class="icon-white icon-list"></i>
                            <fmt:message key="app.listar" />
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

                <div class="inline">
                    <form action="<c:url value="/oferta/${oferta.id}" />" method="POST">
                        <input type="hidden" name="_method" value="delete"/>
                        <button class="btn btn-danger" type="submit">
                            <i class="icon-white icon-remove"></i>
                            <fmt:message key="app.excluir" />
                        </button>
                    </form>
                </div>
                <br class="clearBoth" />
            </div>
        </div>
    </fieldset>
</body>