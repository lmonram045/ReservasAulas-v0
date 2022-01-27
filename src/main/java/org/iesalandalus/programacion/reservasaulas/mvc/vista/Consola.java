package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.format.DateTimeFormatter;

public class Consola {
    private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /** Constructor por defecto */
    private Consola() {
    }

    /** Método para mostrar menú */
    public static void mostrarMenu() {
        for (Opcion opcion : Opcion.values()) {
            System.out.println("\n" + opcion);
        }
    }

    /** Método para mostrar cabecera */
    public static void mostrarCabecera(String cabecera) {
        System.out.printf("%n%s%n", cabecera);
        String formatoCadena = "%0" + cabecera.length() + "d%n";
        System.out.print(String.format(formatoCadena, 0).replace("0", "-"));
    }

    /** Método para elegir una opción */
    public static int elegirOpcion() {
        int ordinal;
        do {
            System.out.print("\nElija una opción: ");
            ordinal = Entrada.entero();

        } while (!Opcion.esOrdinalValido(ordinal));
        return ordinal;
    }

    public static Aula leerAula() {
        System.out.printf("\nIntroduzca el nombre de el nuevo aula: ");

        return new Aula(Entrada.cadena());
    }


}
