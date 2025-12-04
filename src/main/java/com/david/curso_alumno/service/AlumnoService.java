package com.david.curso_alumno.service;

import java.util.List;

import com.david.curso_alumno.dto.AlumnoRequestDto;
import com.david.curso_alumno.dto.AlumnoResponseDto;

public interface AlumnoService {
	
	List<AlumnoResponseDto> findAllStudents();
	
	List<AlumnoResponseDto> findByName(String nombre);
	
	AlumnoResponseDto createStudent(AlumnoRequestDto alumnoRequestDto);
	
	AlumnoResponseDto updateStudent(Long id, AlumnoRequestDto alumnoRequestDto);
	
	AlumnoResponseDto updatePartialStudent(Long id, AlumnoRequestDto alumnoRequestDto);
	
	AlumnoResponseDto deleteStudent(Long id);

}

