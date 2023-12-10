package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Atv;
import modelo.BicicletaElectrica;
import modelo.Carro;
import modelo.Conductor;
import modelo.InfoReserva;
import modelo.Licencia;
import modelo.Moto;
import modelo.Seguro;
import modelo.Vehiculo;

public class InterfazCliente extends JFrame implements ActionListener {
	private static SistemaDeReservas sistemaDeReservas;
	private JTabbedPane reservarVehiculo;
	private JTabbedPane modDatosReserva;

	private JPanel panelReservarVehiculo;
	private JPanel panelReserCentro;
	private JPanel panelReserNorth;
	private JPanel panelReserSouth;
	private JPanel panelReserEste;
	private JPanel panelInfovehiculo;
	private JComboBox<String> carros ;
	private JComboBox<String> seguros;
	private JComboBox<String> vehiculosParaReservar;
	private JComboBox<String> motos;
	private JComboBox<String> atvs;
	private JComboBox<String> bicisElectricas;
	private JList<String> reservas;
	private JButton reservarVehículo;
	private JButton modificarReserva;
	private String idReservaSeleccionada;
	private String carroSeleccionado=null;
	private String seguroSeleccionado=null;
    
	private String idMoto;
	private String idAtv;
	private String idBici;
	
	private InfoReserva reservaSeleccionada;
	private JList<String> consultaCarroas;
	private Carro carroSeleccionado2=null;
	private Moto motoSeleccionada;
	private Atv atvSeleccionada;
	private BicicletaElectrica biciSeleccionada;
	
	private  JRadioButton carro;
	private  JRadioButton moto;
	private  JRadioButton atv;
	private  JRadioButton bibiElec;

	
	private JPanel panelModificarReserva;
	
	private JTextField txtFechaSalida;

	private JTextField txtSedeSalida;
	
	private JTextField txtFechaDeVuelta;
	
	private JTextField txtSedeDeVuelta;
	
	private JTextField txtHoraRecoger;
	
	private JTextField txtNumTarjeta;
	
	private JTextField txtFechaExpiracion;
	
	private JTextField txtCvv;
	
	private JTextField txtNumlicencia;
	
	private JTextField txtPais;
	
	private JTextField txtFechaVencimiento;
	
	private JTextField txtPrecio30;
	
	private ArrayList<JTextField> txtFields;
	
	private JTextField txtModelo;
	
	private JTextField txtPlaca;
	
	private JTextField txtMarca;

	private JTextField txtColor;
	
	private JTextField txtTransmision;
	
	private JTextField txtEstado;
	
	private JTextField txtCategoria;
	
	private JTextField txtUbicacion;
	
	private JTextField txtprecio;
	private String tipoVehiculo;
	
	 
	
	
	
