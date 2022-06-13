package main;


/**
 *
 * @author nathaliatrigo
 */
public abstract class Certificados {
    
    protected String fechaHora;
    
    protected void setHoraFecha(String fechaHoraActual){
        this.fechaHora = fechaHoraActual;
    }
    public abstract void generarCertificado(Persona aux);
    

    public void imprimirDatosPersona(Persona aux){
        System.out.println("Los datos a imprimir seran: ");
        System.out.println(aux.getComuna());
        System.out.println(aux.getNombre());
        System.out.println(aux.getApellido());
        System.out.println(aux.getRut());
        System.out.println("Fecha y hora del certificado:" + this.fechaHora);
    }
   
}
