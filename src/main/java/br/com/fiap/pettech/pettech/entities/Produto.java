package br.com.fiap.pettech.pettech.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_produto")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    @Schema(description = "ID único do produto", example = "123e4567-e89b-12d3-a456-556642440000")
    private UUID id;
    
    @Column(name = "nome")
    @Schema(description = "Nome do produto", example = "Ração para gatos")
    private String nome;
    
    @Column(name = "descricao")
    @Schema(description = "Descrição do produto", example = "Ração premium para gatos adultos")
    private String descricao;
    
    @Column(name = "preco")
    @Schema(description = "Preço do produto", example = "39.90")
    private Double preco;
    
    @Column(name = "url_da_image")
    @Schema(description = "URL da imagem do produto", example = "https://example.com/images/cat_food.jpg")
    private String urlDaImage;
    
    public Produto() {
    }
    
    public Produto(UUID id, String nome, String descricao, Double preco, String urlDaImage) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.urlDaImage = urlDaImage;
    }
    
    // Getters e Setters omitidos para brevidade
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", urlDaImage='" + urlDaImage + '\'' +
                '}';
    }
}
