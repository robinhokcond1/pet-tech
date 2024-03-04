package br.com.fiap.pettech.pettech.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record ProdutoDto(
		@Schema(description = "ID do produto")
		UUID id,
		@Schema(description = "Nome do produto")
		String nome,
		@Schema(description = "Descrição do produto")
		String descricao,
		@Schema(description = "Preço do produto")
		double preco,
		@Schema(description = "URL da imagem do produto")
		String urlDaImage
) {

}
