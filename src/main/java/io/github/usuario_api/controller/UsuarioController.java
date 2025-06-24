package io.github.usuario_api.controller;

import io.github.usuario_api.dto.UsuarioDTO;
import io.github.usuario_api.dto.UsuarioResponseDTO;
import io.github.usuario_api.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@Valid @RequestBody UsuarioDTO dto) {
        UsuarioResponseDTO usuario = usuarioService.cadastrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}

