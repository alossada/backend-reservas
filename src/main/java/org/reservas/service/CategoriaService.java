package org.reservas.service;

import org.reservas.dto.CategoriaDTO;
import org.reservas.model.Categoria;
import org.reservas.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Metodo para guardar una categoría desde un DTO
    public Categoria guardarDesdeDTO(CategoriaDTO dto) throws Exception {
        if (dto.getTitulo() == null || dto.getTitulo().trim().isEmpty()) {
            throw new Exception("El título de la categoría es obligatorio");
        }

        Categoria categoria = new Categoria();
        categoria.setTitulo(dto.getTitulo().trim());

        return categoriaRepository.save(categoria);
    }

    // Metodo para listar todas las categorías como DTOs
    public List<CategoriaDTO> listarTodasDTO() {
        return categoriaRepository.findAll().stream().map(cat -> {
            CategoriaDTO dto = new CategoriaDTO();
            dto.setId(cat.getId());
            dto.setTitulo(cat.getTitulo());
            dto.setDescripcion(cat.getDescripcion());   // 👈
            dto.setImagen(cat.getImagen());             // 👈
            return dto;
        }).collect(Collectors.toList());
    }

    // Metodo para eliminar una categoría
    public void eliminar(Long id) throws Exception {
        if (!categoriaRepository.existsById(id)) {
            throw new Exception("Categoría no encontrada");
        }
        categoriaRepository.deleteById(id);
    }
}


