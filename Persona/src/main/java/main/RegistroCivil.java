package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RegistroCivil {
    public static void main(String [] args)throws IOException {
        ManejoTotal iniciar = new ManejoTotal();
        iniciar.RellenarDatos();
        iniciar.mostrarDatosRegion();
    }
}

 