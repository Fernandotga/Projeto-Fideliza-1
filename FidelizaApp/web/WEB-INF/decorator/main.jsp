<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
        <link type="image/x-icon" rel="shortcut icon" href="/FidelizaApp/resources/img/favicon.png"/>

        <meta name="description" content="Projeto Fideliza" />
        <meta name="author" content="Jeferson Oliveira Cruz"/>
        <meta name="keywords" content="vraptor,fideliza,projeto,fidelidade"/>

        <link type="text/css" rel="stylesheet" href="/FidelizaApp/resources/css/stylesheet.css"/>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:600" type="text/css" rel="stylesheet" />

        <script type="text/javascript" charset=ISO-8859-1" src="/FidelizaApp/resources/js/jquery.min.js"></script>
        <script type="text/javascript" charset=ISO-8859-1" src="/FidelizaApp/resources/js/bootstrap.js"></script>
        <script type="text/javascript" charset=ISO-8859-1" src="/FidelizaApp/resources/js/bootstrap-responsive.js"></script>
        <script type="text/javascript" charset=ISO-8859-1" src="/FidelizaApp/resources/js/jquery.colorbox-1.3.16.min.js"></script>

        <script type="text/javascript" charset=ISO-8859-1" src="/FidelizaApp/resources/js/javascript.js"></script>
        <script type="text/javascript" charset=ISO-8859-1" src="/FidelizaApp/resources/js/bootstrap-inputmask.min.js"></script>
        <script type="text/javascript" charset=ISO-8859-1" src="/FidelizaApp/resources/js/mapa.js"></script>
        <script type="text/javascript" charset=ISO-8859-1" src="/FidelizaApp/resources/js/jquery-ui.custom.min.js"></script>
        <script type="text/javascript" charset=ISO-8859-1" src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=true"></script>

        <title><decorator:title default="Main | Projeto Fideliza"/></title>
    </head>

    <body>
        <div id="wrapper">

            <div class="header">
                <div class="container">
                    <div id="topo">
                        <%@ include file="/header.jsp" %>
                    </div>
                </div>
            </div>

            <div class="navbar">
                <div class="navbar-inner" style="width: auto;">   
                    <div class="container">
                        <%@ include file="/menu.jsp" %>
                    </div>
                </div>
            </div>

            <div id="content">
                <div class="container">
                    <decorator:body/>
                    <c:if test="${not empty errors}">
                        <div id="errors" class="error">
                            <c:forEach var="error" items="${errors}">
                                ${error.category} - ${error.message}<br/>
                            </c:forEach>
                        </div>
                    </c:if>

                    <c:if test="${not empty error}">
                        <div id="error" class="alert alert-error">${error}</div>
                    </c:if>

                    <c:if test="${not empty message}">
                        <div id="notice" class="alert">${message}</div>
                    </c:if>

                    <div id="mensagem"></div>
                </div>
            </div>

            <div id="rodape">
                <div class="container">
                    <%@ include file="/footer.jsp" %>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            function dummy() {
                alert('Dummy function!');
            };

            $.ajaxSetup({
                type: 'get',
                dataType: 'json',
                scriptCharset: 'utf-8',
                error: function(xhr, status, error) {
                    mensagem('<fmt:message key="erro"/>', getError(xhr));
                }
            });

            $(function() {
                console.log('Ready! (:');
            });
        </script>
    </body>
</html>
