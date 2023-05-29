package com.restaurante.mimenu.web.Dto;

import lombok.Data;

@Data
public class ProductoDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private float precio;
    private String imagen;
}
