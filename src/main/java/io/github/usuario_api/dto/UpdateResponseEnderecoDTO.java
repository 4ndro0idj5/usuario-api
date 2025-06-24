package io.github.usuario_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateResponseEnderecoDTO {
    private String mensagem;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;
}
