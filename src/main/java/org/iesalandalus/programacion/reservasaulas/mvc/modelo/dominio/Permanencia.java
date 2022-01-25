package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Permanencia {
    private LocalDate dia;
    private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");;
    private Tramo tramo;

    public Permanencia(LocalDate dia, Tramo tramo) {
        setDia(dia);
        setTramo(tramo);
    }

    public Permanencia (Permanencia permanencia) {
        if (permanencia == null)
            throw new IllegalArgumentException("No se puede copiar una permanencia nula.");

        setDia(permanencia.dia);
        setTramo(permanencia.tramo);
    }

    public LocalDate getDia() {
        return dia;
    }

    private void setDia(LocalDate dia) {
        if (dia == null)
            throw new IllegalArgumentException("El día de una permanencia no puede ser nulo.");

        this.dia = dia;
    }

    public Tramo getTramo() {
        return tramo;
    }

    private void setTramo(Tramo tramo) {
        if (tramo == null)
            throw new IllegalArgumentException("El tramo de una permanencia no puede ser nulo.");

        this.tramo = tramo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permanencia that = (Permanencia) o;
        return Objects.equals(dia, that.dia) && tramo == that.tramo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dia, tramo);
    }

    @Override
    public String toString() {
        return "[dia=" + dia.format(FORMATO_DIA)
                + ", tramo=" + tramo + "]";
    }
}
