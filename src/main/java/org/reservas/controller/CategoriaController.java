package org.reservas.controller;

import org.reservas.dto.CategoriaDTO;
import org.reservas.model.Categoria;
import org.reservas.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    // Método para crear una categoría
    @PostMapping
    public ResponseEntity<?> crearCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        try {
            Categoria guardada = service.guardarDesdeDTO(categoriaDTO);
            return ResponseEntity.ok(guardada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("error", e.getMessage()));
        }
    }

    // Método para listar todas las categorías
    @GetMapping
    public ResponseEntity<?> listarCategorias() {
        return ResponseEntity.ok(service.listarTodasDTO());
    }

    // Método para eliminar una categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok("Categoría eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("error", e.getMessage()));
        }
    }

    // Clase de respuesta de error
    public static class ErrorResponse {
        private String status;
        private String message;

        public ErrorResponse(String status, String message) {
            this.status = status;
            this.message = message;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}




