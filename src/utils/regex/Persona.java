package utils.regex;

public class Persona {

    private final String nombre;
    private final String apellidos;
    private final int edad;

    public Persona() {
        nombre = "";
        apellidos = "";
        edad = 0;
    }

    public Persona(String nombre, String apellidos, int edad) {

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

}