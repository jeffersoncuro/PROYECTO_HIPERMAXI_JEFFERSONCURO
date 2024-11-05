package com.hipermaxi.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductoDTO {

    private Integer id;
    private String descripcion;
    private Integer stock;
    private BigDecimal precio;
}
