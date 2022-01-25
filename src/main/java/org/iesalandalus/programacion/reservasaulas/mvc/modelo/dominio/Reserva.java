package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Reserva {
    private Profesor profesor;
    private Aula aula;
    private Permanencia permanencia;

    public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
        setProfesor(profesor);
        setAula(aula);
        setPermanencia(permanencia);
    }

    public Reserva(Reserva reserva) {
        if (reserva == null)
            throw new IllegalArgumentException("No se puede copiar una reserva nula.");

        setProfesor(reserva.profesor);
        setAula(reserva.aula);
        setPermanencia(reserva.permanencia);
    }

    private void setProfesor(Profesor profesor) {
        if (profesor == null)
            throw new IllegalArgumentException("La reserva debe estar a nombre de un profesor.");

        this.profesor = profesor;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    private void setAula(Aula aula) {
        if (aula == null)
            throw new IllegalArgumentException("La reserva debe ser para un aula concreta.");

        this.aula = aula;
    }

    public Aula getAula() {
        return aula;
    }

    private void setPermanencia(Permanencia permanencia) {
        if (permanencia == null)
            throw new IllegalArgumentException("La reserva se debe hacer para una permanencia concreta.");

        this.permanencia = permanencia;
    }

    public Permanencia getPermanencia() {
        return permanencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(profesor, reserva.profesor) && Objects.equals(aula, reserva.aula) && Objects.equals(permanencia, reserva.permanencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profesor, aula, permanencia);
    }

    @Override
    public String toString() {
        return  "[profesor=" + profesor +
                ", aula=" + aula +
                ", permanencia=" + permanencia +
                ']';
    }
}
