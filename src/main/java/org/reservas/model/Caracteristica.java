package org.reservas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;   // Ej: “Wi-Fi”, “Piscina”

    private String icono;    // Ej: “wifi”, “pool” (nombre de ícono o URL)
}
