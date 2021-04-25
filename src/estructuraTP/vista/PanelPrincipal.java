package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JTextField;

import estructuraTP.DAO.PacienteDAO;
import estructuraTP.modelo.Paciente;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class PanelPrincipal extends JPanel {
	private JTextField textFieldNroPaciente;

	public PanelPrincipal(JFrame frame) {
		setLayout(null);
		
		
		JLabel lblHistoriasClinicas = new JLabel("Historias Clínicas");
		lblHistoriasClinicas.setFont(new Font("Arial Black", Font.PLAIN, 26));
		lblHistoriasClinicas.setBounds(frame.getWidth()/2-264/2, frame.getHeight()/4-85, 264, 85);//(donde aparece width, donde aparece height, width, height)
		add(lblHistoriasClinicas);
		
		JLabel lblNroCliente = new JLabel("Nro Paciente:");
		lblNroCliente.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNroCliente.setBounds((frame.getWidth()/3-168/3)-15, 203, 144, 14);
		add(lblNroCliente);
		
		textFieldNroPaciente = new JTextField();
		textFieldNroPaciente.setBounds(frame.getWidth()/2-168/2, 200, 168, 23);
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
		
		JButton btnConsultarTurno = new JButton("Consultar turnos");
		btnConsultarTurno.setBounds(frame.getWidth()/2-168/2, 280, 168, 23);
		add(btnConsultarTurno);
		
		JLabel lblAclaracion = new JLabel("Seleccione la categoría e ingrese el nro del cliente sólo en caso de querer buscar uno específicamente.");
		lblAclaracion.setBounds(frame.getWidth()/14-264/14, frame.getHeight()/4-85/2, 593, 76);
		add(lblAclaracion);
		
		JButton btnAgregarPaciente = new JButton("Agregar paciente");
		btnAgregarPaciente.setBounds(frame.getWidth()/2-168/2, 320, 168, 23);
		add(btnAgregarPaciente);
		
		JButton btnAsignarTurno = new JButton("Asignar turno");
		btnAsignarTurno.setBounds(frame.getWidth()/2-168/2, 240, 168, 20);
		add(btnAsignarTurno);
		
		JButton btnEliminar = new JButton("Eliminar Paciente");
		btnEliminar.setBounds(frame.getWidth()/2-168/2, 400, 168, 23);
		add(btnEliminar);
		
		JButton btnNewModificar = new JButton("Modificar Paciente");
		btnNewModificar.setBounds(frame.getWidth()/2-168/2, 360, 168, 23);
		add(btnNewModificar);
		
		ActionListener listenerAgregarPaciente = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AgregarPaciente panel = new AgregarPaciente(frame);
				frame.setContentPane(panel);
				frame.validate();
			}
		};
		
		ActionListener listenerConsultarTurnos = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idPaciente = textFieldNroPaciente.getText();
				ConsultarTurno panel = new ConsultarTurno(frame, idPaciente);		
				frame.setContentPane(panel);
				frame.validate();
			}
		};
		

		ActionListener listenerAsignarTurno = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idPaciente = textFieldNroPaciente.getText();
				AsignarTurno panel = new AsignarTurno(frame, idPaciente);
				frame.setContentPane(panel);
				frame.validate();
			}
		};
		
		ActionListener listenerEliminar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean idExistente = false;
				if(!textFieldNroPaciente.getText().isEmpty()) {
					PacienteDAO pDAO = new PacienteDAO();
					ArrayList<Paciente> idsPacientes = pDAO.obtenerIdsPacientes();
					for(Paciente p : idsPacientes) {
						if(p.getIdPaciente() == Integer.parseInt(textFieldNroPaciente.getText())) {
							idExistente = true;
							break;
						}
					}
					if(idExistente) {
						int input = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea eliminar al paciente Nro "+ textFieldNroPaciente.getText() + "?");
						if(input==0)
						{
							int idPaciente = Integer.parseInt(textFieldNroPaciente.getText());
							pDAO.eliminarPaciente(idPaciente);
						}											
					}
					else {
						JOptionPane.showMessageDialog(null, "El número de paciente ingresado no existe.");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe ingresar el número del paciente.");
				}

			}
		};
		btnEliminar.addActionListener(listenerEliminar);
		
		ActionListener listenerModificar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean idExistente = false;
				if(!textFieldNroPaciente.getText().isEmpty()) {
					PacienteDAO pDAO = new PacienteDAO();
					ArrayList<Paciente> idsPacientes = pDAO.obtenerIdsPacientes();
					for(Paciente p : idsPacientes) {
						if(p.getIdPaciente() == Integer.parseInt(textFieldNroPaciente.getText())) {
							idExistente = true;
							break;
						}
					}
					if(idExistente) {
						int idPaciente = Integer.parseInt(textFieldNroPaciente.getText());
						Paciente p = pDAO.obtenerPaciente(idPaciente);
						Modificar panel = new Modificar(frame, p);
						frame.setContentPane(panel);
						frame.validate();											
					}
					else {
						JOptionPane.showMessageDialog(null, "El número de paciente ingresado no existe.");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Debe ingresar el número del paciente.");
				}
			}
		};
		btnNewModificar.addActionListener(listenerModificar);
		btnAgregarPaciente.addActionListener(listenerAgregarPaciente);
		btnAsignarTurno.addActionListener(listenerAsignarTurno);
		btnConsultarTurno.addActionListener(listenerConsultarTurnos);
	}
}
