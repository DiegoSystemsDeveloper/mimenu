package com.restaurante.mimenu.web.controller;

import com.restaurante.mimenu.entity.Producto;
import com.restaurante.mimenu.service.interf.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductoRestController {

    @Autowired
    private IProductoService iProductoService;
    @GetMapping("/productos")
    @ResponseStatus(HttpStatus.OK)
    public List<Producto> getProductos() {
        return iProductoService.findAll();
    }

    @PostMapping("/addProducto")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> addProducto(@RequestBody Producto producto) {
        if(iProductoService.findProductoById(producto.getId()).isEmpty()) {
            iProductoService.save(producto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("/update/producto/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Producto> updateProducto(@PathVariable(value = "id") Long id, @RequestBody Producto producto) {
        Producto productoDb = iProductoService.findById(id);
        if(productoDb != null) {
            productoDb.setNombre(producto.getNombre());
            productoDb.setDescripcion(producto.getDescripcion());
            productoDb.setPrecio(producto.getPrecio());
            productoDb.setImagen(producto.getImagen());
            Producto updatedProducto = iProductoService.updateProducto(productoDb);
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/producto/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteProducto(@PathVariable(value = "id") Long id) {
        if (iProductoService.findById(id) != null) {
            iProductoService.deleteProducto(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
