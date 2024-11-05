package com.hipermaxi.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PedidoDetalleDTO {

    private Long id;
    private ProductoDTO productoDTO;
    private Long cantidad;
    private BigDecimal preciovta;
    private BigDecimal importe;

}
