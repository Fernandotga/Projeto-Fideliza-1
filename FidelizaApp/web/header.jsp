<div class="logo">
    <img src="/FidelizaApp/resources/img/logonova_p.png" alt="Fideliza" title="Fideliza" />
</div>

<label class="title">beta</label>

<div id="identification">
    <fmt:message key="bem.vindo" />:

    <c:choose>
        <c:when test="${session.empresa == null}">Visitante</c:when>
        <c:otherwise>
            ${session.empresa.nomeFantasia} (<a href="<c:url value="/logout"/>">sair</a>)
        </c:otherwise>
    </c:choose>
</div>