<head>
    <title><fmt:message key="app.title"/> | <fmt:message key="app.exibir" /> </title>
</head>
<body>
    <fieldset>
        <div id="legend">
            <legend class=""> 
                <fmt:message key="fidelizados.fidelizados"/> 
                <small> <fmt:message key="app.exibir"/> </small>
            </legend>  
        </div>  

        <div class="control-group">
            <div class="controls">
                <div style="border-bottom: 2px solid #E5E5E5; padding-bottom: 0px">
                    <h3>${entity.nome}</h3>
                </div>
            </div>
        </div>
        <div class="controls">
            <div class="input-prepend">
                <span class="add-on"><i class="icon-home"></i></span>                   
            </div>
            <fmt:message key="fidelizados.endereco" /> ${entity.endereco}
        </div>
        <div class="controls">
            <div class="input-prepend">
                <span class="add-on"><i class="icon-envelope"></i></span>                   
            </div>
            <fmt:message key="fidelizados.email" /> ${entity.email}
        </div>
        <div class="controls">
            <div class="input-prepend">
                <span class="add-on"><i class="icon-user"></i></span>                   
            </div>
            <fmt:message key="fidelizados.genero" /> ${entity.genero}
        </div>
        <div class="controls">
            <div class="input-prepend">
                <span class="add-on"><i class="icon-calendar"></i></span>                   
            </div>
            <fmt:message key="fidelizados.data.nascimento" />  <fmt:formatDate value="${entity.dataNascimento}" pattern="dd/MM/yyyy" /> 
        </div>

        <div class="control-group">
            <div class="controls">
                <div class="inline">
                    <form action="<c:url value="/fidelizados" />" method="GET">
                        <button class="btn btn-success btn-small" style="margin-right: 5px;" type="submit">
                            <i class="icon-white icon-list"></i>
                            <fmt:message key="app.listar" />
                        </button>
                    </form>
                </div>
                <br class="clearBoth" />
            </div>
        </div>
    </fieldset>
</body>