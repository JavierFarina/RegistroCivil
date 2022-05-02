package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RegistroCivil {
    public static void main(String [] args)throws IOException {
        ManejoTotal iniciar = new ManejoTotal();
        int option = 1;
        iniciar.RellenarDatos();
        BufferedReader lector = new BufferedReader( new InputStreamReader( System.in ) );
        while(option != 0)
        {
            System.out.println("*************************************");
            System.out.println("*****BIENVENID@ A REGISTRO CIVIL*****");
            System.out.println("INGRESE LA OPCIÓN QUE DESEA REALIZAR");
            System.out.println("1)Agregar persona:");
            System.out.println("2)Mostrar personas:");
            System.out.println("3)Editar persona:");
            System.out.println("4)Eliminar persona:");
            System.out.println("5)Agregar sucursal:");
            System.out.println("6)Mostrar Sucursales:");
            System.out.println("7)Editar Sucursal:");
            System.out.println("8)Eliminar Sucursal:");
            System.out.println("9)Generar CSV:");
            System.out.println("10)Generar XML:");
            System.out.println("11)Buscar Persona Por Rut:");
            System.out.println("12)Buscar Sucursal Por Comuna:");
            System.out.println("13)Para volver atrás:");  
            option = Integer.parseInt(lector.readLine());
            if(option == 1)
            {
                iniciar.ingresarPersona();
            }
            if(option == 2)
            {
                iniciar.mostrarDatosRegionConPersonas();
            }
            if(option == 3)
            {
                iniciar.editarPersona();
            }
            if(option == 4)
            {
                iniciar.eliminarPersona();
            }
            if(option == 5)
            {
                iniciar.ingresarSucursal();
            }
            if(option == 6)
            {
                iniciar.mostrarDatosSucursales();
            }
            if(option == 7)
            {
                iniciar.editarSucursal();
            }
            if(option == 8)
            {
                iniciar.eliminarSucursal();
            }
            if(option == 9)
            {
                iniciar.generarCSV();
            }
            if (option == 10) {
                iniciar.generarExcel();
            }
            if (option == 11) {
                iniciar.buscarPersona();
            }
            if (option == 12) {
                iniciar.buscarSucursal();
            }
            if(option == 13){
               break;
            }
            System.out.println("");
        }
    }
}

 
