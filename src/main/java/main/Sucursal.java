package main;

import com.csvreader.CsvWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author Javier
 */
public class Sucursal {
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
        for (Map.Entry<String, Persona> entry : this.personas.entrySet()) {
            if(entry.getValue().getRut().equals(rut)){
                System.out.println("Nombre: " + entry.getValue().getNombre());
                System.out.println("Apellido: " + entry.getValue().getApellido());
                System.out.println("Rut: " + entry.getValue().getRut());
            }
        }
    }

    public void buscarPersonaParaEditar(String rut) throws IOException {
        String nombre,apellido = null;
        for (Map.Entry<String, Persona> entry : this.personas.entrySet()) {
            if (entry.getValue().getRut().equals(rut)) {
                BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
                String ingresar = null;
                System.out.println("Desea Editar: ");
                System.out.println("1 Nombre");
                System.out.println("2 Nombre Y Apellido");
                ingresar = lector.readLine();
                if (ingresar.equals("1")) {
                    System.out.println("Ingrese Nuevo Nombre: ");
                    nombre = lector.readLine();
                    entry.getValue().editarPersona(nombre);
                }else{ 
                    System.out.println("Ingrese Nuevo Nombre: ");
                    nombre = lector.readLine();
                    System.out.println("Ingrese Nuevo Apellido: ");
                    apellido = lector.readLine();
                    entry.getValue().editarPersona(nombre, apellido);
                }
            }
        }
    }

    public void buscarPersonaParaEliminar(String rut) throws IOException {
        String nombre,apellido = null;
        for (Map.Entry<String, Persona> entry : this.personas.entrySet()) {
            if (entry.getValue().getRut().equals(rut)) {
                this.personas.remove(rut);
            }
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
