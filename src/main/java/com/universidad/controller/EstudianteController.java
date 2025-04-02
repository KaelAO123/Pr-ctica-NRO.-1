package com.universidad.controller; // Define el paquete al que pertenece esta clase

import com.universidad.dto.EstudianteDTO; // Importa la clase EstudianteDTO del paquete dto
import com.universidad.service.IEstudianteService; // Importa la interfaz IEstudianteService del paquete service

import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación Autowired de Spring
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity de Spring para manejar respuestas HTTP
import org.springframework.web.bind.annotation.*; // Importa las anotaciones de Spring para controladores web

import java.net.URI;
import java.util.List; // Importa la interfaz List para manejar listas

@RestController // Anotación que indica que esta clase es un controlador REST de Spring
@RequestMapping("/api") // Define la ruta base para las solicitudes HTTP a este controlador
public class EstudianteController { // Define la clase EstudianteController

    private final IEstudianteService estudianteService; // Declara una variable final para el servicio de estudiantes

    @Autowired // Anotación que indica que el constructor debe ser usado para inyección de dependencias
    public EstudianteController(IEstudianteService estudianteService) { // Constructor que recibe el servicio de estudiantes
        this.estudianteService = estudianteService; // Asigna el servicio de estudiantes a la variable de instancia
    }

    @GetMapping("/estudiantes") // Anotación que indica que este método maneja solicitudes GET
    public ResponseEntity<List<EstudianteDTO>> obtenerTodosLosEstudiantes() { // Método para obtener una lista de todos los EstudianteDTO
        List<EstudianteDTO> estudiantes = estudianteService.obtenerTodosLosEstudiantes(); // Llama al servicio para obtener todos los estudiantes
        return ResponseEntity.ok(estudiantes); // Retorna una respuesta HTTP 200 OK con la lista de estudiantes
    }

    @GetMapping("/estudiantes/{id}") // Anotación que indica que este método maneja solicitudes GET
    public ResponseEntity<EstudianteDTO> obtenerEstudiantePorID(@PathVariable Long id) { // Método para obtener un EstudianteDTO por su ID
        EstudianteDTO estudiante = estudianteService.obtenerEstudiantePorID(id); // Llama al servicio para obtener el estudiante por su ID
        return ResponseEntity.ok(estudiante); // Retorna una respuesta HTTP 200 OK con el estudiante
    }
    @PostMapping("/estudiantes") // Anotación que indica que este método maneja solicitudes POST
    public ResponseEntity<EstudianteDTO> guardarEstudiante(@RequestBody EstudianteDTO estudianteDTO) { // Método para guardar un EstudianteDTO
        EstudianteDTO estudianteGuardado = estudianteService.guardarEstudiante(estudianteDTO); // Llama al servicio para guardar el estudiante
        URI location = URI.create("/api/estudiantes/" + estudianteGuardado.getId()); // Crea una URI para la ubicación del nuevo recurso
        return ResponseEntity.created(location).body(estudianteGuardado); // Retorna una respuesta HTTP 201 CREATED con el estudiante guardado
    }
    @PutMapping("/estudiantes/{id}") // Anotación que indica que este método maneja solicitudes PUT
    public ResponseEntity<EstudianteDTO> actualizarEstudiantePorID(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO) { // Método para actualizar un EstudianteDTO por su ID
        EstudianteDTO estudianteActualizado = estudianteService.actualizarEstudiantePorID(id, estudianteDTO); // Llama al servicio para actualizar el estudiante
        return ResponseEntity.ok(estudianteActualizado); // Retorna una respuesta HTTP 200 OK con el estudiante actualizado
    }
    
    @DeleteMapping("/estudiantes/{id}") // Anotación que indica que este método maneja solicitudes DELETE
    public ResponseEntity<Void> eliminarEstudiantePorID(@PathVariable Long id) { // Método para eliminar un EstudianteDTO por su ID
        estudianteService.eliminarEstudiantePorID(id); // Llama al servicio para eliminar el estudiante
        return ResponseEntity.noContent().build(); // Retorna una respuesta HTTP 204 No Content
    }
}