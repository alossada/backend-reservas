package org.reservas.controller;

import org.reservas.model.Caracteristica;
import org.reservas.service.CaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caracteristicas")
@CrossOrigin(origins = "*")
public class CaracteristicaController {

    @Autowired
    private CaracteristicaService servicio;

    @PostMapping
    public ResponseEntity<Caracteristica> crear(@RequestBody Caracteristica c) {
        return ResponseEntity.ok(servicio.guardar(c));
    }

    @GetMapping
    public List<Caracteristica> listar() { return servicio.listar(); }

    @PutMapping("/{id}")
    public ResponseEntity<Caracteristica> actualizar(@PathVariable Long id,
                                                     @RequestBody Caracteristica c) {
        return ResponseEntity.ok(servicio.actualizar(id, c));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.ok("Eliminado correctamente");
    }
}
