<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="lib/header.jsp" />

<h2>Generar Nueva Factura</h2>

<form action="FacturaServlet" method="POST">

    <div class="mb-3">
        <label for="cliente" class="form-label">Seleccionar Cliente</label>
        <select id="cliente" name="cliente" class="form-select" required>
            <option value="">-- Seleccione un Cliente --</option>
            <c:forEach var="cliente" items="${clientes}">
                <option value="${cliente.cedula}">${cliente.nombre} (${cliente.cedula})</option>
            </c:forEach>
        </select>
    </div>

    <div class="mb-3">
        <label for="productos" class="form-label">Seleccionar Productos</label>
        <select id="productos" name="productos" class="form-select" multiple required size="10">
            <c:forEach var="producto" items="${productos}">
                <option value="${producto.codigo}">${producto.nombreSoftware} - $${producto.precio}</option>
            </c:forEach>
        </select>
    </div>
    
    <button type="submit" class="btn btn-primary">Generar Factura</button>
    <a href="index.jsp" class="btn btn-secondary">Cancelar</a>
</form>

<jsp:include page="lib/footer.jsp" />
