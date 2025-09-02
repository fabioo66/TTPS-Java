package com.example.visitas50;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Premio extends jakarta.servlet.http.HttpServlet {
    private int contadorVisitantes = 0;
    private String message = "¡Felicitaciones @! eres el visitante número # de nuestro sitio y has sido\n" +
            "seleccionado para el gran premio TTPS - Cursada APROBADA";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        contadorVisitantes++;

        PrintWriter out = response.getWriter();

        String customMessage = this.message.replace("@", request.getParameter("nombre")).replace("#", String.valueOf(this.contadorVisitantes));

        out.println("<!DOCTYPE html>");
        out.println("<html><body>");
        out.println("<p>" + customMessage + "</p>");
        out.println("</body></html>");
        out.close();
    }
}
