package com.corenetwork.presentacion;

import java.io.*;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el nombre del fichero de origen (ej. origen.txt): ");
        String nombreFicheroOrigen = scanner.nextLine();

        System.out.print("Introduce el nombre del fichero de destino (ej. cifrado.txt): ");
        String nombreFicheroDestino = scanner.nextLine();

        System.out.print("Introduce un valor entre 1 y 255 para el cifrado: ");
        int valorCifrado = scanner.nextInt();

        if (valorCifrado < 1 || valorCifrado > 255) {
            System.out.println("El valor debe estar entre 1 y 255.");
            return;
        }

        try {
            cifrarFichero(nombreFicheroOrigen, nombreFicheroDestino, valorCifrado);
            System.out.println("Fichero cifrado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cifrar el fichero: " + e.getMessage());
        }
    }

    private static void cifrarFichero(String nombreFicheroOrigen, String nombreFicheroDestino, int valorCifrado) throws IOException {
        File ficheroOrigen = new File(nombreFicheroOrigen);
        File ficheroDestino = new File(nombreFicheroDestino);

        try (
                FileInputStream fis = new FileInputStream(ficheroOrigen);
                FileOutputStream fos = new FileOutputStream(ficheroDestino)
        ) {
            int byteLeido;

            while ((byteLeido = fis.read()) != -1) {
                int byteCifrado = byteLeido ^ valorCifrado;
                fos.write(byteCifrado);
            }
        }
    }
}
