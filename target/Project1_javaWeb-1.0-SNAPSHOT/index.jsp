<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="lib/header.jsp" />

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h3 class="text-center">Bienvenido a SoftHub Solutions</h3>
                </div>
                <div class="card-body">
                    <form action="UsuarioServlet?action=login" method="POST">
                        <div class="mb-3">
                            <label for="cedula" class="form-label">Cédula</label>
                            <input type="text" class="form-control" id="cedula" name="cedula" required>
                        </div>
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
                            <a href="registroUsuario.jsp" class="btn btn-secondary">Registrarse</a>
                        </div>
                    </form>
                </div>
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null) {
                %>
                <div class="card-footer text-center">
                    <p class="text-danger"><%= error %></p>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>

<jsp:include page="lib/footer.jsp" />