package consola;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class InterfazRegistroCliente extends JFrame{
	
	private JTextField txtNombreCliente;
	
	private JTextField txtCelular;
	
	private JTextField txtContraseña;
	
	private JTextField txtCorreo;

	private JTextField txtUser;
	
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

	public InterfazRegistroCliente() {
		
		int tamX =700;
		int tamY=550;
		setTitle("REGISTRO CLIENTE");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(tamX,tamY);
		setLayout(null);
		

		
	}
	public static void main(String[] args) {
		InterfazRegistroCliente interfazAdmin =new InterfazRegistroCliente();
		interfazAdmin.setLocationRelativeTo(null);
		interfazAdmin.setVisible(true);		
	}
}
