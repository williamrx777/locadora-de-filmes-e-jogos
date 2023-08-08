package com.will.locadora.DTO;

import com.will.locadora.Entity.Filmes;

import java.math.BigDecimal;

public record FilmesMinRecord(Long codigo, String nome, BigDecimal preco, String imagem, String descricao) {

    public FilmesMinRecord(Filmes filmes){
        this(filmes.getCodigo(),filmes.getNome(),filmes.getPreco(),filmes.getImagem(), filmes.getDescricao());
    }

}
