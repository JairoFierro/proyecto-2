package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Stripe extends PasarelaPago{
	
	public String generarTextopago(String pago, TarjetaCredito tarjeta) {
		String texto="";
		texto+="Monto transaccion: "+ pago+ " Informacion medio de pago: "+ tarjeta.generarTexto();
		return texto;
	}
	
	@Override
	public void guardarTransaccion(String pago, TarjetaCredito tarjeta)throws IOException, FileNotFoundException{
		File archivo = new File("data/Transacciones/PayU.txt");  
		boolean seCreo= false;
		if (!archivo.exists()) {
		        archivo.createNewFile();
		        seCreo=true;
		}
		String texto = "";
		if (seCreo==false) {
			System.out.println("entró false");
			BufferedReader docReserva = new BufferedReader(new FileReader(archivo));
			String linea=docReserva.readLine();
			while (linea != null) {
				texto += linea + ";";
				linea = docReserva.readLine();
			}
			docReserva.close();
			texto += generarTextopago( pago,  tarjeta);
			String[] lineas = texto.split(";");
			BufferedWriter reservaDoc = new BufferedWriter(new FileWriter(archivo));
			for (int i = 0 ;i < lineas.length; i++) {
				reservaDoc.write(lineas[i]);
				reservaDoc.newLine();
			}
			reservaDoc.close();
		} else {
			System.out.println("entró true");
			BufferedWriter reservaDoc = new BufferedWriter(new FileWriter(archivo));
			texto += generarTextopago( pago, tarjeta);
			reservaDoc.write(texto);
			reservaDoc.close();
		}
	}

}
