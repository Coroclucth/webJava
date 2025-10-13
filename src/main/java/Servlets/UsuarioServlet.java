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

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private GestionUsuarios gestionUsuarios;

    @Override
    public void init() throws ServletException {
        // Conectar con el gestor de usuarios del ServletContext
        this.gestionUsuarios = (GestionUsuarios) getServletContext().getAttribute("gestionUsuarios");
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
            case "eliminar":
                gestionUsuarios.eliminarUsuarioPorCedula(cedula);
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
        String tipoUsuario = request.getParameter("tipoUsuario");

        // --- Captura de datos comunes (Persona) ---
        String nombre = request.getParameter("nombre");
        String cedula = request.getParameter("cedula");
        String correo = request.getParameter("correo");
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
                Cliente clienteActualizado = new Cliente(nombre, edad, cedula, correo, tipoCliente, historial);
                gestionUsuarios.editarCliente(cedulaOriginal, clienteActualizado);

            } else if ("vendedor".equalsIgnoreCase(tipoUsuario)) {
                String rango = request.getParameter("rango");
                String horario = request.getParameter("horario");
                String sueldo = request.getParameter("sueldo");
                Vendedor vendedorActualizado = new Vendedor(nombre, edad, cedula, correo, rango, horario, sueldo);
                gestionUsuarios.editarVendedor(cedulaOriginal, vendedorActualizado);
            }
            response.sendRedirect("UsuarioServlet?action=listar");
            return;
        }

        // --- Lógica de CREACIÓN ---
        if ("cliente".equalsIgnoreCase(tipoUsuario)) {
            String tipoCliente = request.getParameter("tipoCliente");
            String historial = request.getParameter("historial");
            Cliente nuevoCliente = new Cliente(nombre, edad, cedula, correo, tipoCliente, historial);
            gestionUsuarios.agregarCliente(nuevoCliente);

        } else if ("vendedor".equalsIgnoreCase(tipoUsuario)) {
            String rango = request.getParameter("rango");
            String horario = request.getParameter("horario");
            String sueldo = request.getParameter("sueldo");
            Vendedor nuevoVendedor = new Vendedor(nombre, edad, cedula, correo, rango, horario, sueldo);
            gestionUsuarios.agregarVendedor(nuevoVendedor);
        }

        response.sendRedirect("UsuarioServlet?action=listar");
    }
}