package io.github.usuario_api.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {

    private String email;
    private String senha;

}

