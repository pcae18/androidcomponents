package com.android.supay.test.model;

/**
 *
 *  Model que representa a
 *  un Avenger.
 *
 * @author Paulo_Angeles.
 */
public class Avenger {

    String nombre;
    String genero;
    String imagen;
    String frase;
    Boolean  estaMuerto;
    Boolean tieneGema;

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getImagen() {
        return imagen;
    }

    public String getFrase() {
        return frase;
    }

    public Boolean getEstaMuerto() {
        return estaMuerto;
    }

    public Boolean getTieneGema() {
        return tieneGema;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public void setEstaMuerto(Boolean estaMuerto) {
        this.estaMuerto = estaMuerto;
    }

    public void setTieneGema(Boolean tieneGema) {
        this.tieneGema = tieneGema;
    }
}
