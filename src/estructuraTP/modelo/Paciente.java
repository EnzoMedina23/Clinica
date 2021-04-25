package estructuraTP.modelo;

import java.sql.Date;

public class Paciente {

	private int idPaciente;
	private String nombre;
	private String apellido;
	private int dni;
	private int telefono;
	private String historial;
	
	public Paciente(int idPaciente, String nombre, String apellido, int dni, int telefono, String historial) {
		this.idPaciente = idPaciente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.historial = historial;
	}

	public Paciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public int getDNI() {
		return dni;
	}

	public int getTelefono() {
		return telefono;
	}
	
	public String getHistorial() {
		return historial;
	}
}
