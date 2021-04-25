package estructuraTP.modelo;

import java.util.ArrayList;

public class Especialidad {

	private String especialidad;
	private ArrayList<Medico> medicos;
	
	
	public Especialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	public String getEspecialidad() {
		return especialidad;
	}
}
