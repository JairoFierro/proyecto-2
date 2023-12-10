package consola;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
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

import modelo.AdminLocal;
import modelo.Atv;
import modelo.BicicletaElectrica;
import modelo.Carro;
import modelo.Conductor;
import modelo.Empleado;
import modelo.InfoReserva;
import modelo.Licencia;
import modelo.Moto;
import modelo.usuario;


public class InterfazAdminLocal extends JFrame implements ActionListener {
	private static SistemaDeReservas sistemaDeReservas;
	private JTabbedPane ventanaAdminLocal;
	
	private JPanel panelModCarro;
	
	private JTextField txtNombreEmpleado;
	
	private JTextField txtSueldo;
	
	private JTextField txtUsuario;

	private JTextField txtNumeroDocumento;
	
	private JTextField txtSede;
	
	private JTextField txtModelo;
	
	private JTextField txtPlaca;
	
	private JTextField txtMarca;

	private JTextField txtColor;
	
	private JTextField txtTransmision;
	
	private JTextField txtEstado;
	
	private JTextField txtUbicacion;
	
	private JButton btnMod;
	
	private JButton btnRegistrar;
	
	private JButton btnGuardar;
	
	private JList<String> empleados;
	
	private ArrayList<Empleado> lstEmpleados;
	
	private Empleado empleadoSeleccionado;
	
	private AdminLocal adminLocalLogeado;
	private String tipoVehiculo;
	
	private  JRadioButton carro;
	private  JRadioButton moto;
	private  JRadioButton atv;
	private  JRadioButton bibiElec;
	
	private Carro carroSeleccionado2=null;
	private Moto motoSeleccionada;
	private Atv atvSeleccionada;
	private BicicletaElectrica biciSeleccionada;
	
	
	
