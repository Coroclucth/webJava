package Servlets;

import Modelo.GestionApps;
import Modelo.GestionSistemas;
import Modelo.GestionUsuarios;
import Modelo.GestionFacturas;
import Modelo.Aplicacion;
import Modelo.Sistema;
import Modelo.Cliente;
import Modelo.Vendedor;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Se ejecuta cuando la aplicación arranca
        ServletContext context = sce.getServletContext();

        // 1. Crear instancias de las clases de gestión
        GestionApps gestionApps = new GestionApps();
        GestionSistemas gestionSistemas = new GestionSistemas();
        GestionUsuarios gestionUsuarios = new GestionUsuarios();
        GestionFacturas gestionFacturas = new GestionFacturas();

        // 2. Agregar datos de prueba para que la app no empiece vacía
        // Productos de tipo Aplicacion
        gestionApps.agregarApp(new Aplicacion("Microsoft Office", "2023", "Aplicación", "APP001", 50, 150.00, false, "No", "Sí", "Sí"));
        gestionApps.agregarApp(new Aplicacion("Adobe Photoshop", "CC 2024", "Aplicación", "APP002", 30, 250.50, false, "No", "Sí", "Sí"));
        
        // Productos de tipo Sistema Operativo
        gestionSistemas.agregarSistema(new Sistema("Windows 11", "11 Home", "Sistema Operativo", "SO001", 100, 120.00, false, "No", "Sí", "No"));
        gestionSistemas.agregarSistema(new Sistema("Ubuntu", "24.04 LTS", "Sistema Operativo", "SO002", 200, 0.00, false, "Sí", "No", "Sí"));

        // Usuarios (Clientes y Vendedores)
        gestionUsuarios.agregarUsuario(new Cliente("Juan Perez", 30, "10382764", "juan.perez@email.com", "3101234567", "Masculino", "Calle 35#43-75", "Frecuente", "Ninguno"));
        gestionUsuarios.agregarUsuario(new Vendedor("Ana Lopez", 25, "87654321", "ana.lopez@email.com", "3209876543", "Femenino", "Avenida de los Estudiantes 3", "Senior", "Matutino", "1200"));

        // 3. Guardar las instancias en el ServletContext
        context.setAttribute("gestionApps", gestionApps);
        context.setAttribute("gestionSistemas", gestionSistemas);
        context.setAttribute("gestionUsuarios", gestionUsuarios);
        context.setAttribute("gestionFacturas", gestionFacturas);
        
        System.out.println(">>>> Aplicación inicializada con datos de prueba. <<<<");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
