package org.reservas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String password;

    /**
     * Rol del usuario:
     *  - "ADMIN"  → acceso al panel de administración
     *  - "USER"   → usuario estándar
     *  (Por defecto será "USER")
     */
    @Column(nullable = false)
    private String rol = "USER";
}

