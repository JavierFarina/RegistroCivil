/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.csvreader.CsvWriter;
import java.io.*;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author NathaliaTrigo
 */
public class ManejoTotal {
    private ArrayList<Region> regiones;
    
    public ManejoTotal(){
        this.regiones = new ArrayList();
    }
    
    public void RellenarDatos() throws FileNotFoundException, IOException {
        CSV lineas = new CSV("Regiones");
        String linea = lineas.firstLine();
        linea = lineas.nextLine();
        while (linea != null) {
            Region auxRegion = new Region();
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
            regiones.add(auxRegion);
            linea = lineas.nextLine();
            if (linea == null){
                    break;
            }
        }
    }
    
    public void RellenarSucursales(Region auxRegion,String nombreRegion) throws FileNotFoundException, IOException{
        CSV lineas = new CSV ("Sucursales");
        String linea = lineas.firstLine();
        linea = lineas.nextLine();
        while(linea != null){
            Sucursal auxSucursal = new Sucursal();
            if((lineas.get_csvField(linea, 2)).equals(nombreRegion)){
                auxSucursal.setComuna(lineas.get_csvField(linea, 1));
                auxSucursal.setDireccion(lineas.get_csvField(linea, 0));
                RellenarPersonasSucursal(auxSucursal,lineas.get_csvField(linea, 1));
                auxRegion.setDatosArrayList(auxSucursal);
            }
            linea = lineas.nextLine();
            if(linea == null){
                break;
            }
        }
    }
    public void RellenarPersonasSucursal(Sucursal auxSucursal, String comuna) throws IOException{
        CSV lineas = new CSV ("Persona");
        String linea = lineas.firstLine();
        linea = lineas.nextLine();
        while (linea != null) {
            Persona auxPersona = new Persona();
            if ((lineas.get_csvField(linea, 2).equals(comuna))) {
                auxPersona.setNombre(lineas.get_csvField(linea, 0));
                auxPersona.setApellido(lineas.get_csvField(linea, 1));
                auxPersona.setComuna(lineas.get_csvField(linea, 2));
                auxPersona.setRut(lineas.get_csvField(linea, 3));
                auxSucursal.insertarDatoMapa(auxPersona);
            }
            linea = lineas.nextLine();
            if (linea == null) {
                break;
            }
        }
    }
    
    public void mostrarDatosRegionConPersonas(){
        Region aux;
        System.out.println(("A continuación se muestra el listado de personas por región: "));
        System.out.println("");
        for(int i = 0; i < regiones.size(); i++){
            aux = regiones.get(i);
            System.out.println(("Las personas pertenecientes a la región de " +aux.getNombreRegion()+ " con su respectiva comuna son: "));
            System.out.println("");
            aux.mostrarDatosRegionYPersonas();
        }
    }

    public void mostrarDatosSucursales(){
        System.out.println("A continuación se muestran las comunas con sus respectivas sucursales");   
        for(int i = 0; i < regiones.size(); i++){
            System.out.println(("Las personas pertenecientes a la región de " +regiones.get(i).getNombreRegion()+ " con su respectiva comuna son: "));
            System.out.println("");
            regiones.get(i).mostrarDatosSucursales();
        }
    }


    
    public void ingresarPersona() throws IOException
    {
          BufferedReader lector = new BufferedReader( new InputStreamReader( System.in ) );
          String ingresar = null;
          System.out.println("Ingrese el número de la región para agregar persona: ");
          ingresar = lector.readLine();
          for(int i = 0; i < regiones.size(); i++){
             if((regiones.get(i).getNumeroRegion()).equals(ingresar)){
                System.out.println("Ingrese la comuna a la cual quiere agregar la persona: ");
                ingresar = lector.readLine();
                ingresarPersonaComuna(regiones.get(i),ingresar);
             }
          }
          
    }

     public void ingresarPersonaComuna(Region buscar, String Comuna) throws IOException {
        buscar.buscarComunaParaIngresarPersona(Comuna);
    }
 

