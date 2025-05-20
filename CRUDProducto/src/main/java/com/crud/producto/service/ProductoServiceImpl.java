package com.crud.producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.crud.producto.model.entity.ProductoEntidad;
import com.crud.producto.model.repository.ProductoRepository;

public class ProductoServiceImpl implements IService<ProductoEntidad> {
	
	@Autowired
	private ProductoRepository repository;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<ProductoEntidad> listar() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ProductoEntidad> listar(Pageable page) {
		return repository.findAll(page);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ProductoEntidad> obtenerPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public ProductoEntidad crear(ProductoEntidad producto) {
		return repository.save(producto);
	}

	@Override
	@Transactional
	public ProductoEntidad editar(ProductoEntidad producto, Long id) {
		Optional<ProductoEntidad> optProducto = repository.findById(id);
		if (optProducto.isPresent()) {
			ProductoEntidad productoDb = optProducto.get();
			productoDb.setNombre(producto.getNombre());
			productoDb.setDescripcion(producto.getDescripcion());
			productoDb.setPrecio(producto.getPrecio());
			return repository.save(productoDb);
		} else {
			return null;
		}
	}

	@Override
	public ProductoEntidad eliminar(Long id) {
		Optional<ProductoEntidad> optProducto = repository.findById(id);
		if (optProducto.isPresent()) {
			repository.deleteById(id);
			return optProducto.get();			
		} else {
			return null;
		}
	}

}

