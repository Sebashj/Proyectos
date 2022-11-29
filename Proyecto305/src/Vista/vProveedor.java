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

import Dao.DaoProveedor;
import Modelo.Proveedor;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class vProveedor extends JFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtdescripcion;
	private JTextField txtcontacto;
	private JTextField txtnombre;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	DaoProveedor dao=new DaoProveedor();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblProveedor;
	ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
	int fila=-1;
	Proveedor Proveedor;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vProveedor frame = new vProveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtdescripcion.setText("");
		txtcontacto.setText("");
		txtnombre.setText("");
	}

	public vProveedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vProveedor.class.getResource("/Img/icono.jpg")));
		setLocationRelativeTo(null);
		setTitle("Proveedor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 322);
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
		
		JLabel lblNewLabel_1 = new JLabel("Contacto");
		lblNewLabel_1.setBounds(10, 92, 53, 23);
		contentPane.add(lblNewLabel_1);
		
		txtdescripcion = new JTextField();
		txtdescripcion.setBounds(106, 127, 86, 20);
		contentPane.add(txtdescripcion);
		txtdescripcion.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Descripcion");
		lblNewLabel_1_1.setBounds(10, 126, 86, 23);
		contentPane.add(lblNewLabel_1_1);
		
		txtcontacto = new JTextField();
		txtcontacto.setColumns(10);
		txtcontacto.setBounds(73, 94, 86, 20);
		contentPane.add(txtcontacto);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nombre");
		lblNewLabel_1_2.setBounds(10, 60, 53, 23);
		contentPane.add(lblNewLabel_1_2);
		
		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		txtnombre.setBounds(73, 60, 86, 20);
		contentPane.add(txtnombre);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtnombre.getText().equals("")||txtcontacto.getText().equals("")||txtdescripcion.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Proveedor user=new Proveedor();
					user.setNombreproveedor(txtnombre.getText());
					user.setCantacto(Integer.parseInt(txtcontacto.getText()));
					user.setDescripcion(txtdescripcion.getText());
					if (dao.insertarProveedor(user)) {
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
					if (dao.eliminarProveedor(lista.get(fila).getIdproveedor())) {
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
					if(txtnombre.getText().equals("")||txtcontacto.getText().equals("")||txtdescripcion.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Proveedor.setNombreproveedor(txtnombre.getText());
					Proveedor.setCantacto(Integer.parseInt(txtcontacto.getText()));
					Proveedor.setDescripcion(txtdescripcion.getText());
					if (dao.editarProveedor(Proveedor)) {
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
		scrollPane.setBounds(314, 29, 398, 238);
		contentPane.add(scrollPane);
		
		tblProveedor = new JTable();
		tblProveedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblProveedor.getSelectedRow();
				Proveedor=lista.get(fila);
				lblid.setText(""+lista.get(fila).getIdproveedor());
				txtnombre.setText(""+Proveedor.getNombreproveedor());
				txtcontacto.setText(""+Proveedor.getCantacto());
				txtdescripcion.setText(""+Proveedor.getDescripcion());
				
				
			}
		});
		tblProveedor.setModel(new DefaultTableModel(
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
		scrollPane.setViewportView(tblProveedor);
		
		modelo.addColumn("ID");
		modelo.addColumn("Domicilio");
		modelo.addColumn("Telefono");
		modelo.addColumn("Nombre");
		tblProveedor.setModel(modelo);
		refrescarTabla();
	}
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchProveedors();
		for(Proveedor u: lista) {
			Object o[]=new Object [4];
			o[0]=u.getIdProveedor();
			o[1]=u.getDomicilio();
			o[2]=u.getTelefono();
			o[3]=u.getNombre();
			modelo.addRow(o);
		}
		tblProveedor.setModel(modelo);
	}
}
