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
                    <a href="#endereco" data-toggle="tab">Endereço</a>
                </li>
                <li>
                    <a href="#localizacao" data-toggle="tab">Localização</a>
                </li>
                <li>
                    <a href="#telefone" data-toggle="tab">Telefones</a>
                </li>
                <li>
                    <a href="#web" data-toggle="tab">Web</a>
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
                        <label class="control-label" for="fantasia">
                            <fmt:message key="empresa.nome.fantasia"/>
                        </label>
                        <div class="controls">
                            <input type="text" required="true" id="fantasia" name="entity.nomeFantasia" value="${entity.nomeFantasia}" class="input-xxlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="razao">
                            <fmt:message key="empresa.razao.social"/>
                        </label>
                        <div class="controls">
                            <input type="text" required="true" id="razao" name="entity.razaoSocial" value="${entity.razaoSocial}" class="input-xxlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="cnpj">
                            <fmt:message key="empresa.cnpj"/>
                        </label>
                        <div class="controls">
                            <input type="text" id="cnpj" data-mask="99.999.999/9999-99" name="entity.cnpj" value="${entity.cnpj}" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="responsavel">
                            <fmt:message key="empresa.responsavel"/>
                        </label>
                        <div class="controls">
                            <input type="text" required="true" id="responsavel" name="entity.responsavel" value="${entity.responsavel}" class="input-xxlarge">
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="endereco">
                    <div class="control-group">
                        <label class="control-label" for="cep">
                            <fmt:message key="empresa.cep"/>
                        </label>
                        <div class="controls">
                            <input type="text" id="cep" data-mask="99999-999"  name="entity.cep" value="${entity.cep}" class="input-xlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="uf">
                            <fmt:message key="empresa.uf"/>
                        </label>
                        <div class="controls">
                            <input type="text" id="uf" data-mask="aa" name="entity.uf" value="${entity.uf}" class="input-small">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="cidade">
                            <fmt:message key="empresa.cidade"/>
                        </label>
                        <div class="controls">
                            <input type="text" id="cidade" name="entity.cidade" value="${entity.cidade}" class="input-xxlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="bairro">
                            <fmt:message key="empresa.bairro"/>
                        </label>
                        <div class="controls">
                            <input type="text" id="bairro" name="entity.bairro" value="${entity.bairro}" class="input-xxlarge">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="endereco">
                            <fmt:message key="empresa.endereco"/>
                        </label>
                        <div class="controls">
                            <input type="text" id="endereco" name="entity.endereco" value="${entity.endereco}" class="input-xxlarge">
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="localizacao">
                    <div class="control-group">
                        <label class="control-label" for="latitude">
                            <fmt:message key="empresa.latitude"/>
                        </label>
                        <div class="controls">
                            <input type="text" id="latitude" name="entity.latitude" value="${entity.latitude}" class="input-xxlarge">
                        </div>
                    </div>


                    <div class="control-group">
                        <label class="control-label" for="longitude">
                            <fmt:message key="empresa.longitude"/>
                        </label>
                        <div class="controls">
                            <input type="text" id="longitude" name="entity.longitude" value="${entity.longitude}" class="input-xxlarge">
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="telefone">

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
