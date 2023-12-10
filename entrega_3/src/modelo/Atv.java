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

public class Atv extends Vehiculo{
	
	private String tipoTerreno;
	
	
	public Atv() {
	}
	
	public Atv(String placa,String marca,String modelo,String estado,String categoria,
    		float precio,ArrayList<LocalDateTime> reservas,String color,String ubicacion,String tipoTerreno) {
    	super(placa,marca,modelo, estado,categoria, precio,reservas,color,ubicacion);
    	this.tipoTerreno = tipoTerreno;
	}
	
	public void setTipoTerreno(String tipoTerreno) {
		this.tipoTerreno = tipoTerreno;
	}
	
	public String getTipoTerreno() {
		return tipoTerreno;
	}
	
	
	
    public void guardarAtv(File archivoAtv, boolean seCreo)throws IOException, FileNotFoundException {
    	String texto = "";
    	if (seCreo==false) {
    		BufferedReader docAtv = new BufferedReader(new FileReader(archivoAtv));
    		String linea=docAtv.readLine();
    		while (linea != null) {
    			texto += linea + ";";
    			linea = docAtv.readLine();
    		}
    		docAtv.close();
    		texto += generarTexto();
    		String[] lineas = texto.split(";");
    		BufferedWriter AtvDoc = new BufferedWriter(new FileWriter(archivoAtv));
    		for (int i = 0 ;i < lineas.length; i++) {
    			AtvDoc.write(lineas[i]);
    			AtvDoc.newLine();
    		}
    		AtvDoc.close();
    	}else {
    		BufferedWriter AtvDoc = new BufferedWriter(new FileWriter(archivoAtv));
    		texto += generarTexto();
    		AtvDoc.write(texto);
    		AtvDoc.close();
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
    	texto+= getTipoTerreno();
    	
    	return texto;
	}

}
