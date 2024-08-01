package com.corenetwork.presentacion;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServidorAutorizacion {
    private static final String[] CODIGOS_AUTORIZACION = {"0000", "9137", "9221", "9677"};
    private static final String[] DESCRIPCIONES = {
            "Transacción aceptada",
            "Error al intentar validar la tarjeta",
            "El cliente no está introduciendo el CVV",
            "Saldo insuficiente"
    };

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Servidor de autorización iniciado...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                    String solicitud = reader.readLine();
                    System.out.println("Solicitud recibida: " + solicitud);

                    String respuesta = procesarSolicitud(solicitud);
                    System.out.println("Respuesta: \n" + respuesta);

                    writer.println(respuesta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String procesarSolicitud(String solicitud) {
        String[] partes = solicitud.split(",");
        if (partes.length != 5) {
            return "Solicitud inválida";
        }

        String fechaTransaccion = partes[0];
        String numTarjeta = partes[1];
        String fechaVencimiento = partes[2];
        String cvv = partes[3];
        String cantidad = partes[4];

        Random random = new Random();
        int codigoInterno = random.nextInt(4);

        String codigoAutorizacion = CODIGOS_AUTORIZACION[codigoInterno];
        String descripcion = DESCRIPCIONES[codigoInterno];

        String fechaFormateada = fechaTransaccion.substring(0, 4) + "/" +
                fechaTransaccion.substring(4, 6) + "/" +
                fechaTransaccion.substring(6, 8);

        return String.format(
                "Fecha de la Transacción: %s\n" +
                        "Código de Autorización: %s\n" +
                        "Descripción: %s\n" +
                        "Número de Tarjeta: %s",
                fechaFormateada, codigoAutorizacion, descripcion, numTarjeta
        );
    }
}
