<%@ page import="Modelo.Persona" %>
<%@ page import="Modelo.Vendedor" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SoftHub Solutions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="styles/style.css?v=1.1" rel="stylesheet">
</head>
<body>

<%
    Persona usuario = (Persona) session.getAttribute("usuario");
    String rol = (String) session.getAttribute("rol");
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="d-flex align-items-center">
            <img src="images/SF.png" alt="SoftHub Logo" class="me-2" style="height: 40px; width: auto;">
            <a class="navbar-brand" href="#">SoftHub Solutions</a>
        </div>

        <% if (usuario != null && "vendedor".equals(rol)) { %>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="home.jsp">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ProductoServlet?action=listado">Listar Productos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="adminProductos.jsp">Añadir Producto</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="registroUsuario.jsp">Registrar Usuario</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="UsuarioServlet?action=listar">Lista de Usuarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="FacturaServlet?action=mostrarFormulario">Generar Factura</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="FacturaServlet?action=listar">Listar Facturas</a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="UsuarioServlet?action=logout">Cerrar Sesión</a>
                </li>
            </ul>
        </div>
        <% } else if (usuario != null && "cliente".equals(rol)) { %>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="UsuarioServlet?action=logout">Cerrar Sesión</a>
                </li>
            </ul>
        <% } %>
    </div>
</nav>

<div class="container mt-4">