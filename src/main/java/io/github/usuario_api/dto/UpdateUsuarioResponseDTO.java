package io.github.usuario_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUsuarioResponseDTO {
    String mensagem;
    String nome;
    String email;
    String telefone;
}
