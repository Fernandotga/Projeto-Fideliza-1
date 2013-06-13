<head>
    <title><fmt:message key="app.title"/> | <fmt:message key="app.listar" /> </title>
</head>
<body>
    <fieldset>
        <div id="legend">
            <legend class=""> 
                <fmt:message key="fidelizados.fidelizados"/> 
                <small> <fmt:message key="app.listar"/> </small>
            </legend>  
        </div>

        <c:forEach items="${fidelizadosList}" var="fidelizados">

            <div class="control-group">
                <div class="controls">
                    <h4>#${fidelizados[0].id} - ${fidelizados[0].nome}</h4>
                    <fmt:message key="app.fidelizado.em"/> <fmt:formatDate value="${fidelizados[1].dataFidelidade}" pattern="dd/MM/yyyy" /> 
                </div>

                <div class="controls">
                    <span class="badge badge-success">
                        <b>${fidelizados[1].pontos}</b>
                    </span>
                    <fmt:message key="fidelizados.pontos" />
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <div class="inline">
                        <form action="<c:url value="/fidelizados/${fidelizados[0].id}" />" method="GET">
                            <button class="btn btn-success" style="margin-right: 5px;" type="submit">
                                <i class="icon-white icon-check"></i>
                                <fmt:message key="app.exibir" />
                            </button>
                        </form>
                    </div>
                    <div class="inline">
                        <form action="<c:url value="/fidelizados/${fidelizados[1].id}/baixa" />" method="POST">
                            <button class="btn btn-danger" type="submit">
                                <i class="icon-white icon-repeat"></i>
                                <fmt:message key="app.troca" />
                            </button>
                        </form>
                    </div>
                    <br class="clearBoth" />
                </div>
            </div>


        </c:forEach>
    </fieldset>
</body>