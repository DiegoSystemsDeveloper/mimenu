package com.restaurante.mimenu.service.impl;

import com.restaurante.mimenu.dao.IProductoDao;
import com.restaurante.mimenu.entity.Producto;

import com.restaurante.mimenu.exception.DataNotFoundException;
import com.restaurante.mimenu.service.interf.IProductoService;
import com.restaurante.mimenu.web.Dto.ProductoDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class ProductoServiceImpl implements IProductoService {

    private final IProductoDao iProductoDao;

    private final ModelMapper modelMapper;

    public ProductoServiceImpl(IProductoDao iProductoDao, ModelMapper modelMapper) {
        this.iProductoDao = iProductoDao;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDto> findAll() {
        return StreamSupport.stream(iProductoDao.findAll().spliterator(), false)
                .map(producto -> modelMapper.map(producto, ProductoDto.class))
                .toList();
    }

    @Override
    public ProductoDto updateProducto(Long id, ProductoDto productoDto) {
        Producto producto = iProductoDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("No se encontro un producto con ese ID"));
        Producto productoRequest = modelMapper.map(productoDto, Producto.class);
        producto.setNombre(productoRequest.getNombre());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setImagen(productoRequest.getImagen());

        return modelMapper.map(iProductoDao.save(producto), ProductoDto.class);
    }

    @Override
    public void deleteProducto(Long id) {
        iProductoDao.findById(id).orElseThrow(() -> new DataNotFoundException("No se encontro un producto con ese ID"));
        iProductoDao.deleteById(id);
    }

    @Override
    public ProductoDto save(ProductoDto productoDto) {
        Producto productoRequest = modelMapper.map(productoDto, Producto.class);
        return modelMapper.map(iProductoDao.save(productoRequest), ProductoDto.class);
    }
}
