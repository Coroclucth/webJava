package Servlets;

import Modelo.Cliente;
import Modelo.GestionUsuarios;
import Modelo.Persona;
import Modelo.Vendedor;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private GestionUsuarios gestionUsuarios;

    @Override
    public void init() throws ServletException {
        // Intentar obtener el gestor de usuarios desde el contexto
        this.gestionUsuarios = (GestionUsuarios) getServletContext().getAttribute("gestionUsuarios");

        if (this.gestionUsuarios == null) {
            String rutaData = getServletContext().getRealPath("/data/usuarios.txt");
            this.gestionUsuarios = new GestionUsuarios(rutaData);
            getServletContext().setAttribute("gestionUsuarios", this.gestionUsuarios);
            System.out.println("✅ GestionUsuarios inicializado desde UsuarioServlet en: " + rutaData);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String cedula = request.getParameter("cedula");

        if (action == null || "listar".equals(action)) {
            // Combinar listas de clientes y vendedores para mostrarlas
            List<Persona> usuarios = new LinkedList<>();
            usuarios.addAll(gestionUsuarios.listarClientes());
            usuarios.addAll(gestionUsuarios.listarVendedores());
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("listaUsuarios.jsp").forward(request, response);
            return;
        }

        switch (action) {
            case "logout":
                HttpSession session = request.getSession(false); 
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect("index.jsp");
                break;
            case "eliminar":
                gestionUsuarios.eliminarUsuarioPorCedula(cedula);
                gestionUsuarios.guardarDatos();
                response.sendRedirect("UsuarioServlet?action=listar");
                break;

            case "editar":
                Persona usuarioAEditar = gestionUsuarios.buscarUsuarioPorCedula(cedula);
                if (usuarioAEditar != null) {
                    request.setAttribute("usuario", usuarioAEditar);
                    // Determinar el tipo para la vista de edición
                    if (usuarioAEditar instanceof Cliente) {
                        request.setAttribute("tipoUsuario", "cliente");
                    } else {
                        request.setAttribute("tipoUsuario", "vendedor");
                    }
                    request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
                } else {
                    response.sendRedirect("UsuarioServlet?action=listar");
                }
                break;

            default:
                response.sendRedirect("UsuarioServlet?action=listar");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // --- Lógica de INICIO DE SESIÓN ---
        if ("login".equals(action)) {
            String cedulaLogin = request.getParameter("cedula");
            Persona usuario = gestionUsuarios.buscarUsuarioPorCedula(cedulaLogin);
            HttpSession session = request.getSession();

            if (usuario != null) {
                session.setAttribute("usuario", usuario);
                if (usuario instanceof Vendedor) {
                    session.setAttribute("rol", "vendedor");
                    response.sendRedirect("home.jsp");
                } else {
                    session.setAttribute("rol", "cliente");
                    response.sendRedirect("FacturaServlet?action=mostrarFormulario");
                }
            } else {
                request.setAttribute("error", "Cédula no encontrada. Por favor, regístrese.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            return; // Detener la ejecución aquí para el login
        }

        String tipoUsuario = request.getParameter("tipoUsuario");

        // --- Captura de datos---
        String nombre = request.getParameter("nombre");
        String cedula = request.getParameter("cedula");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String genero = request.getParameter("genero");

        int edad = 0;
        try {
            edad = Integer.parseInt(request.getParameter("edad"));
        } catch (NumberFormatException e) {
            System.err.println("Error de formato en edad: " + e.getMessage());
        }

        // --- Lógica de ACTUALIZACIÓN ---
        if ("update".equals(action)) {
            String cedulaOriginal = request.getParameter("cedulaOriginal");

            if ("cliente".equalsIgnoreCase(tipoUsuario)) {
                String tipoCliente = request.getParameter("tipoCliente");
                String historial = request.getParameter("historial");
                Cliente clienteActualizado = new Cliente(nombre, edad, cedula, correo, telefono, genero, direccion, tipoCliente, historial);
                gestionUsuarios.editarCliente(cedulaOriginal, clienteActualizado);

            } else if ("vendedor".equalsIgnoreCase(tipoUsuario)) {
                String rango = request.getParameter("rango");
                String horario = request.getParameter("horario");
                String sueldo = request.getParameter("sueldo");
                Vendedor vendedorActualizado = new Vendedor(nombre, edad, cedula, correo, telefono, genero, direccion, rango, horario, sueldo);
                gestionUsuarios.editarVendedor(cedulaOriginal, vendedorActualizado);
            }

            gestionUsuarios.guardarDatos(); // Guardar cambios en archivo
            response.sendRedirect("UsuarioServlet?action=listar");
            return;
        }

        // --- Lógica de CREACIÓN ---
        if ("cliente".equalsIgnoreCase(tipoUsuario)) {
            String tipoCliente = request.getParameter("tipoCliente");
            String historial = request.getParameter("historial");
            Cliente nuevoCliente = new Cliente(nombre, edad, cedula, correo, telefono, genero, direccion, tipoCliente, historial);
            gestionUsuarios.agregarCliente(nuevoCliente);

        } else if ("vendedor".equalsIgnoreCase(tipoUsuario)) {
            String rango = request.getParameter("rango");
            String horario = request.getParameter("horario");
            String sueldo = request.getParameter("sueldo");
            Vendedor nuevoVendedor = new Vendedor(nombre, edad, cedula, correo, telefono, genero, direccion, rango, horario, sueldo);
            gestionUsuarios.agregarVendedor(nuevoVendedor);
        }

        // Guardar en archivo TXT después de crear
        gestionUsuarios.guardarDatos();
        response.sendRedirect("UsuarioServlet?action=listar");
    }
}
