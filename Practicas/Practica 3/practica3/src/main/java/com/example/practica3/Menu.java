package com.example.practica3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;

import java.io.PrintWriter;

@WebServlet(name = "Menu", urlPatterns = {"/Menu"})
public class Menu extends jakarta.servlet.http.HttpServlet {

    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException {
        String perfil = (String) request.getAttribute("perfil");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html><body>");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Encabezado");
        if (dispatcher != null){
            dispatcher.include(request, response);
        }

        out.println("<h1>Menu del perfil " + perfil + "</h1>");
        out.println("<ul>");


        if ("administrador".equals(perfil)) {
            out.println("<li>Listar Usuarios Publicadores</li>");
            out.println("<li>ABM Administradores</li>");
            out.println("<li>Ver Estadísticas</li>");
        } else if ("publicador".equals(perfil)) {
            out.println("<li>Actualizar Datos de Contacto</li>");
            out.println("<li>ABM de Publicaciones</li>");
            out.println("<li>Contestar Consultas</li>");
        }

        out.println("</ul>");
        out.println("<a href='login.html'>Cerrar sesión</a>");
        out.println("</body></html>");
    }
}