package io.github.usuario_api.autenticador;

import io.github.usuario_api.entities.Usuario;
import io.github.usuario_api.exceptions.SenhaInvalidaException;
import io.github.usuario_api.exceptions.UsuarioNaoEncontradoException;
import io.github.usuario_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioAutenticador {

    private final UsuarioRepository usuarioRepository;

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Email não cadastrado."));

        if (!usuario.getSenha().equals(senha)) {
            throw new SenhaInvalidaException("Senha incorreta.");
        }

        usuario.setAutenticado(true);

        return usuario;
    }
}
