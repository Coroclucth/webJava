<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="lib/header.jsp" />

<h2>Listado de Productos</h2>

<table class="table table-striped table-hover">
    
    <form action="ProductoServlet" method="GET" class="d-flex align-items-center mb-3">
    <input type="hidden" name="action" value="filtrar">
    <input class="form-control me-2 w-50" type="search" name="codigo" placeholder="Buscar por código" aria-label="Buscar por código"/>
    <button type="submit" class="btn btn-warning btn-sm">Filtrar</button>
</form>
    
    <thead class="table-dark">
        <tr>
            <th>Código</th>
            <th>Nombre</th>
            <th>Versión</th>
            <th>Tipo</th>
            <th>Stock</th>
            <th>Precio</th>
            <th>Obsolescencia</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="producto" items="${productos}">
            <tr>
                <td><c:out value="${producto.codigo}"/></td>
                <td><c:out value="${producto.nombreSoftware}"/></td>
                <td><c:out value="${producto.version}"/></td>
                <td><c:out value="${producto.tipo}"/></td>
                <td><c:out value="${producto.stock}"/></td>
                <td><c:out value="${producto.precio}"/></td>
                <td><c:out value="${producto.obsoleto ? 'Sí' : 'No'}"/></td>
                <td>
                    <a href="ProductoServlet?action=editar&codigo=${producto.codigo}" class="btn btn-warning btn-sm">Editar</a>
                    <a href="ProductoServlet?action=eliminar&codigo=${producto.codigo}" class="btn btn-danger btn-sm" onclick="return confirm('¿Está seguro de que desea eliminar este producto?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<jsp:include page="lib/footer.jsp" />
