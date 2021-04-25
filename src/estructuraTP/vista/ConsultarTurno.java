package estructuraTP.vista;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import estructuraTP.DAO.EspecialidadDAO;
import estructuraTP.DAO.TurnoDAO;
import estructuraTP.modelo.Especialidad;
import estructuraTP.modelo.Turno;

public class ConsultarTurno extends JPanel {

	private JCalendar calendarioDesde = new JCalendar();
	private JCalendar calendarioHasta = new JCalendar();
	private JTextField txtCostodesde;
	private JTextField txtCostohasta;
	private JTextField textFieldMedico;
	private JTextField textFieldNroPaciente;
	
	public ConsultarTurno(JFrame frame, String idPaciente) {
		setLayout(null);
		
		
		String[] nombresColumnas = { "idPaciente", "idMedico", "Fecha", "Hora", "Costo", "Concurrencia"}; 

		DefaultTableModel modelo = new DefaultTableModel(nombresColumnas, 0);
		
		TurnoDAO tdao = new TurnoDAO();
		ArrayList<Turno> resultado = tdao.consultar();
		
		for ( Turno t : resultado)
		{
	
		int idPaciente1 = t.getIdPaciente();
		int idMedico = t.getIdMedico();
		LocalDate fecha = t.getFecha();
		LocalTime hora = t.getHora();
		float costo = t.getCosto();
		boolean concurrencia = t.getConcurrencia();
				
		Object [] registro = {idPaciente1, idMedico, fecha, hora, costo, concurrencia};
		
		modelo.addRow(registro);		
	       
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 70, 615, 150);
		add(scrollPane);
		
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);
		
		JLabel lblConsultarTurno = new JLabel("Consultar turno");
		lblConsultarTurno.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblConsultarTurno.setBounds(10, 11, 216, 23);
		add(lblConsultarTurno);
		
//		table = new JTable();
//		table.setBounds(10, 70, 615, 150);
//		add(table);
		
		JLabel lblFiltrar = new JLabel("Filtrar:");
		lblFiltrar.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblFiltrar.setBounds(10, 230, 80, 23);
		add(lblFiltrar);
		
		JLabel lblFechasEntre = new JLabel("Fechas entre:");
		lblFechasEntre.setBounds(10, 250, 80, 14);
		add(lblFechasEntre);
		
		 
		calendarioDesde.setBounds(10, 270, 160, 120);
		calendarioHasta.setBounds(190, 270, 160, 120);
		add(calendarioDesde);
		add(calendarioHasta);
		
		JLabel lblY = new JLabel("y");
		lblY.setBounds(175, 310, 23, 14);
		add(lblY);
		
		JLabel lblCostoEntre = new JLabel("Costo entre:");
		lblCostoEntre.setBounds(386, 250, 80, 14);
		add(lblCostoEntre);
		
		txtCostodesde = new JTextField();
		txtCostodesde.setBounds(360, 270, 49, 20);
		add(txtCostodesde);
		txtCostodesde.setColumns(10);
		txtCostodesde.addKeyListener(new KeyAdapter(){
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
		
		JLabel lblY_1 = new JLabel("y");
		lblY_1.setBounds(416, 273, 11, 14);
		add(lblY_1);
		
		txtCostohasta = new JTextField();
		txtCostohasta.setBounds(429, 270, 49, 20);
		add(txtCostohasta);
		txtCostohasta.setColumns(10);
		txtCostohasta.addKeyListener(new KeyAdapter(){
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
		
		JLabel lblEspecialidad = new JLabel("Especialidad");
		lblEspecialidad.setBounds(506, 250, 87, 14);
		add(lblEspecialidad);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(506, 270, 87, 20);
		add(comboBox);
		
		EspecialidadDAO edao = new EspecialidadDAO();
		ArrayList<Especialidad> res = edao.consultar();
		for(Especialidad e : res) {
			comboBox.addItem(e.getEspecialidad());
		}
		
		JLabel lblMdico = new JLabel("Id m\u00E9dico:");
		lblMdico.setBounds(376, 301, 64, 14);
		add(lblMdico);
		
		textFieldMedico = new JTextField();
		textFieldMedico.setBounds(364, 322, 114, 20);
		add(textFieldMedico);
		textFieldMedico.setColumns(10);
		textFieldMedico.addKeyListener(new KeyAdapter(){
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
		
		JLabel lblNroPaciente = new JLabel("Nro paciente:");
		lblNroPaciente.setBounds(506, 301, 128, 14);
		add(lblNroPaciente);

		textFieldNroPaciente = new JTextField();
		textFieldNroPaciente.setBounds(506, 322, 114, 20);
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
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(535, 390, 89, 23);
		add(btnConsultar);
		
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

		
		ActionListener listenerConsultar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				LocalDate fechaMenor = calendarioDesde.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate fechaMayor = calendarioHasta.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int costoDesde = Integer.parseInt(txtCostodesde.getText());
				int costoHasta = Integer.parseInt(txtCostohasta.getText());
				String especialidad = (String) comboBox.getSelectedItem();
				int idmedico = Integer.parseInt(textFieldMedico.getText());
				int nroPaciente = Integer.parseInt(textFieldNroPaciente.getText());
				
				TurnoDAO tdao = new TurnoDAO();
				ArrayList<Turno> resultado = tdao.consultarBusqueda(Date.valueOf(fechaMenor), Date.valueOf(fechaMayor), costoDesde, costoHasta, especialidad, idmedico, nroPaciente);
				

				DefaultTableModel modelo = new DefaultTableModel(nombresColumnas,0);	
				for ( Turno t : resultado)
					{
					int idPaciente1 = t.getIdPaciente();
					int idMedico = t.getIdMedico();
					LocalDate fecha = t.getFecha();
					LocalTime hora = t.getHora();
					float costo = t.getCosto();
					boolean concurrencia = t.getConcurrencia();
							
					Object [] registro = {idPaciente1, idMedico, fecha, hora, costo, concurrencia};
					
					modelo.addRow(registro);			
				       
					}

				table.setModel(modelo);
				}
		};
		
		btnConsultar.addActionListener(listenerConsultar);
		btnAtras.addActionListener(listenerAtras);
	}

}
