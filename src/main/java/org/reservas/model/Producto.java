package org.reservas.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
            name = "producto_caracteristica",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "caracteristica_id")
    )
    private List<Caracteristica> caracteristicas;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @jakarta.validation.constraints.NotNull
    @com.fasterxml.jackson.annotation.JsonIgnore        // evita bucle infinito en JSON
    private List<Reserva> reservas = List.of();


    @ElementCollection
    private List<String> imagenes;
}




