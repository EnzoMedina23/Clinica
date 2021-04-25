package estructuraTP.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTP.modelo.Especialidad;

public class EspecialidadDAO extends MySQL_Conn{

	public ArrayList<Especialidad> consultar() {
		Connection c = conexion();
		ArrayList<Especialidad> s = new ArrayList<Especialidad>();
		try {

			Statement stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT especialidad FROM especialidades");
			
			while(rs.next()) {
				
				String nombreEspecialidad = rs.getString("especialidad");
				
				Especialidad e = new Especialidad(nombreEspecialidad);
				s.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return s;
	}
	
}
