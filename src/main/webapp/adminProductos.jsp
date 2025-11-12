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

<h2>Registrar Nuevo Producto</h2>

<c:if test="${not empty error}">
    <div class="alert alert-danger" role="alert">
        ${error}
    </div>
</c:if>

<form action="ProductoServlet" method="POST" onsubmit="return validarFormulario();">

    <div class="mb-3">
        <label for="tipo" class="form-label">Tipo de Producto</label>
        <select id="tipo" name="tipo" class="form-select" onchange="mostrarCamposEspecificos()">
            <option value="aplicacion">Aplicación</option>
            <option value="sistema">Sistema Operativo</option>
        </select>
    </div>

    <div class="row">
        <div class="col-md-6 mb-3">
            <label for="nombreSoftware" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombreSoftware" name="nombreSoftware" required>
        </div>
        <div class="col-md-6 mb-3">
            <label for="codigo" class="form-label">Código</label>
            <input type="text" class="form-control" id="codigo" name="codigo" required>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4 mb-3">
            <label for="version" class="form-label">Versión</label>
            <input type="text" class="form-control" id="version" name="version">
        </div>
        <div class="col-md-4 mb-3">
            <label for="precio" class="form-label">Precio</label>
            <input type="number" step="0.01" class="form-control" id="precio" name="precio" required>
        </div>
        <div class="col-md-4 mb-3">
            <label for="stock" class="form-label">Stock</label>
            <input type="number" class="form-control" id="stock" name="stock" required>
        </div>
    </div>

    <!-- Campos específicos para Aplicación -->
    <div id="camposAplicacion">
         <div class="row">
            <div class="col-md-4 mb-3">
                <label for="juegos" class="form-label">Juegos</label>
                <input type="text" class="form-control" id="juegos" name="juegos">
            </div>
            <div class="col-md-4 mb-3">
                <label for="navegadorWeb" class="form-label">Navegador Web</label>
                <input type="text" class="form-control" id="navegadorWeb" name="navegadorWeb">
            </div>
            <div class="col-md-4 mb-3">
                <label for="diseño" class="form-label">Diseño</label>
                <input type="text" class="form-control" id="diseño" name="diseño">
            </div>
        </div>
    </div>

    <!-- Campos específicos para Sistema  -->
    <div id="camposSistema" style="display: none;">
        <div class="row">
            <div class="col-md-4 mb-3">
                <label for="macOs" class="form-label">Compatibilidad macOS</label>
                <input type="text" class="form-control" id="macOs" name="macOs" placeholder="Ej: Monterey, Ventura">
            </div>
            <div class="col-md-4 mb-3">
                <label for="windows" class="form-label">Compatibilidad Windows</label>
                <input type="text" class="form-control" id="windows" name="windows" placeholder="Ej: 10, 11">
            </div>
            <div class="col-md-4 mb-3">
                <label for="linux" class="form-label">Compatibilidad Linux</label>
                <input type="text" class="form-control" id="linux" name="linux" placeholder="Ej: Ubuntu, Fedora">
            </div>
        </div>
    </div>
    
    <div class="form-check mb-3">
        <input class="form-check-input" type="checkbox" value="true" id="obsoleto" name="obsoleto">
        <label class="form-check-label" for="obsoleto">
            Marcar como Obsoleto
        </label>
    </div>

    <button type="submit" class="btn btn-primary">Registrar Producto</button>
    <a href="ProductoServlet?action=listado" class="btn btn-secondary">Cancelar</a>
</form>

<script>
    function mostrarCamposEspecificos() {
        var tipo = document.getElementById("tipo").value;
        if (tipo === 'aplicacion') {
            document.getElementById('camposAplicacion').style.display = 'block';
            document.getElementById('camposSistema').style.display = 'none';
        } else if (tipo === 'sistema') {
            document.getElementById('camposAplicacion').style.display = 'none';
            document.getElementById('camposSistema').style.display = 'block';
        }
    }
    
    function validarFormulario() {
        return true;
    }
    
    // Llamada inicial para asegurar que se muestren los campos correctos al cargar la página
    mostrarCamposEspecificos();
</script>

<jsp:include page="lib/footer.jsp" />