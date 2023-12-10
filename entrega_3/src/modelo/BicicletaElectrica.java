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

public class BicicletaElectrica extends Vehiculo{
	
    private String autonomia;
    private String tipoBateria;
    
    public BicicletaElectrica() {
    }
	public BicicletaElectrica(String placa,String marca,String modelo,String estado,String categoria,
    		float precio,ArrayList<LocalDateTime> reservas,String color,String ubicacion,String autonomia,String tipoBateria) {
    	super(placa, marca,modelo, estado,categoria, precio,reservas,color,ubicacion);
        this.autonomia = autonomia;
        this.tipoBateria = tipoBateria;
	}
	
	public String getAutonomia() {
		return autonomia;
	}
	
	public String getTipoBateria() {
		return tipoBateria;
	}
	
	public void setAutonomia(String autonomia) {
		this.autonomia = autonomia;
	}
	
	public void setTipoBateria(String tipoBateria) {
		this.tipoBateria = tipoBateria;
	}
	
    public void guardarBiciElec(File archivoBiciElec, boolean seCreo)throws IOException, FileNotFoundException {
    	String texto = "";
    	if (seCreo==false) {
    		BufferedReader docBiciElec = new BufferedReader(new FileReader(archivoBiciElec));
    		String linea=docBiciElec.readLine();
    		while (linea != null) {
    			texto += linea + ";";
    			linea = docBiciElec.readLine();
    		}
    		docBiciElec.close();
    		texto += generarTexto();
    		String[] lineas = texto.split(";");
    		BufferedWriter BiciElecDoc = new BufferedWriter(new FileWriter(archivoBiciElec));
    		for (int i = 0 ;i < lineas.length; i++) {
    			BiciElecDoc.write(lineas[i]);
    			BiciElecDoc.newLine();
    		}
    		BiciElecDoc.close();
    	}else {
    		BufferedWriter BiciElecDoc = new BufferedWriter(new FileWriter(archivoBiciElec));
    		texto += generarTexto();
    		BiciElecDoc.write(texto);
    		BiciElecDoc.close();
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
    	texto+= getAutonomia()+"p0";
    	texto+= getTipoBateria() ;
    	
    	return texto;
	}

}
