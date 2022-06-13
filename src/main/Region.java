package main;

import com.csvreader.CsvWriter;
import java.io.*;
import java.util.*;

/**
 *
 * @author Javier
 */
public class Region implements Contable {
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

    public void mostrarDatosSucursales(ArrayList <String[]> datosSucursal){
        for(int j = 0; j < this.sucursales.size(); j++){
            String[] aux = new String[3];
            aux[0] = this.numeroRegion;
            aux[1] = this.sucursales.get(j).getComuna();
            aux[2] = this.sucursales.get(j).getDireccion();
            datosSucursal.add(aux);
        }
        return;
    }

    public int buscarComunaParaIngresarPersona(String comuna, Persona auxPersona) throws IOException{
        int verificar = 0;
        for (int i = 0; i < this.sucursales.size(); i++) {
            if ((this.sucursales.get(i).getComuna()).equals(comuna)) {
                auxPersona.setComuna(comuna);
                this.sucursales.get(i).insertarDatoMapa(auxPersona);
                verificar = 1;
                System.out.println("");
            }
        }
        if(verificar == 0){
           ErrorComuna error = new ErrorComuna();
           error.setVisible(true);
           return 0;
        }
        return 1;
    }


    public void buscarComunaParaIngresarPersona(Persona aux) throws IOException {
        int verificar = 0;
        for (int i = 0; i < this.sucursales.size(); i++) {
            if ((this.sucursales.get(i).getComuna()).equals(aux.getComuna())) {
                this.sucursales.get(i).insertarDatoMapa(aux);
                System.out.println("La persona ha sido agregada correctamente, seleccione 2 para visualizar:");
                verificar = 1;
                System.out.println("");
            }
        }
        if (verificar == 0) {
            System.out.println("Ingrese una comuna existente");
            buscarComunaParaIngresarPersona(aux);
        }
        return;
    }



    public void ingresarSucursalNueva(Sucursal aux){
      this.sucursales.add(aux);
    }

    public void buscarPersona(String rut, Persona aux, int verificacion) throws IOException{
       for(int i = 0; i < this.sucursales.size(); i++){
          if(verificacion == 0){
              this.sucursales.get(i).buscarPersonaParaEditar(rut, aux);
          }else{
              this.sucursales.get(i).llenarPersona(rut,aux);
          }
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
    
    public Sucursal obtenerSucursal(String comuna){
        for(int i = 0; i < this.sucursales.size(); i++){
           Sucursal sucursal = this.sucursales.get(i);
           
           if(sucursal.getComuna().equals(comuna)){
               return sucursal;
           }
        }
        return null;
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
                System.out.println("Direccion sucursal: "+this.sucursales.get(i).getDireccion());
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

    public int mayorSucursal(int cantidad){
       int contador = 0;
       for(int i = 0; i < this.sucursales.size(); i++){
           if(this.sucursales.get(i).contarPersonas() > contador){
              contador = this.sucursales.get(i).contarPersonas();
           }
       }
       return contador;
    }

    public void buscarSucursalMayor(int cantidadMayor, ArrayList aux){
       for(int i = 0; i < this.sucursales.size(); i++){
          if(this.sucursales.get(i).contarPersonas() == cantidadMayor){
             aux.add(this.sucursales.get(i));
          }
       }
       return;
    }

    public int estaSucursal(String comuna){
       for(int i = 0; i < this.sucursales.size(); i++){
           if((this.sucursales.get(i).getComuna()).equals(comuna)){
               return 1;
           }
       }
       return 0;
    }
    
}
