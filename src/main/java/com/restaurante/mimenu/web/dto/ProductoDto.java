package com.restaurante.mimenu.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ProductoDto {
    private Long id;
    @NotBlank
    @NotNull
    private String nombre;
    @NotBlank
    @NotNull
    private String descripcion;
    @NotBlank
    @NotNull
    private float precio;
    private String imagen;
}
