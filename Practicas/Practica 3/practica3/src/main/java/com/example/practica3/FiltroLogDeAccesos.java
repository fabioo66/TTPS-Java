package com.example.practica3;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.time.ZonedDateTime;

@WebFilter(filterName = "FiltroLogDeAccesos" , urlPatterns = "/*")
public class FiltroLogDeAccesos implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;

        String ip = req.getRemoteAddr();
        String fecha = ZonedDateTime.now().toString();
        String method = req.getMethod();
        String recurso = req.getRequestURI();
        String protocolo = req.getProtocol();
        String userAgent = req.getHeader("User-Agent");

        System.out.printf("IP: %s - Fecha: %s - Metodo: %s - Recurso: %s - Protocolo: %s - Navegador: %s%n",
                ip, fecha, method, recurso, protocolo, userAgent);

        chain.doFilter(request, response);
    }
}