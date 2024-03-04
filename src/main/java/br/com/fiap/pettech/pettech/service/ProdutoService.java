package br.com.fiap.pettech.pettech.service;

import br.com.fiap.pettech.pettech.dto.ProdutoDto;
import br.com.fiap.pettech.pettech.exception.ControllerNotFoundException;
import br.com.fiap.pettech.pettech.entities.Produto;
import br.com.fiap.pettech.pettech.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository repo;
    
    public Collection<ProdutoDto> findAll() {
        var produtos = repo.findAll();
        return produtos
                .stream()
                .map(this::toProdutoDto)
                .collect(Collectors.toList());
    }
    
    public ProdutoDto findById(UUID id) {
        var produto = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado"));
        return toProdutoDto(produto);
    }
    
    public ProdutoDto save(ProdutoDto produtoDto) {
        Produto produto = toProduto(produtoDto);
        produto = repo.save(produto);
        return toProdutoDto(produto);
    }
    
    public ProdutoDto update(UUID id, ProdutoDto produtoDto) {
        try {
            Produto buscaProduto = repo.getReferenceById(id);
            buscaProduto.setNome(produtoDto.nome());
            buscaProduto.setDescricao(produtoDto.descricao());
            buscaProduto.setUrlDaImage(produtoDto.urlDaImage());
            buscaProduto.setId(produtoDto.id());
            buscaProduto.setPreco(produtoDto.preco());
            buscaProduto = repo.save(buscaProduto);
            return toProdutoDto(buscaProduto);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Produto não encontrado");
        }
        
    }
    
    public void delete(UUID id) {
        repo.deleteById(id);
    }
    
    private ProdutoDto toProdutoDto(Produto produto) {
        return new ProdutoDto(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getUrlDaImage()
        );
    }
    
    private Produto toProduto(ProdutoDto produtoDto) {
        return new Produto(
                produtoDto.id(),
                produtoDto.nome(),
                produtoDto.descricao(),
                produtoDto.preco(),
                produtoDto.urlDaImage()
        );
    }
}
