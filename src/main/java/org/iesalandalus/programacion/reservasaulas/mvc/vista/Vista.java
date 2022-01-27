package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

import javax.naming.OperationNotSupportedException;

public class Vista {
    private Controlador controlador;

    public Vista() {
        Opcion.setVista(this);
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void comenzar() {
        int numeroOpcion;
        Opcion opcion;

        Consola.mostrarCabecera("Gestión de reservas de las aulas");

        do {
            Consola.mostrarMenu();
            numeroOpcion = Consola.elegirOpcion();
            opcion = Opcion.getOpcionSegunOrdinal(numeroOpcion);
            opcion.ejecutar();
            System.out.println("\n");
        } while (numeroOpcion != Opcion.SALIR.ordinal());
    }

    public void salir() {
        controlador.terminar();
    }

    public void insertarAula() {
        Consola.mostrarCabecera("Insertar Aula");

        try {
            controlador.insertar(Consola.leerAula());
            System.out.println("El aula se insertó correctamente.");
        } catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarAula() {
        Consola.mostrarCabecera("Borrar Aula");

        try {
            controlador.borrar(Consola.leerAula());
            System.out.println("El aula se eliminó correctamente.");
        } catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarAula() {
        Aula aula;

        Consola.mostrarCabecera("Buscar un aula");

        try {
            aula = controlador.buscar(Consola.leerAula());
            String mensaje = (aula != null) ? aula.toString() : "No existe este aula.";
            System.out.println(mensaje);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarAulas() {
        Consola.mostrarCabecera("Lista de aulas");

        Aula[] aulas = controlador.getAulas();

        if (aulas[0] == null) {
            System.out.println("No se encontraron aulas");
        } else {
            for (Aula aula : aulas) {
                if (aula != null)
                    System.out.println(aula);
            }
        }
    }

    public void insertarProfesor() {
        Consola.mostrarCabecera("Insertar Profesor");

        try {
            controlador.insertar(Consola.leerProfesor());
            System.out.println("El profesor se insertó correctamente.");
        } catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarProfesor() {
        Consola.mostrarCabecera("Borrar profesor");

        try {
            controlador.borrar(Consola.leerProfesor());
            System.out.println("El profesor se eliminó correctamente.");
        } catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarProfesor() {
        Profesor profesor;

        Consola.mostrarCabecera("Buscar un profesor");

        try {
            profesor = controlador.buscar(Consola.leerProfesor());
            String mensaje = (profesor != null) ? profesor.toString() : "No existe este aula.";
            System.out.println(mensaje);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarProfesores() {
        Consola.mostrarCabecera("Lista de profesores");

        Profesor[] profesores = controlador.getProfesores();

        if (profesores[0] == null) {
            System.out.println("No se encontraron profesores");
        } else {
            for (Profesor profesor : profesores) {
                if (profesor != null)
                    System.out.println(profesor);
            }
        }
    }
}
