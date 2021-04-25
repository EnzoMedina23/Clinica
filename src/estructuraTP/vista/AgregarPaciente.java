package estructuraTP.vista;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import estructuraTP.DAO.PacienteDAO;
import estructuraTP.modelo.Paciente;

public class AgregarPaciente extends JPanel {
	private JTextField textFieldNroPaciente;
	private JTextField textFieldNombre;
	private JTextField textFieldDNI;
	private JTextField textFieldTelefono;
	private JTextField textFieldApellido;
	private JCalendar calendario;

	public AgregarPaciente(JFrame frame) {
		setLayout(null);
		
		JLabel lblAgregarPaciente = new JLabel("Agregar paciente");
		lblAgregarPaciente.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAgregarPaciente.setBounds(10, 11, 216, 23);
		add(lblAgregarPaciente);
		
		JLabel lblNroPaciente = new JLabel("Nro paciente:");
		lblNroPaciente.setBounds(10, 75, 95, 14);
		add(lblNroPaciente);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 100, 124, 14);
		add(lblNombre);
		
//		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
//		lblFechaDeNacimiento.setBounds(335, 125, 176, 14);
//		add(lblFechaDeNacimiento);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(335, 100, 95, 14);
		add(lblDni);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(335, 75, 95, 14);
		add(lblTelfono);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 125, 95, 14);
		add(lblApellido);
		
		JLabel lblHistorialClnico = new JLabel("Historial cl\u00EDnico:");
		lblHistorialClnico.setBounds(10, 150, 95, 14);
		add(lblHistorialClnico);
		
		textFieldNroPaciente = new JTextField();
		textFieldNroPaciente.setBounds(144, 72, 116, 20);
		add(textFieldNroPaciente);
		textFieldNroPaciente.setColumns(10);
		textFieldNroPaciente.addKeyListener(new KeyAdapter(){
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
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(144, 97, 116, 20);
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
		
		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(470, 97, 116, 20);
		add(textFieldDNI);
		textFieldDNI.setColumns(10);
		textFieldDNI.addKeyListener(new KeyAdapter(){
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
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(470, 72, 116, 20);
		add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		textFieldTelefono.addKeyListener(new KeyAdapter(){
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
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(144, 125, 116, 20);
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
		

		
		JScrollPane scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        scroll.setBounds(144, 153, 266, 120);
        add(scroll);
        JTextArea textFieldHistorialClinico = new JTextArea();
        scroll.setViewportView(textFieldHistorialClinico);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(535, 390, 89, 23);
		add(btnAgregar);
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.setBounds(10, 390, 89, 23);
		add(btnAtras);
		
//		calendario = new JCalendar();
//		calendario.setBounds(470, 125, 160, 120);
//		add(calendario);
		
		ActionListener listenerAtras = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelPrincipal panel = new PanelPrincipal (frame);
				frame.setContentPane(panel);
				frame.validate();
			}
		};
		
		ActionListener listenerAgregar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!textFieldNroPaciente.getText().trim().isEmpty() && !textFieldNombre.getText().trim().isEmpty() && !textFieldApellido.getText().trim().isEmpty() && !textFieldDNI.getText().trim().isEmpty() && !textFieldTelefono.getText().trim().isEmpty()){
					int idPaciente = Integer.parseInt(textFieldNroPaciente.getText().trim());
					String nombre = textFieldNombre.getText().trim();
					String apellido = textFieldApellido.getText().trim();
					int dni = Integer.parseInt(textFieldDNI.getText().trim());
					int telefono = Integer.parseInt(textFieldTelefono.getText().trim());
					String historial = textFieldHistorialClinico.getText().trim();
//					Date fechaDeNacimiento = (Date) calendario.getDate();
					Paciente p = new Paciente(idPaciente, nombre, apellido, dni, telefono, historial);
					PacienteDAO pdao = new PacienteDAO();
					pdao.crear(p);
					PanelPrincipal panel = new PanelPrincipal (frame);
					frame.setContentPane(panel);
					frame.validate();
				} else {
					JOptionPane.showMessageDialog(null, "Debe completar todo antes de ingresar un paciente nuevo.");
				}
			}
		};

		btnAtras.addActionListener(listenerAtras);
		btnAgregar.addActionListener(listenerAgregar);
	}

}
