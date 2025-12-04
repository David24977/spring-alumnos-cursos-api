package com.david.curso_alumno.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.david.curso_alumno.dto.AlumnoResponseDto;
import com.david.curso_alumno.dto.CursoRequestDto;
import com.david.curso_alumno.dto.CursoResponseDto;
import com.david.curso_alumno.service.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {
	private final CursoService cursoService;
	
	public CursoController(CursoService cursoService) {
		this.cursoService = cursoService;
	}
	
	@GetMapping
	public List<CursoResponseDto> findAllCursos(){
		return cursoService.findAllCursos();
	}
	
	@GetMapping("/{nombre}")
	public CursoResponseDto findByName(@RequestParam String nombre) {
		return cursoService.findByName(nombre);
	}
	
	@GetMapping("/{id}/alumnos")
	public List<AlumnoResponseDto> findByCursoId(@PathVariable Long id){
		return cursoService.findByCursoId(id);
		
	}
	
	@PostMapping
	public CursoResponseDto createCurso(@Valid @RequestBody CursoRequestDto cursoRequestDto) {
		return cursoService.createCurso(cursoRequestDto);
	}
	
	@PutMapping("/{id}")
	public CursoResponseDto updateCurso(@PathVariable Long id, @Valid @RequestBody CursoRequestDto cursoRequestDto) {
		return cursoService.updateCurso(id, cursoRequestDto);
	}
	
	@DeleteMapping("/{id}")
	public CursoResponseDto deleteCurso(@PathVariable Long id) {
		return cursoService.deleteCurso(id);
	}
	
	
	
	
	
	
	

}
