package modelo;

import java.util.Date;

public class TarjetaCredito {
    
    private String NumeroTarjeta;
    private String Vencimiento;
    private String CVV;
    private int monto;
    private String nameTitular;
    private String cedula;
    private int montoReservado;
    

    // Constructor
    public TarjetaCredito(String numeroTarjeta, String vencimiento, String CVV,
    		              int montop,String nameTitularp,String cedulap, int montoReservadop) {
        this.NumeroTarjeta = numeroTarjeta;
        this.Vencimiento = vencimiento;
        this.CVV = CVV;
        this.monto=montop;
        this.montoReservado=montoReservadop;
        this.nameTitular=nameTitularp;
        this.cedula=cedulap;
        this.montoReservado=montoReservadop;
        
    }

    // Getters y setters
    public String getNumeroTarjeta() {
        return NumeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.NumeroTarjeta = numeroTarjeta;
    }

    public String getVencimiento() {
        return Vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.Vencimiento = vencimiento;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }
    
    public int getMonto() {
		return monto;
	}
    
    public void setMonto(int monto) {
		this.monto = monto;
	}
    
    public String getCedula() {
		return cedula;
	}
    
    public String getNameTitular() {
		return nameTitular;
	}
    
    public int getMontoReservado() {
		return montoReservado;
	}
    
    public void setMontoReservado(int montoReservado) {
		this.montoReservado = montoReservado;
	}
    
    public String generarTexto() {
       	String texto="";
    	texto+= getNumeroTarjeta()+":";
    	texto+= getVencimiento()+":";
    	texto+=getCVV()+":";
    	texto+=Integer.toString( getMonto())+":";
    	texto+=getNameTitular()+":";
    	texto+=getCedula()+":";
    	texto+=Integer.toString( getMontoReservado());
    	
    	return texto;
    }
}

