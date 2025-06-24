package io.github.usuario_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoDTO {

    @NotBlank(message = "A rua é obrigatória")
    @Size(min = 3, max = 100, message = "A rua deve ter entre 3 e 100 caracteres")
    private String rua;

    @NotBlank(message = "O número é obrigatório")
    private String numero;
    @NotBlank(message = "O bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 dígitos numéricos (ex: 12345678)")
    private String cep;
}

