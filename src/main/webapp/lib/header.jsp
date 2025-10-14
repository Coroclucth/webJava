<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SoftHub Solutions</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #1a1d20;
            color: #e9ecef;
            min-height: 100vh;
            padding-bottom: 100px;
            position: relative;
        }
        .container {
            background-color: #2c3034;
            border-radius: 8px;
            padding: 20px;
            margin-top: 2rem;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
        }
        .table {
            color: #e9ecef;
        }
        .table-striped > tbody > tr:nth-of-type(odd) {
            background-color: #343a40;
        }
        .table-hover tbody tr:hover {
            background-color: #495057;
            color: #fff;
        }
        .form-control, .form-select {
            background-color: #343a40;
            border-color: #495057;
            color: #e9ecef;
        }

        .form-control::placeholder {
            color: #adb5bd;
            opacity: 1; 
        }
        .form-control:focus, .form-select:focus {
            background-color: #3d444a;
            border-color: #565e64;
            color: #e9ecef;
            box-shadow: 0 0 0 0.25rem rgba(66, 70, 73, 0.5);
        }
        .card {
            background-color: #2c3034;
            border-color: #495057;
        }
        .bg-light {
            background-color: #2d3436 !important;
        }
        .rounded-3 {
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border: 1px solid #404549;
        }
        .display-5 {
            color: #00bc8c !important;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
        }
        .fs-4 {
            color: #e9ecef !important;
            font-weight: 300;
            letter-spacing: 0.3px;
        }
        .btn-primary {
            background-color: #00bc8c;
            border-color: #00bc8c;
            transition: all 0.3s ease;
        }
        .btn-primary:hover {
            background-color: #009b75;
            border-color: #008c69;
            transform: translateY(-2px);
        }
        .footer {
            background-color: #2c3034 !important;
            border-top: 1px solid #495057;
            position: absolute;
            bottom: 0;
            width: 100%;
            color: #e9ecef;
        }
        .text-muted {
            color: #adb5bd !important;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="d-flex align-items-center">
            <img src="images/SF.png" alt="SoftHub Logo" class="me-2" style="height: 40px; width: auto;">
            <a class="navbar-brand" href="index.jsp">SoftHub Solutions</a>
        </div>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Inicio</a>
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
        </div>
    </div>
</nav>

<div class="container mt-4">