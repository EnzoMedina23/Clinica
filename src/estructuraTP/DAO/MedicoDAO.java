package estructuraTP.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTP.modelo.Medico;

public class MedicoDAO extends MySQL_Conn{

	public ArrayList<Medico> consultarPorEspecialidad(String especialidad) {
		Connection c = conexion();
		ArrayList<Medico> s = new ArrayList<Medico>();
		try {

			PreparedStatement preparedStatement;

			preparedStatement = c.prepareStatement("SELECT idmedico, nombre, apellido FROM medico WHERE especialidad = ?");
			
			preparedStatement.setString(1, especialidad);
			
			ResultSet rs = preparedStatement.executeQuery();			
			
			while(rs.next()) {
				
				int idMedico = rs.getInt("idmedico");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				
				Medico m = new Medico(idMedico, nombre, apellido);
				s.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return s;
	}
	
}
