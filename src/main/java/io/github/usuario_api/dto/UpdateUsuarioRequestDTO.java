package io.github.usuario_api.dto;

import lombok.Data;

@Data
public class UpdateUsuarioRequestDTO {

    private String nome;

    private String telefone;

    private String email;
}
