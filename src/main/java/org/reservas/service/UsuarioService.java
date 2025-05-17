package org.reservas.service;

import org.reservas.dto.UsuarioLoginDTO;
import org.reservas.dto.UsuarioLoginResponseDTO;
import org.reservas.dto.UsuarioRegistroDTO;
import org.reservas.model.Usuario;
import org.reservas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /* ⚙️ Registro USER */
    public Usuario registrarUsuario(UsuarioRegistroDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email.");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());   // TODO encriptar
        usuario.setRol("USER");                   // <— siempre USER

        return usuarioRepository.save(usuario);
    }

    /* ⚙️ Crear ADMIN (solo desde /admin) */
    public Usuario crearAdmin(UsuarioRegistroDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email.");
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword()); // TODO encriptar
        usuario.setRol("ADMIN");                  // <— siempre ADMIN
        return usuarioRepository.save(usuario);
    }

    /* ⚙️ Login → devuelve DTO con rol */
    public UsuarioLoginResponseDTO loginUsuario(UsuarioLoginDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .filter(u -> u.getPassword().equals(dto.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Email o contraseña incorrectos."));

        return new UsuarioLoginResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getRol()
        );
    }
}



