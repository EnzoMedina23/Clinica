package estructuraTP.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import estructuraTP.modelo.Especialidad;
import estructuraTP.modelo.Turno;

public class TurnoDAO extends MySQL_Conn{

	public int crear(Turno t) {
		Connection c = conexion();
		int row=0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("INSERT INTO turnos (idpaciente, idmedico, fecha, hora, costo, concurrencia) VALUES (?, ?, ?, ?, ?, ?)");
		
			preparedStatement.setInt(1, t.getIdPaciente());
			preparedStatement.setInt(2, t.getIdMedico());
			preparedStatement.setDate(3, java.sql.Date.valueOf(t.getFecha()));
			java.sql.Time hora = java.sql.Time.valueOf(t.getHora());
			preparedStatement.setTime(4, hora);
			preparedStatement.setFloat(5, t.getCosto());
			preparedStatement.setBoolean(6, false);

			row = preparedStatement.executeUpdate();
			System.out.println(row);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return row;
	}
	
	public ArrayList<Turno> consultar() {
		Connection c = conexion();
		ArrayList<Turno> s = new ArrayList<Turno>();
		try {

			Statement stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT idpaciente, idmedico, fecha, hora, costo, concurrencia FROM turnos");
			
			while(rs.next()) {
				
				int idPaciente = rs.getInt("idpaciente");
				int idMedico = rs.getInt("idmedico");
				LocalDate fecha = rs.getDate("fecha").toLocalDate();
				LocalTime hora = rs.getTime("hora").toLocalTime();
				float costo = rs.getFloat("costo");
				boolean concurrencia = rs.getBoolean("concurrencia");
				
				Turno t = new Turno(idPaciente, idMedico, fecha, hora, costo, concurrencia);
				s.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return s;
	}
	
	public ArrayList<Turno> consultarBusqueda(Date fechaDesde, Date fechaHasta, int costoDesde, int costoHasta, String especialidad, int idmedico, int nroPaciente) {
		Connection c = conexion();
		ArrayList<Turno> s = new ArrayList<Turno>();
		try {						
			PreparedStatement preparedStatement;

			preparedStatement = c.prepareStatement("SELECT t.idpaciente, t.idmedico, t.fecha, t.hora, t.costo, t.concurrencia FROM turnos AS t INNER JOIN medico AS m ON t.idmedico = m.idmedico WHERE t.fecha BETWEEN ? AND ? AND t.costo BETWEEN ? AND ? AND m.especialidad = ? AND t.idmedico = ? AND t.idpaciente = ?");
			
			
			preparedStatement.setDate(1, fechaDesde);
			preparedStatement.setDate(2, fechaHasta);
			preparedStatement.setInt(3, costoDesde);
			preparedStatement.setInt(4, costoHasta);
			preparedStatement.setString(5, especialidad);
			preparedStatement.setInt(6, idmedico);
			preparedStatement.setInt(7, nroPaciente);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				int idPaciente = rs.getInt("idpaciente");
				int idMedico = rs.getInt("idmedico");
				LocalDate fecha = rs.getDate("fecha").toLocalDate();
				LocalTime hora = rs.getTime("hora").toLocalTime();
				float costo = rs.getFloat("costo");
				boolean concurrencia = rs.getBoolean("concurrencia");
				
				Turno t = new Turno(idPaciente, idMedico, fecha, hora, costo, concurrencia);
				s.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return s;
	}
	
}
