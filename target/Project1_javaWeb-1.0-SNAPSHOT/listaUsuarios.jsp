<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="lib/header.jsp" />

<div class="d-flex justify-content-between align-items-center mb-3">
    <h2>Listado de Usuarios</h2>
    <a href="registroUsuario.jsp" class="btn btn-success">Registrar Nuevo Usuario</a>
</div>

<table class="table table-striped table-hover">
    <thead class="table-dark">
        <tr>
            <th>Cédula</th>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Edad</th>
            <th>Teléfono</th>
            <th>Género</th>
            <th>Dirección</th>
            <th>Tipo de Usuario</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="usuario" items="${usuarios}">
            <tr>
                <td><c:out value="${usuario.cedula}"/></td>
                <td><c:out value="${usuario.nombre}"/></td>
                <td><c:out value="${usuario.correo}"/></td>
                <td><c:out value="${usuario.edad}"/></td>
                <td><c:out value="${usuario.telefono}"/></td>
                <td><c:out value="${usuario.genero}"/></td>
                <td><c:out value="${usuario.direccion}"/></td>
                <td>
                    <c:choose>
                        <c:when test="${usuario.tipoUsuario == 'Cliente'}">
                            <span class="badge bg-primary">Cliente</span>
                        </c:when>
                        <c:when test="${usuario.tipoUsuario == 'Vendedor'}">
                            <span class="badge bg-info">Vendedor</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge bg-secondary">Desconocido</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="UsuarioServlet?action=editar&cedula=${usuario.cedula}" class="btn btn-warning btn-sm">Editar</a>
                    <a href="UsuarioServlet?action=eliminar&cedula=${usuario.cedula}" class="btn btn-danger btn-sm" onclick="return confirm('¿Está seguro de que desea eliminar este usuario?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<jsp:include page="lib/footer.jsp" />
