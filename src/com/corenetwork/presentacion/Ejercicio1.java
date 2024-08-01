package com.corenetwork.presentacion;

import java.io.File;
import java.text.SimpleDateFormat;

public class Ejercicio1 {
    public static void main(String[] args) {

        String rutaFichero = "C:\\Users\\manana\\Desktop\\Jose Maria\\Query Sql6.sql";

        File fichero = new File(rutaFichero);

        if (!fichero.exists()) {
            System.out.println("El fichero no existe.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        System.out.println("Información del fichero:");
        System.out.println("Ruta completa: " + fichero.getAbsolutePath());
        System.out.println("Nombre: " + fichero.getName());
        System.out.println("Tamaño (bytes): " + fichero.length());
        System.out.println("Es un directorio: " + fichero.isDirectory());
        System.out.println("Es un fichero: " + fichero.isFile());
        System.out.println("Es oculto: " + fichero.isHidden());
        System.out.println("Lectura: " + fichero.canRead());
        System.out.println("Escritura: " + fichero.canWrite());
        System.out.println("Ejecución: " + fichero.canExecute());
        System.out.println("Última modificación: " + sdf.format(fichero.lastModified()));
    }
}
