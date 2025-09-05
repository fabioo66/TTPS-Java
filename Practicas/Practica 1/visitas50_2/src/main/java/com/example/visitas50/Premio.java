package com.example.visitas50;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Premio", value = "/Premio")
public class Premio extends jakarta.servlet.http.HttpServlet {
    private int contadorVisitantes = 0;
    private String message = "¡Felicitaciones @! eres el visitante número # de nuestro sitio y has sido\n" +
            "seleccionado para el gran premio TTPS - Cursada APROBADA";
    private String ultimoUsuario;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        contadorVisitantes++;

        PrintWriter out = response.getWriter();

        String customMessage = this.message.replace("@", request.getParameter("nombre")).replace("#", String.valueOf(this.contadorVisitantes));
        this.ultimoUsuario = request.getParameter("nombre");

        out.println("<!DOCTYPE html>");
        out.println("<html><body>");
        out.println("<p>" + customMessage + "</p>");
        out.println("</body></html>");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formato = request.getParameter("formato");

        if ("json".equalsIgnoreCase(formato)) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println("{");
            out.println("\"ultimoUsuario\": \"" + (ultimoUsuario != null ? ultimoUsuario : "") + "\",");
            out.println("\"contadorVisitantes\": " + contadorVisitantes);
            out.println("}");
            out.close();
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Último usuario: " + (ultimoUsuario != null ? ultimoUsuario : "Nadie aún") + "</h2>");
            out.println("<p>Visitas: " + contadorVisitantes + "</p>");
            out.println("</body></html>");
            out.close();
        }
    }
}
