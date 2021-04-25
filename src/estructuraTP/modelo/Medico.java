package estructuraTP.modelo;

public class Medico {

	private int idMedico; 
	private String especialidad;
	private String nombre;
	private String apellido;
	private int dni;
	private int campo;
	
	public Medico(int idMedico, String nombre, String apellido) {
		this.idMedico = idMedico;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public int getIdMedico() {
		return idMedico;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
}
