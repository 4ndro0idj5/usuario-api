package io.github.usuario_api.service;

import io.github.usuario_api.autenticador.UsuarioAutenticadoValidator;
import io.github.usuario_api.autenticador.UsuarioAutenticador;
import io.github.usuario_api.dto.*;
import io.github.usuario_api.entities.Endereco;
import io.github.usuario_api.entities.Usuario;
import io.github.usuario_api.exceptions.SenhaInvalidaException;
import io.github.usuario_api.exceptions.UsuarioNaoEncontradoException;
import io.github.usuario_api.mapper.UsuarioMapper;
import io.github.usuario_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioAutenticador usuarioAutenticador;
    private final UsuarioAutenticadoValidator usuarioAutenticadoValidator;

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

    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado com ID: " + id
                ));

        return usuarioMapper.toDTO(usuario);
    }

    public LoginResponseDTO autenticar(LoginRequestDTO dto) {

        Usuario usuario = usuarioAutenticador.autenticar(dto.getEmail(), dto.getSenha());

        Usuario logado = usuarioRepository.save(usuario);

        return usuarioMapper.toLoginResponseDTO(logado);
    }
    public UpdateUsuarioResponseDTO atualizar(Long id, UpdateUsuarioRequestDTO dto) {

        Usuario usuario = usuarioAutenticadoValidator.validarUsuarioAutenticado(id);

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());

        Usuario atualizado = usuarioRepository.save(usuario);

        return usuarioMapper.toUpdateDTO(atualizado);
    }

    public UpdateResponseEnderecoDTO atualizarEndereco(Long id, EnderecoDTO dto) {

        Usuario usuario = usuarioAutenticadoValidator.validarUsuarioAutenticado(id);

        Endereco endereco = usuario.getEndereco();
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setCep(dto.getCep());

        usuarioRepository.save(usuario);

        return usuarioMapper.toEnderecoDTO(endereco);
    }

    public void alterarSenha(Long id, UsuarioSenhaUpdateDTO dto) {

        Usuario usuario = usuarioAutenticadoValidator.validarUsuarioAutenticado(id);

        if (!usuario.getSenha().equals(dto.getSenhaAtual())) {
            throw new SenhaInvalidaException("Senha atual incorreta.");
        }

        usuario.setSenha(dto.getNovaSenha());
        usuarioRepository.save(usuario);
    }
    public void deslogar(Long id) {

        Usuario usuario = usuarioAutenticadoValidator.validarUsuarioAutenticado(id);

        usuario.setAutenticado(false);
        usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {

        Usuario usuario = usuarioAutenticadoValidator.validarUsuarioAutenticado(id);
        usuarioRepository.delete(usuario);
    }
}


