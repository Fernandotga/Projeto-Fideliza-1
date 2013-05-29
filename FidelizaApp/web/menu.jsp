<ul class="nav" id="navSup">
    <!--    Convenção: classe.metodo (empresaSession.isLogged)-->
    <c:if test="${!empresaSession.logged}">
        <li class="active"><a href="<c:url value="/"/>">Identificação</a></li> 
        <li><a href="<c:url value="/empresa/criar"/>">Cadastre-se</a></li> 
        </c:if>
        <c:if test="${empresaSession.logged}">
        <li class="active"><a href="<c:url value="/painel/principal"/>">Painel</a></li>
        <li><a href="#">Ofertas</a></li> 
        <li><a href="#">Fidelidade</a></li> 
        <li><a href="#">Clientes</a></li> 
        <li><a href="#">Mensagens</a></li> 
        <li><a href="#">Relatórios</a></li> 
        <li><a href="#">Empresa</a></li> 
        </c:if>
    <li class="divider-vertical"></li>    
    <li><a href="<c:url value="/projeto/sobre"/>">Sobre</a></li>
</ul>