package main;

import com.csvreader.CsvWriter;
import java.io.*;
import java.util.*;

/**
 *
 * @author Javier
 */
public class Sucursal implements Contable {
    private HashMap<String,Persona> personas;
    private String direccion;
    private String comuna;

    public Sucursal(){
      this.personas = new HashMap();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String Direccion) {
        this.direccion = Direccion;
    }

    public String getComuna() {
        return this.comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }
    //Cambia direccion de la sucursal
    public void cambiarDireccion(String direccion) {
        this.setDireccion(direccion);
    }
    
    //Cambia la comuna de la persona    
    public void editarComuna(String comuna) {
        this.setComuna(comuna);
    }    

    public void insertarDatoMapa(Persona aux){
       personas.put(aux.getRut(), aux);
    }

    public void mostrarDatosPersonas() {
        for (Map.Entry<String, Persona> entry : this.personas.entrySet()) {
            System.out.println("Nombre: " + entry.getValue().getNombre());
            System.out.println("Apellido: " + entry.getValue().getApellido());
            System.out.println("Rut: " + entry.getValue().getRut());
            System.out.println("");
        }
    }

   public void mostrarDatosPersonasPorRut(String rut) {
        if (this.personas.get(rut) != null) {
            System.out.println("Nombre: " + (this.personas.get(rut)).getNombre());
            System.out.println("Apellido: " + (this.personas.get(rut)).getApellido());
            System.out.println("Rut: " + (this.personas.get(rut)).getRut());
        }
    }



    public void buscarPersonaParaEditar(String rut, Persona aux) throws IOException {
        if (this.personas.get(rut) != null) {
            String nombre, apellido = null;
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            String ingresar = null;
            System.out.println("Desea Editar: ");
            System.out.println("1) Nombre");
            System.out.println("2) Nombre Y apellido");
            System.out.println("3) Comuna");
            ingresar = lector.readLine();
            switch(ingresar) 
            {
                case "1": 
                System.out.println("Ingrese nuevo nombre: ");
                nombre = lector.readLine();
                this.personas.get(rut).editarPersona(nombre);
                break;
                case "2":
                System.out.println("Ingrese nuevo nombre: ");
                nombre = lector.readLine();
                System.out.println("Ingrese nuevo apellido: ");
                apellido = lector.readLine();
                this.personas.get(rut).editarPersona(nombre, apellido);
                break;
                case "3":
                System.out.println("Ingrese nueva comuna: ");
                nombre = lector.readLine();
                int verificacion = 0;   
                this.personas.get(rut).editarPersona(nombre, verificacion);
                crearPersonaParaEditarComuna(this.personas.get(rut),aux);
                this.personas.remove(rut);
                break;
            }
        }
    }
    public void llenarPersona(String rut, Persona aux){
       if(this.personas.get(rut) != null){
           crearPersonaParaEditarComuna(this.personas.get(rut),aux);
        }
    }    

    public void crearPersonaParaEditarComuna(Persona aux1,Persona aux2){
        aux2.setApellido(aux1.getApellido());
        aux2.setComuna(aux1.getComuna());
        aux2.setNombre(aux1.getNombre());
        aux2.setRut(aux1.getRut());
    }

    public void buscarPersonaParaEliminar(String rut) throws IOException {
        if (this.personas.get(rut) != null) {
            this.personas.remove(rut);
        }
    }
    
    public void llenarCsv(String region, CsvWriter archivo) throws IOException{
        for (Map.Entry<String, Persona> entry : this.personas.entrySet()) {
            archivo.write(region);
            archivo.write(comuna);
            archivo.write(entry.getValue().getNombre());
            archivo.write(entry.getValue().getApellido());
            archivo.endRecord();
        }
    }
    
    @Override
    public int contarPersonas(){
        int contador = 0;
        for (Map.Entry<String, Persona> entry : this.personas.entrySet()) {
            contador = contador + 1;
        }
        return contador;
    }
    
    public void llenarArray(String region,ArrayList<String[]> aux1){
        for (Map.Entry<String, Persona> entry : this.personas.entrySet()) {
            String [] aux = new String[4];
            aux[0] = region;
            aux[1] = comuna;
            aux[2] = entry.getValue().getNombre();
            aux[3] = entry.getValue().getApellido();
            aux1.add(aux);
        }
    }

}
