package io.github.usuario_api.mapper;

import io.github.usuario_api.dto.LoginResponseDTO;
import io.github.usuario_api.dto.UsuarioDTO;
import io.github.usuario_api.dto.UsuarioResponseDTO;
import io.github.usuario_api.entities.Endereco;
import io.github.usuario_api.entities.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public Usuario fromDTO(UsuarioDTO dto){
        return Usuario.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .senha(dto.getSenha())
                .perfil(dto.getPerfil())
                .autenticado(false)
                .endereco(Endereco.builder()
                        .rua(dto.getEndereco().getRua())
                        .numero(dto.getEndereco().getNumero())
                        .bairro(dto.getEndereco().getBairro())
                        .cidade(dto.getEndereco().getCidade())
                        .cep(dto.getEndereco().getCep())
                        .build())
                .build();
    }

    public UsuarioResponseDTO toDTO(Usuario usuario){
        return UsuarioResponseDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .cpf(usuario.getCpf())
                .build();
    }

    public LoginResponseDTO toLoginResponseDTO(Usuario usuario){
        return LoginResponseDTO.builder()
                .mensagem("Login realizado com sucesso.")
                .nome(usuario.getNome())
                .build();
    }
}
