package com.restaurante.mimenu.web.controller;

import com.restaurante.mimenu.service.interf.IProductoService;
import com.restaurante.mimenu.web.dto.ProductoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    private final IProductoService iProductoService;

    public ProductoRestController(IProductoService iProductoService) {
        this.iProductoService = iProductoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductoDto>> getProductos() {
        return new ResponseEntity<>(iProductoService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductoDto> addProducto(@RequestBody ProductoDto productoDto) {
        return new ResponseEntity<>(iProductoService.save(productoDto), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductoDto> updateProducto(@PathVariable(value = "id") Long id,
                                                      @RequestBody ProductoDto productoDto) {
        return new ResponseEntity<>(iProductoService.updateProducto(id, productoDto), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteProducto(@PathVariable(value = "id") Long id) {
        iProductoService.deleteProducto(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
