/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author Javier
 */
public class Regiones {
    private ArrayList<Sucursal> Sucursales;
    private String NombreRegion;
    private int NumeroRegion;

    public ArrayList<Sucursal> getSucursales() {
        return Sucursales;
    }

    public void setSucursales(ArrayList<Sucursal> Sucursales) {
        this.Sucursales = Sucursales;
    }

    public String getNombreRegion() {
        return NombreRegion;
    }

    public void setNombreRegion(String NombreRegion) {
        this.NombreRegion = NombreRegion;
    }

    public int getNumeroRegion() {
        return NumeroRegion;
    }

    public void setNumeroRegion(int NumeroRegion) {
        this.NumeroRegion = NumeroRegion;
    }
}
