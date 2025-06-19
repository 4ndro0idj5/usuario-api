package io.github.usuario_api.dto;

import io.github.usuario_api.entities.Perfil;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {


    private String nome;


    private String cpf;


    private String email;


    private String telefone;


    private String senha;

    private Perfil perfil;

    private EnderecoDTO endereco;
}

