package com.david.curso_alumno.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CursoRequestDto {
	
	@NotBlank(message="El nombre no puede estar vacío")
	@Size(min = 3, max = 30)
	private String nombreCurso;

}
