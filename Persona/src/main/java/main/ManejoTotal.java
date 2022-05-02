/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.io.*;
import java.util.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Javier
 */
public class ManejoTotal {
    private ArrayList<Regiones> Regiones;
    
    public ManejoTotal(){
        this.Regiones = new ArrayList();
    }
    
    public void RellenarDatos() throws FileNotFoundException, IOException {
        CSV lineas = new CSV("Regiones");
        String linea = lineas.firstLine();
        linea = lineas.nextLine();
        while (linea != null) {
            Regiones auxRegion = new Regiones();
            for (int i = 0; i < 2; i++) {
                switch (i) {
                    case 0: {
                        auxRegion.setNombreRegion(lineas.get_csvField(linea, i));
                        break;
                    }
                    case 1: {
                        auxRegion.setNumeroRegion(lineas.get_csvField(linea, i));
                        break;
                    }
                }
            }
            String nombreRegion = auxRegion.getNombreRegion();
            RellenarSucursales(auxRegion, nombreRegion);
            Regiones.add(auxRegion);
            linea = lineas.nextLine();
            if (linea == null){
                    break;
            }
        }
    }
    
    public void RellenarSucursales(Regiones auxRegion,String nombreRegion) throws FileNotFoundException, IOException{
        CSV lineas = new CSV ("Sucursales");
        String linea = lineas.firstLine();
        linea = lineas.nextLine();
        ArrayList<Sucursal> auxSucursales = new ArrayList();
        while(linea != null){
            Sucursal auxSucursal = new Sucursal();
            if((lineas.get_csvField(linea, 2)).equals(nombreRegion)){
                auxSucursal.setComuna(lineas.get_csvField(linea, 1));
                auxSucursal.setDireccion(lineas.get_csvField(linea, 0));
                RellenarPersonasSucursal(auxSucursal,lineas.get_csvField(linea, 1));
                auxSucursales.add(auxSucursal);
            }
            linea = lineas.nextLine();
            if(linea == null){
                break;
            }
        }
        auxRegion.setSucursales(auxSucursales);
    }
    public void RellenarPersonasSucursal(Sucursal auxSucursal, String comuna) throws IOException{
        CSV lineas = new CSV ("Persona");
        String linea = lineas.firstLine();
        linea = lineas.nextLine();
        HashMap<String,Persona> auxPersonas = new HashMap();
        while (linea != null) {
            Persona auxPersona = new Persona();
            if ((lineas.get_csvField(linea, 1).equals(comuna))) {
                auxPersona.setNombre(lineas.get_csvField(linea, 0));
                auxPersona.setComuna(lineas.get_csvField(linea, 1));
                auxPersona.setRut(lineas.get_csvField(linea, 2));
                auxPersonas.put(lineas.get_csvField(linea,2),auxPersona);
            }
            linea = lineas.nextLine();
            if (linea == null) {
                break;
            }
        }
        auxSucursal.setPersonas(auxPersonas);
    }
    
    public void mostrarDatosRegion(){
        Regiones aux;
        for(int i = 0; i < Regiones.size(); i++){
            aux = Regiones.get(i);
            System.out.println("Region Sucursal:"+aux.getNombreRegion());
            ArrayList<Sucursal> aux1 = aux.getSucursales();
            for(int j = 0; j < aux1.size(); j++){
                System.out.println("Comuna Sucursal:" + (aux1.get(j).getComuna()));
                mostrarDatosPersonas(aux1.get(j).getPersonas());
            }
        }
    }
    public void mostrarDatosPersonas(HashMap<String,Persona> prueba){
        for (Map.Entry<String, Persona> entry : prueba.entrySet()) {
            System.out.println("Persona:" + entry.getValue().getNombre());
        }
    }

    public void mostrarInformacion(int ) {
    }
}



