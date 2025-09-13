package com.example.clasificados1;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InicializaSitio implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) { }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SitioClasificado sitio = new SitioClasificado();

        sitio.setNombre(sce.getServletContext().getInitParameter("nombreSitio"));
        sitio.setEmail(sce.getServletContext().getInitParameter("email"));
        sitio.setTelefono(sce.getServletContext().getInitParameter("telefono"));

        ServletContext contexto = sce.getServletContext();
        contexto.setAttribute("sitio", sitio);
    }
}
