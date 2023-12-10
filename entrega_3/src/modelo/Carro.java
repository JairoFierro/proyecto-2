package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class Carro extends Vehiculo {

    private String transmision;

    // Constructor para inicializar todos los atributos
    public Carro(String placa, String marca, String modelo, String color, String transmision,
                 String estado, String categoria, String ubicacion,float precio,ArrayList<LocalDateTime> reservas) {
    	super(placa,marca,modelo, estado,categoria, precio,reservas,color,ubicacion);
        this.transmision = transmision;
    }

    // Getters y Setters 
    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    
    @Override
    public String generarTexto() {
    	String texto="";
    	texto+= getPlaca()+"p0";
    	texto+= getMarca()+"p0";
    	texto+= getModelo()+"p0";
    	texto+= getColor()+"p0";
    	texto+= getTransmision()+"p0";
    	texto+= getEstado()+"p0";
    	texto+= getCategoria()+"p0";
    	texto+= getUbicacion()+"p0";
    	texto+=String.valueOf(getPrecio())+"p0";
    	texto+=generarTextFecha(getHistorialInformes());
    	return texto;
    }
    
    public String paCuando(String listopara) {
        if ("Disponible".equals(listopara) || "No Disponible".equals(listopara)) {
            return listopara;
        } else {
            return "a partir de " + listopara;
        }
    }
//    public String informe() {
//        String listopara=null;
//		String informeActual = "Informe del Carro:" +
//            "\nPlaca: " + placa +
//            "\nMarca: " + marca +
//            "\nModelo: " + modelo +
//            "\nColor: " + color +
//            "\nTransmisión: " + transmision +
//            "\nEstado: " + estado +
//            "\nCategoría: " + categoria +
//            "\nUbicación : " + ubicacion+
//            "\nEsta disponible:"+paCuando(listopara);
//
//        historialInformes.add(informeActual);
//
//        return informeActual;
//    }
    
    public void guardarCarro(File archivoCarro, boolean seCreo)throws IOException, FileNotFoundException {
    	String texto = "";
    	if (seCreo==false) {
    		BufferedReader docCarro = new BufferedReader(new FileReader(archivoCarro));
    		String linea=docCarro.readLine();
    		while (linea != null) {
    			texto += linea + ";";
    			linea = docCarro.readLine();
    		}
    		docCarro.close();
    		texto += generarTexto();
    		String[] lineas = texto.split(";");
    		BufferedWriter carroDoc = new BufferedWriter(new FileWriter(archivoCarro));
    		for (int i = 0 ;i < lineas.length; i++) {
    			carroDoc.write(lineas[i]);
    			carroDoc.newLine();
    		}
    		carroDoc.close();
    	}else {
    		BufferedWriter carroDoc = new BufferedWriter(new FileWriter(archivoCarro));
    		texto += generarTexto();
    		carroDoc.write(texto);
    		carroDoc.close();
    	}
    }
    

//    public static void guardarHistorialEnArchivo(String nombreArchivo) {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
//            for (String informe : historialInformes) {
//                writer.write(informe);
//                writer.newLine(); 
//                writer.newLine(); // Dos líneas nuevas para separar cada informe
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}

