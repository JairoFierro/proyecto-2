package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Moto  extends Vehiculo{
    private String cilindrada;
	
    public Moto() {
	}
	
    public Moto(String placa,String marca,String modelo,String estado,String categoria,
    		float precio,ArrayList<LocalDateTime> reservas,String color,String ubicacion,String cilindrada) {
    	super(placa,marca,modelo, estado,categoria, precio,reservas,color,ubicacion);
    	this.cilindrada = cilindrada;
	}
    
    public String getCilindrada() {
		return cilindrada;
	}
    
    
    public void setCilindrada(String cilindrada) {
		this.cilindrada = cilindrada;
	}
    
    public void guardarMoto(File archivoMoto, boolean seCreo)throws IOException, FileNotFoundException {
    	String texto = "";
    	if (seCreo==false) {
    		BufferedReader docMoto = new BufferedReader(new FileReader(archivoMoto));
    		String linea=docMoto.readLine();
    		while (linea != null) {
    			texto += linea + ";";
    			linea = docMoto.readLine();
    		}
    		docMoto.close();
    		texto += generarTexto();
    		String[] lineas = texto.split(";");
    		BufferedWriter motoDoc = new BufferedWriter(new FileWriter(archivoMoto));
    		for (int i = 0 ;i < lineas.length; i++) {
    			motoDoc.write(lineas[i]);
    			motoDoc.newLine();
    		}
    		motoDoc.close();
    	}else {
    		BufferedWriter motoDoc = new BufferedWriter(new FileWriter(archivoMoto));
    		texto += generarTexto();
    		motoDoc.write(texto);
    		motoDoc.close();
    	}
    }
    
    @Override
	public String generarTexto() {
    	String texto="";
    	texto+= getPlaca()+"p0";
    	texto+= getMarca()+"p0";
    	texto+= getModelo()+"p0";
    	texto+= getEstado()+"p0";
    	texto+= getCategoria()+"p0";
    	texto+=String.valueOf(getPrecio())+"p0";
    	texto+=generarTextFecha(getHistorialInformes())+"p0";
    	texto+= getColor()+"p0";
    	texto+= getUbicacion()+"p0";
    	texto+= getCilindrada();
    	return texto;
	}


}
