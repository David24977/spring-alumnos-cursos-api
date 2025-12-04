package com.david.curso_alumno.service.serviceImpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.david.curso_alumno.dto.AlumnoResponseDto;
import com.david.curso_alumno.dto.CursoRequestDto;
import com.david.curso_alumno.dto.CursoResponseDto;
import com.david.curso_alumno.model.Curso;
import com.david.curso_alumno.repository.AlumnoRepository;
import com.david.curso_alumno.repository.CursoRepository;
import com.david.curso_alumno.service.CursoService;

@Service
public class CursoServiceImpl implements CursoService{
	
	private final CursoRepository cursoRepository;
	private final AlumnoRepository alumnoRepository;
	
	public CursoServiceImpl(CursoRepository cursoRepository, AlumnoRepository alumnoRepository) {
		this.cursoRepository = cursoRepository;
		this.alumnoRepository = alumnoRepository;
	}

	@Override
	public List<CursoResponseDto> findAllCursos() {
		return cursoRepository.findAll()
				.stream()
				.map(this::mapToCursoResponseDto)
				.toList();
	}
	
	
	@Override
	public CursoResponseDto findByName(String nombre) {
		Curso cursoNombre = cursoRepository.findByNombreCurso(nombre)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));
		
		return mapToCursoResponseDto(cursoNombre);
		
	}
	
	
	

	@Override
	public List<AlumnoResponseDto> findByCursoId(Long id) {
		
		return alumnoRepository.findByCursoId(id)
				.stream()
				.map(a -> new AlumnoResponseDto(
						a.getId(),
						a.getNombre(),
						a.getCurso().getId(),
						a.getCurso().getNombreCurso()))
				.toList();
		}
	
	

	@Override
	public CursoResponseDto createCurso(CursoRequestDto cursoRequestDto) {
		Curso crearCurso = new Curso();
		crearCurso.setNombreCurso(cursoRequestDto.getNombreCurso());
		
		cursoRepository.save(crearCurso);
		return mapToCursoResponseDto(crearCurso);
		
	}
	

	@Override
	public CursoResponseDto updateCurso(Long id, CursoRequestDto cursoRequestDto) {
		Curso encontradoCurso = cursoRepository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));
		
		if(cursoRequestDto.getNombreCurso() != null) {
			encontradoCurso.setNombreCurso(cursoRequestDto.getNombreCurso());
		}
		
		cursoRepository.save(encontradoCurso);
		
		return mapToCursoResponseDto(encontradoCurso);
				
	}
	

	@Override
	public CursoResponseDto deleteCurso(Long id) {
		Curso eliminadoCurso = cursoRepository.findById(id)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));
		
		cursoRepository.delete(eliminadoCurso);
		return mapToCursoResponseDto(eliminadoCurso);
	}
	
	
	private CursoResponseDto mapToCursoResponseDto(Curso curso) {
		return new CursoResponseDto(
				curso.getId(),
				curso.getNombreCurso());
	}

}
