package org.reservas.service;

import org.reservas.model.Reserva;
import org.reservas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    /* Devuelve todas las fechas ocupadas (inclusive) de un producto */
    public List<LocalDate> obtenerFechasOcupadasPorProducto(Long productoId) {
        List<Reserva> reservas = reservaRepository.findByProductoId(productoId);
        List<LocalDate> ocupadas = new ArrayList<>();

        for (Reserva r : reservas) {
            LocalDate fecha = r.getFechaInicio();
            while (!fecha.isAfter(r.getFechaFin())) {
                ocupadas.add(fecha);
                fecha = fecha.plusDays(1);
            }
        }
        return ocupadas;
    }

    /* CRUD */
    public Reserva guardar(Reserva r){ return reservaRepository.save(r); }
}
