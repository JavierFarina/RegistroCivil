/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.csvreader.CsvWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author Javier
 */
public class Region {
    private ArrayList<Sucursal> sucursales;
    private String nombreRegion;
    private String numeroRegion;

    public Region(){
       this.sucursales = new ArrayList();
    }

    public String getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(String NombreRegion) {
        this.nombreRegion = NombreRegion;
    }

    public String getNumeroRegion() {
        return numeroRegion;
    }

    public void setNumeroRegion(String NumeroRegion) {
        this.numeroRegion = NumeroRegion;
    }

    public void setDatosArrayList(Sucursal aux){
        sucursales.add(aux);
    }

    public void mostrarDatosRegionYPersonas(){

        for(int j = 0; j < this.sucursales.size(); j++){
            System.out.println("Comuna: " + this.sucursales.get(j).getComuna());
            System.out.println("");
            this.sucursales.get(j).mostrarDatosPersonas();
        }
    }

    public void mostrarDatosSucursales(){
        for(int j = 0; j < this.sucursales.size(); j++){
            System.out.println("Comuna: " + this.sucursales.get(j).getComuna());
            System.out.println("Direccion: " + this.sucursales.get(j).getDireccion());
            System.out.println("");
        }
    }

    public void buscarComunaParaIngresarPersona(String comuna) throws IOException{
        int verificar = 0;
        for (int i = 0; i < this.sucursales.size(); i++) {
            if ((this.sucursales.get(i).getComuna()).equals(comuna)) {
                Persona auxPersona = new Persona();
                BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
                String datos = null;
                System.out.println("Ingrese el nombre de la persona: ");
                datos = lector.readLine();
                auxPersona.setNombre(datos);
                System.out.println("Ingrese el apellido de la persona: ");
                datos = lector.readLine();
                auxPersona.setApellido(datos);
                System.out.println("Ingrese el rut de la persona: ");
                datos = lector.readLine();
                auxPersona.setRut(datos);
                this.sucursales.get(i).insertarDatoMapa(auxPersona);
                System.out.println("La persona ha sido agregada correctamente, seleccione 2 para visualizar:");
                verificar = 1;
                System.out.println("");
            }
        }
        if(verificar == 0){
           System.out.println("Ingrese una comuna existente");
        }
    }

    public void ingresarSucursalNueva(Sucursal aux){
      this.sucursales.add(aux);
    }

    public void buscarPersona(String rut) throws IOException{
       for(int i = 0; i < this.sucursales.size(); i++){
          this.sucursales.get(i).buscarPersonaParaEditar(rut);
       }
    }

    public void buscarPersona(String rut, int ok) throws IOException{
       for(int i = 0; i < this.sucursales.size(); i++){
          this.sucursales.get(i).buscarPersonaParaEliminar(rut);
       }
    }
    
    public void buscarPersona(String rut, float ok) throws IOException{
       for(int i = 0; i < this.sucursales.size(); i++){
          this.sucursales.get(i).mostrarDatosPersonasPorRut(rut);
       }
    }

    public void buscarSucursal(String comuna) throws IOException{
       for(int i = 0; i < this.sucursales.size(); i++){
           if(this.sucursales.get(i).getComuna().equals(comuna)){
                BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
                String datos = null;
                System.out.println("Ingrese nueva direccion: ");
                datos = lector.readLine();
                this.sucursales.get(i).setDireccion(datos);
           }
        }
    }

    public void buscarSucursal(String comuna, int ok) throws IOException{
       for(int i = 0; i < this.sucursales.size(); i++){
           if(this.sucursales.get(i).getComuna().equals(comuna)){
                this.sucursales.remove(i);
           }
        }
    }
    
    public void buscarSucursal(String comuna, float ok) throws IOException{
       for(int i = 0; i < this.sucursales.size(); i++){
           if(this.sucursales.get(i).getComuna().equals(comuna)){
                System.out.println("Direccion Sucursal: "+this.sucursales.get(i).getDireccion());
           }
        }
    }
    
    public void llenarCsv(CsvWriter archivo) throws IOException{
        for(int i = 0; i < this.sucursales.size(); i++){
            this.sucursales.get(i).llenarCsv(nombreRegion, archivo);
        }
    }
    
    public int contarPersonas(){
        int contador = 0;
        for(int i = 0; i < this.sucursales.size(); i++){
            contador = contador + this.sucursales.get(i).contarPersonas();
        }
        return contador;
    }
    public void llenarArray(ArrayList<String[]> aux1){
        for(int i = 0; i < this.sucursales.size(); i++){
            this.sucursales.get(i).llenarArray(nombreRegion, aux1);
        }
    }
}
