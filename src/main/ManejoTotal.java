
package main;

import com.csvreader.CsvWriter;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;


/**
 *
 * @author Javier
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

    public String[][] mostrarDatosSucursales(){
        ArrayList <String[]> datosSucursal = new ArrayList();   
        for(int i = 0; i < regiones.size(); i++){
            regiones.get(i).mostrarDatosSucursales(datosSucursal);
        }
        String[][] aux = new String [datosSucursal.size()][3];
        for(int i = 0; i < datosSucursal.size(); i++){
            aux[i] = datosSucursal.get(i);
        }
        return aux;
    }


    
    public int ingresarPersona(String comuna,String region,Persona ingresarPersona) throws IOException
    {
          BufferedReader lector = new BufferedReader( new InputStreamReader( System.in ) );
         // String ingresar = null;
         // System.out.println("Ingrese el número de la región para agregar persona: ");
         // ingresar = lector.readLine();
          for(int i = 0; i < regiones.size(); i++){
             if((regiones.get(i).getNumeroRegion()).equals(region)){
                if(ingresarPersonaComuna(regiones.get(i),comuna,ingresarPersona) == 1){
                    return 1;
                }
             }
          }
        return 0;
    }

     public int ingresarPersonaComuna(Region buscar, String Comuna,Persona ingresarPersona) throws IOException {
        if(buscar.buscarComunaParaIngresarPersona(Comuna,ingresarPersona) == 1){
            return 1;
        }
        return 0;
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
        System.out.println("Rut de la persona a buscar: ");
        ingresar = lector.readLine();
        for(int i = 0; i < regiones.size(); i++){
           regiones.get(i).buscarPersona(ingresar, ok);
        }
    }
    public void editarPersona() throws IOException{
        Persona aux = new Persona();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String ingresar = null;
        System.out.println("Rut de la persona a editar: ");
        ingresar = lector.readLine();
        for(int i = 0; i < regiones.size(); i++){
           regiones.get(i).buscarPersona(ingresar, aux, 0);
        }

        if(aux.getRut() != null){
            insertarPersonaNuevaSucursal(aux);
        }
    }

    public Persona retornarPersonaEncontrada() throws IOException {
        Persona aux = new Persona();
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String ingresar = null;
        System.out.println("Rut de la persona: ");
        ingresar = lector.readLine();
        for (int i = 0; i < regiones.size(); i++) {
            regiones.get(i).buscarPersona(ingresar, aux, 1);
        }
        return aux;
    }

    public void insertarPersonaNuevaSucursal(Persona aux) throws IOException {
        int verificacion = revisarComunas(aux);
        if (verificacion == 0) {
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            String ingresar = null;
            while (verificacion == 0) {
                System.out.println("Ingrese una comuna existente: ");
                ingresar = lector.readLine();
                aux.setComuna(ingresar);
                if (revisarComunas(aux) == 1) {
                    ingresarNuevaComunaEnPersona(aux);
                    verificacion = 1;
                }
            }
        } else {
            ingresarNuevaComunaEnPersona(aux);
        }
        return;
    }

    public void ingresarNuevaComunaEnPersona(Persona aux) throws IOException {
        for (int i = 0; i < regiones.size(); i++) {
            if ((regiones.get(i).estaSucursal(aux.getComuna())) == 1) {
                regiones.get(i).buscarComunaParaIngresarPersona(aux);
            }
        }
        return;
    }

    public int revisarComunas(Persona aux) {
        for (int i = 0; i < regiones.size(); i++) {
            if ((regiones.get(i).estaSucursal(aux.getComuna())) == 1) {
                 return 1;
            }
        }
       return 0;
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
       csvSalida.write("Nombre");
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
        System.out.println("Comuna de la sucursal para buscar: ");
        ingresar = lector.readLine();
        for (int i = 0; i < regiones.size(); i++) {
            regiones.get(i).buscarSucursal(ingresar, sobre);
        }
    }
    
    public void generarExcel() throws FileNotFoundException, IOException{

        int tamaño = contarPersonasTotales();
        String []aux;
        String []header = new String[]{"Región","Comuna","Nombre","Apellido"};
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

        FileOutputStream out = new FileOutputStream(new File("./Reportes/Temas.xlsx"));
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

    public void buscarSucursalMayorPersonas(){
        ArrayList <Sucursal> aux = new ArrayList();
        int cantidadMayor = 0;
        for(int i = 0; i < regiones.size(); i++){
           if(this.regiones.get(i).mayorSucursal(cantidadMayor) > cantidadMayor){
              cantidadMayor = this.regiones.get(i).mayorSucursal(cantidadMayor);
           }
        }

        for(int i = 0; i < regiones.size(); i++){
            this.regiones.get(i).buscarSucursalMayor(cantidadMayor,aux);
        }
        this.printearSucursal(aux);
        return;
    }
    
    public void mostrarPersonasSucuralEspecifica() throws IOException{
        // ArrayList <Sucursal> aux = new ArrayList();
        
        System.out.println("Ingrese el numero de la región de la sucursal: ");
        BufferedReader lector = new BufferedReader( new InputStreamReader( System.in ) );
        String numRegion = lector.readLine();
        Region region = null;
        
        for(int i = 0; i < regiones.size(); i++){
           region = this.regiones.get(i);
           if(region.getNumeroRegion().equals(numRegion)){
               break;
           }
           
        }
        
        //Si la región no se encuentra, terminamos la ejecución del metodo
        if(region == null){
            System.out.println("No se encontró la región especificada");
            return;
        }
        
        System.out.println("Ingrese el nombre de la comuna de la sucursal:");
        String nombreComuna = lector.readLine();

        Sucursal sucursal = region.obtenerSucursal(nombreComuna);

        //Si la sucursal no se encuentra, terminamos la ejecución del metodo
        if(sucursal == null){
            System.out.println("No se encontró la sucursal especificada");
            return;
        }
        
        System.out.println("\nLas personas pertenecientes a la sucursal de la comuna " + sucursal.getComuna() + ":\n");

        sucursal.mostrarDatosPersonas();

    }

    public void printearSucursal(ArrayList <Sucursal> aux){
       for(int i = 0; i < aux.size(); i++){
           System.out.println("Comuna de la sucursal con más personas: "+ aux.get(i).getComuna());
       }
       return;
    }

    public void generarCertificadoPersonales() throws IOException{
       Persona aux = null;
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
       String fechaHora = dtf.format(LocalDateTime.now());
       aux = retornarPersonaEncontrada();
       CertificadoDatosPersonales certificado = new CertificadoDatosPersonales();
       certificado.setHoraFecha(fechaHora);
       certificado.imprimirDatosPersona(aux);
       certificado.generarCertificado(aux);
       return;
    }

    public void generarCertificadoResidencia() throws IOException{
       Persona aux = null;
       aux = retornarPersonaEncontrada();
       DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
       String fechaHora = dtf.format(LocalDateTime.now());
       CertificadoDeResidencia certificado = new CertificadoDeResidencia();
       certificado.setHoraFecha(fechaHora);
       certificado.imprimirDatosPersona(aux);
       certificado.generarCertificado(aux);
       return;
    }
}