<head>
    <title><fmt:message key="app.title"/> | <fmt:message key="app.novo" /> </title>
</head>
<body>
    <fieldset>
        <div id="legend">
            <legend class=""> 
                <fmt:message key="fidelidade.fidelidade"/> 
                <small> <fmt:message key="app.formulario"/> </small>
            </legend>  
        </div>  

        <c:choose>
            <c:when test="${entity.id == null}">
                <c:set var="uri" value="/fidelidade" />
            </c:when>
            <c:otherwise>
                <c:set var="uri" value="/fidelidade/${entity.id}/atualizar" />
            </c:otherwise>
        </c:choose>

        <form id="FormFidelidade" action="<c:url value="${uri}" />" method="POST">
            <c:if test="${entity.id != null}">
                <input type="hidden" name="_method" value="PUT"/>
            </c:if>

            <div class="control-group">
                <label class="control-label" for="max_ponto"> <fmt:message key="fidelidade.maximo.pontos"/> </label>
                <div class="controls">
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-chevron-up"></i></span>
                        <input type="number" min="1" max="999" class="span1" required="true" id="max_ponto" name="entity.maximoPontos" value="${entity.maximoPontos}"/>
                    </div>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="recompensa"> <fmt:message key="fidelidade.recompensa"/> </label>
                <div class="controls">
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-certificate"></i></span>
                        <input type="text" class="input-xxlarge" required="true" id="recompensa" name="entity.recompensa" maxlength="100" value="${entity.recompensa}"/>
                    </div>
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <div class="input-prepend">
                        <label><fmt:message key="fidelidade.validade.dia"/> </label>
                        <span class="add-on"><i class="icon-chevron-up"></i></span>
                        <input type="number" min="1" max="999" class="span1" required="true" id="validade_dias" name="entity.validadeDias" value="${entity.validadeDias}"/>
                        <label class="alert alert-success"><fmt:message key="fidelidade.validade.dias"/> </label>
                    </div>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="termos"> <fmt:message key="fidelidade.termos"/> </label>
                <div class="controls">
                    <textarea id="termos" name="entity.termosRecompensa" class="span7" rows="3" maxlength="400">${entity.termosRecompensa}</textarea>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="quantidade_check"> <fmt:message key="fidelidade.quantidade.permitida"/> </label>
                <div class="controls">
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-chevron-up"></i></span>
                        <input type="number" min="0" max="999" class="span1" required="true" id="quantidade_check" name="entity.quantidadePermitida" value="${entity.quantidadePermitida}"/>
                    </div>
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <label class="checkbox inline"> 
                        <input type="checkbox" name="entity.ativo" <c:if test="${entity.ativo}">checked</c:if>/>
                        <fmt:message key="fidelidade.ativo"/> 
                    </label>
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <button type="submit" class="btn btn-success btn-small">
                        <i class="icon-white icon-ok"></i>
                        <fmt:message key="app.gravar" />
                    </button>
                    <a class="btn btn-danger btn-small" href="<c:url value="/fidelidade"/>">
                        <fmt:message key="app.cancelar" /> 
                    </a>
                </div>
            </div>   

        </form>


    </fieldset>
</body>