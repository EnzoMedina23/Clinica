package estructuraTP.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import estructuraTP.modelo.Paciente;
import estructuraTP.modelo.Turno;

public class PacienteDAO extends MySQL_Conn{

	public int crear(Paciente p) {
		int row=0;
		Connection c = conexion();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("INSERT INTO paciente (idpaciente, nombre, apellido, DNI, telefono, historial) VALUES (?,?,?,?,?,?)");
		
			preparedStatement.setInt(1, p.getIdPaciente());
			preparedStatement.setString(2, p.getNombre());
			preparedStatement.setString(3, p.getApellido());
			preparedStatement.setInt(4, p.getDNI());
			preparedStatement.setInt(5, p.getTelefono());
			preparedStatement.setString(6, p.getHistorial());

			row = preparedStatement.executeUpdate();
			System.out.println(row);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return row;
	}

	public Paciente obtenerPaciente(int idPaciente) {
		Connection c = conexion();
		Paciente p = null;
		try {
			PreparedStatement stmt  = c.prepareStatement("SELECT idpaciente, nombre, apellido, DNI, telefono, historial FROM paciente WHERE idpaciente = ?");;
			stmt.setInt(1, idPaciente);
			ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
				
				int idPaciente2 = rs.getInt("idpaciente");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				int dni = rs.getInt("DNI");
				int telefono = rs.getInt("telefono");
				String historial = rs.getString("historial");
				
				
				p = new Paciente(idPaciente2, nombre, apellido, dni, telefono, historial);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return p;
	}

	public int eliminarPaciente(int idPaciente) {
		// TODO Auto-generated method stub
		int row1=0;
		Connection c = conexion();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("DELETE FROM paciente WHERE idpaciente = ?");
			
			preparedStatement.setInt(1, idPaciente);
			
			int row = preparedStatement.executeUpdate();
			row1= row;
			System.out.println(row);
			
			preparedStatement = c.prepareStatement("DELETE FROM turnos WHERE idpaciente = ?");
			preparedStatement.setInt(1, idPaciente);
			row = preparedStatement.executeUpdate();
			System.out.println(row);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return row1;
	}

	public int modificarPaciente(Paciente p) {
		Connection c = conexion();
		int row1=0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("UPDATE paciente SET nombre = ?, apellido = ?, telefono = ?, historial = ? WHERE idpaciente = ?");
		
			preparedStatement.setString(1, p.getNombre());
			preparedStatement.setString(2, p.getApellido());
			preparedStatement.setInt(3, p.getTelefono());
			preparedStatement.setString(4, 	p.getHistorial());
			preparedStatement.setInt(5, p.getIdPaciente());
						
			row1 = preparedStatement.executeUpdate();
			System.out.println(row1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return row1;
	}
	
	public ArrayList<Paciente> obtenerIdsPacientes() {
		Connection c = conexion();
		ArrayList<Paciente> lista = new ArrayList<Paciente>();
		try {
			PreparedStatement stmt  = c.prepareStatement("SELECT idpaciente FROM paciente");;
			ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
				
				int idPaciente = rs.getInt("idpaciente");
				
				Paciente p = new Paciente(idPaciente);
				lista.add(p);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return lista;
	}
	
}
