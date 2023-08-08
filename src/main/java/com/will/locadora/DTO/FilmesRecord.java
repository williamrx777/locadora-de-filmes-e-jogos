package com.will.locadora.DTO;

import com.will.locadora.Entity.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FilmesRecord(
        @NotBlank
        String nome,
        @NotBlank
        String descricao,
        @NotNull
        LocalDate data,
        @NotNull
        BigDecimal preco,
        @NotNull
        Genero genero,
        @NotBlank
        String imagem

) {



}
