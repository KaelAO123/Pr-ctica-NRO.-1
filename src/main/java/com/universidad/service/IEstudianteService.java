package com.universidad.service; // Define el paquete al que pertenece esta interfaz

import com.universidad.dto.EstudianteDTO; // Importa la clase EstudianteDTO del paquete dto

import java.util.List; // Importa la interfaz List para manejar listas

public interface IEstudianteService { // Define la interfaz IEstudianteService
    
    List<EstudianteDTO> obtenerTodosLosEstudiantes(); // Método para obtener una lista de todos los EstudianteDTO
    EstudianteDTO guardarEstudiante(EstudianteDTO estudianteDTO); // Método para guardar un EstudianteDTO
    EstudianteDTO obtenerEstudiantePorID(Long id); // Método para obtener un EstudianteDTO por su ID
    EstudianteDTO actualizarEstudiantePorID(Long id, EstudianteDTO estudianteDTO); // Método para actualizar un EstudianteDTO por su ID
    void eliminarEstudiantePorID(Long id);
}