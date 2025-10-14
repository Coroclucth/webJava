package Servlets;

import Modelo.Aplicacion;
import Modelo.Cliente;
import Modelo.Factura;
import Modelo.GestionApps;
import Modelo.GestionFacturas;
import Modelo.GestionSistemas;
import Modelo.GestionUsuarios;
import Modelo.Sistema;
import Modelo.Software;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "FacturaServlet", urlPatterns = {"/FacturaServlet"})
public class FacturaServlet extends HttpServlet {

    private GestionFacturas gestionFacturas;
    private GestionUsuarios gestionUsuarios;
    private GestionApps gestionApps;
    private GestionSistemas gestionSistemas;

    @Override
    public void init() throws ServletException {
        this.gestionFacturas = (GestionFacturas) getServletContext().getAttribute("gestionFacturas");
        this.gestionUsuarios = (GestionUsuarios) getServletContext().getAttribute("gestionUsuarios");
        this.gestionApps = (GestionApps) getServletContext().getAttribute("gestionApps");
        this.gestionSistemas = (GestionSistemas) getServletContext().getAttribute("gestionSistemas");
    }

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

        } else if ("listar".equals(action)) {
            List<Factura> facturas = gestionFacturas.listarFacturas();
            request.setAttribute("facturas", facturas);
            request.getRequestDispatcher("listaFacturas.jsp").forward(request, response);
        } else {
            // Por defecto, listar facturas
            List<Factura> facturas = gestionFacturas.listarFacturas();
            request.setAttribute("facturas", facturas);
            request.getRequestDispatcher("listaFacturas.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clienteCedula = request.getParameter("cliente");
        String[] productosSeleccionados = request.getParameterValues("productos");

        Cliente cliente = gestionUsuarios.buscarClientePorCedula(clienteCedula);

        LinkedList<Aplicacion> appsCompradas = new LinkedList<>();
        LinkedList<Sistema> sistemasComprados = new LinkedList<>();
        double total = 0;

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

        // El número de factura se asigna en GestionFacturas
        Factura nuevaFactura = new Factura(0, cliente, total, appsCompradas, sistemasComprados);
        gestionFacturas.agregarFactura(nuevaFactura);

        response.sendRedirect("FacturaServlet?action=listar");
    }
}
