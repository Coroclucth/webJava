package Servlets;

import Modelo.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Servlet para gestionar las operaciones relacionadas con las facturas.
@WebServlet(name = "FacturaServlet", urlPatterns = {"/FacturaServlet"})
public class FacturaServlet extends HttpServlet {

    // Gestores para manejar la lógica de negocio de facturas, usuarios y productos.
    private GestionFacturas gestionFacturas;
    private GestionUsuarios gestionUsuarios;
    private GestionApps gestionApps;
    private GestionSistemas gestionSistemas;

    // Inicialización del servlet.
    @Override
    public void init() throws ServletException {
        // Obtener las rutas de los archivos de datos desde el contexto del servlet.
        String rutaUsuarios = getServletContext().getRealPath("/data/usuarios.txt");
        String rutaApps = getServletContext().getRealPath("/data/apps.txt");
        String rutaSistemas = getServletContext().getRealPath("/data/sistemas.txt");
        String rutaFacturas = getServletContext().getRealPath("/data/facturas.txt");


        this.gestionUsuarios = new GestionUsuarios(rutaUsuarios);
        this.gestionApps = new GestionApps(rutaApps);
        this.gestionSistemas = new GestionSistemas(rutaSistemas);

        this.gestionFacturas = new GestionFacturas(rutaFacturas, this.gestionApps, this.gestionSistemas);

        // Guardar todas las instancias de gestión en el contexto del servlet para que estén disponibles en toda la aplicación.
        getServletContext().setAttribute("gestionUsuarios", this.gestionUsuarios);
        getServletContext().setAttribute("gestionApps", this.gestionApps);
        getServletContext().setAttribute("gestionSistemas", this.gestionSistemas);
        getServletContext().setAttribute("gestionFacturas", this.gestionFacturas);
    }

    // Maneja las solicitudes GET.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");


        if ("mostrarFormulario".equals(action)) {
            List<Cliente> clientes = gestionUsuarios.listarClientes();
            List<Aplicacion> apps = gestionApps.listarApps();
            List<Sistema> sistemas = gestionSistemas.listarSistema();
            List<Software> productos = new LinkedList<>();
            productos.addAll(apps);
            productos.addAll(sistemas);

            request.setAttribute("clientes", clientes);
            request.setAttribute("productos", productos);
            request.getRequestDispatcher("generarFactura.jsp").forward(request, response);

        } else {
            List<Factura> facturas = gestionFacturas.listarFacturas();
            request.setAttribute("facturas", facturas);
            request.getRequestDispatcher("listaFacturas.jsp").forward(request, response);
        }
    }

    // Maneja las solicitudes POST.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtengo los datos del formulario de nueva factura.
        String clienteCedula = request.getParameter("cliente");
        String[] productosSeleccionados = request.getParameterValues("productos");

        Cliente cliente = gestionUsuarios.buscarClientePorCedula(clienteCedula);

        LinkedList<Aplicacion> appsCompradas = new LinkedList<>();
        LinkedList<Sistema> sistemasComprados = new LinkedList<>();
        double total = 0;

        // Proceso los productos seleccionados para agregarlos a la factura.
        if (productosSeleccionados != null) {
            for (String codigoProducto : productosSeleccionados) {
                Software producto = gestionApps.buscarPorCodigo(codigoProducto);
                if (producto == null) {
                    producto = gestionSistemas.buscarPorCodigo(codigoProducto);
                }

                if (producto != null) {
                    if (producto instanceof Aplicacion) {
                        appsCompradas.add((Aplicacion) producto);
                    } else if (producto instanceof Sistema) {
                        sistemasComprados.add((Sistema) producto);
                    }
                    total += producto.getPrecio();
                }
            }
        }

        // Creo y guardo la nueva factura.
        Factura nuevaFactura = new Factura(0, cliente, total, appsCompradas, sistemasComprados);
        gestionFacturas.agregarFactura(nuevaFactura);

        // Redirijo al usuario a la lista de facturas.
        response.sendRedirect("FacturaServlet?action=listar");
    }
}



