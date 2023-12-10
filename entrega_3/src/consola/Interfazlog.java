package consola;
import java.util.List;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import modelo.Cliente;
import modelo.usuario;
import modelo.AdminGeneral;
import modelo.AdminLocal;
import modelo.Empleado;
import modelo.Licencia;
import modelo.TarjetaCredito;

public class Interfazlog extends JFrame{
	private static Interfazlog interfaz;
	private JComboBox<String> usuario;
	private JLabel userNameL;
	private JLabel passwordL;
	private JLabel titulo;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JButton loginButton;
	private JTextField txtType;
	private String usuarioSelec;
	private JPanel pnRegistro;
	
	private JTextField txtNombreCliente;
	
	private JTextField txtCelular;
	
	private JTextField txtContraseña;
	
	private JTextField txtCorreo;

	private JTextField txtUserR;
	
	private JTextField txtFechaNacimiento;

	private JTextField txtNacionalidad;
	
	private JTextField txtNumeroLicencia;
	
	private JTextField txtPaisExpedicion;
	
	private JTextField txtVencimiento;
	
	private JTextField txtRutaDI;
	
	private JTextField txtRutaLic;
	
	private JTextField txtNumTarjeta;
	
	private JTextField txtFechaExpiracion;
	
	private JTextField txtCvv;
	
