<body>
    <fieldset>
        <div id="legend">
            <legend class="">
                <fmt:message key="empresa.empresa"/>
                <small> <fmt:message key="app.formulario"/> </small>
            </legend>
        </div>

        <c:choose>
            <c:when test="${entity.id == null}">
                <c:set var="uri" value="/empresa" />
            </c:when>
            <c:otherwise>
                <c:set var="uri" value="/empresa/${entity.id}/atualizar" />
            </c:otherwise>
        </c:choose>

        <form id="cadastroEmpresa" action="<c:url value="${uri}" />" method="POST">
            <c:if test="${entity.id != null}">
                <input type="hidden" name="_method" value="PUT"/>
            </c:if>

            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#dados" data-toggle="tab">Dados Cadastrais</a>
                </li>
                <li>
                    <a href="#localizacao" data-toggle="tab">Localização</a>
                </li>
                <li>
                    <a href="#tab_telefone" data-toggle="tab">Telefones</a>
                </li>
                <li>
                    <a href="#web" data-toggle="tab">Contato Web</a>
                </li>
                <li>
                    <a href="#autenticacao" data-toggle="tab">Autenticação</a>
                </li>
            </ul>

            <div id="myTabContent" class="tab-content">

                <div class="tab-pane active in" id="dados">

                    <div class="control-group">
                        <label class="control-label" for="fantasia"> <fmt:message key="empresa.nome.fantasia"/> </label>
                        <div class="controls">
                            <div class="input-prepend">
                                <span class="add-on"><i class="icon-home"></i></span>
                                <input type="text" required="true" id="fantasia" name="entity.nomeFantasia" value="${entity.nomeFantasia}" maxlength="60" class="input-xxlarge">
                            </div>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="razao"> <fmt:message key="empresa.razao.social"/> </label>
                        <div class="controls">
                            <div class="input-prepend">
                                <span class="add-on"><i class="icon-file"></i></span>
                                <input type="text" required="true" id="razao" name="entity.razaoSocial" value="${entity.razaoSocial}" maxlength="60" class="input-xxlarge">
                            </div>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="cnpj"> <fmt:message key="empresa.cnpj"/> </label>
                        <div class="controls">
                            <div class="input-prepend">
                                <span class="add-on"><i class="icon-folder-close"></i></span>
                                <input type="text" id="cnpj" data-mask="99.999.999/9999-99" name="entity.cnpj" value="${entity.cnpj}" class="input-medium">  
                            </div>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="responsavel"> <fmt:message key="empresa.responsavel"/> </label>
                        <div class="controls">
                            <div class="input-prepend">
                                <span class="add-on"><i class="icon-user"></i></span>
                                <input type="text" required="true" id="responsavel" name="entity.responsavel" value="${entity.responsavel}" maxlength="60" class="input-xxlarge">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="localizacao">
                    <div class="control-group">                                          
                        <label class="control-label" for="txtEndereco"> <fmt:message key="empresa.endereco"/> </label>
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-map-marker"></i></span>
                            <input type="text" id="txtEndereco" placeholder="Endereço Completo" name="entity.endereco" value="${entity.endereco}" style="width: 900px" maxlength="200"/>
                        </div>
                        <div class="controls">                           
                            <div id="gmap"></div>
                        </div>
                        <label class="alert alert-success"><fmt:message key="empresa.marcador.gmap"/></label>
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-globe"></i></span>
                            <input type="text" id="txtLatitude" placeholder="<fmt:message key="empresa.latitude"/>" name="entity.latitude" value="${entity.latitude}" readonly="true" style="text-align: right" class="input-xlarge">
                        </div>
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-globe"></i></span>
                            <input type="text" id="txtLongitude" placeholder="<fmt:message key="empresa.longitude"/>" name="entity.longitude" value="${entity.longitude}" readonly="true" style="text-align: right;" class="input-xlarge">
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="tab_telefone">
                    <div class="control-group">
                        <div class="controls">
                            <a href="#" class="btn btn-success" onclick="adicionar();"> <fmt:message key="app.adicionar"/> </a>
                            <hr />
                        </div>

                        <c:forEach items="${entity.telefones}" var="telefone" varStatus="status">
                            <div class="telefone">
                                <label for="telefone"> <fmt:message key="telefone.tipo"/> / <fmt:message key="telefone.telefone"/></label>

                                <select name="entity.telefones[${status.index}].tipo" id="tipo" style="margin-right: 10px;">
                                    <c:forEach items="${telefoneTypes}" var="tipo">
                                        <c:set var="sel" value="${telefone.tipo eq tipo ? 'selected':''}"></c:set>
                                        <option value="${telefone.tipo}"${sel}>${tipo.label}</option>
                                    </c:forEach>
                                </select>

                                <input type="text" placeholder="" id="telefone" data-mask="(99) 9999-9999" name="entity.telefones[${status.index}].telefone" value="${telefone.telefone}" class="input-large">

                                <input type="hidden" name="entity.telefones[${status.index}].id" value="${telefone.id}" />

                                <a id="remover" class="btn btn-link" onclick="remover(this);"> <fmt:message key="app.excluir"/> </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="tab-pane fade" id="web">
                    <div class="control-group">

                    </div>
                </div>

                <div class="tab-pane fade" id="autenticacao">
                    <div class="control-group">
                        <div class="control-group">
                            <label class="control-label" for="email">
                                <fmt:message key="empresa.email"/>
                            </label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-envelope"></i></span>
                                    <input type="email" required="true" id="email" style="text-transform: lowercase;" name="entity.email" value="${entity.email}" placeholder="empresa@dominio.com.br" class="input-xlarge">
                                </div>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="senha">
                                <fmt:message key="empresa.password"/>
                            </label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-lock"></i></span>
                                    <input type="password" placeholder="******" required="true" id="senha" name="entity.password" value="${entity.password}" class="input-xlarge">
                                </div>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="senhaRepetir">
                                <fmt:message key="empresa.password.repetir"/>
                            </label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on"><i class="icon-lock"></i></span>
                                    <input type="password" placeholder="******" required="true" id="senhaRepetir" class="input-xlarge">
                                </div>
                            </div>
                        </div>

                        <div class="control-group">
                            <!-- Button -->
                            <div class="controls">
                                <button type="submit" class="btn btn-success">
                                    <i class="icon-ok icon-white"></i>
                                    <fmt:message key="app.gravar" />
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </fieldset>
    <script>
                                var model =
                                        '<div class="telefone">' +
                                        '<label for="telefone"> <fmt:message key="telefone.tipo"/> / <fmt:message key="telefone.telefone"/></label>' +
                                        '<select name = "entity.telefones[0].tipo" id="tipo" style = "margin-right: 10px;" >' +
                                        '<c:forEach items="${telefoneTypes}" var="tipo">' +
                                        '<c:set var="sel" value="${telefone.tipo eq tipo ? 'selected':''}"></c:set>' +
                                        '<option value = "${telefone.tipo}"${sel} >${tipo.label} </option>' +
                                        '</c:forEach>' +
                                        '</select>' +
                                        '<input type="text" id="telefone" data-mask="(99) 9999-9999" name="entity.telefones[0].telefone" class="input-large">' +
                                        '<a id="remover" class="btn btn-link" onclick="remover(this);"> <fmt:message key="app.excluir"/> </a>' +
                                        '</div>';


                                function remover(element) {
                                    $(element).parent().remove();
                                    reorderIndexes();
                                }
                                ;

                                function adicionar() {
                                    $('#tab_telefone').append(model);
                                    reorderIndexes();
                                }
                                ;

                                function reorderIndexes() {
                                    var regex = /\[[0-9]\]/g;

                                    $('.telefone').each(function(index) {
                                        var $campos = $(this).find('input'),
                                                $input,
                                                name;

                                        $campos.each(function() {
                                            $input = $(this),
                                                    name = $input.attr('name');

                                            $input.attr('name', name.replace(regex, '[' + index + ']'));
                                        });
                                    });
                                }
                                ;
    </script>

</body>
