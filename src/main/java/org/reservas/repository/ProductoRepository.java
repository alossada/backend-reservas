package org.reservas.repository;

import org.reservas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);

    List<Producto> findByCategoriaIdIn(List<Long> categoriaIds);

    // método para buscar por una sola categoría
    List<Producto> findByCategoriaId(Long categoriaId);
}
