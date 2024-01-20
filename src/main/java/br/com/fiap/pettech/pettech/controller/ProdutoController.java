package br.com.fiap.pettech.pettech.controller;

import br.com.fiap.pettech.pettech.dto.ProdutoDto;
import br.com.fiap.pettech.pettech.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService service;
    @GetMapping
    public ResponseEntity<Collection<ProdutoDto>> findAll(){
        var produtos = service.findAll();
        return ResponseEntity.ok(produtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable UUID id){
        var produto = service.findById(id);
        return ResponseEntity.ok(produto);
    }
    @PostMapping
    public ResponseEntity<ProdutoDto> save(@RequestBody ProdutoDto produtoDto){
        produtoDto = service.save(produtoDto);
        return ResponseEntity.status(201).body(produtoDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> update( @RequestBody ProdutoDto produtoDto, @PathVariable UUID id){
        produtoDto = service.update(id, produtoDto);
        return ResponseEntity.ok(produtoDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
