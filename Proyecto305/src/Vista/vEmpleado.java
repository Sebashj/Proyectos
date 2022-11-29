package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.DaoEmpleado;
import Modelo.Empleado;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class vEmpleado extends JFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtdomocilio;
	private JTextField txttelefono;
	private JTextField txtnombre;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	DaoEmpleado dao=new DaoEmpleado();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblEmpleado;
	ArrayList<Empleado> lista = new ArrayList<Empleado>();
	int fila=-1;
	Empleado Empleado;
	private JTextField txtpuesto;
	private JTextField txtpassword;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vEmpleado frame = new vEmpleado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtdomocilio.setText("");
		txttelefono.setText("");
		txtnombre.setText("");
		txtpuesto.setText("");
		txtpassword.setText("");
	}

	public vEmpleado() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vEmpleado.class.getResource("/Img/icono.jpg")));
		setLocationRelativeTo(null);
		setTitle("Empleado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(20, 26, 33, 23);
		contentPane.add(lblNewLabel);
		
		lblid = new JLabel("1");
		lblid.setHorizontalAlignment(SwingConstants.LEFT);
		lblid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblid.setBounds(73, 26, 86, 23);
		contentPane.add(lblid);
		
		JLabel lblNewLabel_1 = new JLabel("Telefono");
		lblNewLabel_1.setBounds(10, 92, 53, 23);
		contentPane.add(lblNewLabel_1);
		
		txtdomocilio = new JTextField();
		txtdomocilio.setBounds(73, 124, 86, 20);
		contentPane.add(txtdomocilio);
		txtdomocilio.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Domicilio");
		lblNewLabel_1_1.setBounds(10, 123, 53, 23);
		contentPane.add(lblNewLabel_1_1);
		
		txttelefono = new JTextField();
		txttelefono.setColumns(10);
		txttelefono.setBounds(73, 93, 86, 20);
		contentPane.add(txttelefono);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nombre");
		lblNewLabel_1_2.setBounds(10, 58, 53, 23);
		contentPane.add(lblNewLabel_1_2);
		
		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		txtnombre.setBounds(73, 60, 86, 20);
		contentPane.add(txtnombre);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtnombre.getText().equals("")||txttelefono.getText().equals("")||txtdomocilio.getText().equals("")||txtpuesto.getText().equals("")||txtpassword.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Empleado user=new Empleado();
					user.setNombre(txtnombre.getText());
					user.setTelefono(Integer.parseInt(txttelefono.getText()));
					user.setDomicilio(txtdomocilio.getText());
					user.setPuesto(txtpuesto.getText());
					user.setPassword(txtpassword.getText());
					if (dao.insertarEmpleado(user)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se agrego correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		});
		btnAgregar.setBounds(193, 39, 89, 23);
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion =JOptionPane.showConfirmDialog(null , "Estas seguro de eliminar");
					if(opcion==0) {
					if (dao.eliminarEmpleado(lista.get(fila).getIdempleado())) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se elimino correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
					}
				}catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		});
		btnEliminar.setBounds(193, 72, 89, 23);
		contentPane.add(btnEliminar);
		
		btnEditar = new JButton("editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtnombre.getText().equals("")||txttelefono.getText().equals("")||txtdomocilio.getText().equals("")||txtpuesto.getText().equals("")||txtpassword.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Empleado.setNombre(txtnombre.getText());
					Empleado.setTelefono(Integer.parseInt(txttelefono.getText()));
					Empleado.setDomicilio(txtdomocilio.getText());
					Empleado.setPuesto(txtpuesto.getText());
					Empleado.setPassword(txtpassword.getText());
					if (dao.editarEmpleado(Empleado)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se edito correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}catch (Exception e2) {
					
				}
				
			}
		});
		btnEditar.setBounds(193, 105, 89, 23);
		contentPane.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 218, 398, 238);
		contentPane.add(scrollPane);
		
		tblEmpleado = new JTable();
		tblEmpleado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblEmpleado.getSelectedRow();
				Empleado=lista.get(fila);
				lblid.setText(""+lista.get(fila).getIdempleado());
				txtnombre.setText(""+Empleado.getNombre());
				txttelefono.setText(""+Empleado.getTelefono());
				txtdomocilio.setText(""+Empleado.getDomicilio());
				txtpuesto.setText(""+Empleado.getPuesto());
				txtpassword.setText(""+Empleado.getPassword());
				
			}
		});
		tblEmpleado.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblEmpleado);
		
		modelo.addColumn("ID");
		modelo.addColumn("Nombre");
		modelo.addColumn("Telefono");
		modelo.addColumn("Domicilio");
		modelo.addColumn("Puesto");
		modelo.addColumn("Password");
		tblEmpleado.setModel(modelo);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Puesto");
		lblNewLabel_1_1_1.setBounds(10, 155, 53, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Password");
		lblNewLabel_1_1_2.setBounds(10, 184, 53, 23);
		contentPane.add(lblNewLabel_1_1_2);
		
		txtpuesto = new JTextField();
		txtpuesto.setColumns(10);
		txtpuesto.setBounds(73, 156, 86, 20);
		contentPane.add(txtpuesto);
		
		txtpassword = new JTextField();
		txtpassword.setColumns(10);
		txtpassword.setBounds(103, 185, 86, 20);
		contentPane.add(txtpassword);
		refrescarTabla();
	}
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchEmpleados();
		for(Empleado u: lista) {
			Object o[]=new Object [6];
			o[0]=u.getIdempleado();
			o[1]=u.getNombre();
			o[2]=u.getTelefono();
			o[3]=u.getDomicilio();
			o[4]=u.getPuesto();
			o[5]=u.getPassword();
			modelo.addRow(o);
		}
		tblEmpleado.setModel(modelo);
	}
}
