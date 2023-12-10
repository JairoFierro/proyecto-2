package consola;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import modelo.Carro;
import modelo.Seguro;

public class InterfazAdminGeneral extends JFrame{
	private static SistemaDeReservas sistemaDeReservas;
	private JTabbedPane ventanaAdminGeneral;

	private JPanel pnRegistrarCarro;
	
	private JPanel pnTraslado;
	
	private JPanel pnSeguros;
	
	private JTextField txtModelo;
	
	private JTextField txtPlaca;
	
	private JTextField txtMarca;

	private JTextField txtColor;
	
	private JTextField txtTransmision;
	
	private JTextField txtEstado;
	
	private JTextField txtCategoria;
	
	private JTextField txtUbicacion;
	
	private JTextField txtprecio;
	
	private JButton btnAgregar;
	
	private JList<String> vehiculos;
	
	private JList<String> sedes;
	
	private JTextField txtSedeNueva;
	
	private JTextField txtCarroTrasladoDesecho;
	
	private JButton bnTraslado;
	
	private JButton bnEliminarVehiculo;
	
	private JTextField txtCobertura;
	
	private JTextField txtPrecio;
	
	private JTextField txtDescripcion;
	
	private JTextField txtEmpresa;
	
	private JList<String> Seguros;
	
	private JButton bnModificarSeguro;
	
	private JButton bnAgregarSeguro;
	
	
	
