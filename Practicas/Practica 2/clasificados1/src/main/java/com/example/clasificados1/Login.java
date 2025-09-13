package com.example.clasificados1;

import jakarta.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends jakarta.servlet.http.HttpServlet {
    private List<Usuario> usuarios;

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

    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException {
        String username = request.getParameter("Usuario");
        String password = request.getParameter("Clave");

        String perfil = autenticar(username, password);

        if (perfil != null) {
            // guardo el perfil como atributo del request
            request.setAttribute("perfil", perfil);

            // forward hacia el servlet Menú
            request.getRequestDispatcher("/Menu").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/error.html");
        }

    }
}