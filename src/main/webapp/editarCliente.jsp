<%@include file="lib/header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container mt-5">
    <h1 class="mb-4">Editar Cliente: ${cliente.nombre}</h1>
    
    <form action="UsuarioServlet" method="POST"> 
        
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="cedulaOriginal" value="${cliente.cedula}">
        
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="cedula" class="form-label">Cédula</label>
                <input type="text" class="form-control" id="cedula" name="cedula" value="${cliente.cedula}" required> 
            </div>
            <div class="col-md-6 mb-3">
                <label for="nombre" class="form-label">Nombre Completo</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="${cliente.nombre}" required>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="edad" class="form-label">Edad</label>
                <input type="number" class="form-control" id="edad" name="edad" value="${cliente.edad}" required>
            </div>
            <div class="col-md-6 mb-3">
                <label for="correo" class="form-label">Correo electrónico</label>
                <input type="email" class="form-control" id="correo" name="correo" value="${cliente.correo}" required>
            </div>
        </div>

        <hr>
        <div class="row">
             <div class="col-md-6 mb-3">
                <label for="tipoCliente" class="form-label">Tipo de Cliente</label>
                <input type="text" class="form-control" id="tipoCliente" name="tipoCliente" value="${cliente.tipoCliente}" required>
            </div>
            <div class="col-md-6 mb-3">
                <label for="historial" class="form-label">Historial</label>
                <input type="text" class="form-control" id="historial" name="historial" value="${cliente.historial}" required>
            </div>
        </div>
        
        <button type="submit" class="btn btn-warning mt-3">Guardar Cambios</button>
        <a href="index.jsp" class="btn btn-secondary mt-3">Cancelar y Volver</a>
    </form>
</div>
<%@include file="lib/footer.jsp" %>