	public InterfazCliente(ArrayList<String> reservasp,ArrayList<Seguro> segurosp,
			ArrayList<Carro> carrosDisponibles, SistemaDeReservas sistemaDeReservasp,
			ArrayList<Moto> motosp,ArrayList<BicicletaElectrica> biciElectricap,ArrayList<Atv> atvp) {
		this.sistemaDeReservas = sistemaDeReservasp;
		int tamX =850;
		int tamY=550;
		setTitle("CLIENTE");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(tamX,tamY);
		setLayout(new BorderLayout());
		//setResizable(false);
		reservarVehiculo= new JTabbedPane();
		reservarVehiculo.setFocusable(false);
		
		panelReservarVehiculo = new JPanel();
		panelReservarVehiculo.setLayout(new BorderLayout());
		
		panelReserNorth=new JPanel();
		
		panelReserNorth.setLayout(new GridLayout(2,4));
		panelReserNorth.add(new JLabel());
		
		JLabel reserva=new JLabel("Elige un tipo de vehículo");
		reserva.setHorizontalAlignment(JLabel.CENTER);
		panelReserNorth.add(reserva);
		panelReserNorth.add(new JLabel());
		panelReserNorth.add(new JLabel());
		panelReserNorth.add(new JLabel());
		
		carro= new JRadioButton("Carro");
		carro.addActionListener(InterfazCliente.this);
		panelReserNorth.add(carro);
		moto= new JRadioButton("Moto");
		moto.addActionListener(this);
		panelReserNorth.add(moto);
		atv= new JRadioButton("Atv");
		atv.addActionListener(this);
		panelReserNorth.add(atv);
		bibiElec= new JRadioButton("Bicicleta eléctrica");
		bibiElec.addActionListener(this);
		panelReserNorth.add(bibiElec);
		panelReserNorth.add(new Label());
		
		panelReservarVehiculo.add(panelReserNorth,BorderLayout.NORTH);
		
		panelReserCentro= new JPanel();
		
		panelReserCentro = panelReserCentro;
		JPanel panelReserCentroCentro= new JPanel();
		this.panelReserCentro.setLayout(new BorderLayout());
		panelReserCentroCentro.setLayout(new GridLayout(7,2));

		
		JLabel lbFechaSalida = new JLabel("Fecha a reclamar el vehículo:");
		txtFechaSalida = new JTextField(4);
	    
		JLabel lbSedeSalida = new JLabel("Sede donde desea recoger el vehículo:");
		txtSedeSalida = new JTextField(4);
		
		JLabel lbFechaDeVuelta = new JLabel("Fecha a entregar el vehículo: ");
		txtFechaDeVuelta = new JTextField(4);
		
		JLabel lbSedeDeVuelta = new JLabel("Sede donde desea entregar el vehículo: ");
		txtSedeDeVuelta = new JTextField(4);
		
		panelReserCentroCentro.add(new JLabel("Formato de fechas y horas (YYYY.DD.MM.HH.mm) "));
		panelReserCentroCentro.add(new JLabel("ingresar fecha sin hora (YYYY.DD.MM.00.00)"));
		
		panelReserCentroCentro.add(lbFechaSalida);

		panelReserCentroCentro.add(txtFechaSalida);

		panelReserCentroCentro.add(lbSedeSalida);

		panelReserCentroCentro.add(txtSedeSalida);

		panelReserCentroCentro.add(lbFechaDeVuelta);

		panelReserCentroCentro.add(txtFechaDeVuelta);

	    
		panelReserCentroCentro.add(lbSedeDeVuelta);
		panelReserCentroCentro.add(txtSedeDeVuelta);
		
		
		
		panelReserSouth=new JPanel();
		panelReserSouth.setLayout(null); 
		JLabel lbsSeguros= new JLabel("Seguros:");
		lbsSeguros.setBounds(1, 7, 51,30); 
		panelReserSouth.add(lbsSeguros);
		setSeguros(segurosp);
		seguros.setBounds(56, 4, 173,39 );
		seguros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JComboBox seguros = (JComboBox) e.getSource();
            	String seguro= (String)seguros.getSelectedItem();
                for (int i = 0; i < seguros.getItemCount(); i++) {
                	String elemento = (String) seguros.getItemAt(i);
                    if(seguro.equals(elemento)) {
                    	seguroSeleccionado=seguro;
                    }
                }
            }});
		panelReserSouth.add(seguros);
		panelReserCentroCentro.add(panelReserSouth,BorderLayout.SOUTH);
		panelReserCentro.add(panelReserCentroCentro,BorderLayout.CENTER);
		
		
		
		JPanel panelMetodoPago = new JPanel();
		JPanel panelMetodoPagoCentro = new JPanel();
		panelMetodoPagoCentro.setLayout(new GridLayout(4,4));
		JLabel lbMetodopago = new JLabel("       METODO DE PAGO       ");
		
		JLabel lbNumTarjeta = new JLabel("Numero de tarjeta:      ");
		txtNumTarjeta = new JTextField(4);
		
		JLabel lbFechaExpiracion = new JLabel("Fecha de expiración:      ");
		txtFechaExpiracion = new JTextField(4);
		
		JLabel lbCvv = new JLabel("CVV:      ");
		txtCvv= new JTextField(4);
		
		JLabel lbLicencia = new JLabel("       DATOS LICENCIA       ");
		
		JLabel lbNumLicencia = new JLabel("Numero de licencia:");
		txtNumlicencia=new JTextField(4);
		
		JLabel lbPais = new JLabel("Pais:");
		txtPais=new JTextField(4);
		
		JLabel lbFechaVen = new JLabel("Fecha vencimiento:");
		txtFechaVencimiento=new JTextField(4);
		
		panelMetodoPagoCentro.add(lbMetodopago);
		panelMetodoPagoCentro.add(new Label());
		panelMetodoPagoCentro.add(lbLicencia);
		panelMetodoPagoCentro.add(new Label());
		panelMetodoPagoCentro.add(lbNumTarjeta);
		panelMetodoPagoCentro.add(txtNumTarjeta);
		panelMetodoPagoCentro.add(lbNumLicencia);
		panelMetodoPagoCentro.add(txtNumlicencia);
		panelMetodoPagoCentro.add(lbFechaExpiracion);
		panelMetodoPagoCentro.add(txtFechaExpiracion);
		panelMetodoPagoCentro.add(lbPais);
		panelMetodoPagoCentro.add(txtPais);
		panelMetodoPagoCentro.add(lbCvv);
		panelMetodoPagoCentro.add(txtCvv);
		panelMetodoPagoCentro.add(lbFechaVen);
		panelMetodoPagoCentro.add(txtFechaVencimiento);
		
		panelMetodoPago.add(panelMetodoPagoCentro,BorderLayout.CENTER);
		panelMetodoPago.add(new JPanel(),BorderLayout.EAST);
		
		panelReserCentro.add(panelMetodoPago,BorderLayout.SOUTH);
		panelReserCentro.add(panelMetodoPago,BorderLayout.SOUTH);
		panelReserCentro.add(new JPanel(),BorderLayout.EAST);
		panelReservarVehiculo.add(panelReserCentro,BorderLayout.CENTER);
		
		
		panelReserEste=new JPanel();
		panelReserEste.setLayout(new GridLayout(14,1));
		panelReserEste.add(new JLabel("        Tus reservas        "));
		setReservas(reservasp);
        reservas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                	idReservaSeleccionada = reservas.getSelectedValue();   
                	System.out.println("reserva selec id"+idReservaSeleccionada);
                	if(idReservaSeleccionada.equals("No tienes reservas")) {
                		//no hacer nada
                	}else {
                    reservaSeleccionada= sistemaDeReservas.encontrarReservaDelCliente(idReservaSeleccionada);
                	txtFechaSalida.setText(reservaSeleccionada.generarFechaAenseñarI());

                	txtSedeSalida.setText(reservaSeleccionada.getSedeEntrega());
                	
                	txtFechaDeVuelta.setText(reservaSeleccionada.generarFechaAenseñarE());
                	
                	txtSedeDeVuelta.setText(reservaSeleccionada.getSedeDevuelta());
                	}
                	
                }}});
		JScrollPane scrollPane = new JScrollPane(reservas);
		scrollPane.setPreferredSize(new Dimension(210, 150));
		panelReserEste.add(scrollPane) ;
		modificarReserva=new JButton("Modificar reserva");
		modificarReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(idReservaSeleccionada==null) {
            		JOptionPane.showMessageDialog(InterfazCliente.this, "Selecciona una de tus reservas!!", "Reserva no seleccionada", JOptionPane.WARNING_MESSAGE);
            	} else if(seguroSeleccionado==null) {
             		JOptionPane.showMessageDialog(InterfazCliente.this, "Selecciona un seguro!!", "Reserva no seleccionada", JOptionPane.WARNING_MESSAGE);
            	}else if(idReservaSeleccionada.equals("No tienes reservas")){
            		JOptionPane.showMessageDialog(InterfazCliente.this, "No tienes reservas, que esperas para hacer la tuya!!", "Sin reservas", JOptionPane.WARNING_MESSAGE);
            	}else {
                	reservaSeleccionada.setFechaInicio(txtFechaSalida.getText());
                	reservaSeleccionada.setSedeEntrega(txtSedeSalida.getText());
                	reservaSeleccionada.setFechaEntrega(txtFechaDeVuelta.getText());
                	reservaSeleccionada.setSedeDevuelta(txtSedeDeVuelta.getText());
                	reservaSeleccionada.setSeguro(sistemaDeReservas.encontrarSeguroDelCliente(seguroSeleccionado));
                	reservaSeleccionada.setVehiculoEnReserva(carroSeleccionado);
//                	sistemaDeReservas.eliminarDocReser();
//                	sistemaDeReservas.actualizarReservas(reservaSeleccionada);
//                	try {
//						sistemaDeReservas.salavarReservas();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//                	System.out.println("Realizando acciones con: " + idReservaSeleccionada);
                	
                }
            }});
		
		panelReserEste.add(modificarReserva);
		panelReserEste.add(new JLabel("       Motos disponibles       "));
		setMotos(motosp);
		motos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JComboBox motoss = (JComboBox) e.getSource();
            	String moto= (String)motoss.getSelectedItem();
                for (int i = 0; i < motos.getItemCount(); i++) {
                	String elemento = (String) motos.getItemAt(i);
                    if(moto.equals(elemento)) {
                    	motoSeleccionada= sistemaDeReservas.encontrarMoto(moto);
                    	idMoto=moto;
                    	System.out.println("moto:"+motoSeleccionada.getPlaca());
                    }
                }        
            }});
		panelReserEste.add(motos);
		
		panelReserEste.add(new JLabel("       Atv disponibles       "));
		setAtvs(atvp);
		atvs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JComboBox atvss = (JComboBox) e.getSource();
            	String atv= (String)atvss.getSelectedItem();
                for (int i = 0; i < atvs.getItemCount(); i++) {
                	String elemento = (String) atvs.getItemAt(i);
                    if(atv.equals(elemento)) {
                    	atvSeleccionada= sistemaDeReservas.encontrarAtv(atv);
                    	idAtv=atv;
                    	System.out.println("idAtv:"+atvSeleccionada.getPlaca());
                    }
                }        
            }});
		panelReserEste.add(atvs);
		
		panelReserEste.add(new JLabel("       Bicicletas eléctricas disponibles       "));
		setBicisElectricas(biciElectricap);
		bicisElectricas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JComboBox bicis = (JComboBox) e.getSource();
            	String bici= (String)bicis.getSelectedItem();
                for (int i = 0; i < bicisElectricas.getItemCount(); i++) {
                	String elemento = (String) bicisElectricas.getItemAt(i);
                    if(bici.equals(elemento)) {
                    	biciSeleccionada= sistemaDeReservas.encontrarBiciElec(bici);
                    	idBici=bici;
                    	System.out.println("bici:"+biciSeleccionada.getPlaca());
                    }
                }        
            }});
		panelReserEste.add(bicisElectricas);
