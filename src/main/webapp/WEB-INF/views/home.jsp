<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="function" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <!-- Codificación de caracteres -->
        <meta charset="UTF-8">

        <!-- Configuración de ancho y escala inicial para los dispositivos -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Título -->
        <title>Fotografías</title>

        <!-- Hoja de Estilos -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha512-MoRNloxbStBcD8z3M/2BmnT+rg4IsMxPkXaGh2zD6LGNNFE80W3onsAhRcMAMrSoyWL9xD7Ert0men7vR8LUZg==" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.css" integrity="sha512-H9jrZiiopUdsLpg94A333EfumgUBpO9MdbxStdeITo+KEIMaNfHNvwyjjDJb+ERPaRS6DpyRlKbvPUasNItRyw==" crossorigin="anonymous" />
    </head>

    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-dark bg-dark shadow mb-4">
            <div class="container">
                <a class="navbar-brand" href="${pageContext.request.contextPath}">Fotografías</a>
            </div>
        </nav>
        <!-- /Navbar -->

        <!-- Categorías -->
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <core:forEach items="${categorias}" var="categoria">
                        <core:choose>
                            <core:when test="${function:contains(pageContext.request.requestURL, categoria.getId())}">
                                <span class="badge badge-pill badge-success">${categoria.getNombre()}</span>
                            </core:when>

                            <core:otherwise>
                                <a href="${pageContext.request.contextPath}/categoria/${categoria.getId()}" class="badge badge-pill badge-light">${categoria.getNombre()}</a>
                            </core:otherwise>
                        </core:choose>
                    </core:forEach>
                </div>
            </div>
        </div>
        <!-- /Categorías -->

        <!-- Fotografías -->
        <div class="container my-5">
            <div class="row">
                <core:choose>
                    <core:when test="${fotografias != null && fotografias.size() > 0}">
                        <core:forEach items="${fotografias}" var="fotografia">
                            <div class="col-lg-4 col-md-6 mb-4">
                                <div class="card">
                                    <div class="card-body">
                                        <a href="${fotografia.url}" data-fancybox="gallery" data-caption="${fotografia.descripcion}">
                                            <img alt="${function:substring(fotografia.descripcion, 0, 50)}" src="${fotografia.url}" height="350" width="100%" />
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </core:forEach>
                    </core:when>

                    <core:otherwise>
                        <div class="col-12">
                            <div class="alert alert-warning text-center">No hay registros para mostrar</div>
                        </div>
                    </core:otherwise>
                </core:choose>
            </div>
        </div>
        <!-- /Fotografías -->

        <!-- Dependencias JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg==" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.5.7/jquery.fancybox.min.js" integrity="sha512-uURl+ZXMBrF4AwGaWmEetzrd+J5/8NRkWAvJx5sbPSSuOb0bZLqf+tOzniObO00BjHa/dD7gub9oCGMLPQHtQA==" crossorigin="anonymous"></script>
        <!-- /Dependencias JavaScript -->
    </body>
</html>
