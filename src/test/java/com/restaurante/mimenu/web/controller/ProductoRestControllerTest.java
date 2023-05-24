package com.restaurante.mimenu.web.controller;

import com.restaurante.mimenu.entity.Producto;
import com.restaurante.mimenu.service.interf.IProductoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ProductoRestControllerTest {

    @InjectMocks
    private ProductoRestController productoRestController;

    @Mock
    private IProductoService iProductoService;

    @Test
    void getProductos() {
        List<Producto> datos = new ArrayList<>();
        Producto producto = new Producto();
        producto.setNombre("Carnes");
        datos.add(producto);

        Mockito.when(iProductoService.findAll()).thenReturn(datos);
        List<Producto> productos = productoRestController.getProductos();

        assertEquals(1, productos.size());
        assertEquals("Carnes", productos.get(0).getNombre());
        assertNotNull(productos);
        Mockito.verify(iProductoService).findAll();
    }

    @Test
    void addProducto() {
        Producto producto = new Producto();
        producto.setNombre("NOMBRE-PRUEBA");
        producto.setDescripcion("DESC-PRUEBA");
        producto.setPrecio(1000);
        producto.setImagen("IMG-PRUEBA");

        Mockito.doNothing().when(iProductoService).save(producto);
        ResponseEntity<Void> responseEntity = productoRestController.addProducto(producto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Mockito.verify(iProductoService).save(producto);
    }

    @Test
    void updateProducto() {
        Long id = 1L;
        Producto producto = new Producto();
        producto.setNombre("Nuevo Nombre");
        producto.setDescripcion("Nueva Descripción");
        producto.setPrecio(1000);
        producto.setImagen("nueva-imagen.jpg");

        Producto productoDb = new Producto();
        productoDb.setId(id);
        Mockito.when(iProductoService.findById(id)).thenReturn(productoDb);
        Mockito.when(iProductoService.updateProducto(productoDb)).thenReturn(producto);

        ResponseEntity<Producto> responseEntity = productoRestController.updateProducto(id, producto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(producto, responseEntity.getBody());
        assertEquals(producto.getNombre(), productoDb.getNombre());
        assertEquals(producto.getDescripcion(), productoDb.getDescripcion());
        assertEquals(producto.getPrecio(), productoDb.getPrecio());
        assertEquals(producto.getImagen(), productoDb.getImagen());

        Mockito.verify(iProductoService).findById(id);
        Mockito.verify(iProductoService).updateProducto(productoDb);
    }

    @Test
    void deleteProducto() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("NOMBRE-PRUEBA");
        producto.setDescripcion("DESC-PRUEBA");
        producto.setPrecio(1000);
        producto.setImagen("IMG-PRUEBA");

        Mockito.when(iProductoService.findById(1L)).thenReturn(producto);
        Mockito.doNothing().when(iProductoService).deleteProducto(producto.getId());
        ResponseEntity<Void> responseEntity = productoRestController.deleteProducto(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Mockito.verify(iProductoService).deleteProducto(producto.getId());
    }
}