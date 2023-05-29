package com.restaurante.mimenu.web.controller;

import com.restaurante.mimenu.entity.Producto;
import com.restaurante.mimenu.service.interf.IProductoService;
import com.restaurante.mimenu.web.Dto.ProductoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    @Autowired
    private IProductoService iProductoService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductoDto> getProductos() {
        return iProductoService.findAll()
                .stream()
                .map(producto -> modelMapper
                        .map(producto, ProductoDto.class))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductoDto> addProducto(@RequestBody ProductoDto productoDto) {
        Producto productoRequest = modelMapper.map(productoDto, Producto.class);
        if(iProductoService.findProductoById(productoRequest.getId()).isEmpty()) {
            Producto producto = iProductoService.save(productoRequest);
            ProductoDto productoResponse = modelMapper.map(producto, ProductoDto.class);
            return new ResponseEntity<>(productoResponse, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT); //TODO:Mandar excepcion
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductoDto> updateProducto(@PathVariable(value = "id") Long id, @RequestBody ProductoDto productoDto) {
        if(iProductoService.findById(id) != null) {
            Producto productoRequest = modelMapper.map(productoDto, Producto.class);
            Producto updatedProducto = iProductoService.updateProducto(id, productoRequest);
            ProductoDto productoResponse = modelMapper.map(updatedProducto, ProductoDto.class);
            return new ResponseEntity<>(productoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteProducto(@PathVariable(value = "id") Long id) {
        if (iProductoService.findById(id) != null) {
            iProductoService.deleteProducto(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
