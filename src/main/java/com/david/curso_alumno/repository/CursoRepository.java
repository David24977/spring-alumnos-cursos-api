package com.david.curso_alumno.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.curso_alumno.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
	
	Optional<Curso> findByNombreCurso(String nombre);

}