//		JLabel precio30= new JLabel()"Precio"
//		panelReserEste.add(txtPrecio30);
		
		panelReserEste.add(new JLabel("        Carros disponibles:        "));
		setCarros(carrosDisponibles);
		carros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JComboBox placas = (JComboBox) e.getSource();
            	String placa= (String)placas.getSelectedItem();
                for (int i = 0; i < carros.getItemCount(); i++) {
                	String elemento = (String) carros.getItemAt(i);
                    if(placa.equals(elemento)) {
                    	carroSeleccionado2= sistemaDeReservas.encontrarCarro(placa);
                    	carroSeleccionado=placa;
                    }
                }
            }});
		panelReserEste.add(carros);
		panelReserEste.add(new JPanel());
		reservarVehículo=new JButton("Reservar vehículo");
		reservarVehículo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           if(txtFechaSalida.getText().equals("") || txtSedeSalida.getText().equals("") ||
        		    txtFechaDeVuelta.getText().equals("") || txtSedeDeVuelta.getText().equals("") || txtNumTarjeta.getText().equals("") || txtFechaExpiracion.getText().equals("") || txtCvv.getText().equals("") ||txtNumlicencia.getText().equals("") || txtPais.getText().equals("") ||
        		    txtFechaVencimiento.getText().equals("") || tipoVehiculo==null)  {
        	   JOptionPane.showMessageDialog(InterfazCliente.this, "Llena tosdos los campos","Campos vacios", JOptionPane.WARNING_MESSAGE);
           }else if(seguroSeleccionado==null){
             		JOptionPane.showMessageDialog(InterfazCliente.this, "Selecciona un seguro!!", "Seguro no seleccionada", JOptionPane.WARNING_MESSAGE);
            	} else if(carroSeleccionado==null && atvSeleccionada==null && biciSeleccionada==null && motoSeleccionada== null) {
             		JOptionPane.showMessageDialog(InterfazCliente.this, "Selecciona un vehículo!!", "Vehículo no seleccionado", JOptionPane.WARNING_MESSAGE);
//            	}else if (idReservaSeleccionada.equals("No tienes reservas")){
//            		JOptionPane.showMessageDialog(InterfazCliente.this, "No tienes reservas, que esperas para hacer la tuya!!", "Sin reservas", JOptionPane.WARNING_MESSAGE);
            	}else {
            		
            		if((tipoVehiculo.equals("Moto")&& motoSeleccionada==null) || 
            				(tipoVehiculo.equals("Carro")&& carroSeleccionado2==null)||
            				(tipoVehiculo.equals("Atv")&& atvSeleccionada==null)||
            				(tipoVehiculo.equals("BicicletaElectrica")&& biciSeleccionada==null)
            				){
                		JOptionPane.showMessageDialog(InterfazCliente.this, "Selecciona vehículo correspondiente", "no corresponde el vehiculo", JOptionPane.WARNING_MESSAGE);
            		}else {
        				String[] partesI=txtFechaSalida.getText().split("\\.");
                		String[] partesFin=txtFechaDeVuelta.getText().split("\\.");
                		System.out.println("0"+partesI[0]);
                		System.out.println("1"+partesI[1]);
                		System.out.println("2"+partesI[2]);
                        LocalDateTime fechaInicio = LocalDateTime.of(Integer.parseInt(partesI[0]),
                        		Integer.parseInt(partesI[1]),
                        		Integer.parseInt(partesI[2]), 
                        		Integer.parseInt(partesI[3]),
                        		Integer.parseInt(partesI[4])); 
                        LocalDateTime fechaFin = LocalDateTime.of(Integer.parseInt(partesFin[0]),
                        		Integer.parseInt(partesFin[1]),
                        		Integer.parseInt(partesFin[2]), 
                        		Integer.parseInt(partesFin[3]),
                        		Integer.parseInt(partesFin[4]));
                        Vehiculo vehiculoSeleccionado=null;
                        if(tipoVehiculo.equals("Carro")) {
                        	vehiculoSeleccionado=carroSeleccionado2;
                        }else if(tipoVehiculo.equals("Moto")) {
                        	vehiculoSeleccionado=motoSeleccionada;
                        }else if(tipoVehiculo.equals("Atv")) {
                        	vehiculoSeleccionado=atvSeleccionada;
                        }else if(tipoVehiculo.equals("BicicletaElectrica")) {
                        	vehiculoSeleccionado=biciSeleccionada;
                        }
                        	
            			if(estaEnRango(fechaInicio,fechaFin,vehiculoSeleccionado.getHistorialInformes())) {
                    		JOptionPane.showMessageDialog(InterfazCliente.this, "Selecciona otra fecha!!", "Fecha no disponible", JOptionPane.WARNING_MESSAGE);
            			}else {
                            
                      	txtNumlicencia.getText();
            			ArrayList<Date> reservas=new ArrayList();
                        reservas.add(generarFecha(partesI));
                        reservas.add(generarFecha(partesFin));
                        double costoTotal=sistemaDeReservas.calcularCostoReserva(fechaInicio, fechaFin);
                        double costo30 = (costoTotal*0.3);
                        ArrayList<Conductor> conductores=new ArrayList<Conductor>();
                        System.out.println("costo" +sistemaDeReservas.calcularCostoReserva(fechaInicio, fechaFin)); 
                      	Conductor conductor= new Conductor(new Licencia(txtNumlicencia.getText(),
                      			                            txtPais.getText(),
                      	sistemaDeReservas.generarFechaSinHora(txtFechaVencimiento.getText().split("\\.")) ));
                      	LocalDateTime FechaInicio= sistemaDeReservas.generarFecha(txtFechaSalida.getText().split("\\."));
                      	LocalDateTime FechaDeVuelta=sistemaDeReservas.generarFecha(txtFechaDeVuelta.getText().split("\\."));
                      	conductores.add(conductor);
                      	String temñporada=sistemaDeReservas.identificarTemporada();
                      	InfoReserva nuevaReserva=null;
                   		if(tipoVehiculo.equals("Carro")) {
                          	 nuevaReserva=new InfoReserva(sistemaDeReservas.generarId(), costo30,costoTotal,conductores,"Tarjeta",sistemaDeReservas.encontrarSeguroDelCliente(seguroSeleccionado),
                          			temñporada,txtSedeSalida.getText(),txtSedeDeVuelta.getText(),FechaInicio , FechaDeVuelta,
                          			sistemaDeReservas.getClienteEnCurso(), carroSeleccionado,"Carro");
                   			
                		} else if(tipoVehiculo.equals("Moto")) {

                			 nuevaReserva=new InfoReserva(sistemaDeReservas.generarId(), costo30,costoTotal,conductores,"Tarjeta",sistemaDeReservas.encontrarSeguroDelCliente(seguroSeleccionado),
                          			temñporada,txtSedeSalida.getText(),txtSedeDeVuelta.getText(),FechaInicio , FechaDeVuelta,
                          			sistemaDeReservas.getClienteEnCurso(), motoSeleccionada.getPlaca(),"Moto");
                   			
                   		}else if(tipoVehiculo.equals("Atv")) {
                			 nuevaReserva=new InfoReserva(sistemaDeReservas.generarId(), costo30,costoTotal,conductores,"Tarjeta",sistemaDeReservas.encontrarSeguroDelCliente(seguroSeleccionado),
                          			temñporada,txtSedeSalida.getText(),txtSedeDeVuelta.getText(),FechaInicio , FechaDeVuelta,
                          			sistemaDeReservas.getClienteEnCurso(), atvSeleccionada.getPlaca(),"Atv");
                       			 
                       	}else if(tipoVehiculo.equals("BicicletaElectrica")){
                			 nuevaReserva=new InfoReserva(sistemaDeReservas.generarId(), costo30,costoTotal,conductores,"Tarjeta",sistemaDeReservas.encontrarSeguroDelCliente(seguroSeleccionado),
                          			temñporada,txtSedeSalida.getText(),txtSedeDeVuelta.getText(),FechaInicio , FechaDeVuelta,
                          			sistemaDeReservas.getClienteEnCurso(), biciSeleccionada.getPlaca(),"BicicletaElectrica");

            		          }
            	
                    	try {
        					sistemaDeReservas.crearReserva(nuevaReserva);
        					sistemaDeReservas.agregarReservas(nuevaReserva);
        				} catch (IOException e1) {
        					// TODO Auto-generated catch block
        					e1.printStackTrace();
        				}
                  	
                   txtFechaSalida.setText(""); 
                   txtSedeSalida.setText("");
           		   txtFechaDeVuelta.setText("");
           		   txtSedeDeVuelta.setText("");
           		   txtNumTarjeta.setText("");
           		   txtFechaExpiracion.setText("");
           		   txtCvv.setText("");
           		   txtNumlicencia.setText(""); 
           		   txtPais.setText("") ;
           		   txtFechaVencimiento.setText("");
           		   setReservas(sistemaDeReservas.getIdsReservasDelCliente());
           		   
           		  JOptionPane.showMessageDialog(InterfazCliente.this, "precio 30%: "+costo30+"\n precio inicial total "+costoTotal, "Reserva registrada!!", JOptionPane.WARNING_MESSAGE);
            			
            			}
            		}
            	}
            	
            }});
		panelReserEste.add(reservarVehículo);
	
		panelReservarVehiculo.add(panelReserEste,BorderLayout.EAST);
		
		setPanelInfovehiculo(carrosDisponibles);
		
		ImageIcon iconReser = new ImageIcon("iconos\\iconReserva.png");


        Image reserNu = iconReser.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        ImageIcon resizedReser = new ImageIcon(reserNu);
		ImageIcon iconInfo = new ImageIcon("iconos\\iconoMod.png");


        Image infoNu = iconInfo.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        ImageIcon resizedInfo = new ImageIcon(infoNu);

		reservarVehiculo.add("Reservar vehículo",panelReservarVehiculo);
		reservarVehiculo.add("Consulta Carros",panelInfovehiculo);

		reservarVehiculo.setIconAt(0, resizedReser);
		reservarVehiculo.setIconAt(1, resizedInfo);

		
		
		add(reservarVehiculo);
	}
	
	public void setPanelInfovehiculo(ArrayList<Carro> carrosDisponibles) {
		panelInfovehiculo=new JPanel();
		panelInfovehiculo.setLayout(new BorderLayout());
		
		JPanel panelInfovehiculoCentro= new JPanel();
		panelInfovehiculoCentro.setLayout(new GridLayout(13,4));
	
		
		JLabel modelo = new JLabel("Modelo:");
		txtModelo= new JTextField(4);
		
		JLabel placa = new JLabel("Placa:");
		txtPlaca=new JTextField(4);
		
		JLabel marca = new JLabel("Marca:");
		txtMarca = new JTextField(4);
		
		JLabel color = new JLabel("Color:");
		txtColor= new JTextField(4);
		
		JLabel transmision = new JLabel("Transmision:");
		txtTransmision= new JTextField(4);
		
		JLabel estado = new JLabel("Estado:(si tiene algún problema)");
		txtEstado= new JTextField(4);
		
		JLabel ubicacion = new JLabel("Ubicacion:");
		txtUbicacion= new JTextField(4);
		
		JLabel lbcategoria = new JLabel("Categoría:");
		txtCategoria=new JTextField(4);
		
		JLabel lbPrecio = new JLabel("precio:");
		txtprecio=new JTextField(4);
		for(int i=0;i<4;i++) {
			panelInfovehiculoCentro.add(new JLabel());
		}
		panelInfovehiculoCentro.add(modelo);
		panelInfovehiculoCentro.add(txtModelo);
		panelInfovehiculoCentro.add(new JLabel());
		panelInfovehiculoCentro.add(new JLabel());
		
		panelInfovehiculoCentro.add(placa);
		panelInfovehiculoCentro.add(txtPlaca);
		panelInfovehiculoCentro.add(new JLabel());
		panelInfovehiculoCentro.add(new JLabel());
		
		panelInfovehiculoCentro.add(marca);
		panelInfovehiculoCentro.add(txtMarca);
		panelInfovehiculoCentro.add(new JLabel());
		panelInfovehiculoCentro.add(new JLabel());
		
		panelInfovehiculoCentro.add(color);
		panelInfovehiculoCentro.add(txtColor);
		panelInfovehiculoCentro.add(new JLabel());
		panelInfovehiculoCentro.add(new JLabel());
		
		panelInfovehiculoCentro.add(transmision);
		panelInfovehiculoCentro.add(txtTransmision);
		panelInfovehiculoCentro.add(new JLabel());
		panelInfovehiculoCentro.add(new JLabel());
		
		panelInfovehiculoCentro.add(estado);
		panelInfovehiculoCentro.add(txtEstado);
		panelInfovehiculoCentro.add(new JLabel());
		panelInfovehiculoCentro.add(new JLabel());
		
		panelInfovehiculoCentro.add(ubicacion);
		panelInfovehiculoCentro.add(txtUbicacion);
		panelInfovehiculoCentro.add(new JLabel());
		panelInfovehiculoCentro.add(new JLabel());
		
		panelInfovehiculoCentro.add(lbcategoria);
		panelInfovehiculoCentro.add(txtCategoria);
		panelInfovehiculoCentro.add(new JLabel());
		panelInfovehiculoCentro.add(new JLabel());
		
		panelInfovehiculoCentro.add(lbPrecio);
		panelInfovehiculoCentro.add(txtprecio);
		panelInfovehiculoCentro.add(new JLabel());
		panelInfovehiculoCentro.add(new JLabel());
		
		for(int i=0;i<4;i++) {
			panelInfovehiculo.add(new JLabel());
		}
		panelInfovehiculo.add(panelInfovehiculoCentro,BorderLayout.CENTER);
		
		JPanel east=new JPanel();
		consultaCarroas= new JList<String>();
		setConsultaCarros(carrosDisponibles);
		consultaCarroas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                	String placaCarro = consultaCarroas.getSelectedValue();
                    Carro carroSeleccionado= sistemaDeReservas.encontrarCarro(placaCarro);
                    txtModelo.setText(carroSeleccionado.getModelo());
                    txtEstado.setText(carroSeleccionado.getEstado());
                    txtTransmision.setText(carroSeleccionado.getTransmision());
                    txtPlaca.setText(carroSeleccionado.getPlaca());
                	
                    txtMarca.setText(carroSeleccionado.getMarca());
                	
                    txtColor.setText(carroSeleccionado.getColor());
                	
                    txtUbicacion.setText(carroSeleccionado.getUbicacion());
                	
                    txtCategoria.setText(carroSeleccionado.getCategoria());
                    txtprecio.setText( String.valueOf(carroSeleccionado.getPrecio()));
                	
                }}});
		JScrollPane scrollPane = new JScrollPane(consultaCarroas);
		
		east.add(scrollPane);
		panelInfovehiculo.add(east,BorderLayout.EAST);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == carro) {
			carro.setSelected(true);
			moto.setSelected(false);
			atv.setSelected(false);
			bibiElec.setSelected(false);
			tipoVehiculo="Carro";
			System.out.println("carro");
		}else if (e.getSource() == moto) {
			carro.setSelected(false);
			moto.setSelected(true);
			atv.setSelected(false);
			bibiElec.setSelected(false);
			tipoVehiculo="Moto";
			System.out.println("moto");
		}else if (e.getSource() == atv) {
			carro.setSelected(false);
			moto.setSelected(false);
			atv.setSelected(true);
			bibiElec.setSelected(false);
			tipoVehiculo="Atv";
			System.out.println("atv");
		}else if (e.getSource() == bibiElec) {
			carro.setSelected(false);
			moto.setSelected(false);
			atv.setSelected(false);
			bibiElec.setSelected(true);
			tipoVehiculo="BicicletaElectrica";
			System.out.println("bibiElec");
		}
		}
	
	public Date generarFecha(String[] partesFecha){
		int ano = Integer.parseInt(partesFecha[0]);
		int mes = Integer.parseInt(partesFecha[1]);
		int dia = Integer.parseInt(partesFecha[2]);
		int hora = Integer.parseInt(partesFecha[3]);
		int minuto = Integer.parseInt(partesFecha[4]);;
		Date fecha = new Date(ano, mes, dia,hora,minuto);
		return fecha;
	}
	
    public boolean estaEnRango(LocalDateTime fechaInicio, LocalDateTime fechaFin, ArrayList<LocalDateTime> reservas) {
    	boolean enRango=false;
    	for(int i=0;i<reservas.size();i++) {
    		if((reservas.get(i).isAfter(fechaInicio) || reservas.get(i).isEqual(fechaInicio)) &&
    			(reservas.get(i).isBefore(fechaFin) || reservas.get(i).isEqual(fechaFin)))
    		{
    			enRango=true;
    		}
    	}
    	return enRango;
    }

	private void setConsultaCarros(ArrayList<Carro> carrosDisponibles) {
        DefaultListModel<String> modeloLista = new DefaultListModel<>();

        for (int i = 0; i < carrosDisponibles.size(); i++) {
            modeloLista.addElement(carrosDisponibles.get(i).getPlaca());
        }

        this.consultaCarroas = new JList<>(modeloLista);
	}

	private void setCarros(ArrayList<Carro> carrosDisponibles) {
		this.carros=new JComboBox<String>();
		for(int i=0;i<carrosDisponibles.size();i++) {
			carros.addItem(carrosDisponibles.get(i).getPlaca());
		}
		
	}

	private void setMotos(ArrayList<Moto> motosDisponibles) {
		this.motos=new JComboBox<String>();
		for(int i=0;i<motosDisponibles.size();i++) {
			motos.addItem(motosDisponibles.get(i).getPlaca());
		}
	}

	private void setAtvs(ArrayList<Atv> atvsDisponibles) {
		this.atvs=new JComboBox<String>();
		for(int i=0;i<atvsDisponibles.size();i++) {
			atvs.addItem(atvsDisponibles.get(i).getPlaca());
		}
		
	}
	
	private void setBicisElectricas(ArrayList<BicicletaElectrica> bicisDisponibles) {
		this.bicisElectricas=new JComboBox<String>();
		for(int i=0;i<bicisDisponibles.size();i++) {
			bicisElectricas.addItem(bicisDisponibles.get(i).getPlaca());
		}
		
	}

	public void setSeguros( ArrayList<Seguro> seguros) {
		this.seguros=new JComboBox<String>();
		for(int i=0;i<seguros.size();i++) {
			this.seguros.addItem(seguros.get(i).getCobertura());;
		}
	}
	
