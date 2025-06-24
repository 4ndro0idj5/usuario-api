package io.github.usuario_api.service;

import io.github.usuario_api.dto.LoginResponseDTO;
import io.github.usuario_api.dto.LoginRequestDTO;
import io.github.usuario_api.dto.UsuarioDTO;
import io.github.usuario_api.dto.UsuarioResponseDTO;
import io.github.usuario_api.entities.Usuario;
import io.github.usuario_api.exceptions.SenhaInvalidaException;
import io.github.usuario_api.exceptions.UsuarioNaoEncontradoException;
import io.github.usuario_api.mapper.UsuarioMapper;
import io.github.usuario_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioResponseDTO cadastrarUsuario(UsuarioDTO dto) {
        Usuario usuario = usuarioMapper.fromDTO(dto);
        Usuario salvo = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(salvo);
    }
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    public LoginResponseDTO autenticar(LoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Email não cadastrado."));

        if (!usuario.getSenha().equals(dto.getSenha())) {
            throw new SenhaInvalidaException("Senha incorreta.");
        }


        usuario.setAutenticado(true);
        Usuario logado = usuarioRepository.save(usuario);

        return usuarioMapper.toLoginResponseDTO(logado);
    }
}


