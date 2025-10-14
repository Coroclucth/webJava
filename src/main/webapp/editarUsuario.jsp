<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="lib/header.jsp" />

<h2>Editar Usuario</h2>

<c:if test="${not empty usuario}">
    <form action="UsuarioServlet" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="cedulaOriginal" value="<c:out value='${usuario.cedula}'/>">
        <input type="hidden" name="tipoUsuario" value="<c:out value='${tipoUsuario}'/>">

        <fieldset class="border p-3 mb-3">
            <legend class="w-auto px-2">Datos Personales</legend>
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="nombre" class="form-label">Nombre Completo</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" value="<c:out value='${usuario.nombre}'/>" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="cedula" class="form-label">Cédula</label>
                    <input type="text" class="form-control" id="cedula" name="cedula" value="<c:out value='${usuario.cedula}'/>" required>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="correo" class="form-label">Correo Electrónico</label>
                    <input type="email" class="form-control" id="correo" name="correo" value="<c:out value='${usuario.correo}'/>" required>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="edad" class="form-label">Edad</label>
                    <input type="number" class="form-control" id="edad" name="edad" value="<c:out value='${usuario.edad}'/>" required>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 mb-3">
                    <label for="telefono" class="form-label">Teléfono</label>
                    <input type="text" class="form-control" id="telefono" name="telefono" value="<c:out value='${usuario.telefono}'/>" required>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="direccion" class="form-label">Dirección</label>
                    <input type="text" class="form-control" id="direccion" name="direccion" value="<c:out value='${usuario.direccion}'/>" required>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="genero" class="form-label">Género</label>
                    <select class="form-select" id="genero" name="genero" required>
                        <option value="Masculino" ${usuario.genero == 'Masculino' ? 'selected' : ''}>Masculino</option>
                        <option value="Femenino" ${usuario.genero == 'Femenino' ? 'selected' : ''}>Femenino</option>
                        <option value="Otro" ${usuario.genero == 'Otro' ? 'selected' : ''}>Otro</option>
                    </select>
                </div>
            </div>
        </fieldset>

        <c:if test="${tipoUsuario == 'cliente'}">
            <fieldset class="border p-3 mb-3">
                <legend class="w-auto px-2">Datos de Cliente</legend>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="tipoCliente" class="form-label">Tipo de Cliente</label>
                        <input type="text" class="form-control" id="tipoCliente" name="tipoCliente" value="<c:out value='${usuario.tipoCliente}'/>">
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="historial" class="form-label">Historial</label>
                        <input type="text" class="form-control" id="historial" name="historial" value="<c:out value='${usuario.historial}'/>">
                    </div>
                </div>
            </fieldset>
        </c:if>

        <c:if test="${tipoUsuario == 'vendedor'}">
            <fieldset class="border p-3 mb-3">
                <legend class="w-auto px-2">Datos de Vendedor</legend>
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="rango" class="form-label">Rango</label>
                        <input type="text" class="form-control" id="rango" name="rango" value="<c:out value='${usuario.rango}'/>">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="horario" class="form-label">Horario</label>
                        <input type="text" class="form-control" id="horario" name="horario" value="<c:out value='${usuario.horario}'/>">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="sueldo" class="form-label">Sueldo</label>
                        <input type="text" class="form-control" id="sueldo" name="sueldo" value="<c:out value='${usuario.sueldo}'/>">
                    </div>
                </div>
            </fieldset>
        </c:if>

        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        <a href="UsuarioServlet?action=listar" class="btn btn-secondary">Cancelar</a>
    </form>
</c:if>

<c:if test="${empty usuario}">
    <div class="alert alert-danger" role="alert">
        No se encontró el usuario especificado.
    </div>
    <a href="UsuarioServlet?action=listar" class="btn btn-primary">Volver al Listado</a>
</c:if>

<jsp:include page="lib/footer.jsp" />
