package io.github.usuario_api.dto;

import lombok.Data;

@Data
public class UsuarioSenhaUpdateDTO {


    private String senhaAtual;

    private String novaSenha;
}
