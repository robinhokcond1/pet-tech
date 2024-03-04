package br.com.fiap.pettech.pettech.controllerTest;

import br.com.fiap.pettech.pettech.controller.ProdutoController;
import br.com.fiap.pettech.pettech.dto.ProdutoDto;
import br.com.fiap.pettech.pettech.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {
	
	@Mock
	private ProdutoService service;
	
	@InjectMocks
	@Autowired
	private ProdutoController controller;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testFindAll() {
		Collection<ProdutoDto> produtos = mock(Collection.class);
		when(service.findAll()).thenReturn(produtos);
		
		ResponseEntity<Collection<ProdutoDto>> result = controller.findAll();
		
		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(produtos, result.getBody());
	}
	
	@Test
	void testFindById() {
		UUID id = UUID.randomUUID();
		ProdutoDto produto = mock(ProdutoDto.class);
		when(service.findById(id)).thenReturn(produto);
		
		ResponseEntity<ProdutoDto> result = controller.findById(id);
		
		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(produto, result.getBody());
	}
	
	@Test
	void testSave() {
		ProdutoDto produtoDto = mock(ProdutoDto.class);
		when(service.save(produtoDto)).thenReturn(produtoDto);
		
		ResponseEntity<ProdutoDto> result = controller.save(produtoDto);
		
		assertNotNull(result);
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
		assertEquals(produtoDto, result.getBody());
	}
	
	@Test
	void testUpdate() {
		UUID id = UUID.randomUUID();
		ProdutoDto produtoDto = mock(ProdutoDto.class);
		when(service.update(id, produtoDto)).thenReturn(produtoDto);
		
		ResponseEntity<ProdutoDto> result = controller.update(id, produtoDto);
		
		assertNotNull(result);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(produtoDto, result.getBody());
	}
	
	@Test
	void testDelete() {
		UUID id = UUID.randomUUID();
		doNothing().when(service).delete(id);
		
		ResponseEntity<Void> result = controller.delete(id);
		
		assertNotNull(result);
		assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
		assertNull(result.getBody());
	}
}

