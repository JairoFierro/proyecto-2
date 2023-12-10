package consola;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.AdminLocal;
import modelo.BicicletaElectrica;
import modelo.Conductor;
import modelo.Empleado;
import modelo.InfoReserva;
import modelo.Licencia;
import modelo.PasarelaPago;
import modelo.TarjetaCredito;

public class InterfazEmpleado extends JFrame{
	
	private static SistemaDeReservas sistemaDeReservas;
	private JTabbedPane ventanaPago;
	
	private JTextField txtNumlicencia;
	
	private JTextField txtPais;
	
	private JTextField txtFechaVencimiento;
	
	private JTextField txtNumTarjeta;
	
	private JTextField txtFechaExpiracion;
	
	private JTextField txtCvv;
	
	private JTextField txtNameTitular;
	
	private JTextField txtCC;
	
	private JTextField txtMonto;
	
	private JTextField idReserva;
	
	private JComboBox<String> pasarelasPago;
	
	private JButton agregarConductor;
	
	private JButton pagar;
	
	private JButton bloqueoCupo;
	
	private String pasarelaSeleccionada;
	
	private PasarelaPago pasarelaPago;
	
	private ArrayList<TarjetaCredito> tarjetasDisponibles;
	
	private TarjetaCredito tarjetaReserMonto;
	
	private TarjetaCredito tarjetaPago;
	