    public void ingresarSucursal() throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String ingresar = null;
        System.out.println("Ingrese el número de la región para agregar la nueva sucursal: ");
        ingresar = lector.readLine();
        for (int i = 0; i < regiones.size(); i++) {
            if ((regiones.get(i).getNumeroRegion()).equals(ingresar)) {
                Sucursal auxSucursal = new Sucursal();
                System.out.println("Ingrese la comuna a la cual quiere agregar la nueva sucursal: ");
                ingresar = lector.readLine();
                auxSucursal.setComuna(ingresar);
                System.out.println("Ingrese la direccion la cual quiere agregar la nueva sucursal: ");
                ingresar = lector.readLine();
                auxSucursal.setDireccion(ingresar);
                regiones.get(i).ingresarSucursalNueva(auxSucursal);
                System.out.println("La sucursal ha sido agregada correctamente, seleccione 2 para visualizar:");

            }
        }
    }

    public void buscarPersona() throws IOException{
        float ok = 1;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String ingresar = null;
        System.out.println("Rut de la persona a editar: ");
        ingresar = lector.readLine();
        for(int i = 0; i < regiones.size(); i++){
           regiones.get(i).buscarPersona(ingresar, ok);
        }
    }
    public void editarPersona() throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String ingresar = null;
        System.out.println("Rut de la persona a editar: ");
        ingresar = lector.readLine();
        for(int i = 0; i < regiones.size(); i++){
           regiones.get(i).buscarPersona(ingresar);
        }
    }

    public void eliminarPersona() throws IOException{
        int opcionSobre = 1;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String ingresar = null;
        System.out.println("Rut de la persona a eliminar: ");
        ingresar = lector.readLine();
        for(int i = 0; i < regiones.size(); i++){
           regiones.get(i).buscarPersona(ingresar, opcionSobre);
        }
    }

    public void editarSucursal() throws IOException{
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String ingresar = null;
        System.out.println("Comuna de la sucursal para cambiar direccion: ");
        ingresar = lector.readLine();
        for (int i = 0; i < regiones.size(); i++) {
            regiones.get(i).buscarSucursal(ingresar);
        }
    }

    public void eliminarSucursal() throws IOException{
        int sobre = 1;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String ingresar = null;
        System.out.println("Comuna de la sucursal para eliminar: ");
        ingresar = lector.readLine();
        for (int i = 0; i < regiones.size(); i++) {
            regiones.get(i).buscarSucursal(ingresar, sobre);
        }
    }
    public void generarCSV() throws IOException {
       String nombreCsv = "./Reportes/ReporteCSV.txt";
       boolean existe = new File(nombreCsv).exists();
       if(existe){
          File archivoUsuarios = new File(nombreCsv);
          archivoUsuarios.delete();
       }
           
       CsvWriter csvSalida = new CsvWriter(new FileWriter(nombreCsv, true), ',');
       csvSalida.write("Region");
       csvSalida.write("Comuna");
       csvSalida.write("NombrePersona");
       csvSalida.write("Apellido");
       csvSalida.endRecord();
       for(int i = 0; i < this.regiones.size(); i++){
           this.regiones.get(i).llenarCsv(csvSalida);
       }
       csvSalida.close();
    }
    
    public void buscarSucursal() throws IOException{
        float sobre = 1;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String ingresar = null;
        System.out.println("Comuna de la sucursal para Buscar: ");
        ingresar = lector.readLine();
        for (int i = 0; i < regiones.size(); i++) {
            regiones.get(i).buscarSucursal(ingresar, sobre);
        }
    }
    
    public void llenarDatos(String[][] matriz){
        
    }
    
    public void generarExcel() throws FileNotFoundException, IOException{
        int tamaño = contarPersonasTotales();
        String []aux;
        String []header = new String[]{"Region","Comuna","Nombre","Apellido"};
        ArrayList<String[]> aux1 = new ArrayList();
        for (int i = 0; i < regiones.size(); i++) {
            regiones.get(i).llenarArray(aux1);
        }
        
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Data");
        Row rowCol = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            Cell cell = rowCol.createCell(i);
            cell.setCellValue(header[i]);
        }
        for (int j = 0; j < aux1.size(); j++) {
            String [] auxA = new String[header.length];
            auxA = aux1.get(j);
            Row row = sheet.createRow(j + 1);
            for (int k = 0; k < auxA.length; k++) {
                Cell cell = row.createCell(k);
                cell.setCellValue(auxA[k]);
            }
        }
        FileOutputStream out = new FileOutputStream(new File("./Reportes/Temas.Xlsx.xlsx"));
        wb.write(out);
        wb.close();
        out.close();
        return;
    }
    
    public int contarPersonasTotales(){
        int contadorPersonas = 0;
        for (int i = 0; i < regiones.size(); i++) {
            contadorPersonas = contadorPersonas + regiones.get(i).contarPersonas();
        }
        return contadorPersonas;
    }
}



