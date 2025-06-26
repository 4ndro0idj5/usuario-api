package io.github.usuario_api.dto;

import io.github.usuario_api.entities.Perfil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDTO {
    Long id;
    String nome;
    String cpf;
    String email;
    Boolean autenticado;
    Perfil perfil;
}
