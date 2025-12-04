package com.david.curso_alumno.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.curso_alumno.dto.AlumnoRequestDto;
import com.david.curso_alumno.dto.AlumnoResponseDto;
import com.david.curso_alumno.service.AlumnoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
	
	private final AlumnoService alumnoService;
	
	public AlumnoController(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}
	
	@GetMapping
	public List<AlumnoResponseDto> findAllStudents(){
		return alumnoService.findAllStudents();
	}
	
	@GetMapping("/nombre/{nombre}")
	public List<AlumnoResponseDto> findByName(@PathVariable String nombre){
		return alumnoService.findByName(nombre); 
	}
	
	/*
	 * Este método suplanta a los 2 anteriores, sirve para mostrar todos
	 * o por nombre, con un método sirve para dos
	 * @GetMapping 
	 * public List<AlumnoResponseDto> findAllStudentsOrName
	 * (@RequestParam(required = false) String nombre ){ if(nombre == null ||
	 * nombre.isBlank()) { return alumnoService.findAllStudents(); } return
	 * alumnoService.findByName(nombre); }
	 */
	
	
	@PostMapping
	public AlumnoResponseDto createStudent(@Valid @RequestBody AlumnoRequestDto alumnoRequestDto) {
		return alumnoService.createStudent(alumnoRequestDto);
		
	}
	
	@PutMapping("/{id}")
	public AlumnoResponseDto updateStudent(@PathVariable Long id, @Valid @RequestBody AlumnoRequestDto alumnoRequestDto) {
		return alumnoService.updateStudent(id, alumnoRequestDto);
	}
	
	@PatchMapping("/{id}")
	public AlumnoResponseDto updatePartialStudent(@PathVariable Long id, @RequestBody AlumnoRequestDto alumnoRequestDto) {
		return alumnoService.updatePartialStudent(id, alumnoRequestDto);
	}
	
	@DeleteMapping("/{id}")
	public AlumnoResponseDto deleteStudent(@PathVariable Long id) {
		return alumnoService.deleteStudent(id);
	}
	

}
