package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public abstract class Vehiculo {
	 private String marca;
	 private String modelo;
	 private String estado;
	 private String categoria;
	 private String color;
	 private float precio;
	 private ArrayList<LocalDateTime> reservas;
	 private String ubicacion;
	 private String placa;
	 
	public Vehiculo() {
	}
	 
	public Vehiculo(String placa,String marca,String modelo,String estado,String categoria,
			float precio,ArrayList<LocalDateTime> reservas,String color,String ubicacion) {
		this.placa=placa;
		this.marca=marca;
		this.modelo=modelo;
		this.estado=estado;
		this.categoria=categoria;
		this.precio=precio;
		this.reservas=reservas;
		this.color=color;
		this.ubicacion=ubicacion;
	}
	
	public abstract String generarTexto();
	
    public String generarTextFecha(ArrayList<LocalDateTime> reservas) {
    	String texto="";
    	for(int i=0;i<reservas.size();i++) {
    		texto+= reservas.get(i).getYear()+".";
    		texto+=reservas.get(i).getMonthValue()+".";
    		texto+=reservas.get(i).getDayOfMonth()+".";
    		texto+=reservas.get(i).getHour()+".";
    		texto+=reservas.get(i).getMinute()+".";
    		texto+="-";
    		
    	}
    	return texto;
    }
    
    public String getPlaca() {
		return placa;
	}
    
    public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
	    this.categoria = categoria;
	}
	
	public String getMarca() {
		return marca;
	}
    public void setMarca(String marca) {
        this.marca = marca;
    }
	
	public String getModelo() {
		return modelo;
	}
	
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

	
	public String getEstado() {
		return estado;
	}
	
    public void setEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }
	
	public float getPrecio() {
		return precio;
	}
    
    public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public ArrayList<LocalDateTime> getHistorialInformes() {
		return reservas;
	}
	
}
