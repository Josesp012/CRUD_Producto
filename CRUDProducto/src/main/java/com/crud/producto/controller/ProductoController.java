package com.crud.producto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.producto.model.entity.ProductoEntidad;
import com.crud.producto.service.ProductoServiceImpl;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	
	@Autowired
	private ProductoServiceImpl serviceImpl;
	
	@GetMapping
	public ResponseEntity<List<ProductoEntidad>> getAll() {
		return ResponseEntity.ok(serviceImpl.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductoEntidad> getById(@PathVariable Long id) {
		Optional<ProductoEntidad> optProducto = serviceImpl.obtenerPorId(id);
		if (optProducto.isPresent()) {
			return ResponseEntity.ok(optProducto.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<ProductoEntidad> create(@RequestBody ProductoEntidad producto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(serviceImpl.crear(producto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductoEntidad> update(@RequestBody ProductoEntidad producto, @PathVariable Long id) {
		ProductoEntidad productoDb = serviceImpl.editar(producto, id);
		if (productoDb != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(productoDb);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductoEntidad> delete(@PathVariable Long id) {
		ProductoEntidad productoDb = serviceImpl.eliminar(id);
		if (productoDb != null) {
			return ResponseEntity.status(HttpStatus.OK).body(productoDb);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
