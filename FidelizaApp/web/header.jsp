<div class="logo">
    <img src="/FidelizaApp/resources/img/logonova_p.png" alt="Fideliza" title="Fideliza" />
</div>

<div id="identification">
    <fmt:message key="bem.vindo" />,
    <c:choose>
        <c:when test="${!empresaSession.logged}"></i> Visitante  </c:when>
        <c:otherwise>
            ${empresaSession.empresa.razaoSocial}
        <a style="margin-left: 15px;" href="<c:url value="/logout"/>"> <i class="icon-off"></i> <fmt:message key="app.sair"/></a>
    </c:otherwise>
</c:choose>
</div>