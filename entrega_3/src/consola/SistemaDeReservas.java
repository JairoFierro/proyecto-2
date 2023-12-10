package consola;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.imageio.ImageIO;

import modelo.AdminGeneral;
import modelo.AdminLocal;
import modelo.Atv;
import modelo.BicicletaElectrica;
import modelo.Carro;

import modelo.Cliente;
import modelo.Conductor;
import modelo.Carro;
import modelo.Documento;
import modelo.Empleado;
import modelo.InfoReserva;
import modelo.Licencia;
import modelo.Moto;
import modelo.Sede;
import modelo.Seguro;
import modelo.TarjetaCredito;
import modelo.usuario;


public class SistemaDeReservas {
	private String clienteLogeado;
	private Cliente clienteEnCurso;
	private ArrayList<InfoReserva> reservasDelCliente;
	private ArrayList<String> idsReservasDelCliente;
	private ArrayList<Seguro>  seguros; 
	private ArrayList<InfoReserva> reservas;
	private ArrayList<Carro> carros;
	private ArrayList<Carro> carrosDisponibles;
	private InfoReserva reserva; 
	private InfoReserva reservaEnCurso2;
	private double tarifaPorDia=50000.0; 
	private double tarifaPorHora=10000.0;
	private HashMap<String,ArrayList<usuario> > usuarios;
	ArrayList<Empleado> listaEmpleados;
	ArrayList<AdminGeneral> listaAdminsGen;
	ArrayList<AdminLocal> listaAdminsLoc;
	ArrayList<Cliente> listaClientes;
	ArrayList<usuario> listaUsClientes;
	ArrayList<usuario> listaUsAdminsLoc;
	ArrayList<usuario> listaUsEmpleados;
	ArrayList<Carro> listaCarros;
	ArrayList<Moto> listaMotos;
	ArrayList<Atv> listaAtvs;
	ArrayList<BicicletaElectrica> listaBicisElectricas;
	ArrayList<String> pasarelasNames;
	ArrayList<TarjetaCredito> tarjetasPagoSimulado;
	
	
public double calcularCostoReserva(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        long diasDiferencia = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
        long horasDiferencia = ChronoUnit.HOURS.between(fechaInicio, fechaFin);

        if (diasDiferencia >= 1) {
            return diasDiferencia * tarifaPorDia;
        } else {
            return horasDiferencia * tarifaPorHora;
        }
    }	


public boolean verificarFecha(String fecha) {
	//"dd/MM/yyyy"
	DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("yyyy/dd/MM");
	LocalDate fechaActual = LocalDate.now();
    try {
        LocalDate fechaVencimiento = LocalDate.parse(fecha, formatter);

        return fechaVencimiento.isAfter(fechaActual);
    } catch (Exception e) {
    	 System.out.println("dio error" );
        return false;
    }
}

public ArrayList<Carro> getListaCarros() {
	return listaCarros;
}

public ArrayList<InfoReserva> getReservas() {
	return reservas;
}

public void setReservas(ArrayList<InfoReserva> reservas) {
	this.reservas = reservas;
}


public String identificarTemporada() {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(new Date());

	        int mes = cal.get(Calendar.MONTH) + 1; 
	        if (mes >= 1 && mes <= 4) {
	            return "Baja";
	        } else if (mes >= 5 && mes <= 8) {
	            return "Alta";
	        } else  {
	            return "media";
	        } 
	}

public ArrayList<Cliente> getListaClientes() {
	return listaClientes;
}

public ArrayList<AdminGeneral> getListaAdminsGen() {
	return listaAdminsGen;
}

public ArrayList<AdminLocal> getListaAdminsLoc() {
	return listaAdminsLoc;
}

public ArrayList<Empleado> getListaEmpleados() {
	return listaEmpleados;
}

public ArrayList<usuario> getListaUsAdminsLoc() {
	return listaUsAdminsLoc;
}

public ArrayList<usuario> getListaUsClientes() {
	return listaUsClientes;
}

public ArrayList<usuario> getListaUsEmpleados() {
	return listaUsEmpleados;
}

public ArrayList<Atv> getListaAtvs() {
	return listaAtvs;
}

public ArrayList<BicicletaElectrica> getListaBicisElectricas() {
	return listaBicisElectricas;
}

public ArrayList<Moto> getListaMotos() {
	return listaMotos;
}

public void cargarUsuario()throws FileNotFoundException, IOException{
	cargarUsCliente();
	cargarUsEmpleados();
//	cargarUsAdminlocal();
}


public void cargarUsCliente() throws FileNotFoundException, IOException{
	
	FileReader fr = new FileReader("data/Usuarios/Clientes.txt");
	this.listaClientes=new ArrayList<Cliente>();
	this.listaUsClientes=new ArrayList<usuario>();
	BufferedReader br = new BufferedReader(fr);
	String linea = br.readLine();
	while (linea != null) {
		String[] partes = linea.split(">");
		for(int i=0;i<partes.length;i++) {
			System.out.println(partes[i]);
		}
		String[] partesLic= partes[7].split(":");
		Licencia licencia= new Licencia(partesLic[0], partesLic[1], generarFechaSinHora(partesLic[2].split("\\.")));
		String[] partesTar= partes[8].split(":");
		TarjetaCredito tajerta= new TarjetaCredito(partesTar[0], partesTar[1], partesTar[2],Integer.parseInt(partesTar[3]),
				                                    partesTar[4],partesTar[5],Integer.parseInt(partesTar[6]));
		ArrayList<String> ids= new ArrayList<String>();
		String[] partesId =partes[9].split("<");
		for(int i=0;i<partesId.length;i++) {
			ids.add(partesId[i]);
		}
		Cliente cliente=new Cliente(partes[0],partes[1],partes[2], partes[3],partes[4],
				generarFechaSinHora(partes[5].split("\\.")),partes[6],
				licencia,tajerta,ids);
		listaClientes.add(cliente);
		listaUsClientes.add(cliente);
		linea = br.readLine();
		
	}
	br.close();
}
public void cargarUsEmpleados() throws FileNotFoundException, IOException{
	FileReader fr = new FileReader("data/Usuarios/Empleados.txt");
	this.listaEmpleados=new ArrayList<Empleado>();
	this.listaUsEmpleados=new ArrayList<usuario>();
	BufferedReader br = new BufferedReader(fr);
	String linea = br.readLine();
	while (linea != null) {
		String[] partes = linea.split(":");//falta Separados
		Empleado empleado=new Empleado(partes[0],partes[1],partes[2],partes[3],partes[4],partes[5]);
		listaEmpleados.add(empleado);
		listaUsEmpleados.add(empleado);
		linea = br.readLine();
		
	}
	br.close();
}

public Empleado obtenerEmpleado(String numeroDoc) {
	Empleado empleadoOb=null;
	for(int i=0;i<listaEmpleados.size();i++) {
		if(listaEmpleados.get(i).getNumeroDoc().equals(numeroDoc)) {
			empleadoOb=listaEmpleados.get(i);
		}
		
	}
	return empleadoOb;
}

//public void cargarUsAdmingeneral()throws FileNotFoundException, IOException {
//	FileReader fr = new FileReader("data/Usuarios/Admingeneral.txt");
//	this.listaAdminsGen=new ArrayList<usuario>();
//	BufferedReader br = new BufferedReader(fr); 
//	String linea = br.readLine();
//	while (linea != null) {
//		String[] partes = linea.split("{");
//		AdminGeneral admin = new AdminGeneral(partes[0],partes[1]);
//		listaAdminsGen.add(admin);
//		linea = br.readLine();
//		
//	}
//	br.close();
//}

public void cargarUsAdminlocal()throws FileNotFoundException, IOException {
	FileReader fr = new FileReader("data/Usuarios/AdminsLocal.txt");
	this.listaUsAdminsLoc=new ArrayList<usuario>(); 
	this.listaAdminsLoc= new ArrayList<AdminLocal>();
	BufferedReader br = new BufferedReader(fr);
	String linea = br.readLine();
	while (linea != null) {
		String[] partes = linea.split("{");
		AdminLocal admin = new AdminLocal(partes[0],partes[1]);
		listaAdminsLoc.add(admin);
		listaAdminsLoc.add(admin);
		linea = br.readLine();
		
	}
	br.close();
}

public Carro encontrarCarro(String placa) {
	for(int i=0;i<carros.size();i++){
		if(carros.get(i).getPlaca().equals(placa) ) {
			System.out.println("carros de sisreservas"+ carros.get(i).getPlaca());
			return carros.get(i);
		}
	}
	return null;
}

public Moto encontrarMoto(String placa) {
	for(int i=0;i<listaMotos.size();i++){
		if(listaMotos.get(i).getPlaca().equals(placa) ) {
			System.out.println("motos de sisreservas"+ listaMotos.get(i).getPlaca());
			return listaMotos.get(i);
		}
	}
	return null;
}

public Atv encontrarAtv(String placa) {
	for(int i=0;i<listaAtvs.size();i++){
		if(listaAtvs.get(i).getPlaca().equals(placa) ) {
			System.out.println("atvs de sisreservas"+ listaAtvs.get(i).getPlaca());
			return listaAtvs.get(i);
		}
	}
	return null;
}

public BicicletaElectrica encontrarBiciElec(String placa) {
	for(int i=0;i<listaBicisElectricas.size();i++){
		if(listaBicisElectricas.get(i).getPlaca().equals(placa) ) {
			System.out.println("bicis de sisreservas"+ listaBicisElectricas.get(i).getPlaca());
			return listaBicisElectricas.get(i);
		}
	}
	return null;
}

public ArrayList<String> getPasarelasNames() {
	return pasarelasNames;
}

public void setPasarelasNames(ArrayList<String> pasarelasNames) {
	this.pasarelasNames = pasarelasNames;
}

public void cargarPasarelasPago()throws FileNotFoundException, IOException {
	FileReader fr = new FileReader("data/PasarelasPago.txt");
	this.pasarelasNames=new ArrayList<String>(); 
	BufferedReader br = new BufferedReader(fr);
	String linea = br.readLine();
	while (linea != null) {
		pasarelasNames.add(linea);
		linea = br.readLine();
		
	}
	for(int i =0;i<pasarelasNames.size();i++) {
		System.out.println(pasarelasNames.get(i));
	}
	br.close();
}

public String generarId(){
	return Integer.toString(reservas.size()+1);
}

public void agregarReservas(InfoReserva reserva){
	reservas.add(reserva);
}

public ArrayList<Conductor> cargarConductores(String conductores) throws IOException{
	ArrayList<Conductor> finalArray= new ArrayList<Conductor>();
	String[] porConductor= conductores.split(",");
	for (int i = 0 ;i < porConductor.length;i++) {
		String[] partes= porConductor[i].split(":");
		String[] parteFecha = partes[2].split("\\.");
		finalArray.add(new Conductor(new Licencia(partes[0], partes[1], generarFechaSinHora(parteFecha))));
	}
	return finalArray;
}

public ArrayList<Carro> totalCarros(){
	return carros;
}

public  ArrayList<Carro> carrosDisponibles(){
	return carros;
}

//public Date generarFecha(String[] partesFecha){
//	int ano = Integer.parseInt(partesFecha[0]);
//	int mes = Integer.parseInt(partesFecha[1]);
//	int dia = Integer.parseInt(partesFecha[2]);
//	int hora = Integer.parseInt(partesFecha[3]);
//	int minuto = Integer.parseInt(partesFecha[4]);;
//	Date fecha = new Date(ano, mes, dia,hora,minuto);
//	return fecha;
//}

public LocalDateTime generarFecha(String[] reservas) {

	LocalDateTime fechaini= LocalDateTime.of(Integer.parseInt(reservas[0]),Integer.parseInt(reservas[1]), 
			Integer.parseInt(reservas[2]),Integer.parseInt(reservas[3]),Integer.parseInt(reservas[4]));

	return fechaini;
	
}

public LocalDate generarFechaSinHora(String[] reservas) {
	//"yyyy/dd/MM"
	LocalDate fechaini= LocalDate.of(Integer.parseInt(reservas[0]),Integer.parseInt(reservas[1]), 
			Integer.parseInt(reservas[2]));

	return fechaini;
	
}

//public Date generarFechaSinHora(String[] partesFecha){
//	int ano = Integer.parseInt(partesFecha[0]);
//	int mes = Integer.parseInt(partesFecha[1]);
//	int dia = Integer.parseInt(partesFecha[2]);
//	Date fecha = new Date(ano, mes, dia);
//	return fecha;
//}


public ArrayList<Seguro> getSeguros() {
	return seguros;
}

public void actualizarReservas(InfoReserva nuevaReserva) {
	int p=0;
	for(int i=0;i<reservas.size();i++){
		System.out.println(reservas.size());
		if(reservas.get(i)==nuevaReserva ) {
			p= i;
		}
	}
	reservas.set(p, nuevaReserva);
}


public void actualizarInfoEmpleado(Empleado empleadoSeleccionado) throws IOException {
    FileWriter fw = new FileWriter("data/Usuarios/Empleados.txt");
    BufferedWriter bw = new BufferedWriter(fw);

    for (int i=0;i<listaEmpleados.size();i++) {
        bw.write(listaEmpleados.get(i).generarTexto());
        bw.newLine(); 
    }

    bw.close();
	
}


public void actualizarCarros() throws IOException {
    FileWriter fw = new FileWriter("data/Carros.txt");
    BufferedWriter bw = new BufferedWriter(fw);

    for (int i=0;i<listaCarros.size();i++) {
        bw.write(listaCarros.get(i).generarTexto());
        bw.newLine(); 
    }

    bw.close();
	
}

public void actualizarMotos() throws IOException {
    FileWriter fw = new FileWriter("data/Motos.txt");
    BufferedWriter bw = new BufferedWriter(fw);

    for (int i=0;i<listaMotos.size();i++) {
        bw.write(listaMotos.get(i).generarTexto());
        bw.newLine(); 
    }

    bw.close();
	
}

public void actualizarAtvs() throws IOException {
    FileWriter fw = new FileWriter("data/Atvs.txt");
    BufferedWriter bw = new BufferedWriter(fw);

    for (int i=0;i<listaAtvs.size();i++) {
        bw.write(listaAtvs.get(i).generarTexto());
        bw.newLine(); 
    }

    bw.close();
	
}

public void actualizarBicis() throws IOException {
    FileWriter fw = new FileWriter("data/BicicletaElectrica.txt");
    BufferedWriter bw = new BufferedWriter(fw);

    for (int i=0;i<listaBicisElectricas.size();i++) {
        bw.write(listaBicisElectricas.get(i).generarTexto());
        bw.newLine(); 
    }

    bw.close();
	
}

public ArrayList<String> getIdsReservasDelCliente() {
	String login= clienteEnCurso.getLogin();
	idsReservasDelCliente=new ArrayList<String>();
	reservasDelCliente=new ArrayList<InfoReserva>();
	for (int i=0;i<reservas.size();i++) {
		if(reservas.get(i).getCliente().getLogin().equals(login)) {
			idsReservasDelCliente.add(reservas.get(i).getId());
			reservasDelCliente.add(reservas.get(i));
			
		}
	}
	
	if(idsReservasDelCliente.size()==0) {
		idsReservasDelCliente.add("No tienes reservas");
	}
	return idsReservasDelCliente;
}

public void setReservasDelCliente() {
	
	this.reservasDelCliente = reservasDelCliente;
}

public InfoReserva encontrarReservaDelCliente(String id) {
	InfoReserva reserva=null;
	System.out.println("id"+id);
	for(int i=0;i<reservas.size();i++){
		System.out.println("Ids"+reservas.get(i).getId());
		if(reservas.get(i).getId().equals(id)) {
			reserva= reservas.get(i);
			System.out.println("entro");
		}
	}
	return reserva;
}

public void eliminarDocReser() {
    File archivo = new File("data/reservas.txt"); 
    archivo.delete();
}

public void salavarReservas() throws IOException {
    FileWriter fw = new FileWriter("data/reservas.txt");
    BufferedWriter bw = new BufferedWriter(fw);

    for (int i=0;i<reservas.size();i++) {
        bw.write(reservas.get(i).generarTextoReserva());
        bw.newLine(); 
    }

    bw.close();
}

public Seguro encontrarSeguroDelCliente(String cobertura) {
	Seguro nuevoSeguro=null;
	for(int i=0;i<seguros.size();i++) {
		if(seguros.get(i).getCobertura().equals(cobertura)) {
			nuevoSeguro= seguros.get(i);
	     }
	}
	return nuevoSeguro;
}



public Seguro cargarSeguro(String infSeguro) {
	String[] partes= infSeguro.split("'");
	Seguro seguro=new Seguro(partes[0], partes[1], partes[2], partes[3]);
	return seguro;
}

public void cargarReservas() throws FileNotFoundException, IOException {
	File archivo = new File("data/reservas.txt");
	
	if (!archivo.exists()) {
		reservas=new ArrayList<InfoReserva>();
		System.out.println("No hay reservas");
	}else {
	FileReader fr = new FileReader("data/reservas.txt");
	reservas=new ArrayList<InfoReserva>();
	BufferedReader br = new BufferedReader(fr);
	String linea = br.readLine();
	boolean seCreo=false;
	while (linea != null) {

		String[] partes = linea.split("&");
		
		String id = partes[0];
		
		double precio30= Float.parseFloat(partes[1]);
		
		double precioServicioCompleto= Float.parseFloat(partes[2]);
		
		ArrayList<Conductor> coductor = cargarConductores(partes[3]);
		
		
		String medioDePago =partes[4];
		
		Seguro seguro= cargarSeguro(partes[5]);
		
		String Temporada=partes[6];
		
		String sedeEntrega=partes[7];
		
		String sedeDeVuelta=partes[8];
		
		LocalDateTime fechaInicio= generarFecha(partes[9].split("\\."));
		
		LocalDateTime fechaEntrega= generarFecha(partes[10].split("\\."));
		
		String[] partesCliente = partes[11].split(">");
		String[] idReservas=partesCliente[5].split("<");
		String[] partesLicencia=partesCliente[7].split(":");
		Licencia licCliente= new Licencia(partesLicencia[0], partesLicencia[1], generarFechaSinHora(partesLicencia[2].split("\\.")));
		String[] partesTarjeta=partesCliente[8].split(":");
		System.out.println("partesCliente[8]"+partesCliente[8]);
		TarjetaCredito trajetaCliente= new TarjetaCredito(partesTarjeta[0], partesTarjeta[1], partesTarjeta[2],Integer.parseInt(partesTarjeta[3]),
				                                           partesTarjeta[4],partesTarjeta[5],Integer.parseInt(partesTarjeta[6]));
		ArrayList<String> ids=new ArrayList<String>(); 
		for (int i = 0 ;i < idReservas.length;i++) {
			ids.add(idReservas[i]);
		}
		
	
		Cliente cliente = new Cliente(partesCliente[0], partesCliente[1], partesCliente[2],
				partesCliente[3],partesCliente[4], generarFechaSinHora(partesCliente[5].split("\\.")) ,
				partesCliente[6],licCliente,trajetaCliente,ids);
		
		String carro = partes[12];
		
		String tipoVehiculop=partes[12];
		InfoReserva reserva = new InfoReserva(id,precio30, precioServicioCompleto,
				coductor,medioDePago,seguro, Temporada,sedeEntrega,sedeDeVuelta,
				fechaInicio,fechaEntrega,cliente,carro,tipoVehiculop);
		
		reservas.add(reserva);
		linea = br.readLine();
	}
	}
}

public void cargarSeguros() throws FileNotFoundException, IOException {
	FileReader fr = new FileReader("data/Seguros.txt");
	ArrayList<Seguro> listaSeguro=new ArrayList<Seguro>();
	BufferedReader br = new BufferedReader(fr);
	String linea = br.readLine();
	while (linea != null) {
		String[] partes = linea.split("'");
		Seguro seguro=new Seguro(partes[0],partes[1],partes[2], partes[3]);
		listaSeguro.add(seguro);
		linea = br.readLine();
	}
	seguros=listaSeguro;	
	br.close();
}

public ArrayList<LocalDateTime> generarFechaNueva(String[] reservas) {
	ArrayList<LocalDateTime> listaReservas = new ArrayList<>();
	
	for(int i=0;i<reservas.length;i++) {
		String[] fechas =reservas[i].split("\\.");
		System.out.println("anio"+fechas[0]);
		System.out.println("mes"+fechas[1]);
		System.out.println("dia"+fechas[2]);
		LocalDateTime fechaini= LocalDateTime.of(Integer.parseInt(fechas[0]),Integer.parseInt(fechas[1]), 
				Integer.parseInt(fechas[2]),Integer.parseInt(fechas[3]),Integer.parseInt(fechas[4]));
		listaReservas.add(fechaini);
	}
	return listaReservas;
	
}

public void cargarCarros() throws FileNotFoundException, IOException {
	FileReader fr = new FileReader("data/Carros.txt");
	this.listaCarros=new ArrayList<Carro>();
	ArrayList<Carro> listaCarrosDisponibles=new ArrayList<Carro>();
	BufferedReader br = new BufferedReader(fr);
	String linea = br.readLine();
	while (linea != null) {
		String[] partes = linea.split("p0");
		String[] partesFecha = partes[9].split("-");
		Carro carro=new Carro(partes[0],partes[1],partes[2], partes[3],partes[4],
				partes[5],partes[6], partes[7],Float.parseFloat(partes[8]),generarFechaNueva(partesFecha));
		if(Boolean.parseBoolean(partes[8])==true) {
			listaCarrosDisponibles.add(carro);
		}
		listaCarros.add(carro);
		linea = br.readLine();
		
	}
	carros=listaCarros;	
	carrosDisponibles=listaCarrosDisponibles;
	br.close();
}

public void cargarMotos() throws FileNotFoundException, IOException {
	FileReader fr = new FileReader("data/Motos.txt");
	this.listaMotos=new ArrayList<Moto>();
	BufferedReader br = new BufferedReader(fr);
	String linea = br.readLine();
	while (linea != null) {
		String[] partes = linea.split("p0");
		String[] partesFecha = partes[6].split("-");
		Moto moto=new Moto(partes[0],partes[1],partes[2], partes[3],partes[4],
				Float.parseFloat(partes[5]),generarFechaNueva(partesFecha),partes[7], partes[8],partes[9]);
		listaMotos.add(moto);
		linea = br.readLine();
		
	}
	br.close();
}

public void cargarAtvs() throws FileNotFoundException, IOException {
	FileReader fr = new FileReader("data/Atvs.txt");
	this.listaAtvs=new ArrayList<Atv>();
	BufferedReader br = new BufferedReader(fr);
	String linea = br.readLine();
	while (linea != null) {
		String[] partes = linea.split("p0");
		String[] partesFecha = partes[6].split("-");
		Atv atv=new Atv(partes[0],partes[1],partes[2], partes[3],partes[4],
				Float.parseFloat(partes[5]),generarFechaNueva(partesFecha),partes[7], partes[8],partes[9]);
		listaAtvs.add(atv);
		linea = br.readLine();
		
	}
	br.close();
}

public void cargarBiciElec() throws FileNotFoundException, IOException {
	FileReader fr = new FileReader("data/BicicletaElectrica.txt");
	this.listaBicisElectricas=new ArrayList<BicicletaElectrica>();
	BufferedReader br = new BufferedReader(fr);
	String linea = br.readLine();
	while (linea != null) {
		String[] partes = linea.split("p0");
		String[] partesFecha = partes[6].split("-");
		BicicletaElectrica bici=new BicicletaElectrica(partes[0],partes[1],partes[2], partes[3],partes[4],
				Float.parseFloat(partes[5]),generarFechaNueva(partesFecha),partes[7], partes[8],partes[9],partes[10]);
		listaBicisElectricas.add(bici);
		linea = br.readLine();
		
	}
	br.close();
}

public Cliente getClienteEnCurso() {
	return clienteEnCurso;
}

public void setClienteEnCurso(String clienteLog){
	System.out.println("tamaño lisCliente"+listaClientes.size());
	for(int i=0;i<listaClientes.size();i++) {
		if(listaClientes.get(i).getLogin().equals(clienteLog)) {
			System.out.println("cliente"+listaClientes.get(i).getLogin());
			this.clienteEnCurso=listaClientes.get(i);
		}
	}
	
}

//public void enseñarReserva() {
//	for(int i = 0 ;i < reservas.size();i++) {
//		System.out.println(reservas.get(i).generarTextoFechaE());
//		
//	}
//}

public void enseñarSeguros() {
	for(int i = 0 ;i < seguros.size();i++) {
		System.out.println(seguros.get(i).getCobertura());
	}
}

public void crearReserva(InfoReserva reservaNueva) throws IOException, FileNotFoundException{
    File archivo = new File("data/reservas.txt");
    boolean seCreo= false;
    if (!archivo.exists()) {
        archivo.createNewFile();
        seCreo=true;
    }
    reservaNueva.guardarReserva(archivo,seCreo);
	
}

public void crearSeguro( Seguro nuevoSeguro)throws IOException, FileNotFoundException{
	File archivoSeguro = new File("data/Seguros.txt");
    boolean seCreo= false;
    if (!archivoSeguro.exists()) {
    	archivoSeguro.createNewFile();
    	seCreo=true;
    }
    nuevoSeguro.guardarSeguro(archivoSeguro,seCreo);
    
}

public void agregarCarro( Carro nuevoCarro)throws IOException, FileNotFoundException{
	File archivoCarro = new File("data/Carros.txt");
    boolean seCreo= false;
    if (!archivoCarro.exists()) {
    	archivoCarro.createNewFile();
    	seCreo=true;
    }
    nuevoCarro.guardarCarro(archivoCarro,seCreo);
    
}

public void agregarMoto( Moto nuevaMoto)throws IOException, FileNotFoundException{
	File archivoMoto = new File("data/Motos.txt");
    boolean seCreo= false;
    if (!archivoMoto.exists()) {
    	archivoMoto.createNewFile();
    	seCreo=true;
    }
    nuevaMoto.guardarMoto(archivoMoto,seCreo);
}

public void agregarAtv( Atv nuevaAtv)throws IOException, FileNotFoundException{
	File archivoAtv = new File("data/Atvs.txt");
    boolean seCreo= false;
    if (!archivoAtv.exists()) {
    	archivoAtv.createNewFile();
    	seCreo=true;
    }
    nuevaAtv.guardarAtv(archivoAtv,seCreo);
}

public void agregarBiciElec( BicicletaElectrica nuevaBiciElec)throws IOException, FileNotFoundException{
	File archivoBiciElec = new File("data/BicicletaElectrica.txt");
    boolean seCreo= false;
    if (!archivoBiciElec.exists()) {
    	archivoBiciElec.createNewFile();
    	seCreo=true;
    }
    nuevaBiciElec.guardarBiciElec(archivoBiciElec,seCreo);
}



public void registrarCliente(Cliente nuevoCliente)throws IOException, FileNotFoundException{
	File archivoClientes = new File("data/Usuarios/Clientes.txt");
    boolean seCreo= false;
    if (!archivoClientes.exists()) {
    	archivoClientes.createNewFile();
    	seCreo=true;
    }
    nuevoCliente.registroCliente(archivoClientes,seCreo);
    
}

public void registrarEmpleado(Empleado nuevoEmpleado)throws IOException, FileNotFoundException{
	File archivoEmpleados = new File("data/Usuarios/Empleados.txt");
    boolean seCreo= false;
    if (!archivoEmpleados.exists()) {
    	archivoEmpleados.createNewFile();
    	seCreo=true;
    }
    nuevoEmpleado.registroEmpleado(archivoEmpleados,seCreo);
    
}

public ArrayList<TarjetaCredito> getTarjetasPagoSimulado() {
	return tarjetasPagoSimulado;
}

public void setTarjetasPagoSimulado(ArrayList<TarjetaCredito> tarjetasPagoSimulado) {
	this.tarjetasPagoSimulado = tarjetasPagoSimulado;
}

public void cargarTarjetas()throws IOException, FileNotFoundException{
	FileReader fr = new FileReader("data/TarjetasPagoSimulado.txt");
	this.tarjetasPagoSimulado=new ArrayList<TarjetaCredito>();
	BufferedReader br = new BufferedReader(fr);
	String linea = br.readLine();
	while (linea != null) {
		String[] partesTar= linea.split(":");
		TarjetaCredito tajerta= new TarjetaCredito(partesTar[0], partesTar[1], partesTar[2],
				Integer.parseInt(partesTar[3]),partesTar[4],partesTar[5],Integer.parseInt(partesTar[6]));
		
		tarjetasPagoSimulado.add(tajerta);
		linea = br.readLine();
	}
	br.close();
	
}

public void guardarTarjetas() throws IOException {
    FileWriter fw = new FileWriter("data/TarjetasPagoSimulado.txt");
    BufferedWriter bw = new BufferedWriter(fw);

    for (int i=0;i<tarjetasPagoSimulado.size();i++) {
        bw.write(tarjetasPagoSimulado.get(i).generarTexto());
        bw.newLine(); 
    }

    bw.close();
}



//public void cargarLicencia() {
//	String ruta = input("Ingrese la ruta de la imagen(ej->C:\\Users\\arfie\\Downloads\\licencia.jpeg)");
//	try {
//		BufferedImage imagenLicencia = null;
//		imagenLicencia = ImageIO.read(new File(ruta));
//		System.out.println("Se ha leido");
//		licencia = new Licencia(123, "Col", fechaInicio, imagenLicencia);
//		ImageIO.write(imagenLicencia, "png", new File("data/licencia.png"));
//		System.out.println("Se creo la licencia");
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//}
//
//public void cargarImagen(String tipoDoc,String numDoc) {
//	String ruta = input("Ingrese la ruta de la imagen(ej->C:\\Users\\arfie\\Downloads\\licencia.jpeg)");
//	try {
//		if (tipoDoc == "documento") {
//			BufferedImage imagenDocumento = null;+
//			imagenDocumento = ImageIO.read(new File(ruta));
//			System.out.println("Se ha leido");
//			documento= new Documento(imagenDocumento,numDoc);
//			ImageIO.write(imagenDocumento, "png", new File("data/Documento"+numDoc+".png"));
//		}else { 
//			BufferedImage imagenLicencia = null;
//			imagenLicencia = ImageIO.read(new File(ruta));
//			System.out.println("Se ha leido");
//			licencia = new Licencia(123, "Col", fechaInicio, imagenLicencia);
//			ImageIO.write(imagenLicencia, "png", new File("data/licencia"+numDoc+".png"));
//			System.out.println("Se creo la licencia");
//		}
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//}

public void asignarReserva(){
	for(int i=0;i<reservas.size();i++) {
		
	}
}

public String input(String mensaje)
{
	try
	{
		System.out.print(mensaje + ": ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}
	catch (IOException e)
	{
		System.out.println("Error leyendo de la consola");
		e.printStackTrace();
	}
	return null;
}



public static void main(String[] args) throws FileNotFoundException, IOException {  
//	Interfazlog log= new Interfazlog();
//	log.setLocationRelativeTo(null);
//	log.setVisible(true);	
	SistemaDeReservas sistemaDeReservas= new SistemaDeReservas();
//	  ArrayList<Conductor> list=new ArrayList<Conductor>(); 
//	    list.add(new Conductor(new Licencia("10777222","Colombia", new Date())));
//		ArrayList<String> idreservas = new ArrayList<String>();
//		idreservas.add("7");
//		idreservas.add("8");
//	    InfoReserva reservaEnCurso = new InfoReserva( "8",10000.0, 400000.0, 
//	    		new ArrayList<Conductor>(list),
//	    		"tarjeta",new Seguro("todo", "47555", "cubre todo", "compania"),
//	            "alta","¨Principal","chapinero", new Date(),new Date(),
//	            new Cliente("g.chaparr","3456","Juan Vasquez", "j.vasquez@uniandes.edu.co", "320555",
//	            		new Date(),"Colombiana",new Licencia("1998567", "colombia", new Date()),
//	            		 new TarjetaCredito("12345566","3/29","314"), idreservas),"XZY-666");
//	sistemaDeReservas.crearReserva(reservaEnCurso);
//	sistemaDeReservas.crearSeguro();
//	String hello = "1";
//	sistemaDeReservas.cargarSeguros();
//	sistemaDeReservas.cargarCarros();
	
	LocalDateTime fechaini= LocalDateTime.of(2023, 05, 24, 12, 30);
	LocalDateTime fechaFin= LocalDateTime.of(2023, 05, 28, 13, 30);
	ArrayList<LocalDateTime> reservas = new ArrayList<>();
	reservas.add(fechaini);
	reservas.add(fechaFin);
	Moto moto= new Moto("DDS-009","Yamaha","Enduro","sin problemas","Para montaña",190000,reservas,
			"verde","chapinero","250 cc");
	Carro carro= new Carro("Ads-678", "Chevrolet", "Familiar", "verde","manual" , "Sin problemas",
			"SUV", "Chapinero", 180000,reservas);
	
	Atv atv= new Atv("ERF-345","Can-Am","Todoterreno","Sin problemas","Fin deportivo",250000,reservas,
			         "negro","chapinero","Carretera");
	
	BicicletaElectrica biciElectrica= new BicicletaElectrica("LLL-345","Specialized","Turbo Vado ","Sin problemas","Urbana",250000,reservas,
	         "negro","chapinero","360 Wh ","LiFePO4");
	//sistemaDeReservas.agregarCarro(carro);
	//sistemaDeReservas.agregarMoto(moto);
	//sistemaDeReservas.agregarAtv(atv);
	//sistemaDeReservas.agregarBiciElec(biciElectrica);
//	sistemaDeReservas.agregarCarro();
	//sistemaDeReservas.enseñarSeguros();
	//sistemaDeReservas.cargarReservas();
	//sistemaDeReservas.enseñarReserva();
	//Pedir numDocumento y pedir numLic
//	sistemaDeReservas.cargarImagen("lic","123");
}


	
}
