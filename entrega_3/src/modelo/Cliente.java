package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;


public class Cliente extends usuario{
	private String nombre;
	private String correo;
	private String celular;
	private LocalDate fechaNacimiento;
	private String nacionalidad;
	private Licencia licencia;
	private TarjetaCredito tarjeta;
	
	private ArrayList<String> idReserva=new ArrayList<String>();
	

	public Cliente(String log, String pword, String name, String mail,
			      String cel,LocalDate fechaNacimiento,String nacionalidad,
			       Licencia licencia,TarjetaCredito tarjeta,ArrayList<String> idReserva) {
		super(log, pword);
		this.nombre = name;
		this.correo = mail;
		this.celular = cel;
		this.fechaNacimiento=fechaNacimiento;
		this.nacionalidad=nacionalidad;
		this.licencia=licencia;
		this.tarjeta=tarjeta;
		setIdReserva(idReserva);
	}
	
	public TarjetaCredito getTarjeta() {
		return tarjeta;
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public Licencia getLicencia() {
		return licencia;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public String getNombre() {
		return nombre;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public String getCelular() {
		return celular;
	}
	public ArrayList<String> getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(ArrayList<String> idReserva ) {
		if(this.idReserva.size()==0) {
			this.idReserva.add(idReserva.get(0));
		}else {
        for (int i = 0; i < this.idReserva.size(); i++) {
        	this.idReserva.add(idReserva.get(i));
        }
		
	}
	}
	
    public void registroCliente(File archivoClientes, boolean seCreo)throws IOException, FileNotFoundException {
    	String texto = "";
    	if (seCreo==false) {
    		BufferedReader docCliente = new BufferedReader(new FileReader(archivoClientes));
    		String linea=docCliente.readLine();
    		while (linea != null) {
    			texto += linea + ";";
    			linea = docCliente.readLine();
    		}
    		docCliente.close();
    		texto += generarTexto();
    		String[] lineas = texto.split(";");
    		BufferedWriter clienteDoc = new BufferedWriter(new FileWriter(archivoClientes));
    		for (int i = 0 ;i < lineas.length; i++) {
    			clienteDoc.write(lineas[i]);
    			clienteDoc.newLine();
    		}
    		clienteDoc.close();
    	}else {
    		BufferedWriter clienteDoc = new BufferedWriter(new FileWriter(archivoClientes));
    		texto += generarTexto();
    		clienteDoc.write(texto);
    		clienteDoc.close();
    	}
    }
    public String generarTextoFechaNacimiento() {
    	String texto = "";
    	texto+= fechaNacimiento.getYear()+".";
    	texto+=fechaNacimiento.getMonthValue()+".";
    	texto+=fechaNacimiento.getDayOfMonth();
    	return texto;
    }
	
	public String generarTexto() {
		String texto= "";
		texto+= getLogin()+">";
		texto+= getPassword()+">";
		texto+= getNombre()+">";
		texto+= getCorreo()+">";
		texto+= getCelular()+">";
		texto+= generarTextoFechaNacimiento()+">";
		texto+= getNacionalidad()+">";
		texto+= licencia.generarTexto()+">";
		texto+= tarjeta.generarTexto()+">";
		for(int i = 0; i < idReserva.size(); i++) {
			texto+=idReserva.get(i)+"<";
		}
		System.out.println(texto);

		return texto;
}
}