	private JButton btnregistrar;
	ArrayList<Empleado> listaEmpleados;
	ArrayList<AdminGeneral> listaAdminsGen;
	ArrayList<AdminLocal> listaAdminsLoc;
	ArrayList<Cliente> listaClientes;
	
	
	public Interfazlog()   {
		int tamX =700;
		int tamY=580;
		setTitle("Sistema de reservas");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(tamX,tamY);
		setResizable(false);
		
		JTabbedPane ventanaInterfazLogueo= new JTabbedPane();
		ventanaInterfazLogueo.setFocusable(false);
		
		
		JPanel pnLogueo= new JPanel();
		pnLogueo.setLayout(null);
		titulo= new JLabel("BIENVENIDO");
		titulo.setBounds(215, 50, 300,50);
		Font font = new Font("Impact", Font.PLAIN, 36);
		titulo.setFont(font);
		pnLogueo.add(titulo,BorderLayout.NORTH);
		

		userNameL = new JLabel("Usuario");
		userNameL.setBounds(115, 150, 80, 25);
		pnLogueo.add(userNameL);

		txtUser = new JTextField(20);
		txtUser.setBounds(195, 150, 220, 30);
		pnLogueo.add(txtUser);

		passwordL = new JLabel("Password");
		passwordL.setBounds(115, 200, 80, 25);
		pnLogueo.add(passwordL);

		txtPassword = new JPasswordField(20);
		txtPassword.setBounds(195, 200, 220, 30);
		pnLogueo.add(txtPassword);
		
		JLabel userLb = new JLabel("¿Que tipo de usuario eres?");
		userLb.setBounds(418, 125, 160 , 25);
		pnLogueo.add(userLb);
		usuario= new JComboBox<String>();
		usuario.addItem("Sin Usuario");
		usuario.addItem("Cliente");
		usuario.addItem("Administrador local");
		usuario.addItem("Administrador general");
		usuario.addItem("Empleado");
		usuario.setBounds(425, 150, 150,30);
		usuario.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        JComboBox usuarios = (JComboBox) e.getSource();
		        usuarioSelec= (String)usuarios.getSelectedItem();
		}});
		pnLogueo.add(usuario);
		
		SistemaDeReservas sistemaDeReservas= new SistemaDeReservas();
		//sistemaDeReservas.setClienteLogeado("g.chaparr");
		try {
			sistemaDeReservas.cargarTarjetas();
			sistemaDeReservas.cargarPasarelasPago();
			sistemaDeReservas.cargarUsuario();
			sistemaDeReservas.cargarReservas();
			sistemaDeReservas.cargarSeguros();
			sistemaDeReservas.cargarCarros();
			sistemaDeReservas.cargarMotos();
			sistemaDeReservas.cargarAtvs();
			sistemaDeReservas.cargarBiciElec();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		loginButton = new JButton("Entrar");
		loginButton.setBounds(250, 255, 100, 45);
		ActionListener listenLoginBtn=new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				String login = txtUser.getText();
                char[] passwordChar = txtPassword.getPassword();
                String pwrod = new String(passwordChar);
                int a=0;
                if(usuarioSelec==null || usuarioSelec=="Sin Usuario") {
                	JOptionPane.showMessageDialog(Interfazlog.this, "Selecciona un usuario!!","Usuario no seleccionado",JOptionPane.WARNING_MESSAGE);
                }else if(login.equals("")||pwrod.equals("")){
                	JOptionPane.showMessageDialog(Interfazlog.this, "Llena todos los campos!!","Campo en blanco",JOptionPane.WARNING_MESSAGE);
                }
                else{
                	System.out.println("usuario "+ usuarioSelec);
                	 ArrayList<usuario> listaUs = new ArrayList<usuario>();
                	if(usuarioSelec.equals("Cliente")){
                		listaUs= sistemaDeReservas.getListaUsClientes();
                	}
                	else if(usuarioSelec.equals("Administrador local")) 
                	{
                		listaUs= sistemaDeReservas.getListaUsAdminsLoc();
                	}
                	else if(usuarioSelec.equals("Administrador general")) 
                	{
                		AdminGeneral adminGen= new AdminGeneral("Admin.Gen", "1435");
                    }else if(usuarioSelec.equals("Empleado")) {
                    	listaUs= sistemaDeReservas.getListaUsEmpleados();
                    }
                    
    				if(usuarioSelec.equals("Cliente")) {
    					boolean entrar=Cliente.entrar(login,pwrod,listaUs);
    					if (entrar) {
//    						for(int i=0;i<sistemaDeReservas.getIdsReservasDelCliente().size();i++) {
//    						System.out.println("reservas"+sistemaDeReservas.getIdsReservasDelCliente().get(i));
//    						}
    						interfaz.setVisible(false);
    						sistemaDeReservas.setClienteEnCurso(login);
    						InterfazCliente interfazCli =new InterfazCliente(sistemaDeReservas.getIdsReservasDelCliente(),sistemaDeReservas.getSeguros(),
    								sistemaDeReservas.carrosDisponibles(),sistemaDeReservas,
    								sistemaDeReservas.getListaMotos(),sistemaDeReservas.getListaBicisElectricas(),
    								sistemaDeReservas.getListaAtvs());
    						interfazCli.setLocationRelativeTo(null);
    						interfazCli.setVisible(true);
    					}else {
    						JOptionPane.showMessageDialog(Interfazlog.this, "Regístrate!!","Usuario no registrado",JOptionPane.WARNING_MESSAGE);
    					}
    				}
    				else if(usuarioSelec.equals("Administrador local")) {
    					//boolean entrar=AdminLocal.entrar(login,pwrod,listaUs);
    					boolean entrar=true;
    					if (entrar) {
    						interfaz.setVisible(false);
    						ArrayList<String> empleados = new ArrayList<String>();
    						empleados.add("hola casa de ");
    						empleados.add("hola");
    						empleados.add("perro");
    						empleados.add("casa");
    						empleados.add("casa");
    						empleados.add("casa");
    						empleados.add("casa");
    						empleados.add("casa");
    						empleados.add("casa");
    						empleados.add("casa");
    						InterfazAdminLocal interAL = new InterfazAdminLocal(sistemaDeReservas.getListaEmpleados(),sistemaDeReservas);
    						interAL.setLocationRelativeTo(null);
    						interAL.setVisible(true);
    					}else {
    						JOptionPane.showMessageDialog(Interfazlog.this, "Regístrate!!","Usuario no registrado",JOptionPane.WARNING_MESSAGE);
    					}
    					
    				}
    				else if (usuarioSelec.equals("Administrador general")) {
    					if (login.equals("Admin.Gen") && pwrod.equals("1435")) {
    						interfaz.setVisible(false);
    						ArrayList<String> sedes = new ArrayList<String>();
    						sedes.add("Usaquen");
    						sedes.add("Chapinero");
    						sedes.add("Secundaria");
    						sedes.add("Principal");
    						InterfazAdminGeneral interAG = new InterfazAdminGeneral(sistemaDeReservas.totalCarros(),sedes,sistemaDeReservas.getSeguros(),sistemaDeReservas);
    						interAG.setLocationRelativeTo(null);
    						interAG.setVisible(true);
    					}else {
    						JOptionPane.showMessageDialog(Interfazlog.this,"No eres el administrador","Anuncio!!",JOptionPane.WARNING_MESSAGE);
    					}
    				}
    				else if(usuarioSelec.equals("Empleado")){
    					boolean entrar=Empleado.entrar(login,pwrod,listaUs);
    				if(entrar) {
    					interfaz.setVisible(false);
    					InterfazEmpleado interEM = new InterfazEmpleado(sistemaDeReservas.getPasarelasNames(),sistemaDeReservas,
    							                 sistemaDeReservas.getTarjetasPagoSimulado(),sistemaDeReservas.getReservas());
    					interEM.setLocationRelativeTo(null);
    					interEM.setVisible(true);
    				}else{
    					JOptionPane.showMessageDialog(Interfazlog.this, "Registrate!!","Usuario no registrado",JOptionPane.WARNING_MESSAGE);
    				}
                }

			}}
		};

		loginButton.addActionListener(listenLoginBtn);
		pnLogueo.add(loginButton);
		
		pnRegistro= new JPanel();
		pnRegistro.setLayout(null);
		JLabel title=new JLabel("REGISTRARSE");
		title.setBounds(195, 8, 300,50);
		title.setHorizontalAlignment(JLabel.CENTER);
		pnRegistro.add(title,BorderLayout.NORTH);
		
		JLabel titleDatosPer=new JLabel("DATOS PERSONALES");
		titleDatosPer.setBounds(80, 50, 150, 25);
		pnRegistro.add(titleDatosPer);
		
		JLabel lbNombreCliente=new JLabel("Nombre:");
		lbNombreCliente.setBounds(10, 85, 50, 25);
		pnRegistro.add(lbNombreCliente);
		
		txtNombreCliente=new JTextField(4);
		txtNombreCliente.setBounds(120, 85, 150, 25);
		pnRegistro.add(txtNombreCliente);
		
		JLabel lbCelular=new JLabel("Celular:");
		lbCelular.setBounds(10, 125, 50, 25);
		pnRegistro.add(lbCelular);
		txtCelular=new JTextField(4);
		txtCelular.setBounds(120, 125, 150, 25);
		pnRegistro.add(txtCelular);
		

		JLabel lbFechaNacimiento=new JLabel("Fecha nacimiento:");
		lbFechaNacimiento.setBounds(10, 165, 150, 25);
		pnRegistro.add(lbFechaNacimiento);
		txtFechaNacimiento=new JTextField(4);
		txtFechaNacimiento.setBounds(120, 165, 150, 25);
		pnRegistro.add(txtFechaNacimiento);
		
		JLabel lbNacionalidad=new JLabel("Nacionalidad:");
		lbNacionalidad.setBounds(10, 205, 80, 25);
		pnRegistro.add(lbNacionalidad);
		txtNacionalidad=new JTextField(4);
		txtNacionalidad.setBounds(120, 205, 150, 25);
		pnRegistro.add(txtNacionalidad);
		
		
		JLabel lbCorreo=new JLabel("Correo :");
		lbCorreo.setBounds(10, 245, 150, 25);
		pnRegistro.add(lbCorreo);
		txtCorreo=new JTextField(4);
		txtCorreo.setBounds(120, 245, 150, 25);
		pnRegistro.add(txtCorreo);
		
		JLabel lbDocumento=new JLabel("CC:");
		lbDocumento.setBounds(10, 280, 150, 25);
		pnRegistro.add(lbDocumento);
		txtRutaDI=new JTextField(4);
		txtRutaDI.setBounds(120, 280, 150, 25);
		pnRegistro.add(txtRutaDI);
		
		JLabel titleDatosLic=new JLabel("DATOS LICENCIA");
		titleDatosLic.setBounds(80, 325, 150, 25);
		pnRegistro.add(titleDatosLic);
		
		JLabel lbNumeroLicencia=new JLabel("Numero licencia:");
		lbNumeroLicencia.setBounds(10, 355, 120, 25);
		pnRegistro.add(lbNumeroLicencia);
		txtNumeroLicencia=new JTextField(4);
		txtNumeroLicencia.setBounds(120, 355, 150, 25);
		pnRegistro.add(txtNumeroLicencia);
		
		JLabel lbPaisExpedicion=new JLabel("Pais expedicion:");
		lbPaisExpedicion.setBounds(10, 395, 120, 25);
		pnRegistro.add(lbPaisExpedicion);
		txtPaisExpedicion=new JTextField(4);
		txtPaisExpedicion.setBounds(120, 395, 150, 25);
		pnRegistro.add(txtPaisExpedicion);
		
		JLabel lbVencimiento=new JLabel("Vencimiento:");
		lbVencimiento.setBounds(10, 435, 120, 25);
		pnRegistro.add(lbVencimiento);
		txtVencimiento=new JTextField(4);
		txtVencimiento.setBounds(120, 435, 150, 25);
		pnRegistro.add(txtVencimiento);
		
