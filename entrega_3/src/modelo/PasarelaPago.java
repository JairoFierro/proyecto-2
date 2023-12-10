package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import consola.InterfazEmpleado;

public abstract class PasarelaPago {
	
	private ArrayList<TarjetaCredito> tarjetasDisponibles;
	
	private TarjetaCredito tarjetaPago;

	
	public abstract void guardarTransaccion(String pago, TarjetaCredito tarjeta)throws IOException, FileNotFoundException;
	
	
	

	public int realizarPago(TarjetaCredito tarjetaPago, int valorPago) {
		int pagoRealizado;
		int valorCuenta=tarjetaPago.getMonto();
		if(tarjetaPago.getMontoReservado()==0) {
			tarjetaPago.setMonto(valorCuenta-valorPago);
			pagoRealizado=valorCuenta-valorPago;
		}else {
			int total=tarjetaPago.getMontoReservado()+ valorPago;
			tarjetaPago.setMonto(valorCuenta-total);
			tarjetaPago.setMontoReservado(0);
			pagoRealizado=total;
		}
		return pagoRealizado;
		
	}
	
    public boolean existeTitular(String titular) {
    	
    	for(int i=0;i<tarjetasDisponibles.size();i++) {
			
			if(tarjetasDisponibles.get(i).getNameTitular().equals(titular.toUpperCase())) {
				return true;
			}

		}
    	
    	return false;

    }
    
    
    public boolean esFechaDeVencimientoValida(String fecha) {
    	String[] fechaPartes=fecha.split("/");
    	String mes= fechaPartes[0];
    	String anio="20"+fechaPartes[1];
    	String fechaCompleta= "01/"+mes+"/"+anio;
    	System.out.println("Fecha actual: " + fechaCompleta);
    	if(Integer.parseInt(mes)>12||Integer.parseInt(mes)<0 ) {
    		return false;
    	}
    	
    	DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	LocalDate fechaActual = LocalDate.now();
        try {
            LocalDate fechaVencimiento = LocalDate.parse(fechaCompleta, formatter);

            return fechaVencimiento.isAfter(fechaActual);
        } catch (Exception e) {
        	 System.out.println("dio error" );
            return false;
        }
    }
    
    public boolean existeCVV(String cvv) {
    	for(int i=0;i<tarjetasDisponibles.size();i++) {
			
			if(tarjetasDisponibles.get(i).getCVV().equals(cvv)) {
				return true;
			}

		}
    	
    	return false;
    	
    }
    
    public boolean existeTar(String numTar) {
    	for(int i=0;i<tarjetasDisponibles.size();i++) {
			
			if(tarjetasDisponibles.get(i).getNumeroTarjeta().equals(numTar)) {
				return true;
			}

		}
    	
    	return false;
    	
    }
    
    public boolean existeDoc(String doc) {
    	for(int i=0;i<tarjetasDisponibles.size();i++) {
			
			if(tarjetasDisponibles.get(i).getCedula().equals(doc)) {
				return true;
			}

		}
    	
    	return false;
    	
    }
    
    public void  encontrarTarjeta(String numTar){
    	for(int i=0;i<tarjetasDisponibles.size();i++) {
			if(tarjetasDisponibles.get(i).getNumeroTarjeta().equals(numTar)) {
				tarjetaPago= tarjetasDisponibles.get(i);
			}
    	}
    	
    }
    
	
	public String verificarInformacion(String numTar,String nameTitular,String fechaExp,
			String cedula,String cvv,String monto, ArrayList<TarjetaCredito> tarjetasDisponiblesp ){
		
	    tarjetasDisponibles=tarjetasDisponiblesp;
		boolean encotroTitular= existeTitular(nameTitular);
		boolean vencimientoValido= esFechaDeVencimientoValida(fechaExp);
		boolean encontroCvv= existeCVV(cvv);
		boolean encontroNumTarjeta= existeTar(numTar);
		boolean encontroDoc= existeDoc(cedula);

		if(fechaExp.length()<=4 || fechaExp.length()>5 ||fechaExp.contains(String.valueOf("/"))==false) { 
     		
			return "fecha Invalida";
			
		}else if(encotroTitular==false) { 
			
			return "titular invalido";
			
		}else if(vencimientoValido==false) {
    		return "Tu tarjeta ha expirado!!";

		}else if(cvv.length()>3 ||cvv.length()<3 ) {
			return "cvv invalido";
			
		}else if(encontroNumTarjeta==false) {
    		return  "Numero de tarjeta invalido";

		}else if(encontroDoc==false) {
			return "Documento no valido";

		} else { 
			encontrarTarjeta(numTar);
			System.out.println(tarjetaPago.getMonto());
			System.out.println(Integer.parseInt(monto)> tarjetaPago.getMonto());
			if((Integer.parseInt(monto)+ tarjetaPago.getMontoReservado() )> tarjetaPago.getMonto()) {
			  return "excede el monto"; 
			  
	       }else { 
	    	   return "valido";
	       }
		}

   }
	
}
