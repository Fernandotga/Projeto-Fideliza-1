<body>
    <fieldset>
        <div id="legend">
            <legend class="">
                <fmt:message key="empresa.empresa"/>
                <small> <fmt:message key="app.novo"/> </small>
            </legend>
        </div>

        <c:choose>
            <c:when test="${entity.id == null}">
                <c:set var="uri" value="/empresa" />
            </c:when>
            <c:otherwise>
                <c:set var="uri" value="/empresa/${entity.id}" />
            </c:otherwise>
        </c:choose>

        <form id="cadastroEmpresa" action="<c:url value="${uri}" />" method="POST">
            <c:if test="${entity.id != null}">
                <input type="hidden" name="_method" value="put"/>
            </c:if>

            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#dados" data-toggle="tab">Dados Cadastrais</a>
                </li>
                <li>
                    <a href="#localizacao" data-toggle="tab">Localização</a>
                </li>
                <li>
                    <a href="#telefone" data-toggle="tab">Telefones</a>
                </li>
                <li>
                    <a href="#web" data-toggle="tab">Contato Web</a>
                </li>
                <li>
                    <a href="#logo" data-toggle="tab">Logotipo</a>
                </li>
                <li>
                    <a href="#autenticacao" data-toggle="tab">Autenticação</a>
                </li>
            </ul>

            <div id="myTabContent" class="tab-content">

                <div class="tab-pane active in" id="dados">

                    <div class="control-group">
                        <div class="controls">
                            <input type="text" required="true" placeholder="<fmt:message key="empresa.nome.fantasia"/>" id="fantasia" name="entity.nomeFantasia" value="${entity.nomeFantasia}" class="input-xxlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <input type="text" required="true" placeholder="<fmt:message key="empresa.razao.social"/>" id="razao" name="entity.razaoSocial" value="${entity.razaoSocial}" class="input-xxlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <input type="text" placeholder="<fmt:message key="empresa.cnpj"/>" id="cnpj" data-mask="99.999.999/9999-99" name="entity.cnpj" value="${entity.cnpj}" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <input type="text" required="true" placeholder="<fmt:message key="empresa.responsavel"/>" id="responsavel" name="entity.responsavel" value="${entity.responsavel}" class="input-xxlarge">
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="localizacao">
                    <div class="control-group">
                        <input type="text" id="txtEndereco" placeholder="<fmt:message key="empresa.endereco"/>" name="txtEndereco" value="${entity.endereco}" style="width: 925px"/>
                        <div class="controls">
                            <div id="gmap"></div>
                        </div>
                        <label class="alert"><fmt:message key="empresa.marcador.gmap"/></label>
                        <input type="text" id="txtLatitude" placeholder="<fmt:message key="empresa.latitude"/>" name="txtLatitude" value="${entity.latitude}" class="input-xlarge">
                        <input type="text" id="txtLongitude" placeholder="<fmt:message key="empresa.longitude"/>" name="txtLongitude" value="${entity.longitude}" class="input-xlarge">
                    </div>
                </div>

                <div class="tab-pane fade" id="telefone">

                    <div class="control-group">
                        <div class="controls">

                            <select name="telefone.telefoneTipo" id="tipo">
                                <c:forEach items="${telefoneTypes}" var="tipo">
                                    <c:set var="sel" value="${telefone.telefoneTipo eq tipo ? 'selected':''}"></c:set>
                                    <option value="${tipo}"${sel}>${tipo.label}</option>
                                </c:forEach>
                            </select>

                            <input type="text" placeholder="<fmt:message key="telefone.telefone"/>" id="telefone" data-mask="(99)9999-9999" name="telefone.telefone" value="${telefone.telefone}" class="input-large">
                            <div class="control-group">
                                <div class="controls"> 
                                    <input type="submit" class="btn btn-success" value="<fmt:message key="app.gravar" />"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th><fmt:message key="telefone.tipo"/></th>
                                <th><fmt:message key="telefone.telefone"/></th>
                                <th><fmt:message key="app.excluir"/></th>
                            </tr>
                        </thead>
                        <c:forEach items="${telefoneList}" var="listaTelefone">
                            <tr>
                                <td>${listaTelefone.telefoneTipo}</td>
                                <td>${listaTelefone.telefone}</td>
                                <td>
                                    <form action="/telefone/${listaTelefone.id}" method="post">
                                        <input type="hidden" name="_method" value="delete"/>
                                        <i class="icon-remove"></i>
                                        <input type="submit" class="btn btn-link" value="Remover"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div class="tab-pane fade" id="web">

                </div>

                <div class="tab-pane fade" id="logo">
                    <div class="control-group">
                        <label class="control-label" for="logo">
                            <fmt:message key="empresa.url.logo"/>
                        </label>
                        <div class="controls">
                            <input type="text" id="logo" name="entity.urlLogo" value="${entity.urlLogo}" class="input-xxlarge">
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="autenticacao">
                    <div class="control-group">
                        <div class="control-group">
                            <label class="control-label" for="email">
                                <fmt:message key="empresa.email"/>
                            </label>
                            <div class="controls">
                                <input type="email" required="true" id="email" style="text-transform: lowercase;" name="entity.email" value="${entity.email}" placeholder="empresa@dominio.com.br" class="input-xlarge">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="senha">
                                <fmt:message key="empresa.password"/>
                            </label>
                            <div class="controls">
                                <input type="password" required="true" id="senha" name="entity.password" value="${entity.password}" class="input-xlarge">
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="senhaRepetir">
                                <fmt:message key="empresa.password.repetir"/>
                            </label>
                            <div class="controls">
                                <input type="password" required="true" id="senhaRepetir" class="input-xlarge">
                            </div>
                        </div>

                        <div class="control-group">
                            <!-- Button -->
                            <div class="controls">
                                <button type="submit" class="btn btn-success">
                                    <fmt:message key="app.gravar" />
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </form>

    </fieldset>

</body>
