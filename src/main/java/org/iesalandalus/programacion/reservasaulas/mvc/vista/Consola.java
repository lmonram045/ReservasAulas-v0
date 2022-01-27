package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

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
        return new Aula(leerNombreAula());
    }

    /** No se exactamente que tiene que hacer este método */
    public static String leerNombreAula() {
        System.out.print("\nIntroduzca el nombre de el nuevo aula: ");

        return Entrada.cadena();
    }

    public static Profesor leerProfesor() {
        String nombre = leerNombreProfesor();

        System.out.print("\nIntroduzca el correo de el nuevo profesor: ");
        String correo = Entrada.cadena();

        System.out.print("\nIntroduzca el telefono de el nuevo profesor (Opcional): ");
        String telefono = Entrada.cadena();

        if (telefono.trim().isEmpty())
            return new Profesor(nombre, correo);

        return new Profesor(nombre, correo, telefono);
    }

    public static String leerNombreProfesor() {
        System.out.print("\nIntroduzca el nombre de el nuevo profesor: ");
        return Entrada.cadena();
    }

    public Tramo leerTramo() {
        String tramoString = "";
        do {
            System.out.print("\nIntroduzca el tramo de la reserva (mañana/tarde): ");
            tramoString = Entrada.cadena();
        } while (!Objects.equals(tramoString, "mañana") && !Objects.equals(tramoString, "tarde"));

        if (tramoString == "mañana")
            return Tramo.MANANA;

        return Tramo.TARDE;
    }

    public static LocalDate leerDia() {
        System.out.printf("%s (dd/MM/yyyy): ", "Introduzca la fecha ");
        String fechaStr = Entrada.cadena();
        LocalDate fecha = null;
        try {
            fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("ERROR: La fecha introducida no tiene el formato adecuado.");
        }
        return fecha;
    }


}
