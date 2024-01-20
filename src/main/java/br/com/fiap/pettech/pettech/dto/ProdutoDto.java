package br.com.fiap.pettech.pettech.dto;

import java.util.UUID;

public record ProdutoDto(
        UUID id,
        String nome,
        String descricao,
        double preco,
        String urlDaImage) {

}
