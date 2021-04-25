package estructuraTP.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Turno {

	private int idTurno;
	private int idPaciente;
	private int idMedico;
	private LocalDate fecha;
	private LocalTime hora;
	private float costo;
	private boolean concurrencia;
	
	public Turno(int idPaciente, int idMedico, LocalDate fecha, LocalTime hora, float costo) {
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
		this.fecha = fecha;
		this.hora = hora;
		this.costo = costo;
	}

	public Turno(int idPaciente, int idMedico, LocalDate fecha, LocalTime hora, float costo, boolean concurrencia) {
		this.idPaciente = idPaciente;
		this.idMedico = idMedico;
		this.fecha = fecha;
		this.hora = hora;
		this.costo = costo;
		this.concurrencia = concurrencia;
	}

	public int getIdPaciente() {
		return idPaciente;
	}
	
	public int getIdMedico() {
		return idMedico;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public LocalTime getHora() {
		return hora;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public boolean getConcurrencia() {
		return concurrencia;
	}
}
