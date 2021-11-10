package com.example.a04_intenciones.modelo.entidad;

import java.io.Serializable;

//Podemos crear paquetes y clases con boton derecho "new"

//Si queremos pasar un objeto de una actividad a otra, la clase debe de implementar
//Serializable
public class Usuario implements Serializable {

    private String nombre;
    private String password;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
