package io.github.usuario_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUsuarioRequestDTO {

    @NotBlank(message="O campo nome é obrigatório.")
    private String nome;
    @NotBlank(message="O campo telefone é obrigatório.")
    private String telefone;
    @NotBlank(message="O campo email é obrigatório.")
    private String email;
}
