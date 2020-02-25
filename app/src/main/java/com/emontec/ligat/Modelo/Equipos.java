package com.emontec.ligat.Modelo;

public class Equipos {

    private String posicion;
    private String nombre;
    private String puntos;
    private String diferencia;
    private String grupo;


    public Equipos(){    }

    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) {
        this.grupo = grupo; }


    public String getPosicion() { return posicion; }
    public void setPosicion(String posicion) {
        this.posicion = posicion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre; }

    public String getPuntos() {
        return puntos;
    }
    public void setPuntos(String puntos) {
        this.puntos = puntos; }

    public String getDiferencia() {
        return diferencia;
    }
    public void setDiferencia(String diferencia) {
        this.diferencia = diferencia; }

}


