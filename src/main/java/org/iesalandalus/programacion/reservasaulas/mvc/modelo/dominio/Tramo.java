package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

public enum Tramo {
    // Enumerado Tramo, con los valores para el constructor
    MANANA("Mañana"), TARDE("Tarde");

    // Variable para almacenar la cadena
    private final String cadenaAMostrar;

    // Constructor con parámetros para Tramo
    private Tramo(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    // Método toString para mostrar cadena de Tramo
    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}
