package utils.regex;

import java.util.Calendar;

public class ProfesorInterino extends Profesor {

    private final Calendar FechaComienzoInterinidad;

    public ProfesorInterino(Calendar fechaComienzaInterinidad) {
        super();
        FechaComienzoInterinidad = fechaComienzaInterinidad;
    }

    public ProfesorInterino(String nombre, String apellidos, int edad, Calendar fechaComienzaInterinidad) {
        super(nombre, apellidos, edad);
        FechaComienzoInterinidad = fechaComienzaInterinidad;
    }

    public Calendar getFechaComienzoInterinidad() {
        return FechaComienzoInterinidad;
    }

    public void mostrarDatos() {
        System.out.println("Datos ProfesorInterino. Comienzo interinidad: " +

                FechaComienzoInterinidad.getTime());
    }

}