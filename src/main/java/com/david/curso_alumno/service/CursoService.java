package com.david.curso_alumno.service;

import java.util.List;

import com.david.curso_alumno.dto.AlumnoResponseDto;
import com.david.curso_alumno.dto.CursoRequestDto;
import com.david.curso_alumno.dto.CursoResponseDto;

public interface CursoService {
	
	List<CursoResponseDto> findAllCursos();
	
	CursoResponseDto findByName(String nombre);
	
	List<AlumnoResponseDto> findByCursoId(Long cursoId);//Aunque devuelva una lista de Alumnos va aquí, porque el recurso principal es curso
	//Queremos la lista de alumnos de un curso(curso es la rest principal /cursos/{id}/alumnos
	
	CursoResponseDto createCurso(CursoRequestDto cursoRequestDto);
	
	CursoResponseDto updateCurso(Long id, CursoRequestDto cursoRequestDto);
	
	CursoResponseDto deleteCurso(Long id);
	
	
	

}