	private ArrayList<InfoReserva> reservas;
	
	
	public InterfazEmpleado(ArrayList<String> pasarelasDispop, SistemaDeReservas sistemaDeReservasp,
			ArrayList<TarjetaCredito> tarjetasDisponiblesp,  ArrayList<InfoReserva> reservasp) {
		sistemaDeReservas=sistemaDeReservasp;
		tarjetasDisponibles=tarjetasDisponiblesp;
		reservas=reservasp;
		
		int tamX =700;
		int tamY=550;
		setTitle("Empleado");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(tamX,tamY);
		setLayout(new BorderLayout());
			
		ventanaPago= new JTabbedPane();
		ventanaPago.setFocusable(false);
		JPanel panelPago = new JPanel();
			
		JPanel panelTitle= new JPanel();
		panelTitle.setLayout(new GridLayout(1,3));
		panelTitle.add(new Label());
		JLabel title=new JLabel("PAGO");
		title.setHorizontalAlignment(JLabel.CENTER);
		panelTitle.add(title);
		panelTitle.add(new Label());
		panelPago.add(panelTitle,BorderLayout.NORTH);
			
			
		JPanel panelInfoPago= new JPanel();
		panelInfoPago.setLayout(new GridLayout(12,4));
		
			
		JLabel titleInfoTar=new JLabel("Información de tarjeta ");
		JLabel numTarjeta=new JLabel("Numero de Tarjeta");
		txtNumTarjeta= new JTextField(5);
		
		JLabel lbFechaExpiracion = new JLabel("Fecha de expiración(MM/AA):      ");
		txtFechaExpiracion = new JTextField(4);
		
		
		JLabel lbNumTarjeta = new JLabel("Numero de tarjeta:      ");
		txtNumTarjeta = new JTextField(4);
		
		JLabel lbCvv = new JLabel("CVV:      ");
		txtCvv= new JTextField(4);
		
		JLabel monto = new JLabel("Monto:      ");
		txtMonto= new JTextField(4);
		
		JLabel titleInfoTi=new JLabel("Información titular ");
		
		JLabel titular=new JLabel("Nombre del titular:  ");
		txtNameTitular= new JTextField(4);
		
		JLabel labelCC=new JLabel("CC:  ");
		txtCC= new JTextField(4);
		
		JLabel labelPasarelaPago=new JLabel("Elije una pasarela de pago:  ");
		setPasarelas(pasarelasDispop);
		pasarelasPago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JComboBox pasarelas = (JComboBox) e.getSource();
            	String pasarela= (String)pasarelas.getSelectedItem();
                for (int i = 0; i < pasarelas.getItemCount(); i++) {
                	String elemento = (String) pasarelas.getItemAt(i);
                    if(pasarela.equals(elemento)) {
                    	pasarelaSeleccionada=pasarela;
                    }
                }
            }});
		setBtnPagar();
		setbtnBloqueoCupo();
		
		
		panelInfoPago.add(titleInfoTar);
		panelInfoPago.add(new JLabel());
		panelInfoPago.add(titleInfoTi);
		panelInfoPago.add(new JLabel());
		
		panelInfoPago.add(numTarjeta);
		panelInfoPago.add(txtNumTarjeta);
		panelInfoPago.add(titular);
		panelInfoPago.add(txtNameTitular);
		panelInfoPago.add(lbFechaExpiracion);
		panelInfoPago.add(txtFechaExpiracion);
		panelInfoPago.add(labelCC);
		panelInfoPago.add(txtCC);
		panelInfoPago.add(lbCvv);
		panelInfoPago.add(txtCvv);
		panelInfoPago.add(labelPasarelaPago);
		panelInfoPago.add(pasarelasPago);
		panelInfoPago.add(monto);
		panelInfoPago.add(txtMonto);
		panelInfoPago.add(new JLabel());
		panelInfoPago.add(new JLabel());
		panelInfoPago.add(new JLabel());
		panelInfoPago.add(pagar);
		panelInfoPago.add(new JLabel());
		panelInfoPago.add(bloqueoCupo);
		
		
		for(int i=0;i<23;i++) {
			panelInfoPago.add(new JLabel());
		}
		
		panelPago.add(panelInfoPago,BorderLayout.CENTER);
		
		JPanel panelNewConductor= new JPanel();
		panelNewConductor.setLayout(new GridLayout(8,4));
		
		JLabel lbLicencia = new JLabel("   NUEVO CONDUCTOR   ");
		panelNewConductor.add(new JLabel());
		lbLicencia.setHorizontalAlignment(JLabel.CENTER);
		panelNewConductor.add(lbLicencia);
		panelNewConductor.add(new JLabel());
		panelNewConductor.add(new JLabel());
		
		JLabel lbNumLicencia = new JLabel("Numero de licencia:");
		txtNumlicencia=new JTextField(4);
		
		JLabel lbPais = new JLabel("Pais:");
		txtPais=new JTextField(4);
		
		JLabel lbFechaVen = new JLabel("Fecha vencimiento(yyyy/dd/MM):");
		txtFechaVencimiento=new JTextField(4);
		
		JLabel lbIdReserva = new JLabel("Id reserva: ");
		idReserva=new JTextField(4);
		
		agregarConductor= new JButton("Agregar");
		panelNewConductor.add(lbNumLicencia);
		panelNewConductor.add(txtNumlicencia);
		panelNewConductor.add(new JLabel());
		panelNewConductor.add(new JLabel());
		panelNewConductor.add(lbPais);
		panelNewConductor.add(txtPais);
		panelNewConductor.add(new JLabel());
		panelNewConductor.add(new JLabel());
		panelNewConductor.add(lbFechaVen);
		panelNewConductor.add(txtFechaVencimiento);
		panelNewConductor.add(new JLabel());
		panelNewConductor.add(new JLabel());
		panelNewConductor.add(lbIdReserva);
		panelNewConductor.add(idReserva);
		panelNewConductor.add(new JLabel());
		panelNewConductor.add(new JLabel());
		panelNewConductor.add(new JLabel());
		agregarConductor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){ 
            	if (txtNumlicencia.getText().equals("")||txtPais.getText().equals("")||
            	txtFechaVencimiento.getText().equals("") || idReserva.getText().equals("")){
             		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Llena todos los campos!!", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            	}else{
            		boolean fechaValida= sistemaDeReservasp.verificarFecha(txtFechaVencimiento.getText());
            		if(fechaValida==true) {
            			InfoReserva reserva= sistemaDeReservas.encontrarReservaDelCliente(idReserva.getText());
            			if(reserva==null) {
                     		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Reserva no encontrada", "Campos vacios", JOptionPane.WARNING_MESSAGE);
            			}else {
            				
            				Conductor newConductor= new Conductor( new Licencia(txtNumlicencia.getText(),txtPais.getText(), sistemaDeReservas.generarFechaSinHora(txtFechaVencimiento.getText().split("/"))));
            				ArrayList<Conductor> conductores= reserva.getConductor(); 
            				conductores.add(newConductor);
            				reserva.setConductor(conductores);
            				
            				try {
        						sistemaDeReservas.salavarReservas();
        					} catch (IOException e1) {
        						// TODO Auto-generated catch block
        						e1.printStackTrace();
        					}
            				txtNumlicencia.setText("");
            				txtPais.setText("");
                        	txtFechaVencimiento.setText(""); 
                        	idReserva.setText("");
            				
            			}
            		}else {
                 		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Ingresa una fecha valida!!", "Fecha invalida", JOptionPane.WARNING_MESSAGE);
            		}
            		
            	}
            	
            }});
		panelNewConductor.add(agregarConductor);
		
		for(int i=0;i<10;i++) {
			panelNewConductor.add(new JLabel());
		}
		
		
		ImageIcon iconpago = new ImageIcon("iconos\\Pago.png");
		ImageIcon iconNewConduc = new ImageIcon("iconos\\iconoMod.png");
		Image modPago = iconpago.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		Image modCond = iconNewConduc.getImage().getScaledInstance(17, 17, Image.SCALE_SMOOTH);
		ImageIcon resizedModEmpleado = new ImageIcon(modPago);
		ImageIcon resizedModCar = new ImageIcon(modCond);
		ventanaPago.add("Registrar/Modificar datos empleado",panelInfoPago);
		ventanaPago.add("Agregar conductor",panelNewConductor);
		ventanaPago.setIconAt(0, resizedModEmpleado);
		ventanaPago.setIconAt(1, resizedModCar);
		add(ventanaPago);
			
		}
	
	
	public void setbtnBloqueoCupo(){
		bloqueoCupo=new JButton("Bloqueo cupo");
		bloqueoCupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            	
            	if(pasarelaSeleccionada==null) {
             		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Selecciona una pasarela de pago!!", "Pasarela no seleccionada", JOptionPane.WARNING_MESSAGE);
            	}else if(txtNumTarjeta.getText().equals("")||txtNameTitular.getText().equals("")||
            			txtFechaExpiracion.getText().equals("")||txtCC.getText().equals("")||
            			txtCvv.getText().equals("")||txtMonto.getText().equals("")) {
             		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Llena todos los campos!!", "Campos vacios", JOptionPane.WARNING_MESSAGE);

            	}else { 
            		
    		 		try {
						Class clase = Class.forName("modelo."+pasarelaSeleccionada);
						
						pasarelaPago = (PasarelaPago) clase.getDeclaredConstructor(null).newInstance(null);
						
						String valido= pasarelaPago.verificarInformacion(txtNumTarjeta.getText(), txtNameTitular.getText(),
		            			txtFechaExpiracion.getText(), txtCC.getText(),
		            			txtCvv.getText(),txtMonto.getText(),tarjetasDisponibles);
						
						
	            	if(valido.equals("fecha Invalida")) { 
                 		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Ingresa la fecha en este formato  MM/AA!!", "formato invalido", JOptionPane.WARNING_MESSAGE);
            		}else if(valido.equals("titular invalido")) { 
                 		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Ingrese un titular valido!!", "Titular invalido", JOptionPane.WARNING_MESSAGE);
            		}else if(valido.equals("Tu tarjeta ha expirado!!")) {
                		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Tu tarjeta ha expirado!!","Fecha invalida",JOptionPane.WARNING_MESSAGE);
            		}else if(valido.equals("cvv invalido")) {
                		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Ingresa un CVV valido!!","CVV incorrecto",JOptionPane.WARNING_MESSAGE);
            		}else if(valido.equals( "Numero de tarjeta invalido")) {
                		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Numero de tarjeta invalido","Tarjeta invalida",JOptionPane.WARNING_MESSAGE);
            		}else if(valido.equals("Documento no valido")) {
                		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Documento no valido","Documento no valido",JOptionPane.WARNING_MESSAGE);
            		} else if(valido.equals("excede el monto")) { 
                    	JOptionPane.showMessageDialog(InterfazEmpleado.this, "Excede el monto","Pago invalido",JOptionPane.WARNING_MESSAGE);
            		}else { 
                		for(int i=0;i<tarjetasDisponibles.size();i++) {
                			
                			if(tarjetasDisponibles.get(i).getNameTitular().equals(txtNameTitular.getText().toUpperCase())) {
                				tarjetaReserMonto=tarjetasDisponibles.get(i);
                				if(tarjetasDisponibles.get(i).getMonto()>= Integer.parseInt(txtMonto.getText())) {
                					tarjetasDisponibles.get(i).setMontoReservado(Integer.parseInt(txtMonto.getText()));
                					tarjetasDisponibles.get(i).setMonto(tarjetasDisponibles.get(i).getMonto()-Integer.parseInt(txtMonto.getText()));
                        		}
                			}
                		}
                		
                		try {
    						sistemaDeReservas.guardarTarjetas();
    					} catch (IOException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
                		sistemaDeReservas.setTarjetasPagoSimulado(tarjetasDisponibles);
                		txtNumTarjeta.setText("");
                		txtNameTitular.setText("");
            			txtFechaExpiracion.setText("");
            			txtCC.setText("");
            			txtCvv.setText("");
            			txtMonto.setText("");
            			
            		}
    		 		}catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						System.out.println("No existe la pasarela"+ pasarelaSeleccionada);
						e2.printStackTrace();
					}catch (Exception e1) {
						System.out.println("Error!!");
						e1.printStackTrace();
					}
            	}

                }});
		
	}
	
	public void setPasarelas(ArrayList<String> pasarelasDispo) {
		this.pasarelasPago=new JComboBox<String>();
		for(int i=0;i<pasarelasDispo.size();i++) {
			pasarelasPago.addItem(pasarelasDispo.get(i));
		}
		
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
    
    public boolean existeTitular(String titular) {
    	
    	for(int i=0;i<tarjetasDisponibles.size();i++) {
			
			if(tarjetasDisponibles.get(i).getNameTitular().equals(titular.toUpperCase())) {
				return true;
			}

		}
    	
    	return false;

    }
    
    public boolean existeCVV(String cvv) {
    	for(int i=0;i<tarjetasDisponibles.size();i++) {
			
			if(tarjetasDisponibles.get(i).getCVV().equals(cvv)) {
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
    
    public boolean existeTar(String numTar) {
    	for(int i=0;i<tarjetasDisponibles.size();i++) {
			
			if(tarjetasDisponibles.get(i).getNumeroTarjeta().equals(numTar)) {
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
    
	public void setBtnPagar(){
		pagar= new JButton("Realizar pago");
		pagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            	if(pasarelaSeleccionada==null) {
             		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Selecciona una pasarela de pago!!", "Pasarela no seleccionada", JOptionPane.WARNING_MESSAGE);
            	}else if(txtNumTarjeta.getText().equals("")||txtNameTitular.getText().equals("")||
            			txtFechaExpiracion.getText().equals("")||txtCC.getText().equals("")||
            			txtCvv.getText().equals("")||txtMonto.getText().equals("")) {
             		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Llena todos los campos!!", "Campos vacios", JOptionPane.WARNING_MESSAGE);

            	}else { 
            		
    		 		try {
						Class clase = Class.forName("modelo."+pasarelaSeleccionada);
						
						pasarelaPago = (PasarelaPago) clase.getDeclaredConstructor(null).newInstance(null);
						
						String valido= pasarelaPago.verificarInformacion(txtNumTarjeta.getText(), txtNameTitular.getText(),
		            			txtFechaExpiracion.getText(), txtCC.getText(),
		            			txtCvv.getText(),txtMonto.getText(),tarjetasDisponibles);
						
						
	            	if(valido.equals("fecha Invalida")) { 
                 		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Ingresa la fecha en este formato  MM/AA!!", "formato invalido", JOptionPane.WARNING_MESSAGE);
            		}else if(valido.equals("titular invalido")) { 
                 		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Ingrese un titular valido!!", "Titular invalido", JOptionPane.WARNING_MESSAGE);
            		}else if(valido.equals("Tu tarjeta ha expirado!!")) {
                		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Tu tarjeta ha expirado!!","Fecha invalida",JOptionPane.WARNING_MESSAGE);
            		}else if(valido.equals("cvv invalido")) {
                		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Ingresa un CVV valido!!","CVV incorrecto",JOptionPane.WARNING_MESSAGE);
            		}else if(valido.equals( "Numero de tarjeta invalido")) {
                		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Numero de tarjeta invalido","Tarjeta invalida",JOptionPane.WARNING_MESSAGE);
            		}else if(valido.equals("Documento no valido")) {
                		JOptionPane.showMessageDialog(InterfazEmpleado.this, "Documento no valido","Documento no valido",JOptionPane.WARNING_MESSAGE);
            		} else if(valido.equals("excede el monto")) { 
                    	JOptionPane.showMessageDialog(InterfazEmpleado.this, "Excede el monto","Pago invalido",JOptionPane.WARNING_MESSAGE);
            		}else { 
            			encontrarTarjeta(txtNumTarjeta.getText());
            			
            			int pagoRealizado= pasarelaPago.realizarPago(tarjetaPago, Integer.parseInt(txtMonto.getText()));
            			pasarelaPago.guardarTransaccion(String.valueOf(pagoRealizado),tarjetaPago);
                		txtNumTarjeta.setText("");
                		txtNameTitular.setText("");
            			txtFechaExpiracion.setText("");
            			txtCC.setText("");
            			txtCvv.setText("");
            			txtMonto.setText("");
                		try {
    						sistemaDeReservas.guardarTarjetas();
    						
    					} catch (IOException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
                		sistemaDeReservas.setTarjetasPagoSimulado(tarjetasDisponibles);
            		}
    		 		}catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						System.out.println("No existe la pasarela"+ pasarelaSeleccionada);
						e2.printStackTrace();
					}catch (Exception e1) {
						System.out.println("Error!!");
						e1.printStackTrace();
					}
            	}
            
		 }});
	}
						
						
		
	
	
}
