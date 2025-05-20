package com.crud.producto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<T> {
	List<T> listar();
	Page<T> listar(Pageable page);
	Optional<T> obtenerPorId(Long id);
	T crear(T entity);
	T editar(T entity, Long id);
	T eliminar(Long id);
}
