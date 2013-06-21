<head>
    <title><fmt:message key="app.title"/> | <fmt:message key="app.novo" /> </title>
</head>
<body>
    <fieldset>
        <div id="legend">
            <legend class=""> 
                <fmt:message key="oferta.oferta"/> 
                <small> <fmt:message key="app.formulario"/> </small>
            </legend>  
        </div>  

        <c:choose>
            <c:when test="${entity.id == null}">
                <c:set var="uri" value="/oferta" />
            </c:when>
            <c:otherwise>
                <c:set var="uri" value="/oferta/${entity.id}/atualizar" />
            </c:otherwise>
        </c:choose>

        <form id="cadastroOferta" action="<c:url value="${uri}" />" method="POST">
            <c:if test="${entity.id != null}">
                <input type="hidden" name="_method" value="PUT"/>
            </c:if>

            <div class="control-group">
                <label for="categoria" class="control-label"> <fmt:message key="oferta.categoria"/> </label>
                <div class="controls">
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-th-list"></i></span>
                        <select name="entity.categoria" id="tipo" style="margin-right: 10px;">
                            <c:forEach items="${categoriaTypes}" var="tipo">
                                <c:set var="sel" value="${entity.categoria eq tipo ? 'selected':''}"></c:set>
                                <option value="${tipo}"${sel}>${tipo.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="descricao"> <fmt:message key="oferta.descricao.item"/> </label>
                <div class="controls">
                    <input type="text" required="true" id="descricao" name="entity.descricaoItem" value="${entity.descricaoItem}" maxlength="50" class="input-xxlarge">
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="detalhe"> <fmt:message key="oferta.descricao.detalhe"/> </label>
                <div class="controls">
                    <textarea id="detalhe" name="entity.descricaoDetalhe" class="span7" rows="3" maxlength="400">${entity.descricaoDetalhe}</textarea>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="preco"> <fmt:message key="oferta.preco"/> </label>
                <div class="controls">
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-tags"></i></span>
                        <input type="text" required="true" placeholder="Ex: 100,00" id="preco" name="entity.preco" value="<fmt:formatNumber value="${entity.preco}" minFractionDigits="2"/>" maxlength="8" class="input-small">
                    </div>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="inicio"> <fmt:message key="oferta.data.inicio"/> </label>
                <div class="controls">
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-calendar"></i></span>
                            <caelum:campoData id="inicio" name="entity.dataInicioOferta" value="${entity.dataInicioOferta}" required="true"/>  
                    </div>
                </div>
                <div class="controls">
                    <label class="control-label" for="final"><fmt:message key="oferta.data.final"/> </label>
                    <div class="input-prepend">
                        <span class="add-on"><i class="icon-calendar"></i></span>
                            <caelum:campoData id="final" name="entity.dataFinalOferta" value="${entity.dataFinalOferta}" required="true"/>
                    </div>
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <button type="submit" class="btn btn-success btn-small">
                        <i class="icon-white icon-ok"></i>
                        <fmt:message key="app.gravar" />
                    </button>
                    <a class="btn btn-danger btn-small" href="<c:url value="/oferta"/>">
                        <fmt:message key="app.cancelar" /> 
                    </a>
                </div>
            </div>

        </form>
    </fieldset>
</body>