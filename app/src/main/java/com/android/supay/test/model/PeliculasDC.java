package com.android.supay.test.model;

public class PeliculasDC {

    private String protagonista;
    private String titulo;
    private String anio;
    private String image;

    public PeliculasDC() {
    }

    public PeliculasDC(String protagonista, String titulo, String anio, String image) {
        this.protagonista = protagonista;
        this.titulo = titulo;
        this.anio = anio;
        this.image = image;
    }

    public String getProtagonista() {
        return protagonista;
    }

    public void setProtagonista(String protagonista) {
        this.protagonista = protagonista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "PeliculasDC{" +
                "protagonista='" + protagonista + '\'' +
                ", titulo='" + titulo + '\'' +
                ", anio='" + anio + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
