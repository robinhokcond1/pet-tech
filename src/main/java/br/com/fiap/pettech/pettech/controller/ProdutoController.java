package br.com.fiap.pettech.pettech.controller;

import br.com.fiap.pettech.pettech.dto.ProdutoDto;
import br.com.fiap.pettech.pettech.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Endpoints para gerenciar produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService service;
    
    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Endpoint para listar todos os produtos disponíveis.")
    public ResponseEntity<Collection<ProdutoDto>> findAll() {
        var produtos = service.findAll();
        return ResponseEntity.ok(produtos);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Endpoint para buscar um produto por ID.")
    public ResponseEntity<ProdutoDto> findById(
            @Parameter(description = "ID do produto a ser buscado", required = true)
            @PathVariable UUID id) {
        var produto = service.findById(id);
        return ResponseEntity.ok(produto);
    }
    
    @PostMapping
    @Operation(summary = "Cadastrar um novo produto", description = "Endpoint para cadastrar um novo produto.")
    public ResponseEntity<ProdutoDto> save(
            @Parameter(description = "Dados do novo produto", required = true)
            @RequestBody ProdutoDto produtoDto) {
        produtoDto = service.save(produtoDto);
        return ResponseEntity.status(201).body(produtoDto);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um produto", description = "Endpoint para atualizar um produto existente.")
    public ResponseEntity<ProdutoDto> update(
            @Parameter(description = "ID do produto a ser atualizado", required = true)
            @PathVariable UUID id,
            @Parameter(description = "Novos dados do produto", required = true)
            @RequestBody ProdutoDto produtoDto) {
        produtoDto = service.update(id, produtoDto);
        return ResponseEntity.ok(produtoDto);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um produto", description = "Endpoint para excluir um produto.")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do produto a ser excluído", required = true)
            @PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
