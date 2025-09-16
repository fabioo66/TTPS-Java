package com.example.practica3;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@WebServlet(name = "LoginMultilenguaje", urlPatterns = {"/LoginMultilenguaje"})
public class LoginMultilenguaje extends HttpServlet {

    private List<Usuario> usuarios;

    @Override
    public void init() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("fabio", "fabio123", "administrador"));
        usuarios.add(new Usuario("valen", "valen123", "publicador"));
        usuarios.add(new Usuario("juan", "juan123", "publicador"));
    }

    private String autenticar(String username, String password) {
        return this.usuarios.stream()
                .filter(u -> u.getNombreDeUsuario().equals(username) && u.getClave().equals(password))
                .findFirst()
                .map(Usuario::getPerfil)
                .orElse(null);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Recuperar el lenguaje que puso el filtro
        String lenguaje = (String) request.getAttribute("lenguaje");
        if (lenguaje == null) {
            lenguaje = "es"; // por defecto español
        }

        // Cargar el archivo de properties
        ResourceBundle textos = ResourceBundle.getBundle("textos_" + lenguaje);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Generar el formulario de login traducido
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"" + lenguaje + "\">");
        out.println("<head><meta charset=\"UTF-8\"><title>" + textos.getString("titulo") + "</title></head>");
        out.println("<body>");
        out.println("<h1>" + textos.getString("titulo") + "</h1>");
        out.println("<form action='LoginMultilenguaje' method='POST'>");
        out.println("<label>" + textos.getString("labelusuario") + "</label>");
        out.println("<input type='text' name='Usuario'><br>");
        out.println("<label>" + textos.getString("labelpassword") + "</label>");
        out.println("<input type='password' name='Clave'><br>");
        out.println("<button type='submit'>" + textos.getString("boton") + "</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, jakarta.servlet.ServletException {

        String username = request.getParameter("Usuario");
        String password = request.getParameter("Clave");

        String perfil = autenticar(username, password);

        if (perfil != null) {
            // guardar el perfil como atributo del request
            request.setAttribute("perfil", perfil);

            // forward al servlet Menu
            request.getRequestDispatcher("/Menu").forward(request, response);
        } else {
            // redirigir a una página de error
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }
}
