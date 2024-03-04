package br.com.fiap.pettech.pettech.serviceTest;

import br.com.fiap.pettech.pettech.dto.ProdutoDto;
import br.com.fiap.pettech.pettech.entities.Produto;
import br.com.fiap.pettech.pettech.exception.ControllerNotFoundException;
import br.com.fiap.pettech.pettech.repository.ProdutoRepository;
import br.com.fiap.pettech.pettech.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {
	
	@Mock
	private ProdutoRepository repository;
	
	@InjectMocks
	@Autowired
	private ProdutoService service;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testFindAll() {
		List<Produto> produtos = new ArrayList<>();
		produtos.add(new Produto(UUID.randomUUID(), "Produto 1", "Descrição do produto 1", 100.0, "url1"));
		produtos.add(new Produto(UUID.randomUUID(), "Produto 2", "Descrição do produto 2", 200.0, "url2"));
		when(repository.findAll()).thenReturn(produtos);
		
		List<ProdutoDto> result = (List<ProdutoDto>) service.findAll();
		
		assertNotNull(result);
		assertEquals(produtos.size(), result.size());
	}
	
	@Test
	void testFindById() {
		UUID id = UUID.randomUUID();
		Produto produto = new Produto(id, "Produto 1", "Descrição do produto 1", 100.0, "url");
		when(repository.findById(id)).thenReturn(Optional.of(produto));
		
		ProdutoDto result = service.findById(id);
		
		assertNotNull(result);
		assertEquals(produto.getId(), result.id());
	}
	
	@Test
	void testFindByIdNotFound() {
		UUID id = UUID.randomUUID();
		when(repository.findById(id)).thenReturn(Optional.empty());
		
		assertThrows(ControllerNotFoundException.class, () -> service.findById(id));
	}
	
	@Test
	void testSave() {
		ProdutoDto produtoDto = new ProdutoDto(null, "Produto 1", "Descrição do produto 1", 100.0, "url");
		Produto produto = new Produto(UUID.randomUUID(), "Produto 1", "Descrição do produto 1", 100.0, "url");
		when(repository.save(any())).thenReturn(produto);
		
		ProdutoDto result = service.save(produtoDto);
		
		assertNotNull(result);
		assertNotNull(result.id());
	}
	
	@Test
	void testUpdate() {
		UUID id = UUID.randomUUID();
		ProdutoDto produtoDto = new ProdutoDto(id, "Produto 1", "Descrição do produto 1", 100.0, "url");
		Produto produto = new Produto(id, "Produto 1", "Descrição do produto 1", 100.0, "url");
		when(repository.getReferenceById(id)).thenReturn(produto);
		when(repository.save(any())).thenReturn(produto);
		
		ProdutoDto result = service.update(id, produtoDto);
		
		assertNotNull(result);
		assertEquals(produtoDto.id(), result.id());
	}
	
	@Test
	void testUpdateNotFound() {
		UUID id = UUID.randomUUID();
		ProdutoDto produtoDto = new ProdutoDto(id, "Produto 1", "Descrição do produto 1", 100.0, "url");
		when(repository.getReferenceById(id)).thenThrow(ControllerNotFoundException.class);
		
		assertThrows(ControllerNotFoundException.class, () -> service.update(id, produtoDto));
	}
	
	@Test
	void testDelete() {
		UUID id = UUID.randomUUID();
		doNothing().when(repository).deleteById(id);
		
		assertDoesNotThrow(() -> service.delete(id));
	}
}
