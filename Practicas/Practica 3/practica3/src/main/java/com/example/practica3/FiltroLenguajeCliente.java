package com.example.practica3;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*") // se aplica a todas las URLs del proyecto
public class FiltroLenguajeCliente implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // no hace falta inicializar nada
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        // Leer el encabezado Accept-Language
        String acceptLanguage = req.getHeader("Accept-Language");

        String lenguaje = "es"; // por defecto Español

        if (acceptLanguage != null) {
            // ejemplo de valor: "es-ES,es;q=0.9,en;q=0.8"
            if (acceptLanguage.toLowerCase().startsWith("en")) {
                lenguaje = "en";
            } else if (acceptLanguage.toLowerCase().startsWith("es")) {
                lenguaje = "es";
            }
            // se pueden agregar más idiomas acá
        }

        // Guardar como atributo del request
        request.setAttribute("lenguaje", lenguaje);

        // continuar la cadena de filtros/servlets
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // nada que destruir
    }
}
