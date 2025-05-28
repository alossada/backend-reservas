package org.reservas.service;

import org.reservas.dto.CaracteristicaDTO;
import org.reservas.dto.CategoriaDTO;
import org.reservas.dto.ProductoDTO;
import org.reservas.model.Caracteristica;
import org.reservas.model.Categoria;
import org.reservas.model.Producto;
import org.reservas.repository.CaracteristicaRepository;
import org.reservas.repository.CategoriaRepository;
import org.reservas.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CaracteristicaRepository caracteristicaRepository;

    // Método para guardar un producto desde un DTO
    public Producto guardarDesdeDTO(ProductoDTO dto) throws Exception {
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre del producto es obligatorio");
        }

        if (dto.getDescripcion() == null || dto.getDescripcion().trim().isEmpty()) {
            throw new Exception("La descripción del producto es obligatoria");
        }

        if (dto.getCategoria() == null || dto.getCategoria().getId() == null) {
            throw new Exception("El producto debe tener una categoría seleccionada");
        }

        if (productoRepository.findByNombre(dto.getNombre()).isPresent()) {
            throw new Exception("Ya existe un producto con ese nombre");
        }

        Categoria categoria = categoriaRepository.findById(dto.getCategoria().getId())
                .orElseThrow(() -> new Exception("Categoría no encontrada con ID: " + dto.getCategoria().getId()));

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre().trim());
        producto.setDescripcion(dto.getDescripcion().trim());
        producto.setImagenes(dto.getImagenes() != null ? dto.getImagenes() : List.of());
        producto.setCategoria(categoria);

        if (dto.getCaracteristicas() != null && !dto.getCaracteristicas().isEmpty()) {
            List<Caracteristica> caracteristicas = dto.getCaracteristicas().stream()
                    .map(carDTO -> caracteristicaRepository.findById(carDTO.getId())
                            .orElseThrow(() -> new RuntimeException("Característica no encontrada con ID: " + carDTO.getId())))
                    .collect(Collectors.toList());
            producto.setCaracteristicas(caracteristicas);
        } else {
            producto.setCaracteristicas(List.of());
        }

        return productoRepository.save(producto);
    }

    // Método para listar todos los productos como DTOs
    public List<ProductoDTO> listarTodosDTO() {
        return productoRepository.findAll().stream().map(producto -> {
            ProductoDTO dto = new ProductoDTO();
            dto.setId(producto.getId());
            dto.setNombre(producto.getNombre());
            dto.setDescripcion(producto.getDescripcion());
            dto.setImagenes(producto.getImagenes());

            if (producto.getCategoria() != null) {
                Categoria categoria = producto.getCategoria();
                CategoriaDTO categoriaDTO = new CategoriaDTO();
                categoriaDTO.setId(categoria.getId());
                categoriaDTO.setTitulo(categoria.getTitulo());
                categoriaDTO.setDescripcion(categoria.getDescripcion());
                categoriaDTO.setImagen(categoria.getImagen());
                dto.setCategoria(categoriaDTO);
            }

            if (producto.getCaracteristicas() != null && !producto.getCaracteristicas().isEmpty()) {
                List<CaracteristicaDTO> caracteristicasDTO = producto.getCaracteristicas().stream()
                        .map(car -> {
                            CaracteristicaDTO carDTO = new CaracteristicaDTO();
                            carDTO.setId(car.getId());
                            carDTO.setNombre(car.getNombre());
                            carDTO.setIcono(car.getIcono());
                            return carDTO;
                        }).collect(Collectors.toList());
                dto.setCaracteristicas(caracteristicasDTO);
            } else {
                dto.setCaracteristicas(List.of());
            }

            return dto;
        }).collect(Collectors.toList());
    }

    // Método para filtrar productos por múltiples categorías
    public List<Producto> buscarPorCategorias(List<Long> categoriaIds) {
        return productoRepository.findByCategoriaIdIn(categoriaIds);
    }

    // 🔥 Nuevo método para buscar productos por una sola categoría
    public List<Producto> buscarPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

    // Método para eliminar un producto
    public void eliminar(Long id) throws Exception {
        if (!productoRepository.existsById(id)) {
            throw new Exception("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }
}

