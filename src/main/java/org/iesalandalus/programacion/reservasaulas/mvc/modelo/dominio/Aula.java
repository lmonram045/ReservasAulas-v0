package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Aula {
    private String nombre;

    public Aula(String nombre) {
        setNombre(nombre);
    }

    public Aula(Aula aula) {
        if (aula == null)
            throw new IllegalArgumentException("No se puede copiar un aula nula.");

        setNombre(aula.nombre);
    }

    private void setNombre(String nombre) {
        if (nombre == null)
            throw new IllegalArgumentException("El nombre del aula no puede ser nulo.");

        if (nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre del aula no puede estar vacío.");

        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aula aula = (Aula) o;
        return Objects.equals(nombre, aula.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "[nombre=" + nombre + "]";
    }
}
