package io.github.usuario_api.autenticador;

import io.github.usuario_api.entities.Usuario;
import io.github.usuario_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioAutenticadoValidator {

    private final UsuarioRepository usuarioRepository;

    public Usuario validarUsuarioAutenticado(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!Boolean.TRUE.equals(usuario.getAutenticado())) {
            throw new RuntimeException("Usuário não autenticado. Faça login antes de atualizar os dados.");
        }

        return usuario;
    }
}
