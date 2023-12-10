package modelo;

import java.util.Date;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Licencia {

    private String numero;
    private String pais;
    private LocalDate fechaVencimiento;
    private BufferedImage imagen;

    // Constructor
    public Licencia(String numero, String pais, LocalDate fechaVencimiento){
        this.numero = numero;
        this.pais = pais;
        this.fechaVencimiento = fechaVencimiento;
        //this.imagen = ImageIO.read(new File("D://Escritorio//banvelkozpls.png"));
    }

    // Getters y setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(String pathImagen) throws IOException {
        this.imagen = ImageIO.read(new File(pathImagen));
    }
    public String generarTexto() {
    	String texto="";
    	texto+= getNumero()+":";
    	texto+= getPais()+":";
    	texto+=generarTextoFecha();

    	return texto;

    }
    public String generarTextoFecha() {
    	
    	String texto="";
    	texto+= fechaVencimiento.getYear()+".";
    	texto+=fechaVencimiento.getMonthValue()+".";
    	texto+=fechaVencimiento.getDayOfMonth();

    	return texto;
    }
}
