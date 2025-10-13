package Servlets;

import java.io.IOException;
import java.util.LinkedList;

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
        this.gestionApps = (GestionApps) getServletContext().getAttribute("gestionApps");
        if (this.gestionApps == null) {
            this.gestionApps = new GestionApps();
            getServletContext().setAttribute("gestionApps", this.gestionApps);
        }
        
        this.gestionSistemas = (GestionSistemas) getServletContext().getAttribute("gestionSistemas");
        if (this.gestionSistemas == null) {
            this.gestionSistemas = new GestionSistemas();
            getServletContext().setAttribute("gestionSistemas", this.gestionSistemas);
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String codigo = request.getParameter("codigo");
        
        // Lógica de Listado (por defecto o al volver de una operación)
        if (action == null || "listado".equals(action)) {
            LinkedList<Aplicacion> apps = gestionApps.listarApps();
            LinkedList<Modelo.Sistema> sistemas = gestionSistemas.listarSistema();
            LinkedList<Software> productos = new LinkedList<>();
            productos.addAll(apps);
            productos.addAll(sistemas);
            request.setAttribute("productos", productos);
            request.getRequestDispatcher("listaProductos.jsp").forward(request, response);
            return;
        }

        switch (action) {
            case "eliminar":
                // Intenta eliminar de Aplicaciones y luego de Sistemas
                if (!gestionApps.eliminarApp(codigo)) {
                    gestionSistemas.eliminarSistema(codigo);
                }
                response.sendRedirect("ProductoServlet"); // Vuelve a listar
                break;
            case "editar":
                // Busca el producto en ambas listas
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
            default:
                response.sendRedirect("ProductoServlet"); 
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String tipoProducto = request.getParameter("tipo"); // Value is different for create vs update
        
        // Captura de datos comunes (Software)
        String nombreSoftware = request.getParameter("nombreSoftware");
        String version = request.getParameter("version");
        String codigo = request.getParameter("codigo");
        
        int stock = 0;
        double precio = 0.0;
        try {
             stock = Integer.parseInt(request.getParameter("stock"));
             precio = Double.parseDouble(request.getParameter("precio"));
        } catch (NumberFormatException e) {
             System.err.println("Error de formato numérico: " + e.getMessage());
        }
        
        boolean obsoleto = "true".equals(request.getParameter("obsoleto")); 
        
        // 1. Lógica de ACTUALIZACIÓN (Edición)
        if ("update".equals(action)) {
            String codigoOriginal = request.getParameter("codigoOriginal");
            
            if ("Aplicación".equalsIgnoreCase(tipoProducto)) {
                String juegos = request.getParameter("juegos");
                String navegadorWeb = request.getParameter("navegadorWeb");
                String diseño = request.getParameter("diseño");
                Aplicacion appActualizada = new Aplicacion(nombreSoftware, version, "Aplicación", codigo, stock, precio, obsoleto, juegos, navegadorWeb, diseño);
                gestionApps.editarApp(codigoOriginal, appActualizada);

            } else if ("Sistema Operativo".equalsIgnoreCase(tipoProducto)) {
                String macOs = request.getParameter("macOs");
                String windows = request.getParameter("windows");
                String linux = request.getParameter("linux");
                Sistema sistemaActualizado = new Sistema(nombreSoftware, version, "Sistema Operativo", codigo, stock, precio, obsoleto, macOs, windows, linux);
                gestionSistemas.editarSistema(codigoOriginal, sistemaActualizado);
            }

            response.sendRedirect("ProductoServlet");
            return;
        }

        // 2. Lógica de CREACIÓN (Registro)
        try {
            if ("aplicacion".equalsIgnoreCase(tipoProducto)) {
                String juegos = request.getParameter("juegos");
                String navegadorWeb = request.getParameter("navegadorWeb");
                String diseño = request.getParameter("diseño");
                
                Aplicacion nuevaApp = new Aplicacion(nombreSoftware, version, "Aplicación", codigo, stock, precio, obsoleto, juegos, navegadorWeb, diseño);
                gestionApps.agregarApp(nuevaApp);
                
            } else if ("sistema".equalsIgnoreCase(tipoProducto)) {
                String macOs = request.getParameter("macOs");
                String windows = request.getParameter("windows");
                String linux = request.getParameter("linux");

                Sistema nuevoSistema = new Sistema(nombreSoftware, version, "Sistema Operativo", codigo, stock, precio, obsoleto, macOs, windows, linux);
                gestionSistemas.agregarSistema(nuevoSistema);
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