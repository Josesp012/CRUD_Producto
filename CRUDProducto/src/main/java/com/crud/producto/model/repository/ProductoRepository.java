package com.crud.producto.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.producto.model.entity.ProductoEntidad;

public interface ProductoRepository extends JpaRepository<ProductoEntidad,Long> {

}
