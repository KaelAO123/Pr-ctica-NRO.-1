package com.universidad.service.impl; // Define el paquete al que pertenece esta clase

import com.universidad.dto.EstudianteDTO; // Importa la clase EstudianteDTO del paquete dto
import com.universidad.model.Estudiante; // Importa la clase Estudiante del paquete model
import com.universidad.repository.EstudianteRepository; // Importa la clase EstudianteRepository del paquete repository
import com.universidad.service.IEstudianteService; // Importa la interfaz IEstudianteService del paquete service

import jakarta.annotation.PostConstruct; // Importa la anotación PostConstruct de Jakarta
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación Autowired de Spring
import org.springframework.stereotype.Service; // Importa la anotación Service de Spring

import java.util.ArrayList; // Importa la clase ArrayList para manejar listas
import java.util.List; // Importa la interfaz List para manejar listas

@Service // Anotación que indica que esta clase es un servicio de Spring
public class EstudianteServiceImpl implements IEstudianteService { // Define la clase EstudianteServiceImpl que implementa la interfaz IEstudianteService

    private final EstudianteRepository estudianteRepository; // Declara una variable final para el repositorio de estudiantes

    @Autowired // Anotación que indica que el constructor debe ser usado para inyección de dependencias
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) { // Constructor que recibe el repositorio de estudiantes
        this.estudianteRepository = estudianteRepository; // Asigna el repositorio de estudiantes a la variable de instancia
    }
    
    @PostConstruct // Anotación que indica que este método debe ejecutarse después de la construcción del bean
    public void init() { // Método para inicializar datos de ejemplo
        estudianteRepository.init(); // Llama al método init del repositorio de estudiantes
    }

    @Override // Anotación que indica que este método sobrescribe un método de la interfaz
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() { // Método para obtener una lista de todos los EstudianteDTO
        List<Estudiante> estudiantes = estudianteRepository.findAll(); // Obtiene todos los estudiantes del repositorio
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>(); // Crea una nueva lista para los EstudianteDTO
        
        for (Estudiante estudiante : estudiantes) { // Itera sobre la lista de estudiantes
            estudiantesDTO.add(convertToDTO(estudiante)); // Convierte cada estudiante a EstudianteDTO y lo agrega a la lista
        }
        return estudiantesDTO; // Retorna la lista de EstudianteDTO
    }

    @Override
    public EstudianteDTO guardarEstudiante(EstudianteDTO estudianteDTO) { // Método para guardar un EstudianteDTO
        Estudiante estudiante = convertToEntity(estudianteDTO); // Convierte el EstudianteDTO a Estudiante
        Estudiante estudianteGuardado = estudianteRepository.save(estudiante); // Guarda el estudiante en el repositorio
        return convertToDTO(estudianteGuardado); // Convierte el estudiante guardado a EstudianteDTO y lo retorna
    }
    
    @Override
    public EstudianteDTO obtenerEstudiantePorID(Long id) { // Método para obtener un EstudianteDTO por su ID
        Estudiante estudiante = estudianteRepository.findAll().stream() // Obtiene todos los estudiantes y los convierte a un stream
                .filter(e -> e.getId().equals(id)) // Filtra el stream para encontrar el estudiante con el ID dado
                .findFirst() // Toma el primer resultado del filtro
                .orElse(null); // Si no se encuentra, retorna null
        return convertToDTO(estudiante); // Convierte el estudiante a EstudianteDTO y lo retorna
    }

    @Override
    public EstudianteDTO actualizarEstudiantePorID(Long id, EstudianteDTO estudianteDTO) { // Método para actualizar un EstudianteDTO por su ID
        Estudiante estudianteExistente = estudianteRepository.findAll().stream() // Obtiene todos los estudiantes y los convierte a un stream
                .filter(e -> e.getId().equals(id)) // Filtra el stream para encontrar el estudiante con el ID dado
                .findFirst() // Toma el primer resultado del filtro
                .orElse(null); // Si no se encuentra, retorna null
        
        if (estudianteExistente != null) { // Si se encuentra el estudiante
            Estudiante estudianteActualizado = convertToEntity(estudianteDTO); // Convierte el EstudianteDTO a Estudiante
            estudianteActualizado.setId(id); // Asigna el ID al estudiante actualizado
            estudianteRepository.save(estudianteActualizado); // Guarda el estudiante actualizado en el repositorio
            return convertToDTO(estudianteActualizado); // Convierte el estudiante actualizado a EstudianteDTO y lo retorna
        }
        return null; // Si no se encuentra el estudiante, retorna null
    }

    @Override
    public void eliminarEstudiantePorID(Long id) { // Método para eliminar un EstudianteDTO por su ID
        estudianteRepository.deleteById(id); // Llama al método deleteById del repositorio para eliminar el estudiante
    }

    // Método auxiliar para convertir entidad a DTO
    private EstudianteDTO convertToDTO(Estudiante estudiante) { // Método para convertir un Estudiante a EstudianteDTO
        return EstudianteDTO.builder() // Usa el patrón builder para crear un EstudianteDTO
                .id(estudiante.getId()) // Asigna el ID
                .nombre(estudiante.getNombre()) // Asigna el nombre
                .apellido(estudiante.getApellido()) // Asigna el apellido
                .email(estudiante.getEmail()) // Asigna el email
                .fechaNacimiento(estudiante.getFechaNacimiento()) // Asigna la fecha de nacimiento
                .numeroInscripcion(estudiante.getNumeroInscripcion()) // Asigna el número de inscripción
                .build(); // Construye el objeto EstudianteDTO
    }
    
    // Método auxiliar para convertir DTO a entidad
    private Estudiante convertToEntity(EstudianteDTO estudianteDTO) { // Método para convertir un EstudianteDTO a Estudiante
        return Estudiante.builder() // Usa el patrón builder para crear un Estudiante
                .id(estudianteDTO.getId()) // Asigna el ID
                .nombre(estudianteDTO.getNombre()) // Asigna el nombre
                .apellido(estudianteDTO.getApellido()) // Asigna el apellido
                .email(estudianteDTO.getEmail()) // Asigna el email
                .fechaNacimiento(estudianteDTO.getFechaNacimiento()) // Asigna la fecha de nacimiento
                .numeroInscripcion(estudianteDTO.getNumeroInscripcion()) // Asigna el número de inscripción
                .build(); // Construye el objeto Estudiante
    }
}