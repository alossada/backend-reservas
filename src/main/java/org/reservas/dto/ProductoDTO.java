package org.reservas.dto;

import java.util.List;
import lombok.Data;
import org.reservas.dto.CaracteristicaDTO;
import org.reservas.dto.CategoriaDTO;

@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<String> imagenes;
    private CategoriaDTO categoria;
    private List<CaracteristicaDTO> caracteristicas;
}



