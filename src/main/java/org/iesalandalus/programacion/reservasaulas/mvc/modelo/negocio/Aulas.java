package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;
import java.util.Objects;

public class Aulas {
    private int capacidad;
    private int tamano;
    private Aula[] coleccionAulas;

    public Aulas(int capacidad) {
        if (capacidad < 1)
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");

        tamano = 0;
        this.capacidad = capacidad;
        coleccionAulas = new Aula[capacidad];
    }

    public Aula[] get() {
        return copiaProfundaAulas();
    }

    private Aula[] copiaProfundaAulas() {
        Aula[] aulasCopia = new Aula[capacidad];

        int i = 0;
        while (!tamanoSuperado(i)) {
            aulasCopia[i] = new Aula(coleccionAulas[i]);
            i++;
        }

        return aulasCopia;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Aula aula) throws OperationNotSupportedException {
        // Comprobamos que no sea nula la cita
        if (aula == null)
            throw new NullPointerException("ERROR: No se puede insertar un aula nula.");

        // Utilizo el método capacidadSuperada para no dejar métodos de esta clase sin usar, pero con comprobar si las
        // variables tamaño y capacidad son iguales, es suficiente
        if (capacidadSuperada(tamano))
            throw new OperationNotSupportedException("ERROR: No se aceptan más aulas.");

        // Comprobamos que no existe esa cita
        if (!tamanoSuperado(buscarIndice(aula)))
            throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");

        coleccionAulas[tamano] = new Aula(aula);
        tamano++;
    }

    private int buscarIndice(Aula aula) {
        int indice = 0;
        // For each para recorrer las citas y sacar buscar un índice
        for (Aula aulas : coleccionAulas) {
            if (aula.equals(aulas))
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

    public Aula buscar(Aula aula) {
        if (aula == null)
            throw new IllegalArgumentException("ERROR: No se puede buscar un aula nula.");

        if (!tamanoSuperado(buscarIndice(aula)))
            return new Aula(coleccionAulas[buscarIndice(aula)]);

        return null;
    }

    public void borrar(Aula aula) throws OperationNotSupportedException {
// Comprobamos que no sea nula
        if (aula == null)
            throw new IllegalArgumentException("ERROR: No se puede borrar un aula nula.");

        // guardamos el índice de la cita
        int indice = buscarIndice(aula);

        // comprobamos que la cita existe
        if (tamanoSuperado(indice))
            throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");

        // La borramos y desplazando el array hacia la izquierda.
        desplazarUnaPosicionHaciaIzquierda(indice);
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; !tamanoSuperado(i); i++) {
            coleccionAulas[i] = coleccionAulas[i + 1];
            if (tamanoSuperado(i + 1))
                coleccionAulas[i] = null;
        }

        tamano--;
    }

    public String[] representar() {
        int lon = coleccionAulas.length;
        String[] representacion = new String[coleccionAulas.length];
        for (int i = 0; i < coleccionAulas.length - 1; i++) {
            representacion[i] = coleccionAulas[i].toString();
        }
        return representacion;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aulas aulas = (Aulas) o;
        return capacidad == aulas.capacidad && tamano == aulas.tamano && Arrays.equals(coleccionAulas, aulas.coleccionAulas);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacidad, tamano);
        result = 31 * result + Arrays.hashCode(coleccionAulas);
        return result;
    }

    @Override
    public String toString() {
        return "Aulas{" +
                "capacidad=" + capacidad +
                ", tamano=" + tamano +
                ", coleccionAulas=" + Arrays.toString(coleccionAulas) +
                '}';
    }
}
