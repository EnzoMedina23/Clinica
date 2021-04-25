package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import estructuraTP.DAO.PacienteDAO;
import estructuraTP.DAO.TurnoDAO;
import estructuraTP.modelo.Paciente;
import estructuraTP.modelo.Turno;

class Testing {
	@Test
	public void consultaTurnos() {
	    // Arrange
		ArrayList<Turno> expected =new ArrayList<Turno>();
		Turno t1= new Turno(0, 0, null, null, 0, false);
		Turno t2= new Turno(0, 0, null, null, 0, false);
		expected.add(t1);
		expected.add(t2);
		TurnoDAO tdao = new TurnoDAO();
		ArrayList<Turno> resultado = tdao.consultar();

		Boolean result = false;
		if(expected.size() == resultado.size())
			result = true;
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void consultaTurnos2() {
	    // Arrange
		ArrayList<Turno> expected =new ArrayList<Turno>();
		Turno t1= new Turno(0, 0, null, null, 0, false);

		expected.add(t1);

		TurnoDAO tdao = new TurnoDAO();
		ArrayList<Turno> resultado = tdao.consultar();

		Boolean result = false;
		if(expected.size() == resultado.size())
			result = true;
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void consultaTurnosFiltrado() {
	    // Arrange
		ArrayList<Turno> expected =new ArrayList<Turno>();
		Turno t1= new Turno(0, 0, null, null, 0, false);
		expected.add(t1);

		TurnoDAO tdao = new TurnoDAO();
		LocalDate fechaMenor = LocalDate.of(2020, 10, 25);
		LocalDate fechaMayor = LocalDate.of(2020, 10, 29);
		ArrayList<Turno> resultado = tdao.consultarBusqueda(Date.valueOf(fechaMenor), Date.valueOf(fechaMayor), 100, 3000, "Pediatra", 3, 3);

		Boolean result = false;
		if(expected.size() == resultado.size())
			result = true;
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void consultaTurnosFiltrado2() {
	    // Arrange
		ArrayList<Turno> expected =new ArrayList<Turno>();
		Turno t1= new Turno(0, 0, null, null, 0, false);
		expected.add(t1);


		TurnoDAO tdao = new TurnoDAO();
		LocalDate fechaMenor = LocalDate.of(2020, 10, 25);
		LocalDate fechaMayor = LocalDate.of(2020, 11, 29);
		ArrayList<Turno> resultado = tdao.consultarBusqueda(Date.valueOf(fechaMenor), Date.valueOf(fechaMayor), 100, 3000, "Cirujano", 4, 1);
		
		
		
		Boolean result = false;
		if(expected.size() == resultado.size())
			result = true;
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void consultaTurnosFiltrado3() {
	    // Arrange
		ArrayList<Turno> expected =new ArrayList<Turno>();
		Turno t1= new Turno(0, 0, null, null, 0, false);
		Turno t2= new Turno(0, 0, null, null, 0, false);
		expected.add(t1);
		expected.add(t2);


		TurnoDAO tdao = new TurnoDAO();
		LocalDate fechaMenor = LocalDate.of(2020, 10, 25);
		LocalDate fechaMayor = LocalDate.of(2020, 11, 29);
		ArrayList<Turno> resultado = tdao.consultarBusqueda(Date.valueOf(fechaMenor), Date.valueOf(fechaMayor), 100, 3000, "Cirujano", 4, 1);
		
		
		
		Boolean result = false;
		if(expected.size() == resultado.size())
			result = true;
		Assert.assertEquals(false, result);
	}
	
	@Test
	public void AgregarPaciente() {
	    // Arrange
		Paciente p = new Paciente(8, "Inti", "Cabrera", 43211212, 1145283223, "Nada");
		PacienteDAO pdao = new PacienteDAO();
		int result = pdao.crear(p);
		pdao.eliminarPaciente(8);
		

		Assert.assertEquals(1, result);
	}
	
	@Test
	public void EliminarPaciente() {
	    // Arrange
		Paciente p = new Paciente(8, "Inti", "Cabrera", 43211212, 1145283223, "Nada");
		PacienteDAO pdao = new PacienteDAO();
		pdao.crear(p);
		int result = pdao.eliminarPaciente(8);
		

		Assert.assertEquals(1, result);
	}
	
	@Test
	public void modificarPaciente() {
	    // Arrange
		Paciente p = new Paciente(8, "Inti", "Cabrera", 43211212, 1145283223, "Nada");
		PacienteDAO pdao = new PacienteDAO();
		pdao.crear(p);
		Paciente p1 = new Paciente(8, "Roman", "Cabrera", 43211212, 1145283223, "Nada");
		int result = pdao.modificarPaciente(p1);
		pdao.eliminarPaciente(8);
		

		Assert.assertEquals(1, result);
	}
	
	
	
}
