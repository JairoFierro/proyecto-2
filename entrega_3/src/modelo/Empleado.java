package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JTextField;

import modelo.Carro;
import modelo.InfoReserva;
import modelo.Cliente;
import modelo.Sede;

public class Empleado extends usuario{

	private String sedeName;
	private String nombre;
	private String salario;
	private String numeroDoc;
	

	public Empleado(String log, String pword,String sedep,String nombrep,String salariop,String documentop) {
		super(log, pword);
		sedeName=sedep;
		nombre=nombrep;
		salario= salariop;
		numeroDoc=documentop;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNumeroDoc() {
		return numeroDoc;
	}
	
	public void setNumeroDoc(String numeroDoc) {
		this.numeroDoc = numeroDoc;
	}
	
	public String getSalario() {
		return salario;
	}
	
	public void setSalario(String salario) {
		this.salario = salario;
	}
	public String getSede() {
		return sedeName;
	}
	public void setSedeName(String sedeName) {
		this.sedeName = sedeName;
	}
	
	
	
//	public void estado_carro(String placa,String estado, List<Carro> lista) {
//		int a = 0;
//		Carro instancias = lista.get(a);
//		String estado_instancia = instancias.getEstado();
//		if(placa != estado_instancia) {
//			a += 1;
//			estado_carro(placa,lista);
//			
//		}else {
//			instancias.cambiarEstado(estado);
//		}
//	}
//	public void nuevo_empleado(String log, String pword, String name, String mail, int cel, List<Cliente> lista) {
//		Cliente customer = new Cliente(log,pword,name,mail,cel);
//		lista.add(customer);
//		
//	}
//    public void entregaCarros(Carro carro) {
//        carro.cambiarEstado("alquilado");
//        carro.setAlquilado(true);
//    }
//    public void devolucionCarro(Carro carro) {
//        carro.cambiarEstado("sucio");
//        carro.setAlquilado(false);
//    }
//    public void registroConductor() {
//        // Puede llamar a ese método para obtener la información deseada
//        String infoNLic = obtenerTextoInfoNLic();
//        String infoNP = obtenerTextoInfoNP();
//        String infoNV = obtenerTextoInfoNV();
//        String direccionImagen = obtenerDireccionImagen();
//
//        // Unir los Strings en uno solo
//        String informacionCompleta = infoNLic + "," + infoNP + "," + infoNV + "," + direccionImagen;
//
//        InfoReserva infoReserva = new InfoReserva();
//
//        // Llamar al método cargarConductores de la clase InfoReserva
//        String resultadoCargarConductores = infoReserva.cargarConductores(informacionCompleta);
//    }
//    
    public String generarTexto(){
    	String texto="";
    	texto+= getLogin()+":";
    	texto+= getPassword()+":";
    	texto+= getSede()+":";
    	texto+= getNombre()+":";
    	texto+= getSalario()+":";
    	texto+= getNumeroDoc();
    	
    	return texto;
    	
    }
    
    public void registroEmpleado(File archivoEmpleados, boolean seCreo)throws IOException, FileNotFoundException {
    	String texto = "";
    	if (seCreo==false) {
    		BufferedReader docEmpleado = new BufferedReader(new FileReader(archivoEmpleados));
    		String linea=docEmpleado.readLine();
    		while (linea != null) {
    			texto += linea + ";";
    			linea = docEmpleado.readLine();
    		}
    		docEmpleado.close();
    		texto += generarTexto();
    		String[] lineas = texto.split(";");
    		BufferedWriter empleadoDoc = new BufferedWriter(new FileWriter(archivoEmpleados));
    		for (int i = 0 ;i < lineas.length; i++) {
    			empleadoDoc.write(lineas[i]);
    			empleadoDoc.newLine();
    		}
    		empleadoDoc.close();
    	}else {
    		BufferedWriter empleadoDoc = new BufferedWriter(new FileWriter(archivoEmpleados));
    		texto += generarTexto();
    		empleadoDoc.write(texto);
    		empleadoDoc.close();
    	}
    }
    
    private String obtenerTextoInfoNLic(JTextField textField) {
        return textField.getText();
    }

    private String obtenerTextoInfoNP(JTextField textField) {
        return textField.getText();
    }

    private String obtenerTextoInfoNV(JTextField textField) {
        return textField.getText();
    }

    private String obtenerDireccionImagen(JTextField textField) {
        return textField.getText();
    }
		
		
	}
