package io.github.usuario_api.service;

import io.github.usuario_api.dto.*;
import io.github.usuario_api.entities.Endereco;
import io.github.usuario_api.entities.Usuario;
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
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com este e-mail"));

        if (!usuario.getSenha().equals(dto.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }


        usuario.setAutenticado(true);
        Usuario logado = usuarioRepository.save(usuario);

        return usuarioMapper.toLoginResponseDTO(logado);
    }
    public UpdateUsuarioResponseDTO atualizar(Long id, UpdateUsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuario.getAutenticado()) {
            throw new RuntimeException("Usuário não autenticado. Faça login antes de atualizar os dados.");
        }

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());

        Usuario atualizado = usuarioRepository.save(usuario);

        return usuarioMapper.toUpdateDTO(atualizado);
    }

    public UpdateResponseEnderecoDTO atualizarEndereco(Long id, EnderecoDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuario.getAutenticado()) {
            throw new RuntimeException("Usuário não autenticado. Faça login antes de atualizar o endereço.");
        }

        Endereco endereco = usuario.getEndereco();
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setCep(dto.getCep());

        usuarioRepository.save(usuario);

        return usuarioMapper.toEnderecoDTO(endereco);
    }
}


