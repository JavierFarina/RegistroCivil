/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.*;

/**
 *
 * @author Javier
 */
public class Sucursal {
    private HashMap<String,Persona> Personas;
    private String Direccion;
    private String Comuna;

    public HashMap<String,Persona> getPersonas() {
        return Personas;
    }

    public void setPersonas(HashMap<String,Persona> Personas) {
        this.Personas = Personas;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getComuna() {
        return Comuna;
    }

    public void setComuna(String Comuna) {
        this.Comuna = Comuna;
    }

}