//		JLabel lbLicencia=new JLabel("Ruta imagen Lic:");
//		lbLicencia.setBounds(10, 475, 125, 25);
//		pnRegistro.add(lbLicencia);
//		txtRutaLic=new JTextField(4);
//		txtRutaLic.setBounds(120, 475, 150, 25);
//		pnRegistro.add(txtRutaLic);
		
		JLabel titleDatosLOG=new JLabel("DATOS LOGUEO");
		titleDatosLOG.setBounds(450, 50, 150, 25);
		pnRegistro.add(titleDatosLOG);

		JLabel lbUser=new JLabel("User:");
		lbUser.setBounds(400, 85, 50, 25);
		pnRegistro.add(lbUser);
		txtUserR=new JTextField(4);
		txtUserR.setBounds(480, 85, 150, 25);
		pnRegistro.add(txtUserR);

		
		JLabel lbContraseña=new JLabel("Contraseña:");
		lbContraseña.setBounds(400, 125, 70, 25);
		pnRegistro.add(lbContraseña);
		txtContraseña=new JTextField(4);
		txtContraseña.setBounds(480, 125, 150, 25);
		pnRegistro.add(txtContraseña);
		
		JLabel titleMedioPago=new JLabel("DATOS MEDIO DE PAGO");
		titleMedioPago.setBounds(450, 170, 150, 25);
		pnRegistro.add(titleMedioPago);
		
		JLabel lbNumTar=new JLabel("Numero tarjeta:");
		lbNumTar.setBounds(400, 210, 150, 25);
		pnRegistro.add(lbNumTar);
		txtNumTarjeta=new JTextField(4);
		txtNumTarjeta.setBounds(505, 210, 150, 25);
		pnRegistro.add(txtNumTarjeta);
		
		JLabel lbFechaExp=new JLabel("Fecha Expiración:");
		lbFechaExp.setBounds(400, 250, 100, 25);
		pnRegistro.add(lbFechaExp);
		txtFechaExpiracion=new JTextField(4);
		txtFechaExpiracion.setBounds(505, 250, 150, 25);
		pnRegistro.add(txtFechaExpiracion);
		
		JLabel lbCvv=new JLabel("CVV:");
		lbCvv.setBounds(400, 290, 50, 25);
		pnRegistro.add(lbCvv);
		txtCvv=new JTextField(4);
		txtCvv.setBounds(505, 290, 150, 25);
		pnRegistro.add(txtCvv);
	
		JLabel lbEtiqueta=new JLabel("Las fechas se deben escribir en el siguiente Formato:");
		lbEtiqueta.setBounds(350, 330, 350, 25);
		pnRegistro.add(lbEtiqueta);
		
		JLabel lb1=new JLabel("Formato de fechas y horas (YYYY.DD.MM.HH.mm)");
		lb1.setBounds(350, 350, 350, 25);
		pnRegistro.add(lb1);
		
		JLabel lb2=new JLabel("Formato de fechas sin horas (YYYY.DD.MM.00.00)");
		lb2.setBounds(350, 370, 350, 25);
		pnRegistro.add(lb2);
		
		btnregistrar=new JButton("Registrarse");
		btnregistrar.setBounds(460, 400, 150, 25);
		btnregistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (txtNombreCliente.getText().equals("") || txtCelular.getText().equals("") ||
            	txtContraseña.getText().equals("") || txtCorreo.getText().equals("") ||
            	txtUserR.getText().equals("") ||txtFechaNacimiento.getText().equals("") ||
                txtNacionalidad.getText().equals("") || txtNumeroLicencia.getText().equals("") ||
            	txtPaisExpedicion.getText().equals("") ||txtVencimiento.getText().equals("") ||
            	txtNumTarjeta.getText().equals("") || txtFechaExpiracion.getText().equals("") ||
            	txtCvv.getText().equals("")||txtRutaDI.getText().equals("") ) 
            	{
            		JOptionPane.showMessageDialog(Interfazlog.this, "Llena todos los campos!!","Campo en blanco",JOptionPane.WARNING_MESSAGE);
                	
            	}else {

                	String numTarjeta=txtNumTarjeta.getText(); 
                	String fechaExpiracionTa= txtFechaExpiracion.getText(); 
                	String cvv= txtCvv.getText();
                	
                	TarjetaCredito tarjeta = new TarjetaCredito(numTarjeta, fechaExpiracionTa, cvv,500000, 
                			                       txtNombreCliente.getText(),txtRutaDI.getText(),0);
                	
                	String numLicencia=txtNumeroLicencia.getText();
                	String paisExpedicion= txtPaisExpedicion.getText(); 
                	LocalDate vencimientoLic = generarFechaSinHora(txtVencimiento.getText());
                	
                	Licencia liCliente= new Licencia(numLicencia, paisExpedicion, vencimientoLic);
                	
            		//Crear Nuevo cliente
            		String nombre =txtNombreCliente.getText();
            		String celular=txtCelular.getText();
            		String pword= txtContraseña.getText(); 
                	String correo=txtCorreo.getText();
                	String log= txtUserR.getText(); 
                	LocalDate fechaNacimiento=generarFechaSinHora(txtFechaNacimiento.getText());
                	String nacionalidad=txtNacionalidad.getText();
                	ArrayList<String> idReserva=new ArrayList<String>();
                	idReserva.add("0");
                	Cliente nuevoCliente=new Cliente(log, pword, nombre, correo,celular ,
                			fechaNacimiento, nacionalidad, liCliente,tarjeta, idReserva);
                	try {
						sistemaDeReservas.registrarCliente(nuevoCliente);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

            	}
                
                }
            });
		pnRegistro.add(btnregistrar);
		
		
		ImageIcon iconUser = new ImageIcon("iconos\\iconUser.png");
		ImageIcon iconRegis = new ImageIcon("iconos\\iconRegis.png");
		Image modUser = iconUser.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
		Image modRegis = iconRegis.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
		ImageIcon resizedModUser = new ImageIcon(modUser);
		ImageIcon resizedRegis = new ImageIcon(modRegis);
		ventanaInterfazLogueo.add("Iniciar Sesion",pnLogueo);
		ventanaInterfazLogueo.add("Registro Cliente",pnRegistro);
		ventanaInterfazLogueo.setIconAt(0, resizedModUser);
		ventanaInterfazLogueo.setIconAt(1, resizedRegis);
		add(ventanaInterfazLogueo);
	}
	
	
	public LocalDate generarFechaSinHora(String fecha1){
		
			String[] partesFecha=fecha1.split("\\.");
			
			LocalDate fecha= LocalDate.of(Integer.parseInt(partesFecha[0]),Integer.parseInt(partesFecha[1]), 
					Integer.parseInt(partesFecha[2]));
	
			return fecha;
		}
	
	public static void main(String[] args) {
		interfaz =new Interfazlog();
		interfaz.setLocationRelativeTo(null);
		interfaz.setVisible(true);		
		
	}

}
