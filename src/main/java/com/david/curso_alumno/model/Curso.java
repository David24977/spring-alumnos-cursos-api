package com.david.curso_alumno.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, name = "nombre_curso", length = 30, unique = true)
	private String nombreCurso;
	
	/* RELACIÓN INVERSA (NO CREA COLUMNA)
  @OneToMany(mappedBy = "curso")
  List<Alumno> alumnos;
  Se suele hacer para practicar, pero las empresas no lo usan porque crea muchos problemas de recursividad,
  en este ejemplo no lo voy a usar
  */

}
