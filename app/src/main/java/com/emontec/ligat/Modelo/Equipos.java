package com.emontec.ligat.Modelo;

public class Equipos {

    private String posicion;
    private String nombre;
    private String puntos;
    private String diferencia;
    private  String par_ju;
    private  String par_ga;
    private  String par_em;
    private  String par_pe;
    private  String gol_fa;
    private  String gol_contra;

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

    public String getPar_ju() {
        return par_ju;
    }
    public void setPar_ju(String par_ju) {
        this.par_ju = par_ju; }


    public String getPar_ga() {
        return par_ga;
    }
    public void setPar_ga(String par_ga) {
        this.par_ga = par_ga; }


    public String getPar_em() {
        return par_em;
    }
    public void setPar_em(String par_em) {
        this.par_em = par_em; }


    public String getPar_pe() {
        return par_pe;
    }
    public void setPar_pe(String par_pe) {
        this.par_pe = par_pe; }


    public String getGol_fa() {
        return gol_fa;
    }
    public void setGol_fa(String gol_fa) {
        this.gol_fa = gol_fa; }


    public String getGol_contra() {
        return gol_contra;
    }
    public void setGol_contra(String gol_contra) {
        this.gol_contra = gol_contra; }

}


