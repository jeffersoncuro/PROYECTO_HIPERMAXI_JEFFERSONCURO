package com.hipermaxi.service;

import com.hipermaxi.dtos.ProductoCreateDTO;
import com.hipermaxi.dtos.ProductoDTO;
import com.hipermaxi.dtos.ProductoUpdateDTO;

import java.util.HashMap;
import java.util.List;

public interface ProductoService {

    List<ProductoDTO>  listarProductos();
    ProductoDTO obtenerProductoPorID(Integer id);
    ProductoDTO registrarProducto(ProductoCreateDTO productoCreateDTO);
    ProductoDTO actualizarProducto(ProductoUpdateDTO productoUpdateDTO);
    HashMap eliminarProducto(Integer id);


    String generarPdftoBase64();

}
