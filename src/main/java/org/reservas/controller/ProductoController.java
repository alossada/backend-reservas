package org.reservas.controller;

import org.reservas.dto.ProductoDTO;
import org.reservas.model.Producto;
import org.reservas.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductoController {

    @Autowired
    private ProductoService service;


    // Metodo para crear un producto
    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody ProductoDTO productoDTO) {
        try {
            Producto guardado = service.guardarDesdeDTO(productoDTO);
            return ResponseEntity.ok(guardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("error", e.getMessage()));
        }
    }

    // Metodo para listar todos los productos
    @GetMapping
    public ResponseEntity<?> listarProductos() {
        return ResponseEntity.ok(service.listarTodosDTO());
    }

    // Metodo para eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok("Producto eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("error", e.getMessage()));
        }
    }
// Metodo para filtrar productos por una o más categorías
@GetMapping("/filtrar")
public ResponseEntity<List<Producto>> filtrarPorCategorias(@RequestParam List<Long> categorias) {
    List<Producto> productos = service.buscarPorCategorias(categorias);
    return ResponseEntity.ok(productos);
}

    // Nuevo metodo para obtener productos por ID de categoría
    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Producto>> obtenerProductosPorCategoria(@PathVariable Long id) {
        try {
            List<Producto> productos = service.buscarPorCategoria(id);
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
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

