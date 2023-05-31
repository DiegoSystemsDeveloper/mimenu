package com.restaurante.mimenu.dao;

import com.restaurante.mimenu.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IProductoDao extends CrudRepository<Producto, Long> {
    Optional<Producto> findById(Long id);
}
