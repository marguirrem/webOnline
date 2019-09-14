<%-- 
    Document   : categorias
    Created on : 24/08/2019, 02:43:38 PM
    Author     : marlon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="menu.html"></jsp:include>
            <div class="container">

                <h2>Listado de Empaques</h2>
                <button class="btn btn-primary">Crear Tipo de Empaque</button>
                <table class="table table-striped">
                    <thead class="thead-dark ">
                        <tr>
                            <td>Código Empaque</td>
                            <td>Descripción</td>
                            <td>Editar</td>
                            <td>Eliminar</td>
                        </tr>
                    </thead>
                    <tbody id="tbodyApi">
                    <c:forEach items="${tiposEmpaque}" var="tipoEmpaque">
                        <tr>
                            <td>${tipoEmpaque.codigoEmpaque }</td>
                            <td>${tipoEmpaque.descripcion }</td>
                            <td> <button class="btn btn-info">Editar</button> </td>
                            <td> <button class="btn btn-danger">Eliminar</button> </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
