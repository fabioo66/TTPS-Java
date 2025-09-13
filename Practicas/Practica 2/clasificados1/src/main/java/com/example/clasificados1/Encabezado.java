package com.example.clasificados1;
import jakarta.servlet.annotation.WebServlet;

import java.io.PrintWriter;

@WebServlet(name = "Encabezado", urlPatterns = {"/Encabezado"})
public class Encabezado extends jakarta.servlet.http.HttpServlet {

    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException {

        SitioClasificado sitio = (SitioClasificado) request.getServletContext().getAttribute("sitio");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<header>");
        out.println("<h1>" + sitio.getNombre() + "</h1>");
        out.println("<p>Contáctenos: " + sitio.getEmail() + " - " + sitio.getTelefono() + "</p>");
        out.println("</header>");
    }
}
