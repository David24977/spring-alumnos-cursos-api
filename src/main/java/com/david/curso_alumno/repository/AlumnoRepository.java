package com.david.curso_alumno.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.curso_alumno.model.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long>{
	List<Alumno> findByNombreContainingIgnoreCase(String nombre);
	List<Alumno> findByCursoId(Long cursoId);

}

