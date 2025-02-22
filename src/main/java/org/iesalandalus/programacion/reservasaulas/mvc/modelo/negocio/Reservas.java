package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

import javax.naming.OperationNotSupportedException;

public class Reservas {
    private int capacidad;
    private int tamano;
    private Reserva[] coleccionReservas;

    public Reservas(int capacidad) {
        if (capacidad < 1)
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");

        tamano = 0;
        this.capacidad = capacidad;
        coleccionReservas = new Reserva[capacidad];
    }

    public Reserva[] get() {
        return copiaProfundaReservas();
    }

    private Reserva[] copiaProfundaReservas() {
        Reserva[] reservasCopia = new Reserva[capacidad];

        for (int i = 0; !tamanoSuperado(i); i++)
            reservasCopia[i] = new Reserva(coleccionReservas[i]);

        return reservasCopia;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        // Comprobamos que el profesor no sea nulo
        if (reserva == null)
            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");

        // Utilizo el método capacidadSuperada para no dejar métodos de esta clase sin usar, pero con comprobar si las
        // variables tamaño y capacidad son iguales, es suficiente
        if (capacidadSuperada(tamano))
            throw new OperationNotSupportedException("ERROR: No se aceptan más reservas.");

        // Comprobamos que no existe ese profesor
        if (!tamanoSuperado(buscarIndice(reserva)))
            throw new OperationNotSupportedException("ERROR: Ya existe una reserva con ese nombre.");

        coleccionReservas[tamano] = new Reserva(reserva);
        tamano++;
    }

    private int buscarIndice(Reserva reserva) {
        int indice = 0;
        // For each para recorrer las citas y sacar buscar un índice
        for (Reserva reservas : coleccionReservas) {
            if (reserva.equals(reservas))
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

    public Reserva buscar(Reserva reserva) {
        if (reserva == null)
            throw new IllegalArgumentException("ERROR: No se puede buscar una reserva nula.");

        if (!tamanoSuperado(buscarIndice(reserva)))
            return new Reserva(coleccionReservas[buscarIndice(reserva)]);

        return null;
    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        // Comprobamos que no sea nulo
        if (reserva == null)
            throw new IllegalArgumentException("ERROR: No se puede borrar una reserva nula.");

        // guardamos el índice de la reserva
        int indice = buscarIndice(reserva);

        // comprobamos que la reserva existe
        if (tamanoSuperado(indice))
            throw new OperationNotSupportedException("ERROR: No existe ninguna reserva con ese nombre.");

        // La borramos y desplazando el array hacia la izquierda.
        desplazarUnaPosicionHaciaIzquierda(indice);
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; !tamanoSuperado(i); i++) {
            coleccionReservas[i] = coleccionReservas[i + 1];
            if (tamanoSuperado(i + 1))
                coleccionReservas[i] = null;
        }

        tamano--;
    }

    public String[] representar() {
        String[] representacion = new String[coleccionReservas.length];
        for (int i = 0; i < coleccionReservas.length - 1; i++) {
            representacion[i] = coleccionReservas[i].toString();
        }
        return representacion;
    }

    public Reserva[] getReservasProfesor(Profesor profesor) {
        if (profesor == null)
            throw new NullPointerException("ERROR: El profesor no puede ser nulo.");

        // Array para guardar salida auxiliar de reservas
        Reserva[] auxiliarReserva = new Reserva[capacidad];
        // Variable para controlar el array auxiliar creado anteriormente
        int i = 0;
        for (Reserva reserva : coleccionReservas) {
            if (reserva != null && reserva.getProfesor().equals(profesor)) {
                auxiliarReserva[i] = reserva;
                i++;
            }
        }
        return auxiliarReserva;
    }

    public Reserva[] getReservasAula(Aula aula) {
        if (aula == null)
            throw new NullPointerException("ERROR: El aula no puede ser nula.");

        // Array para guardar salida auxiliar de reservas
        Reserva[] auxiliarReserva = new Reserva[capacidad];
        // Variable para controlar el array auxiliar creado anteriormente
        int i = 0;
        for (Reserva reserva : coleccionReservas) {
            if (reserva != null && reserva.getAula().equals(aula)) {
                auxiliarReserva[i] = reserva;
                i++;
            }
        }
        return auxiliarReserva;
    }

    public Reserva[] getReservasPermanencia(Permanencia permanencia) {
        if (permanencia == null)
            throw new NullPointerException("ERROR: La permanencia no puede ser nula.");

        // Array para guardar salida auxiliar de reservas
        Reserva[] auxiliarReserva = new Reserva[capacidad];
        // Variable para controlar el array auxiliar creado anteriormente
        int i = 0;
        for (Reserva reserva : coleccionReservas) {
            if (reserva != null && reserva.getPermanencia().equals(permanencia)) {
                auxiliarReserva[i] = reserva;
                i++;
            }
        }
        return auxiliarReserva;
    }

    public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
        if (aula == null)
            throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");

        if (permanencia == null)
            throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");

        for (int i = 0; i < coleccionReservas.length; i++) {
            if (aula == coleccionReservas[i].getAula() && permanencia == coleccionReservas[i].getPermanencia())
                return false;

            return true;
        }

        return true;
    }
}
