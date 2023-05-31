package com.restaurante.mimenu.service.interf;

import com.restaurante.mimenu.web.Dto.ProductoDto;

import java.util.List;

public interface IProductoService {
    List<ProductoDto> findAll();
    ProductoDto updateProducto(Long id, ProductoDto productoDto);

    void deleteProducto(Long id);

    ProductoDto save(ProductoDto productoDto);
}
