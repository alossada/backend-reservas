package org.reservas.controller;

import jakarta.validation.Valid;
import org.reservas.dto.UsuarioLoginDTO;
import org.reservas.dto.UsuarioLoginResponseDTO;
import org.reservas.dto.UsuarioRegistroDTO;
import org.reservas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /* Registro público (rol USER) */
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody UsuarioRegistroDTO dto) {
        try {
            usuarioService.registrarUsuario(dto);
            return ResponseEntity.ok("Usuario registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /* Crear ADMIN  —  solo desde el panel admin (debes proteger este endpoint) */
    @PostMapping("/admin/crear")
    public ResponseEntity<?> crearAdmin(@Valid @RequestBody UsuarioRegistroDTO dto) {
        try {
            usuarioService.crearAdmin(dto);
            return ResponseEntity.ok("Administrador creado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /* Login */
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@Valid @RequestBody UsuarioLoginDTO dto) {
        try {
            UsuarioLoginResponseDTO resp = usuarioService.loginUsuario(dto);
            return ResponseEntity.ok(resp);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}


