package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profesor {
    private static final String ER_TELEFONO = "(\\+34|0034|34)?[ -]*(6|8|9)[ -]*([0-9][ -]*){8}";
    private static final String ER_CORREO = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
    private String nombre;
    private String correo;
    private String telefono;

    public Profesor(String nombre, String correo) {
        setNombre(nombre);
        setCorreo(correo);
    }

    public Profesor(String nombre, String correo, String telefono) {
        setNombre(nombre);
        setCorreo(correo);
        setTelefono(telefono);
    }

    public Profesor(Profesor profesor) {
        if (profesor == null)
            throw new IllegalArgumentException("No se puede copiar un profesor nulo.");

        setNombre(profesor.nombre);
        setCorreo(profesor.correo);
        setTelefono(profesor.telefono);
    }

    private void setNombre(String nombre) {
        if (nombre == null)
            throw new IllegalArgumentException("El nombre del profesor no puede ser nulo.");

        if (nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre del profesor no puede estar vacío.");

        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new IllegalArgumentException("El correo del profesor no puede ser nulo.");
        }

        Pattern pattern = Pattern.compile(ER_CORREO);

        Matcher matcher = pattern.matcher(correo);

        if (!matcher.matches()) {

            throw new IllegalArgumentException("El correo del profesor no es válido.");

        }

        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        if (telefono == null)
            this.telefono = null;
        else {
            Pattern pat = Pattern.compile(ER_TELEFONO);
            Matcher mat = pat.matcher(telefono);

            if (!mat.matches())
                throw new IllegalArgumentException("El teléfono del profesor no es válido.");

            this.telefono = telefono;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profesor profesor = (Profesor) o;
        return Objects.equals(nombre, profesor.nombre) && Objects.equals(correo, profesor.correo) && Objects.equals(telefono, profesor.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, correo, telefono);
    }

    @Override
    public String toString() {
        if (telefono != null) {
            return  "[nombre=" + nombre  +
                    ", correo=" + correo +
                    ", telefono=" + telefono +
                    ']';
        } else {
            return  "[nombre=" + nombre  +
                    ", correo=" + correo +
                    ']';
        }

    }
}
