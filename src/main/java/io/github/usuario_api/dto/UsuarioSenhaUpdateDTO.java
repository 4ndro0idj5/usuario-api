package io.github.usuario_api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioSenhaUpdateDTO {

    @NotBlank(message="O campo senha atual é obrigatório.")
    private String senhaAtual;
    @NotBlank(message="O campo nova senha é obrigatório.")
    private String novaSenha;
}