	public InterfazAdminGeneral(ArrayList<Carro> vehiculos,ArrayList<String> sedes,ArrayList<Seguro> seguros, SistemaDeReservas sistemaDeReservas) {
		int tamX =700;
		this.sistemaDeReservas = sistemaDeReservas;
		int tamY=550;
		setTitle("ADMINISTRADOR GENERAL");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(tamX,tamY);
		setLayout(new BorderLayout());
		
		ventanaAdminGeneral= new JTabbedPane();
		ventanaAdminGeneral.setFocusable(false);
		
		setpnRegistrarCarro();
		setVehiculos(vehiculos);
		setSedes(sedes);
		setPnTraslado();
		setPnSeguro(seguros);
	
		ImageIcon iconNuevoCar = new ImageIcon("iconos\\iconoNuevoCar.png");
		ImageIcon iconTraslado = new ImageIcon("iconos\\iconModCar.png");
		ImageIcon iconSeguro = new ImageIcon("iconos\\iconMod.png");
		Image  nuevoCar = iconNuevoCar.getImage().getScaledInstance(24,24, Image.SCALE_SMOOTH);
		Image modCar = iconTraslado.getImage().getScaledInstance(17, 17, Image.SCALE_SMOOTH);
		Image modSeguro = iconSeguro.getImage().getScaledInstance(17, 17, Image.SCALE_SMOOTH);
		ImageIcon resizedNuevoCar = new ImageIcon(nuevoCar);
		ImageIcon resizedModCar = new ImageIcon(modCar);
		ImageIcon resizedSeguro = new ImageIcon(modSeguro);
		ventanaAdminGeneral.add("Registrar nuevo carro",pnRegistrarCarro);
    	ventanaAdminGeneral.add("Actualizar información de un carro",pnTraslado);
    	ventanaAdminGeneral.add("Agregar Seguro",pnSeguros);
		ventanaAdminGeneral.setIconAt(0, resizedNuevoCar);
      	ventanaAdminGeneral.setIconAt(1, resizedModCar);
      	ventanaAdminGeneral.setIconAt(2, resizedSeguro);
		add(ventanaAdminGeneral);
		
	}
	
	
	public void setPnSeguro(ArrayList<Seguro> seguros) {
		this.pnSeguros=new JPanel();
		JPanel pnData=new JPanel();
		pnData.setLayout(new GridLayout(4,2));
		setSeguros(seguros);
		JLabel lbcobertura= new JLabel("Cobertura del seguro");
		txtCobertura= new JTextField(4);
		JLabel lbPrecio= new JLabel("Precio:");
		txtPrecio=new JTextField(4);
		JLabel lbDescripcion= new JLabel("Descripcion:");
		txtDescripcion=new JTextField(4);
		JLabel lbEmpresa= new JLabel("Empresa que lo oferece:");
		txtEmpresa=new JTextField(4);
		
		pnData.add(lbcobertura);
		pnData.add(txtCobertura);
		pnData.add(lbPrecio);
		pnData.add(txtPrecio);
		pnData.add(lbDescripcion);
		pnData.add(txtDescripcion);
		pnData.add(lbEmpresa);
		pnData.add(txtEmpresa);
		
		pnSeguros.add(pnData, BorderLayout.CENTER);
		JPanel pnEste=new JPanel();
		
		this.Seguros= new JList<String>();
		setSeguros(seguros);
		pnEste.add(Seguros);
		
//		bnModificarSeguro=new JButton("Modificar Seguro");
		
		bnAgregarSeguro=new JButton("Agregar Seguro");
//		pnEste.add(bnModificarSeguro);
		bnAgregarSeguro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	if(txtCobertura.getText().equals("") || txtPrecio.getText().equals("") ||
            			txtDescripcion.getText().equals("") || txtEmpresa.getText().equals("")) {
            		JOptionPane.showMessageDialog(InterfazAdminGeneral.this, "Llena tosdos los campos","Campos vacios", JOptionPane.WARNING_MESSAGE);

            	}else {
     
            	Seguro nuevoSeguro =new Seguro(txtCobertura.getText(),txtPrecio.getText(), txtDescripcion.getText(),txtEmpresa.getText());
            	try {
					sistemaDeReservas.crearSeguro(nuevoSeguro);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	    txtCobertura.setText("");
               	
               	txtPrecio.setText("");
               	
               	txtDescripcion.setText("");
               	
               	txtEmpresa.setText("");
               	setSeguros(seguros);
            	}
            }});
		pnEste.add(bnAgregarSeguro);
		pnSeguros.add(pnEste, BorderLayout.EAST);
		
	}
	
	public void setSeguros(ArrayList<Seguro> Seguros) {
        DefaultListModel<String> modeloLista = new DefaultListModel<>();

        for (int i = 0; i < Seguros.size(); i++) {
            modeloLista.addElement(Seguros.get(i).getCobertura());
        }
        this.Seguros = new JList<>(modeloLista);
		
	}
	
	public void setPnTraslado() {
		this.pnTraslado=new JPanel();
		pnTraslado.setLayout(null);
		JLabel titulo= new JLabel("Traslado vehículo ");
		titulo.setBounds(290, 10, 200,20);
		pnTraslado.add(titulo);
		
		JLabel lbTextField =new JLabel("Vehículos en inventario");
		lbTextField.setBounds(60, 50, 180,20);
		pnTraslado.add(lbTextField);
		
		pnTraslado.add(new JLabel("Vehículos en inventario"));
		
		JScrollPane scrollPane = new JScrollPane(vehiculos);
		scrollPane.setBounds(30, 85, 200,150);
		pnTraslado.add(scrollPane) ;
		
		JLabel lbSedes =new JLabel("Sedes");
		lbSedes.setBounds(110, 280, 180,20);
		pnTraslado.add(lbSedes);
		
		JScrollPane scrollPane2 = new JScrollPane(sedes);
		scrollPane2.setBounds(30, 310, 200,150);
		pnTraslado.add(scrollPane2) ;
		
		JLabel lbvehiculo=new JLabel("Ingresa el Vehículo:");
		lbvehiculo.setBounds(350, 85, 180,20);
		pnTraslado.add(lbvehiculo);
		
		txtCarroTrasladoDesecho=new JTextField(4);
		txtCarroTrasladoDesecho.setBounds(350, 110, 150,35);
		pnTraslado.add(txtCarroTrasladoDesecho);
		
		JLabel lbTraslado=new JLabel("En caso que desees hacer un traslado a otra sede,");
		lbTraslado.setBounds(350, 180, 300,20);
		pnTraslado.add(lbTraslado) ;
		JLabel lbTraslado2=new JLabel("ingresa la sede de destino:");
		lbTraslado2.setBounds(350, 195, 180,20);
		pnTraslado.add(lbTraslado2) ;
		
		JLabel lbTraslado3=new JLabel("trasladar a:");
		lbTraslado3.setBounds(350, 230, 180,20);
		pnTraslado.add(lbTraslado3) ;
		
		txtSedeNueva= new JTextField(4);
		txtSedeNueva.setBounds(350, 255, 150,35);
		pnTraslado.add(txtSedeNueva);
		
		bnTraslado=new JButton("Trasladar");
		bnTraslado.setBounds(350, 350, 100,35);
		pnTraslado.add(bnTraslado);
		
		bnEliminarVehiculo=new JButton("Eliminar");
		bnEliminarVehiculo.setBounds(480, 350, 100,35);
		pnTraslado.add(bnEliminarVehiculo);
		
		
	}
	
	public void setpnRegistrarCarro(){
		this.pnRegistrarCarro= new JPanel();
		pnRegistrarCarro.setLayout(new BorderLayout());
		
		JPanel panelModCarroSur= new JPanel();
		JLabel titleModCar = new JLabel("AGREGAR VEHÍCULO A CATÁLOGO");
		titleModCar.setHorizontalAlignment(JLabel.CENTER);
		panelModCarroSur.add(titleModCar);
		pnRegistrarCarro.add(panelModCarroSur,BorderLayout.NORTH);
		
		JPanel panelModCarroCentro= new JPanel();
		panelModCarroCentro.setLayout(new GridLayout(13,4));
	
		
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
			panelModCarroCentro.add(new JLabel());
		}
		panelModCarroCentro.add(modelo);
		panelModCarroCentro.add(txtModelo);
		panelModCarroCentro.add(new JLabel());
		panelModCarroCentro.add(new JLabel());
		
		panelModCarroCentro.add(placa);
		panelModCarroCentro.add(txtPlaca);
		panelModCarroCentro.add(new JLabel());
		panelModCarroCentro.add(new JLabel());
		
		panelModCarroCentro.add(marca);
		panelModCarroCentro.add(txtMarca);
		panelModCarroCentro.add(new JLabel());
		panelModCarroCentro.add(new JLabel());
		
		panelModCarroCentro.add(color);
		panelModCarroCentro.add(txtColor);
		panelModCarroCentro.add(new JLabel());
		panelModCarroCentro.add(new JLabel());
		
		panelModCarroCentro.add(transmision);
		panelModCarroCentro.add(txtTransmision);
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
		
		panelModCarroCentro.add(lbcategoria);
		panelModCarroCentro.add(txtCategoria);
		panelModCarroCentro.add(new JLabel());
		panelModCarroCentro.add(new JLabel());
		
		panelModCarroCentro.add(lbPrecio);
		panelModCarroCentro.add(txtprecio);
		panelModCarroCentro.add(new JLabel());
		panelModCarroCentro.add(new JLabel());
		
		for(int i=0;i<4;i++) {
			panelModCarroCentro.add(new JLabel());
		}

		panelModCarroCentro.add(new JLabel());
		btnAgregar= new JButton("Agregar");		
		
		btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(txtModelo.getText().equals("") || txtPlaca.getText().equals("") ||
            		txtMarca.getText().equals("") || txtColor.getText().equals("")||
            		txtTransmision.getText().equals("") || txtEstado.getText().equals("")|
            		txtUbicacion.getText().equals("") ) {
            		JOptionPane.showMessageDialog(InterfazAdminGeneral.this, "Llena tosdos los campos","Campos vacios", JOptionPane.WARNING_MESSAGE);
            		
            	}else {
            		
            		Carro carroNuevo= new Carro(txtPlaca.getText(), txtMarca.getText(),
            				txtModelo.getText(),txtColor.getText(),txtTransmision.getText(), 
            				txtEstado.getText(), txtCategoria.getText(), txtUbicacion.getText(),
            				Float.parseFloat(txtprecio.getText()),null);
            		try {
						sistemaDeReservas.agregarCarro(carroNuevo);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		
            		txtprecio.setText("");
            		txtModelo.setText("");
                	
            		txtPlaca.setText("");
            
            		txtMarca.setText("");

            		txtColor.setText("");
       
            		txtTransmision.setText("");

            		txtEstado.setText("");

            		txtUbicacion.setText("");
            		txtCategoria.setText("");

            	}
    
            	
            	
            }});
		panelModCarroCentro.add(btnAgregar);

		
		pnRegistrarCarro.add(panelModCarroCentro,BorderLayout.CENTER);
		
	}
	
	public void setVehiculos(ArrayList<Carro> vehiculos) {
        DefaultListModel<String> modeloLista = new DefaultListModel<>();

        for (int i = 0; i < vehiculos.size(); i++) {
            modeloLista.addElement(vehiculos.get(i).getPlaca());
        }

        this.vehiculos = new JList<>(modeloLista);
		
	}
	
	public void setSedes(ArrayList<String> sedes) {
        DefaultListModel<String> modeloLista = new DefaultListModel<>();

        for (int i = 0; i < sedes.size(); i++) {
            modeloLista.addElement(sedes.get(i));
        }

        this.sedes = new JList<>(modeloLista);
		
	}
	
	public static void main(String[] args) {
		sistemaDeReservas=new SistemaDeReservas();
		try {
			sistemaDeReservas.cargarSeguros();
			sistemaDeReservas.cargarCarros();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> empleados = new ArrayList<String>();
		empleados.add("Maria Cordoba");
		empleados.add("Pedro Hidalgo");
		empleados.add("Juan Diaz");
		empleados.add("Pablo Ramirez");
		ArrayList<String> sedes = new ArrayList<String>();
		sedes.add("Usaquen");
		sedes.add("Chapinero");
		sedes.add("Secundaria");
		sedes.add("Principal");
		InterfazAdminGeneral interfazAdmin =new InterfazAdminGeneral(sistemaDeReservas.totalCarros(),sedes,sistemaDeReservas.getSeguros(),sistemaDeReservas);
		interfazAdmin.setLocationRelativeTo(null);
		interfazAdmin.setVisible(true);		
	}
}
