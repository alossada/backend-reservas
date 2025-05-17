package org.reservas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioRegistroDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio.")
    private String apellido;

    @Email(message = "El correo electrónico no es válido.")
    @NotBlank(message = "El correo electrónico es obligatorio.")
    private String email;

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    @NotBlank(message = "La contraseña es obligatoria.")
    private String password;
}

