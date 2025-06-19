package io.github.usuario_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDTO {
    String nome;
    String cpf;
    String email;
}
