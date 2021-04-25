package estructuraTP.vista;

import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import estructuraTP.DAO.EspecialidadDAO;
import estructuraTP.DAO.MedicoDAO;
import estructuraTP.DAO.PacienteDAO;
import estructuraTP.DAO.TurnoDAO;
import estructuraTP.modelo.Especialidad;
import estructuraTP.modelo.Medico;
import estructuraTP.modelo.Paciente;
import estructuraTP.modelo.Turno;

public class AsignarTurno extends JPanel {
	
	private JTextField textFieldNroPaciente;
	private JTextField textFieldHorario;
	private JTextField textFieldCosto;
	private JCalendar calendario = new JCalendar();
	
	public AsignarTurno(JFrame frame, String idPaciente) {
		setLayout(null);
		
		JLabel lblAsignarTurno = new JLabel("Asignar turno");
		lblAsignarTurno.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblAsignarTurno.setBounds(10, 11, 147, 23);
		add(lblAsignarTurno);
		
		JLabel lblEspecialidad = new JLabel("Especialidad:");
		lblEspecialidad.setFont(new Font("Arial", Font.PLAIN, 16));
		lblEspecialidad.setBounds(10, 46, 113, 14);
		add(lblEspecialidad);
		
		JComboBox comboBoxEspecialidad = new JComboBox();
		comboBoxEspecialidad.setBounds(10, 70, 136, 20);
		add(comboBoxEspecialidad);
		EspecialidadDAO edao = new EspecialidadDAO();
		ArrayList<Especialidad> especialidades = edao.consultar();
		for(Especialidad e : especialidades) {
			comboBoxEspecialidad.addItem(e.getEspecialidad());
		}
		
		JLabel lblMedico = new JLabel("Médico:");
		lblMedico.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMedico.setBounds(10, 128, 113, 14);
		add(lblMedico);
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setFont(new Font("Arial", Font.PLAIN, 16));
		lblCosto.setBounds(10, 177, 113, 14);
		add(lblCosto);
		JButton btnMedicos = new JButton("Ver Medicos");
		btnMedicos.setBounds(10, 101, 136, 14);
		add(btnMedicos);
		
		
		JComboBox comboBoxMedico = new JComboBox();
		comboBoxMedico.setBounds(10, 153, 136, 20);
		add(comboBoxMedico);
		
		
		ActionListener listenerMedicos = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			MedicoDAO mdao = new MedicoDAO();
			comboBoxMedico.removeAllItems();
			ArrayList<Medico> medicosPorEspecialidad = mdao.consultarPorEspecialidad(comboBoxEspecialidad.getSelectedItem().toString());
			for(Medico m : medicosPorEspecialidad) {
				comboBoxMedico.addItem(m.getIdMedico() + " " + m.getNombre() + " " + m.getApellido());
			}
			}
		};

		btnMedicos.addActionListener(listenerMedicos);
		JLabel lblPacienteNro = new JLabel("Paciente nro:");
		lblPacienteNro.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPacienteNro.setBounds(200, 46, 97, 14);
		add(lblPacienteNro);
		
		JLabel lblHorario = new JLabel("Horario:");
		lblHorario.setFont(new Font("Arial", Font.PLAIN, 16));
		lblHorario.setBounds(200, 100, 97, 14);
		add(lblHorario);
		
		textFieldCosto = new JTextField();
		textFieldCosto.setBounds(10, 195, 97, 20);
		add(textFieldCosto);
		textFieldCosto.setColumns(10);
		textFieldCosto.addKeyListener(new KeyAdapter(){
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
		
		textFieldNroPaciente = new JTextField();
		textFieldNroPaciente.setBounds(200, 70, 97, 20);
		add(textFieldNroPaciente);
		textFieldNroPaciente.setColumns(10);
		textFieldNroPaciente.setText(idPaciente);
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
		
		JComboBox comboBoxHorario = new JComboBox();
		comboBoxHorario.setBounds(200, 124, 97, 20);
		add(comboBoxHorario);
		
		int hora = 8;
		for(int i = 0; i < 25; i++) {
			if(hora<10)
			{
				if(i % 2 == 0) {
					comboBoxHorario.addItem("0"+hora + ":00");
				} else {
					comboBoxHorario.addItem("0"+hora + ":30");
					hora++;
				}	
			}
			else
			{
				if(i % 2 == 0) {
					comboBoxHorario.addItem(hora + ":00");
				} else {
					comboBoxHorario.addItem(hora + ":30");
					hora++;
				}	
			}
					
		}		
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Arial", Font.PLAIN, 16));
		lblFecha.setBounds(343, 48, 97, 14);
		add(lblFecha);
		
		calendario.setBounds(400, 48, 160, 120);
		add(calendario);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(535, 390, 89, 23);
		add(btnAceptar);
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.setBounds(10, 390, 89, 23);
		add(btnAtras);
		
		
		
		ActionListener listenerAtras = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelPrincipal panel = new PanelPrincipal (frame);
				frame.setContentPane(panel);
				frame.validate();
			}
		};

		ActionListener listenerAceptar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!textFieldNroPaciente.getText().trim().isEmpty()){
					int idPaciente = Integer.parseInt(textFieldNroPaciente.getText().trim());
					String[] idYNombreMedico = comboBoxMedico.getSelectedItem().toString().split(" ");
					int idMedico = Integer.parseInt(idYNombreMedico[0]);
					LocalDate fecha = calendario.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					float costo = Integer.parseInt(textFieldCosto.getText().trim());
					String hora= comboBoxHorario.getSelectedItem().toString();
					LocalTime hora2 = LocalTime.parse(hora+":00");
					Turno t = new Turno(idPaciente, idMedico, fecha, hora2, costo);
					TurnoDAO tdao = new TurnoDAO();
					tdao.crear(t);
					PanelPrincipal panel = new PanelPrincipal (frame);
					frame.setContentPane(panel);
					frame.validate();
				} else {
					JOptionPane.showMessageDialog(null, "Debe completar todo antes de ingresar un paciente nuevo.");
				}
			}
		};
		
		btnAtras.addActionListener(listenerAtras);
		btnAceptar.addActionListener(listenerAceptar);
	}
}
