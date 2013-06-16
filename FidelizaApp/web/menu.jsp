<b>
    <ul class="nav" id="navSup">
        <!--    Convenção: classe.metodo (empresaSession.isLogged)-->
        <c:if test="${!empresaSession.logged}">
            <li class="active"><a href="<c:url value="/"/>">Identificação</a></li> 
            <li><a href="<c:url value="/empresa/criar"/>">Cadastre-se</a></li> 
            </c:if>
            <c:if test="${empresaSession.logged}">
            <li class="active"><a href="<c:url value="/painel/principal"/>">Painel</a></li>
            <li><a href="<c:url value="/oferta"/>">Minhas Ofertas</a></li> 
            <li><a href="<c:url value="/fidelidade"/>">Cartão Fidelidade</a></li> 
            <li><a href="<c:url value="/fidelizados"/>">Fidelizados</a></li> 
            <li class="divider-vertical"></li> 
            <li><a href="<c:url value="/empresa/${empresaSession.empresa.id}"/>">Minha Empresa</a></li> 
            </c:if>
        <li class="divider-vertical"></li>    
        <li><a href="<c:url value="/projeto/sobre"/>">Sobre</a></li>
        <li class="divider-vertical"></li> 
    </ul>
</b>