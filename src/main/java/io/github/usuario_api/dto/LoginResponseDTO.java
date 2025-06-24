package io.github.usuario_api.dto;

import io.github.usuario_api.entities.Perfil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
    private String mensagem;
    private String nome;
}
