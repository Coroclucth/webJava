<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="lib/header.jsp" />

<h2>Registrar Nuevo Usuario</h2>

<form action="UsuarioServlet" method="POST" onsubmit="return validarFormularioUsuario();">

    <div class="mb-3">
        <label for="tipoUsuario" class="form-label">Tipo de Usuario</label>
        <select id="tipoUsuario" name="tipoUsuario" class="form-select" onchange="mostrarCamposEspecificosUsuario()">
            <option value="cliente">Cliente</option>
            <option value="vendedor">Vendedor</option>
        </select>
    </div>

    <fieldset class="border p-3 mb-3">
        <legend class="w-auto px-2">Datos Personales</legend>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="nombre" class="form-label">Nombre Completo</label>
                <input type="text" class="form-control" id="nombre" name="nombre" required>
            </div>
            <div class="col-md-6 mb-3">
                <label for="cedula" class="form-label">Cédula</label>
                <input type="text" class="form-control" id="cedula" name="cedula" required>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="correo" class="form-label">Correo Electrónico</label>
                <input type="email" class="form-control" id="correo" name="correo" required>
            </div>
            <div class="col-md-6 mb-3">
                <label for="edad" class="form-label">Edad</label>
                <input type="number" class="form-control" id="edad" name="edad" required>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 mb-3">
                <label for="telefono" class="form-label">Teléfono</label>
                <input type="text" class="form-control" id="telefono" name="telefono" required>
            </div>
            <div class="col-md-4 mb-3">
                <label for="direccion" class="form-label">Dirección</label>
                <input type="text" class="form-control" id="direccion" name="direccion" required>
            </div>
            <div class="col-md-4 mb-3">
                <label for="genero" class="form-label">Género</label>
                <select class="form-select" id="genero" name="genero" required>
                    <option value="Masculino">Masculino</option>
                    <option value="Femenino">Femenino</option>
                    <option value="Otro">Otro</option>
                </select>
            </div>
        </div>
    </fieldset>

    <!-- Campos específicos para Cliente -->
    <fieldset id="camposCliente" class="border p-3 mb-3">
        <legend class="w-auto px-2">Datos de Cliente</legend>
         <div class="row">
            <div class="col-md-6 mb-3">
                <label for="tipoCliente" class="form-label">Tipo de Cliente</label>
                <input type="text" class="form-control" id="tipoCliente" name="tipoCliente" placeholder="Ej: Frecuente, Ocasional">
            </div>
            <div class="col-md-6 mb-3">
                <label for="historial" class="form-label">Historial</label>
                <input type="text" class="form-control" id="historial" name="historial" placeholder="Ej: Compras anteriores">
            </div>
        </div>
    </fieldset>

    <!-- Campos específicos para Vendedor  -->
    <fieldset id="camposVendedor" style="display: none;" class="border p-3 mb-3">
        <legend class="w-auto px-2">Datos de Vendedor</legend>
        <div class="row">
            <div class="col-md-4 mb-3">
                <label for="rango" class="form-label">Rango</label>
                <input type="text" class="form-control" id="rango" name="rango" placeholder="Ej: Junior, Senior">
            </div>
            <div class="col-md-4 mb-3">
                <label for="horario" class="form-label">Horario</label>
                <input type="text" class="form-control" id="horario" name="horario" placeholder="Ej: Matutino, Vespertino">
            </div>
            <div class="col-md-4 mb-3">
                <label for="sueldo" class="form-label">Sueldo</label>
                <input type="text" class="form-control" id="sueldo" name="sueldo">
            </div>
        </div>
    </fieldset>
    
    <button type="submit" class="btn btn-primary">Registrar Usuario</button>
    <a href="UsuarioServlet?action=listar" class="btn btn-secondary">Cancelar</a>
</form>

<script>
    function mostrarCamposEspecificosUsuario() {
        var tipo = document.getElementById("tipoUsuario").value;
        if (tipo === 'cliente') {
            document.getElementById('camposCliente').style.display = 'block';
            document.getElementById('camposVendedor').style.display = 'none';
        } else if (tipo === 'vendedor') {
            document.getElementById('camposCliente').style.display = 'none';
            document.getElementById('camposVendedor').style.display = 'block';
        }
    }
    
    function validarFormularioUsuario() {
        return true;
    }
    
    // Llamada inicial para asegurar que se muestren los campos correctos al cargar la página
    document.addEventListener('DOMContentLoaded', mostrarCamposEspecificosUsuario);
</script>

<jsp:include page="lib/footer.jsp" />
