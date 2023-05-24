package com.restaurante.mimenu.service.interf;

import com.restaurante.mimenu.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> findAll();

    Producto findById(Long id);

    Producto updateProducto(Producto producto);

    void deleteProducto(Producto producto);

    Optional<Producto> findProductoById(Long id);

    void deleteProducto(Long id);

    void save(Producto producto);
}
