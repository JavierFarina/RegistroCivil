package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Persona {

    //Declaración de variables
    private String rut;
    private String nombre;
    private String fechaNacimiento;
    private String fechaDefuncion;
    private String estadoCivil;
    private String profesion;
    private String nombreMadre;
    private String rutMadre;
    private String nombrePadre;
    private String rutPadre;
    private String comuna;

    //setters y getters
    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
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

    public String getNombreMadre() {
        return nombreMadre;
    }

    public String getRutMadre() {
        return rutMadre;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public String getRutPadre() {
        return rutPadre;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public void setNombreMadre(String nombreMadre) {
        this.nombreMadre = nombreMadre;
    }

    public void setRutMadre(String rutMadre) {
        this.rutMadre = rutMadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public void setRutPadre(String rutPadre) {
        this.rutPadre = rutPadre;
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


        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0: {
                   this.nombre = nuevoCSV.get_csvField(linea, i);
                }
                case 1: {
                    this.rut = nuevoCSV.get_csvField(linea, i);
                }
                case 2: {
                    this.fechaNacimiento = nuevoCSV.get_csvField(linea, i);
                }
            }
        }
    }
    
    
}

