<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="lib/header.jsp" />

<div class="p-5 mb-4 bg-light rounded-3">
    <div class="container-fluid py-5">
        <h1 class="display-5 fw-bold">Bienvenido a SoftHub Solutions</h1>
        <p class="col-md-8 fs-4">Utilice la barra de navegación para gestionar los productos y usuarios del sistema.</p>
        <a href="registroUsuario.jsp" class="btn btn-primary btn-lg">Registrarse</a>
        <a href="ProductoServlet?action=listado" class="btn btn-primary btn-lg">Ver Productos</a>
    </div>
</div>

<jsp:include page="lib/footer.jsp" />
