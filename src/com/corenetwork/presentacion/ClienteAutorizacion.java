package com.corenetwork.presentacion;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteAutorizacion {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Introduce la fecha de la transacción (AAAAMMDD): ");
            String fechaTransaccion = scanner.nextLine();

            System.out.print("Introduce el número de tarjeta (16 dígitos): ");
            String numTarjeta = scanner.nextLine();

            System.out.print("Introduce la fecha de vencimiento (AAAAMMDD): ");
            String fechaVencimiento = scanner.nextLine();

            System.out.print("Introduce el CVV (3 dígitos): ");
            String cvv = scanner.nextLine();

            System.out.print("Introduce la cantidad: ");
            String cantidad = scanner.nextLine();

            String solicitud = fechaTransaccion + "," + numTarjeta + "," + fechaVencimiento + "," + cvv + "," + cantidad;
            writer.println(solicitud);

            String respuesta = reader.readLine();
            System.out.println("Respuesta del servidor:\n" + respuesta);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
