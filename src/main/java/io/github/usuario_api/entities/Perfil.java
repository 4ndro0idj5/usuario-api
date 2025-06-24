package io.github.usuario_api.entities;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Perfil {

    CLIENTE,
    PROPRIETARIO;

    @JsonCreator
    public static Perfil from(String value) {
        for (Perfil perfil : Perfil.values()) {
            if (perfil.name().equalsIgnoreCase(value)) {
                return perfil;
            }
        }
        throw new IllegalArgumentException("Valor inválido para o campo 'perfil'. Valores aceitos: CLIENTE, PROPRIETARIO.");
    }
}

