package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

import estructuraTP.DAO.PacienteDAO;
import estructuraTP.modelo.Paciente;

public class Modificar extends JPanel {
	private JTextField textFieldID;
	private JTextField textFieldApellido;
	private JTextField textFieldNombre;
	private JTextField textFieldTel;

	/**
	 * Create the panel.
	 */
	public Modificar(JFrame frame, Paciente p) {
		setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Modifique datos a actualizar");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel.setBounds(150, 23, 360, 63);
		add(lblNewLabel);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(505, 352, 89, 23);
		add(btnModificar);

		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(49, 352, 89, 23);
		add(btnAtras);
		
		JLabel lblID = new JLabel("Nro Paciente:");
		lblID.setBounds(199, 97, 89, 14);
		add(lblID);
		
		JLabel lblTel = new JLabel("Telefono:");
		lblTel.setBounds(199, 202, 67, 14);
		add(lblTel);
		
		
		JLabel lblPaciente = new JLabel(String.valueOf(p.getIdPaciente()));
		lblPaciente.setBounds(299, 97, 46, 14);
		add(lblPaciente);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(199, 170, 67, 14);
		add(lblNombre);

		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(199, 135, 67, 14);
		add(lblApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(276, 132, 101, 20);
		add(textFieldApellido);
		textFieldApellido.setColumns(10);
		textFieldApellido.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if(Character.isDigit(c)) {
					evt.consume();
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Solo se aceptan letras.");

				}
			}
		});

		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(276, 167, 101, 20);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		textFieldNombre.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if(Character.isDigit(c)) {
					evt.consume();
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Solo se aceptan letras.");

				}
			}
		});

		
		textFieldTel = new JTextField();
		textFieldTel.setBounds(276, 199, 101, 20);
		add(textFieldTel);
		textFieldTel.setColumns(10);
		textFieldTel.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if(c<'0'|| c>'9') {
					evt.consume();
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Solo se aceptan dígitos.");

				}
			}
		});
		
		JLabel lblHist = new JLabel("Historial:");
		lblHist.setDoubleBuffered(true);
		lblHist.setBounds(199, 233, 67, 14);
		add(lblHist);
		
		JScrollPane scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        scroll.setBounds(276, 229, 252, 111);
        add(scroll);
        JTextArea textFieldHistorialClinico = new JTextArea();
        scroll.setViewportView(textFieldHistorialClinico);
		

		textFieldNombre.setText(p.getNombre());
		textFieldApellido.setText(p.getApellido());
		textFieldTel.setText(String.valueOf(p.getTelefono()));
		textFieldHistorialClinico.setText(p.getHistorial());
		ActionListener listenerAtras = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelPrincipal panel = new PanelPrincipal (frame);
				frame.setContentPane(panel);
				frame.validate();
			}
		};
		btnAtras.addActionListener(listenerAtras);
		
		ActionListener listenerMod = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Paciente p1 = new Paciente(p.getIdPaciente(), textFieldNombre.getText(), textFieldApellido.getText(),0, Integer.parseInt(textFieldTel.getText()), textFieldHistorialClinico.getText());
				PacienteDAO pDao = new PacienteDAO();
				pDao.modificarPaciente(p1);
				PanelPrincipal panel = new PanelPrincipal (frame);
				frame.setContentPane(panel);
				frame.validate();
			}
		};
		btnModificar.addActionListener(listenerMod);	
		
	}
}
