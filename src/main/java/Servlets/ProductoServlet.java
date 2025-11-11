package Servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import Modelo.Aplicacion;
import Modelo.GestionApps;
import Modelo.GestionSistemas;
import Modelo.Sistema;
import Modelo.Software;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {

    private GestionApps gestionApps;
    private GestionSistemas gestionSistemas;

    @Override
    public void init() throws ServletException {
        // === Inicializar y vincular las clases de gestión ===
        this.gestionApps = (GestionApps) getServletContext().getAttribute("gestionApps");
        this.gestionSistemas = (GestionSistemas) getServletContext().getAttribute("gestionSistemas");

        // --- Definir rutas para guardar los archivos ---
        String rutaBase = getServletContext().getRealPath("/data/");
        String rutaApps = rutaBase + "apps.txt";
        String rutaSistemas = rutaBase + "sistemas.txt";

        if (this.gestionApps == null) {
            this.gestionApps = new GestionApps(rutaApps);
            getServletContext().setAttribute("gestionApps", this.gestionApps);
            System.out.println("GestionApps inicializada en: " + rutaApps);
        }

        if (this.gestionSistemas == null) {
            this.gestionSistemas = new GestionSistemas(rutaSistemas);
            getServletContext().setAttribute("gestionSistemas", this.gestionSistemas);
            System.out.println("GestionSistemas inicializada en: " + rutaSistemas);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String codigo = request.getParameter("codigo");

        // === LISTAR (por defecto o al regresar de una operación) ===
        if (action == null || "listado".equals(action)) {
            LinkedList<Aplicacion> apps = gestionApps.listarApps();
            LinkedList<Sistema> sistemas = gestionSistemas.listarSistema();

            LinkedList<Software> productos = new LinkedList<>();
            productos.addAll(apps);
            productos.addAll(sistemas);

            request.setAttribute("productos", productos);
            request.getRequestDispatcher("listaProductos.jsp").forward(request, response);
            return;
        }

        // === OTRAS ACCIONES ===
        switch (action) {
            case "eliminar":
                boolean eliminado = gestionApps.eliminarApp(codigo);
                if (!eliminado) {
                    gestionSistemas.eliminarSistema(codigo);
                }
                response.sendRedirect("ProductoServlet");
                break;

            case "editar":
                Software productoAEditar = gestionApps.buscarPorCodigo(codigo);
                if (productoAEditar == null) {
                    productoAEditar = gestionSistemas.buscarPorCodigo(codigo);
                }

                if (productoAEditar != null) {
                    request.setAttribute("producto", productoAEditar);
                    request.getRequestDispatcher("editarProducto.jsp").forward(request, response);
                } else {
                    response.sendRedirect("ProductoServlet");
                }
                break;

            case "filtrar":
                Software productoFiltrado = gestionApps.buscarPorCodigo(codigo);
                if (productoFiltrado == null) {
                    productoFiltrado = gestionSistemas.buscarPorCodigo(codigo);
                }

                List<Software> listaFiltrada = new ArrayList<>();
                if (productoFiltrado != null) {
                    listaFiltrada.add(productoFiltrado);
                }

                request.setAttribute("productos", listaFiltrada);
                request.getRequestDispatcher("listaProductos.jsp").forward(request, response);
                break;

            default:
                response.sendRedirect("ProductoServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String tipoProducto = request.getParameter("tipo");

        // --- Datos comunes ---
        String nombreSoftware = request.getParameter("nombreSoftware");
        String version = request.getParameter("version");
        String codigo = request.getParameter("codigo");
        int stock = 0;
        double precio = 0.0;
        boolean obsoleto = "true".equals(request.getParameter("obsoleto"));

        try {
            stock = Integer.parseInt(request.getParameter("stock"));
            precio = Double.parseDouble(request.getParameter("precio"));
        } catch (NumberFormatException e) {
            System.err.println("Error de formato numérico: " + e.getMessage());
        }

        // === ACTUALIZAR ===
        if ("update".equals(action)) {
            String codigoOriginal = request.getParameter("codigoOriginal");

            if ("Aplicación".equalsIgnoreCase(tipoProducto)) {
                String juegos = request.getParameter("juegos");
                String navegadorWeb = request.getParameter("navegadorWeb");
                String diseño = request.getParameter("diseño");
                Aplicacion appActualizada = new Aplicacion(nombreSoftware, version, "Aplicación", codigo, stock, precio, obsoleto, juegos, navegadorWeb, diseño);
                gestionApps.editarApp(codigoOriginal, appActualizada);
                gestionApps.guardarDatos();

            } else if ("Sistema Operativo".equalsIgnoreCase(tipoProducto)) {
                String macOs = request.getParameter("macOs");
                String windows = request.getParameter("windows");
                String linux = request.getParameter("linux");
                Sistema sistemaActualizado = new Sistema(nombreSoftware, version, "Sistema Operativo", codigo, stock, precio, obsoleto, macOs, windows, linux);
                gestionSistemas.editarSistema(codigoOriginal, sistemaActualizado);
                gestionSistemas.guardarDatos();
            }

            response.sendRedirect("ProductoServlet");
            return;
        }

        // === CREAR ===
        try {
            if ("aplicacion".equalsIgnoreCase(tipoProducto)) {
                String juegos = request.getParameter("juegos");
                String navegadorWeb = request.getParameter("navegadorWeb");
                String diseño = request.getParameter("diseño");

                Aplicacion nuevaApp = new Aplicacion(nombreSoftware, version, "Aplicación", codigo, stock, precio, obsoleto, juegos, navegadorWeb, diseño);
                gestionApps.agregarApp(nuevaApp);
                gestionApps.guardarDatos();

            } else if ("sistema".equalsIgnoreCase(tipoProducto)) {
                String macOs = request.getParameter("macOs");
                String windows = request.getParameter("windows");
                String linux = request.getParameter("linux");

                Sistema nuevoSistema = new Sistema(nombreSoftware, version, "Sistema Operativo", codigo, stock, precio, obsoleto, macOs, windows, linux);
                gestionSistemas.agregarSistema(nuevoSistema);
                gestionSistemas.guardarDatos();
            }
        } catch (Exception e) {
            System.err.println("Error al crear producto: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Error al crear el producto: " + e.getMessage());
            request.getRequestDispatcher("adminProductos.jsp").forward(request, response);
            return;
        }

        response.sendRedirect("ProductoServlet");
    }
}
