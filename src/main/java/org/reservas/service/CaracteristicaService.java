package org.reservas.service;

import org.reservas.model.Caracteristica;
import org.reservas.repository.CaracteristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaracteristicaService {

    @Autowired
    private CaracteristicaRepository repo;

    public Caracteristica guardar(Caracteristica c) { return repo.save(c); }

    public List<Caracteristica> listar() { return repo.findAll(); }

    public Caracteristica actualizar(Long id, Caracteristica datos) {
        Caracteristica c = repo.findById(id).orElseThrow();
        c.setNombre(datos.getNombre());
        c.setIcono(datos.getIcono());
        return repo.save(c);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}
