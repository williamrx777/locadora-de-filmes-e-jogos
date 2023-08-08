package com.will.locadora.DTO;

import com.will.locadora.Entity.Console;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record JogosRecord(
        @NotBlank
        String nome,
        @NotNull
        BigDecimal preco,
        @NotNull
        Console console,
        @NotNull
        LocalDate data,
        String imagem
) {
}
