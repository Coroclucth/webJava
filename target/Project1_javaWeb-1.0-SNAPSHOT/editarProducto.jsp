<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="lib/header.jsp" />

<h2>Editar Producto</h2>

<c:if test="${not empty producto}">
    <form action="ProductoServlet" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="codigoOriginal" value="<c:out value='${producto.codigo}'/>">
        
        <div class="mb-3">
            <label for="tipo" class="form-label">Tipo de Producto</label>
            <input type="text" class="form-control" id="tipo" name="tipo" value="<c:out value='${producto.tipo}'/>" readonly>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="nombreSoftware" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombreSoftware" name="nombreSoftware" value="<c:out value='${producto.nombreSoftware}'/>" required>
            </div>
            <div class="col-md-6 mb-3">
                <label for="codigo" class="form-label">Código</label>
                <input type="text" class="form-control" id="codigo" name="codigo" value="<c:out value='${producto.codigo}'/>" required>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4 mb-3">
                <label for="version" class="form-label">Versión</label>
                <input type="text" class="form-control" id="version" name="version" value="<c:out value='${producto.version}'/>">
            </div>
            <div class="col-md-4 mb-3">
                <label for="precio" class="form-label">Precio</label>
                <input type="number" step="0.01" class="form-control" id="precio" name="precio" value="<c:out value='${producto.precio}'/>" required>
            </div>
            <div class="col-md-4 mb-3">
                <label for="stock" class="form-label">Stock</label>
                <input type="number" class="form-control" id="stock" name="stock" value="<c:out value='${producto.stock}'/>" required>
            </div>
        </div>

        <!-- Campos específicos para Aplicación -->
        <div id="camposAplicacion" style="display: none;">
            <c:if test="${producto.tipo == 'Aplicación'}">
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="juegos" class="form-label">Juegos</label>
                        <input type="text" class="form-control" id="juegos" name="juegos" value="<c:out value='${producto.juegos}'/>">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="navegadorWeb" class="form-label">Navegador Web</label>
                        <input type="text" class="form-control" id="navegadorWeb" name="navegadorWeb" value="<c:out value='${producto.navegadorWeb}'/>">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="diseño" class="form-label">Diseño</label>
                        <input type="text" class="form-control" id="diseño" name="diseño" value="<c:out value='${producto.diseño}'/>">
                    </div>
                </div>
            </c:if>
        </div>

        <!-- Campos específicos para Sistema -->
        <div id="camposSistema" style="display: none;">
             <c:if test="${producto.tipo == 'Sistema Operativo'}">
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="macOs" class="form-label">Compatibilidad macOS</label>
                        <input type="text" class="form-control" id="macOs" name="macOs" value="<c:out value='${producto.macOs}'/>">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="windows" class="form-label">Compatibilidad Windows</label>
                        <input type="text" class="form-control" id="windows" name="windows" value="<c:out value='${producto.windows}'/>">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="linux" class="form-label">Compatibilidad Linux</label>
                        <input type="text" class="form-control" id="linux" name="linux" value="<c:out value='${producto.linux}'/>">
                    </div>
                </div>
            </c:if>
        </div>
        
        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" value="true" id="obsoleto" name="obsoleto" ${producto.obsoleto ? 'checked' : ''}>
            <label class="form-check-label" for="obsoleto">
                Marcar como Obsoleto
            </label>
        </div>

        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        <a href="ProductoServlet?action=listado" class="btn btn-secondary">Cancelar</a>
    </form>
</c:if>

<c:if test="${empty producto}">
    <div class="alert alert-danger" role="alert">
        No se encontró el producto especificado.
    </div>
    <a href="ProductoServlet?action=listado" class="btn btn-primary">Volver al Listado</a>
</c:if>

<script>
    // Muestra los campos correctos según el tipo de producto
    document.addEventListener("DOMContentLoaded", function() {
        var tipo = document.getElementById("tipo").value.trim();
        document.getElementById('camposAplicacion').style.display = (tipo === 'Aplicación') ? 'block' : 'none';
        document.getElementById('camposSistema').style.display = (tipo === 'Sistema Operativo') ? 'block' : 'none';
    });
</script>

<jsp:include page="lib/footer.jsp" />
