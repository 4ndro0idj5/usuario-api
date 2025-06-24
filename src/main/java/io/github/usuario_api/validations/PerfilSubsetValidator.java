package io.github.usuario_api.validations;

import io.github.usuario_api.entities.Perfil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class PerfilSubsetValidator implements ConstraintValidator<PerfilSubset, Perfil> {

    private Perfil[] subset;

    @Override
    public void initialize(PerfilSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Perfil value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;  // Se quiser obrigar o campo a ser não nulo, mantenha o @NotNull no campo
        }
        return Arrays.asList(subset).contains(value);
    }
}