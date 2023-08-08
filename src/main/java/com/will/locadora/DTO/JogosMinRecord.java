package com.will.locadora.DTO;

import com.will.locadora.Entity.Console;
import com.will.locadora.Entity.Jogos;

import java.math.BigDecimal;

public record JogosMinRecord(Long codigo, String nome, BigDecimal preco, String imagem, Console console) {

    public JogosMinRecord(Jogos jogos){
        this(jogos.getCodigo(),jogos.getNome(),jogos.getPreco(),jogos.getImagem(),jogos.getConsole());
    }

}
