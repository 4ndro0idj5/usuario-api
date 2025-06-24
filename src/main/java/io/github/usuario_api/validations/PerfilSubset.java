package io.github.usuario_api.validations;

import io.github.usuario_api.entities.Perfil;
import jakarta.validation.Payload;

public @interface PerfilSubset {
    Perfil[] anyOf();
    String message() default "Perfil inválido. Os valores aceitos são: CLIENTE ou PROPRIETARIO.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
