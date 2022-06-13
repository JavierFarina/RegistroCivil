package main;

import com.csvreader.CsvWriter;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antoanais
 */
public class CertificadoDeResidencia extends Certificados {


    public void generarCertificado(Persona aux) {
        String nombreCsv = "./Reportes/Certificado" + aux.getNombre() + ".txt";
        boolean existe = new File(nombreCsv).exists();
        if (existe) {
            File archivoUsuarios = new File(nombreCsv);
            archivoUsuarios.delete();
        }

        CsvWriter csvSalida;   
        try {
            csvSalida = new CsvWriter(new FileWriter(nombreCsv, true), ',');
            csvSalida.write("Comuna");
            csvSalida.write("Rut");
            csvSalida.write("Nombre");
            csvSalida.write("Apellido");
            csvSalida.write("Fecha y Hora");
            csvSalida.endRecord();
            csvSalida.write(aux.getComuna());   
            csvSalida.write(aux.getRut());
            csvSalida.write(aux.getNombre());
            csvSalida.write(aux.getApellido());
            csvSalida.write(this.fechaHora);
            csvSalida.close();
        } catch (IOException ex) {
            Logger.getLogger(CertificadoDatosPersonales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
    }

}