//    public void setReservas(ArrayList<String> reservas) {
//        DefaultListModel<String> modeloLista = new DefaultListModel<>();
////        if(reservas ==null) {
////        	modeloLista.addElement("No tienes reservas");
////        }else {
//        for (int i = 0; i < reservas.size(); i++) {
//			System.out.println("reservas interfaz"+reservas.get(i));
//            modeloLista.addElement(reservas.get(i));
//        }
//        
//
//        this.reservas = new JList<>(modeloLista);
//    }
    
	public void setReservas(ArrayList<String> reservasp) {
        DefaultListModel<String> modeloLista = new DefaultListModel<>();

        for (int i = 0; i < reservasp.size(); i++) {
            modeloLista.addElement(reservasp.get(i));
        }

        this.reservas = new JList<>(modeloLista);
        
        reservas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                	idReservaSeleccionada = reservas.getSelectedValue();   
                	System.out.println("reserva selec id"+idReservaSeleccionada);
                	if(idReservaSeleccionada.equals("No tienes reservas")) {
                		//no hacer nada
                	}else {
                    reservaSeleccionada= sistemaDeReservas.encontrarReservaDelCliente(idReservaSeleccionada);
                	txtFechaSalida.setText(reservaSeleccionada.generarFechaAenseñarI());

                	txtSedeSalida.setText(reservaSeleccionada.getSedeEntrega());
                	
                	txtFechaDeVuelta.setText(reservaSeleccionada.generarFechaAenseñarE());
                	
                	txtSedeDeVuelta.setText(reservaSeleccionada.getSedeDevuelta());
                	}
                	
                }}});
		
	}
    

}
