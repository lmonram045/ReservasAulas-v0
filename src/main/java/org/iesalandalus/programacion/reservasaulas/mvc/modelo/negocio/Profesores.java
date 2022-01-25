package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;

import javax.naming.OperationNotSupportedException;

public class Profesores {
    private int capacidad;
    private int tamano;
    private Profesor[] coleccionProfesores;

    public Profesores(int capacidad) {
        if (capacidad < 1)
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");

        tamano = 0;
        this.capacidad = capacidad;
        coleccionProfesores = new Profesor[capacidad];
    }

    public Profesor[] get() {
        return copiaProfundaProfesores();
    }

    private Profesor[] copiaProfundaProfesores() {
        Profesor[] profesoresCopia = new Profesor[capacidad];

        for (int i = 0; !tamanoSuperado(i); i++)
            profesoresCopia[i] = new Profesor(coleccionProfesores[i]);

        return profesoresCopia;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Profesor profesor) throws OperationNotSupportedException {
        // Comprobamos que el profesor no sea nulo
        if (profesor == null)
            throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");

        // Utilizo el método capacidadSuperada para no dejar métodos de esta clase sin usar, pero con comprobar si las
        // variables tamaño y capacidad son iguales, es suficiente
        if (capacidadSuperada(tamano))
            throw new OperationNotSupportedException("ERROR: No se aceptan más profesores.");

        // Comprobamos que no existe ese profesor
        if (!tamanoSuperado(buscarIndice(profesor)))
            throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese nombre.");

        coleccionProfesores[tamano] = new Profesor(profesor);
        tamano++;
    }

    private int buscarIndice(Profesor profesor) {
        int indice = 0;
        // For each para recorrer las citas y sacar buscar un índice
        for (Profesor profesores : coleccionProfesores) {
            if (profesor.equals(profesores))
                return indice;

            indice++;
        }

        return indice;
    }

    private boolean tamanoSuperado(int indice) {
        return (tamano <= indice);
    }

    private boolean capacidadSuperada(int tamano) {
        return (tamano >= capacidad);
    }

    public Profesor buscar(Profesor profesor) {
        if (profesor == null)
            throw new IllegalArgumentException("ERROR: No se puede buscar un profesor nulo.");

        if (!tamanoSuperado(buscarIndice(profesor)))
            return new Profesor(coleccionProfesores[buscarIndice(profesor)]);

        return null;
    }

    public void borrar(Profesor profesor) throws OperationNotSupportedException {
        // Comprobamos que no sea nulo
        if (profesor == null)
            throw new IllegalArgumentException("ERROR: No se puede borrar un profesor nulo.");

        // guardamos el índice de la cita
        int indice = buscarIndice(profesor);

        // comprobamos que la cita existe
        if (tamanoSuperado(indice))
            throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese nombre.");

        // La borramos y desplazando el array hacia la izquierda.
        desplazarUnaPosicionHaciaIzquierda(indice);
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; !tamanoSuperado(i); i++) {
            coleccionProfesores[i] = coleccionProfesores[i + 1];
            if (tamanoSuperado(i + 1))
                coleccionProfesores[i] = null;
        }

        tamano--;
    }

    public String[] representar() {
        String[] representacion = new String[coleccionProfesores.length];
        for (int i = 0; i < coleccionProfesores.length - 1; i++) {
            representacion[i] = coleccionProfesores[i].toString();
        }
        return representacion;
    }
}