	public InterfazAdminLocal(ArrayList<Empleado> empleadosp, SistemaDeReservas sistemaDeReservas) {
		this.sistemaDeReservas=sistemaDeReservas;
		lstEmpleados=empleadosp;
		setEmpleados(empleadosp);
		int tamX =700;
		int tamY=550;
		setTitle("ADMINISTRADOR LOCAL");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(tamX,tamY);
		setLayout(new BorderLayout());
		
		ventanaAdminLocal= new JTabbedPane();
		ventanaAdminLocal.setFocusable(false);
		
		setPanelModCarro();
		
		JPanel panelModReserEmpleado = new JPanel();
		
		JPanel panelTitle= new JPanel();
		panelTitle.setLayout(new GridLayout(1,3));
		panelTitle.add(new Label());
		JLabel title=new JLabel("INFORMACIÓN EMPLEADO");
		title.setHorizontalAlignment(JLabel.CENTER);
		panelTitle.add(title);
		panelTitle.add(new Label());
		panelModReserEmpleado.add(panelTitle,BorderLayout.NORTH);
		
		
		JPanel panelDatos= new JPanel();
		panelDatos.setLayout(new GridLayout(15,3));
		
		JLabel nombreEmpleado=new JLabel("Nombre del empleado:  ");
		txtNombreEmpleado= new JTextField(5);
		
		JLabel sueldo=new JLabel("Salario:  ");
		txtSueldo= new JTextField(4);
		
		JLabel usuario=new JLabel("Usuario:  ");
		txtUsuario= new JTextField(4);
		
		JLabel numeroDocumento=new JLabel("No Documento:  ");
		txtNumeroDocumento= new JTextField(4);
		
		JLabel sede=new JLabel("Sede en la que trabaja:  ");
		txtSede=new JTextField(4);
		
		panelDatos.add(nombreEmpleado);
		panelDatos.add(txtNombreEmpleado);
		panelDatos.add(new JLabel());
		panelDatos.add(sueldo);
		panelDatos.add(txtSueldo);
		panelDatos.add(new JLabel());
		panelDatos.add(usuario);
		panelDatos.add(txtUsuario);
		panelDatos.add(new JLabel());
		panelDatos.add(numeroDocumento);
		panelDatos.add(txtNumeroDocumento);
		panelDatos.add(new JLabel());
		panelDatos.add(sede);
		panelDatos.add(txtSede);
		panelDatos.add(new JLabel());
		
		for(int i=0;i<30;i++) {
			if(i==4) {
				btnRegistrar=new JButton("Registrar");
				btnRegistrar.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	if(txtNombreEmpleado.getText().equals("")||txtSueldo.getText().equals("")||
		            		txtUsuario.getText().equals("") ||txtNumeroDocumento.getText().equals("")||
		            		txtSede.getText().equals("")){
		            		JOptionPane.showMessageDialog(InterfazAdminLocal.this, "Llena todos los campos!!","Campo en blanco",JOptionPane.WARNING_MESSAGE);
		            		
		            	}else{
		            		Boolean registrado=verificarRegistro(lstEmpleados, txtNumeroDocumento.getText()); 
		            		if(registrado== false) {
		            			Empleado nuevoEmpleado= new Empleado(txtUsuario.getText(),"12345",
		            					txtSede.getText(), txtNombreEmpleado.getText(), 
		            					txtSueldo.getText(), txtNumeroDocumento.getText());
		            			registrarEmpleado(nuevoEmpleado);
		            			txtUsuario.setText("");
            					txtSede.setText("");
            					txtNombreEmpleado.setText(""); 
            					txtSueldo.setText("");
            					txtNumeroDocumento.setText("");
		            		}else {
		            			JOptionPane.showMessageDialog(InterfazAdminLocal.this,"Usuario Registrado" ,"Registrado",JOptionPane.WARNING_MESSAGE);
		            		}
		            		
		            	}
		       
		            	
		            }});
				panelDatos.add(btnRegistrar);
			}else {
				panelDatos.add(new JLabel());
			}
		}

		
		panelModReserEmpleado.add(panelDatos,BorderLayout.CENTER);
		
		JPanel infoEmpleados= new JPanel();
		infoEmpleados.setLayout(new GridLayout(5,1));
		

		infoEmpleados.add(new JLabel("         Elije un empleado"));
		empleados.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                	String docEmpleado = empleados.getSelectedValue();  
                	empleadoSeleccionado= sistemaDeReservas.obtenerEmpleado(docEmpleado);
            		txtUsuario.setText(empleadoSeleccionado.getLogin());
					txtSede.setText(empleadoSeleccionado.getSede());
					txtNombreEmpleado.setText(empleadoSeleccionado.getNombre()); 
					txtSueldo.setText(empleadoSeleccionado.getSalario());
					txtNumeroDocumento.setText(empleadoSeleccionado.getNumeroDoc());
                	
                }}});
		JScrollPane scrollPane = new JScrollPane(empleados);
		scrollPane.setPreferredSize(new Dimension(145, 90));
		infoEmpleados.add(scrollPane);
		JPanel infoEmpleadosSur=new JPanel();
		btnMod=new JButton("Modificar informacion");
		btnMod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(txtNombreEmpleado.getText().equals("")||txtSueldo.getText().equals("")||
            		txtUsuario.getText().equals("") ||txtNumeroDocumento.getText().equals("")||
            		txtSede.getText().equals("")){
            		JOptionPane.showMessageDialog(InterfazAdminLocal.this, "Selecciona un usuario!!","usuario no seleccionado",JOptionPane.WARNING_MESSAGE);
            		
            	}else{
            			empleadoSeleccionado.setNombre(txtNombreEmpleado.getText());
            			empleadoSeleccionado.setNumeroDoc(txtNumeroDocumento.getText());
            			empleadoSeleccionado.setSalario(txtSueldo.getText());
            			empleadoSeleccionado.setSedeName(txtSede.getText());
            			empleadoSeleccionado.setLogin(txtUsuario.getText());
            			
            			try {
							sistemaDeReservas.actualizarInfoEmpleado(empleadoSeleccionado);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
            			txtUsuario.setText("");
    					txtSede.setText("");
    					txtNombreEmpleado.setText(""); 
    					txtSueldo.setText("");
    					txtNumeroDocumento.setText("");
            		
            	}
       
            	
            }});
		infoEmpleadosSur.add(btnMod);
		infoEmpleados.add(infoEmpleadosSur,BorderLayout.SOUTH);
		panelModReserEmpleado.add(infoEmpleados,BorderLayout.EAST);
		
		ImageIcon iconEmpleado = new ImageIcon("iconos\\iconoMod.png");
		ImageIcon iconModCar = new ImageIcon("iconos\\iconModCar.png");
		Image modEmpleado = iconEmpleado.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		Image modCar = iconModCar.getImage().getScaledInstance(17, 17, Image.SCALE_SMOOTH);
		ImageIcon resizedModEmpleado = new ImageIcon(modEmpleado);
		ImageIcon resizedModCar = new ImageIcon(modCar);
		ventanaAdminLocal.add("Registrar/Modificar datos empleado",panelModReserEmpleado);
		ventanaAdminLocal.add("Actualizar información de un carro",panelModCarro);
		ventanaAdminLocal.setIconAt(0, resizedModEmpleado);
		ventanaAdminLocal.setIconAt(1, resizedModCar);
		add(ventanaAdminLocal);
		
	}
	
	
	public void setEmpleados(ArrayList<Empleado> empleados) {
        DefaultListModel<String> modeloLista = new DefaultListModel<>();

        for (int i = 0; i < empleados.size(); i++) {
            modeloLista.addElement(empleados.get(i).getNumeroDoc());
        }

        this.empleados = new JList<>(modeloLista);
		
	}
	
	
	
	
	public void setPanelModCarro() {
		this.panelModCarro= new JPanel();
		panelModCarro.setLayout(new BorderLayout());
		
		JPanel panelModCarroSur= new JPanel();
		panelModCarroSur.add(new JLabel());
		panelModCarroSur.add(new JLabel());
		panelModCarroSur.add(new JLabel());
		JLabel titleModCar = new JLabel("ACTUALIZAR");
		panelModCarroSur.setLayout(new GridLayout(2,4));
		titleModCar.setHorizontalAlignment(JLabel.CENTER);
		panelModCarroSur.add(titleModCar);
		panelModCarroSur.add(new JLabel());
		panelModCarroSur.add(new JLabel());
		panelModCarroSur.add(new JLabel());
		
		carro= new JRadioButton("Carro");
		carro.addActionListener(InterfazAdminLocal.this);
		panelModCarroSur.add(carro);
		moto= new JRadioButton("Moto");
		moto.addActionListener(this);
		panelModCarroSur.add(moto);
		atv= new JRadioButton("Atv");
		atv.addActionListener(this);
		panelModCarroSur.add(atv);
		bibiElec= new JRadioButton("Bicicleta eléctrica");
		bibiElec.addActionListener(this);
		panelModCarroSur.add(bibiElec);
		panelModCarroSur.add(new Label());
		
	
		panelModCarro.add(panelModCarroSur,BorderLayout.NORTH);
		
		
		
		
		JPanel panelModCarroCentro= new JPanel();
		panelModCarroCentro.setLayout(new GridLayout(11,4));
	
		JLabel placa = new JLabel("Placa:");
		txtPlaca=new JTextField(4);
		
		JLabel estado = new JLabel("Estado:");
		txtEstado= new JTextField(4);
		
		JLabel ubicacion = new JLabel("Ubicacion:");
		txtUbicacion= new JTextField(4);
		
		
		for(int i=0;i<4;i++) {
			panelModCarroCentro.add(new JLabel());
		}
		
		panelModCarroCentro.add(placa);
		panelModCarroCentro.add(txtPlaca);
		panelModCarroCentro.add(new JLabel());
		panelModCarroCentro.add(new JLabel());
		
		panelModCarroCentro.add(estado);
		panelModCarroCentro.add(txtEstado);
		panelModCarroCentro.add(new JLabel());
		panelModCarroCentro.add(new JLabel());
		
		panelModCarroCentro.add(ubicacion);
		panelModCarroCentro.add(txtUbicacion);
		panelModCarroCentro.add(new JLabel());
		panelModCarroCentro.add(new JLabel()); 
		
		panelModCarroCentro.add(new JLabel());
		btnGuardar= new JButton("Guardar");	
		btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(txtEstado.getText().equals("")||txtUbicacion.getText().equals("")||txtPlaca.getText().equals("")) {
            		JOptionPane.showMessageDialog(InterfazAdminLocal.this, "Llena todos los campos!!","Campos en blanco",JOptionPane.WARNING_MESSAGE);
            		
            	}else { 
            		if(tipoVehiculo== null) {
                 		JOptionPane.showMessageDialog(InterfazAdminLocal.this, "Selecciona un vehículo!!", "Vehículo no seleccionado", JOptionPane.WARNING_MESSAGE);
            		}else {
       
            		if( tipoVehiculo.equals("carro")) {
            			carroSeleccionado2=sistemaDeReservas.encontrarCarro(txtPlaca.getText());
            			if(motoSeleccionada==null) {
                     		JOptionPane.showMessageDialog(InterfazAdminLocal.this, "verifica informacion del vehiculo!!", "Vehículo no encontrado", JOptionPane.WARNING_MESSAGE);
            			}else {
            				for(int i=0;i< sistemaDeReservas.getListaCarros().size();i++) {
            					if(sistemaDeReservas.getListaCarros().get(i).getPlaca().equals(txtPlaca.getText())) {
            						sistemaDeReservas.getListaCarros().get(i).setEstado(txtEstado.getText());
            						sistemaDeReservas.getListaCarros().get(i).setUbicacion(txtUbicacion.getText());
            					}
            				}

            			try {
							sistemaDeReservas.actualizarCarros();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
            		}
            		}else if( tipoVehiculo.equals("Moto")) {
            			motoSeleccionada=sistemaDeReservas.encontrarMoto(txtPlaca.getText());
            			if(motoSeleccionada==null) {
                     		JOptionPane.showMessageDialog(InterfazAdminLocal.this, "verifica informacion del vehiculo!!", "Vehículo no encontrado", JOptionPane.WARNING_MESSAGE);
            			}else {
            				for(int i=0;i< sistemaDeReservas.getListaMotos().size();i++) {
            					if(sistemaDeReservas.getListaMotos().get(i).getPlaca().equals(txtPlaca.getText())) {
            					  sistemaDeReservas.getListaMotos().get(i).setEstado(txtEstado.getText());
            					  sistemaDeReservas.getListaMotos().get(i).setUbicacion(txtUbicacion.getText());
            					}
            				}
                			
                			
                			try {
    							sistemaDeReservas.actualizarMotos();
    						} catch (IOException e1) {
    							// TODO Auto-generated catch block
    							e1.printStackTrace();
    						}
            			}
            			
            		}else if( tipoVehiculo.equals("Atv")) {
            			atvSeleccionada=sistemaDeReservas.encontrarAtv(txtPlaca.getText());
            			if(atvSeleccionada==null) {
                     		JOptionPane.showMessageDialog(InterfazAdminLocal.this, "verifica informacion del vehiculo!!", "Vehículo no encontrado", JOptionPane.WARNING_MESSAGE);
            			}else {
            				for(int i=0;i< sistemaDeReservas.getListaAtvs().size();i++) {
            					if(sistemaDeReservas.getListaAtvs().get(i).getPlaca().equals(txtPlaca.getText())) {
            						sistemaDeReservas.getListaAtvs().get(i).setEstado(txtEstado.getText());
            						sistemaDeReservas.getListaAtvs().get(i).setUbicacion(txtUbicacion.getText());
            					}
            				}
            		
            			
            			try {
							sistemaDeReservas.actualizarAtvs();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
            			}
            		}else if( tipoVehiculo.equals("BicicletaElectrica")) {
            			biciSeleccionada=sistemaDeReservas.encontrarBiciElec(txtPlaca.getText());
            			if(biciSeleccionada==null) {
                     		JOptionPane.showMessageDialog(InterfazAdminLocal.this, "verifica informacion del vehiculo!!", "Vehículo no encontrado", JOptionPane.WARNING_MESSAGE);
            			}else {
            				for(int i=0;i< sistemaDeReservas.getListaBicisElectricas().size();i++) {
            					if(sistemaDeReservas.getListaBicisElectricas().get(i).getPlaca().equals(txtPlaca.getText())) {
            						sistemaDeReservas.getListaBicisElectricas().get(i).setEstado(txtEstado.getText());
            						sistemaDeReservas.getListaBicisElectricas().get(i).setUbicacion(txtUbicacion.getText());
            					}
            				}

            			try {
							sistemaDeReservas.actualizarBicis();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
            			
            		}
            		}
            		}
            		
            		
            		
            	}
            	
            }});
		panelModCarroCentro.add(btnGuardar);
		
		
		for(int i=0;i<20;i++) {
			panelModCarroCentro.add(new JLabel());
		}

		
		panelModCarro.add(panelModCarroCentro,BorderLayout.CENTER);
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
	
	public void registrarEmpleado(Empleado nuevoEmpleado) {
		try {
			sistemaDeReservas.registrarEmpleado(nuevoEmpleado);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Boolean verificarRegistro( ArrayList<Empleado> lstEmpleados, String numeroDoc) {
		Boolean registrado=false;
		for(int i=0;i<lstEmpleados.size();i++) {
			if(numeroDoc.equals(lstEmpleados.get(i).getNumeroDoc())) {
				registrado=true;
			}
		}
		return registrado;
	}
	
//	public static void main(String[] args) {
//		ArrayList<String> empleados = new ArrayList<String>();
//		empleados.add("hola casa de ");
//		empleados.add("hola");
//		empleados.add("perro");
//		empleados.add("casa");
//		empleados.add("casa");
//		empleados.add("casa");
//		empleados.add("casa");
//		empleados.add("casa");
//		empleados.add("casa");
//		empleados.add("casa");
//		sistemaDeReservas= new SistemaDeReservas();
//		InterfazAdminLocal interfazAdmin =new InterfazAdminLocal(empleados,sistemaDeReservas);
//		interfazAdmin.setLocationRelativeTo(null);
//		interfazAdmin.setVisible(true);		
//	}
//
}
