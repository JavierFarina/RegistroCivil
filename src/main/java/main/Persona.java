package main;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Persona {

    //Declaración de variables
    private String rut;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String fechaDefuncion;
    private String estadoCivil;
    private String profesion;
    private String nombreMadre;
    private String rutMadre;
    private String nombrePadre;
    private String rutPadre;
    private String comuna;
    private String rutConyuge;
    
    //setters y getters

    public String getRut() {
        return rut;
    }
    public String getRutConyuge() {
        return rut;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFechaDefuncion() {
        return fechaDefuncion;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getComuna() {
        return comuna;
    }

    public String getProfesion() {
        return profesion;
    }

    public String getRutMadre() {
        return rutMadre;
    }

    public String getRutPadre() {
        return rutPadre;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
    public void setRutConyuge (String rutConyuge) {
        this.rutConyuge = rutConyuge;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido (String apellido) {
        this.apellido = apellido;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setFechaDefuncion(String fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public void setRutMadre(String rutMadre) {
        this.rutMadre = rutMadre;
    }
    public void setRutPadre(String rutPadre) {
        this.rutPadre = rutPadre;
    }

    @Override
    public String toString() {
        return this.nombre + " " + this.apellido;
    }
    //Sobrecarga de métodos
    
    /* Edita el estado civil de la persona y a su vez el del conyuge mediante 
       el rut */
    public void editarEstadoPersona(String estadoCivil, String rutConyuge) {
        this.setEstadoCivil(estadoCivil);
        this.setRutConyuge(rutConyuge);
    }
    /* Le agrega la fecha de defuncion a la persona mediante su rut o nombre*/
    public void editarEstadoPersona(String rut, String nombre, String fechaDefuncion) {
        this.setRut(rut);
        this.setFechaDefuncion(fechaDefuncion);
        this.setNombre(nombre);
    }
    /* Le cambia el nombre a la persona mediante su rut*/
    public void editarPersona(String nombre) {
        this.setNombre(nombre);
    }
    /* Le cambia el nombre y/o apellido a la persona mediante su rut*/
    public void editarPersona(String nombre, String apellido) {
        this.setNombre(nombre);
        this.setApellido(apellido);
    }

    public void poblarUsuario(int LineaPersona) throws FileNotFoundException, IOException {
        CSV nuevoCSV = new CSV("Datos");
        String linea = nuevoCSV.firstLine();
        if(LineaPersona == 0){
            linea = nuevoCSV.nextLine();
        }else{
            linea = nuevoCSV.nextLine();
            int i = 0;
            while (i < LineaPersona) {
                linea = nuevoCSV.nextLine();
                i++;
            }
        }


        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0: {
                   this.nombre = nuevoCSV.get_csvField(linea, i);
                }
                case 1: {
                   this.apellido = nuevoCSV.get_csvField(linea, i);
                }
                case 2: {
                    this.rut = nuevoCSV.get_csvField(linea, i);
                }
                case 3: {
                    this.fechaNacimiento = nuevoCSV.get_csvField(linea, i);
                }
            }
        }
    }

}

