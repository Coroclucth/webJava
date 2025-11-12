<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String rol = (String) session.getAttribute("rol");
    if (!"vendedor".equals(rol)) {
        response.sendRedirect("index.jsp");
        return; 
    }
%>

<jsp:include page="lib/header.jsp" />

<h2>Listado de Facturas</h2>

<table class="table table-striped table-hover">
    <thead class="table-dark">
        <tr>
            <th>Número de Factura</th>
            <th>Cliente</th>
            <th>Cédula Cliente</th>
            <th>Total</th>
            <th>Productos Comprados</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="factura" items="${facturas}">
            <tr>
                <td><c:out value="${factura.numeroFactura}"/></td>
                <td><c:out value="${factura.cliente.nombre}"/></td>
                <td><c:out value="${factura.cliente.cedula}"/></td>
                <td>$<c:out value="${factura.total}"/></td>
                <td>
                    <ul>
                        <c:forEach var="app" items="${factura.listaApps}">
                            <li>${app.nombreSoftware} (Aplicación)</li>
                        </c:forEach>
                        <c:forEach var="sis" items="${factura.listaSistemas}">
                            <li>${sis.nombreSoftware} (Sistema)</li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="FacturaServlet?action=mostrarFormulario" class="btn btn-success">Generar Nueva Factura</a>

<jsp:include page="lib/footer.jsp" />
