package com.example.clasificados1;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ThreadLocalRandom;

@WebServlet("/ImprimeCupon")
public class ImprimeCupon extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String texto = request.getParameter("texto");
        if (texto == null || texto.isBlank()) {
            texto = "Festival Capital!";
        }

        int codigo = ThreadLocalRandom.current().nextInt(1000000, 99999999);

        // Creo un lienzo del tamaño que quieras (por ej. 800x600)
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // Fondo blanco
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 800, 600);

        // Cargo la imagen base (remera)
        BufferedImage remera = ImageIO.read(
                getServletContext().getResourceAsStream("/WEB-INF/remera.jpg")
        );
        if (remera != null) {
            g.drawImage(remera, 50, 50, null);
        }

        // Dibujo el texto en la remera
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString(texto, 100, 200);

        // Dibujo el código de retiro
        g.setFont(new Font("Arial", Font.PLAIN, 22));
        g.drawString("Código: " + codigo, 100, 250);

        g.dispose();

        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.close();
    }
}
