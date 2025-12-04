package com.david.curso_alumno.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "Gestión alumnos_curso API",
        version = "1.0.0",
        description = "API REST para gestionar alumnos y cursos"
    )
)
public class OpenApiConfig {}
