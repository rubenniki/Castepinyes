package com.ruben.castepinyes;

/**
 * Created by ruben on 18/10/2017.
 */

public class PersonaCollaInformation {
    public String nombre;
    public String apellido1;
    public String telefono;

    public PersonaCollaInformation(String nombre, String apellido1) {
        this.apellido1 = apellido1;
        this.nombre = nombre;
    }

    public PersonaCollaInformation(String nombre, String apellido1, String telefono) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.telefono = telefono;
    }
}
