package com.emontec.ligat.Modelo;

public class M_goleador {

    private String id_equipo;
    private String id_jugador;
    private String nombre;
    private String goles;
    private String equipo;
    private String logo_visita;


    public M_goleador(){    }

    public String getId_jugador() { return id_jugador; }
    public void setId_jugador(String id_jugador) {
        this.id_jugador = id_jugador; }


    public String getId_equipo() { return id_equipo; }
    public void setId_equipo(String id_equipo) {
        this.id_equipo = id_equipo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre; }

    public String getGoles() {
        return goles;
    }
    public void setGoles(String goles) {
        this.goles = goles; }

    public String getEquipo() {
        return equipo;
    }
    public void setEquipo(String equipo) {
        this.equipo = equipo; }

    public String getLogo_visita() {
        return logo_visita;
    }
    public void setLogo_visita(String logo_visita) {
        this.logo_visita = logo_visita; }
}


