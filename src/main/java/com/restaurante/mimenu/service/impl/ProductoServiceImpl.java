package com.restaurante.mimenu.service.impl;

import com.restaurante.mimenu.dao.IProductoDao;
import com.restaurante.mimenu.entity.Producto;

import com.restaurante.mimenu.service.interf.IProductoService;
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
    public Producto updateProducto(Producto producto) {
        return iProductoDao.save(producto);
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
    public void save(Producto producto) {
        iProductoDao.save(producto);
    }
}
