package modelo;
import java.util.List;



import modelo.Carro;


public class AdminGeneral extends usuario{
	;
	
	public AdminGeneral(String log, String pword) {
		super(log,pword);
		
		
	}
	
	public void nuevo_car(String placa, String marca, String modelo, String color, String transmision,
            String estado, String categoria, int ubicacion, String Alquilado,String Disponibilidad, List<Carro> lista) {
		
	
	}
	public void carro_fuera(String placa, List<Carro> lista) {
		int a = 0;

		Carro instancias = lista.get(a);
		Car instancias = lista.get(a);

		String placa_instancia = instancias.getPlaca();
		if(placa != placa_instancia) {
			a += 1;
			carro_fuera(placa,lista);
			
		}else {
			lista.remove(a);
		}
	}
	
    public String generarTexto(){
    	String texto="";
    	texto+= getLogin()+"{";
    	texto+= getPassword();
    	
    	return texto;
    	
    }

}
