package com.restaurante.mimenu.service.impl;

import com.restaurante.mimenu.dao.IProductoDao;
import com.restaurante.mimenu.entity.Producto;

import com.restaurante.mimenu.service.interf.IProductoService;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoDao iProductoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>) iProductoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return iProductoDao.findById(id).orElse(null);
    }

    @Override
    public Producto updateProducto(Long id, Producto producto) {
        Producto producto1 = iProductoDao.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("Producto"));
        producto1.setNombre(producto.getNombre());
        producto1.setDescripcion(producto.getDescripcion());
        producto1.setPrecio(producto.getPrecio());
        producto1.setImagen(producto.getImagen());
        return iProductoDao.save(producto1);
    }

    @Override
    public void deleteProducto(Producto producto) {
        iProductoDao.deleteById(producto.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findProductoById(Long id) {
        return iProductoDao.findProductoById(id);
    }

    @Override
    public void deleteProducto(Long id) {
        iProductoDao.deleteById(id);
    }

    @Override
    public Producto save(Producto producto) {
        return iProductoDao.save(producto);
    }
}
