package org.reservas.controller;

import org.reservas.model.Reserva;
import org.reservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservaController {

    @Autowired
    private ReservaService servicio;

    /* Endpoint para fechas ocupadas de un producto */
    @GetMapping("/ocupadas/{productoId}")
    public ResponseEntity<?> fechasOcupadas(@PathVariable Long productoId) {
        try {
            List<LocalDate> fechas = servicio.obtenerFechasOcupadasPorProducto(productoId);
            return ResponseEntity.ok(fechas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo obtener la disponibilidad.");
        }
    }

    /* creación de reserva (opcional) */
    @PostMapping
    public Reserva crear(@RequestBody Reserva r){ return servicio.guardar(r); }
}
