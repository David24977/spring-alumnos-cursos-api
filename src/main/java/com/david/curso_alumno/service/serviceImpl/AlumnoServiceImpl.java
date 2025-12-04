package com.david.curso_alumno.service.serviceImpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.david.curso_alumno.dto.AlumnoRequestDto;
import com.david.curso_alumno.dto.AlumnoResponseDto;
import com.david.curso_alumno.model.Alumno;
import com.david.curso_alumno.model.Curso;
import com.david.curso_alumno.repository.AlumnoRepository;
import com.david.curso_alumno.repository.CursoRepository;
import com.david.curso_alumno.service.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService{
	
	private final AlumnoRepository alumnoRepository;
	private final CursoRepository cursoRepository;

	
	public AlumnoServiceImpl(AlumnoRepository alumnoRepository, CursoRepository cursoRepository) {
		this.alumnoRepository = alumnoRepository;
		this.cursoRepository = cursoRepository;
	}

	@Override
	public List<AlumnoResponseDto> findAllStudents() {
		return alumnoRepository.findAll()
				.stream()
				.map(a -> mapToResponseDto(a))
				.toList();
	}

	@Override
	public List<AlumnoResponseDto> findByName(String nombre) {
		//Para evitar búsquedas sin nombre o mal estructuradas, saltos en blanco, devolvemos todos los alumnos
		if(nombre == null || nombre.isBlank()) {
		    return findAllStudents();
		}
		return alumnoRepository.findByNombreContainingIgnoreCase(nombre)
				.stream()
				.map(this::mapToResponseDto)
				.toList();
		}
	

	@Override
	public AlumnoResponseDto createStudent(AlumnoRequestDto alumnoRequestDto) {
		
		Curso curso = cursoRepository.findById(alumnoRequestDto.getCursoId())
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));
		Alumno crearAlumno = new Alumno();
		crearAlumno.setNombre(alumnoRequestDto.getNombre());
		crearAlumno.setCurso(curso);
		
		alumnoRepository.save(crearAlumno);
		
		return mapToResponseDto(crearAlumno);
		
	}

	@Override
	public AlumnoResponseDto updateStudent(Long id, AlumnoRequestDto alumnoRequestDto) {
		Curso curso = cursoRepository.findById(alumnoRequestDto.getCursoId())
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));
		
		Alumno alumnoActualizado = alumnoRepository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));
		
		alumnoActualizado.setNombre(alumnoRequestDto.getNombre());
		alumnoActualizado.setCurso(curso);
		
		alumnoRepository.save(alumnoActualizado);
		
		return mapToResponseDto(alumnoActualizado);
	}
	

	@Override
	public AlumnoResponseDto updatePartialStudent(Long id, AlumnoRequestDto alumnoRequestDto) {
		
		
		Alumno actualizadoParcialAlumno = alumnoRepository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));
		
		if(alumnoRequestDto.getNombre() != null) {
			actualizadoParcialAlumno.setNombre(alumnoRequestDto.getNombre());
		}
		
		if(alumnoRequestDto.getCursoId() != null) {
			Curso curso = cursoRepository.findById(alumnoRequestDto.getCursoId())
					.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));
			actualizadoParcialAlumno.setCurso(curso);
		}
		
		alumnoRepository.save(actualizadoParcialAlumno);
	
		
		return mapToResponseDto(actualizadoParcialAlumno);
	}

	@Override
	public AlumnoResponseDto deleteStudent(Long id) {
		Alumno eliminado = alumnoRepository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alumno no encontrado"));
		
		alumnoRepository.delete(eliminado);
		
		return mapToResponseDto(eliminado);
		
	}
	
	public AlumnoResponseDto mapToResponseDto(Alumno alumno) {
		return new AlumnoResponseDto(
				alumno.getId(),
				alumno.getNombre(),
				alumno.getCurso().getId(),
				alumno.getCurso().getNombreCurso());
	}
	

}




