package com.hipermaxi.controller;


import com.hipermaxi.dtos.ProductoCreateDTO;
import com.hipermaxi.dtos.ProductoDTO;
import com.hipermaxi.dtos.ProductoUpdateDTO;
import com.hipermaxi.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductoController {


    private final ProductoService productoService;
    public ProductoController(ProductoService productoService){
        this.productoService=productoService;
    }

    @Operation(summary = "Endpoint que  permite listar los productos", description = "Endpoint que  permite listar los productos")
    @GetMapping("productos")
    public ResponseEntity<List<ProductoDTO>> listarProductos(){
        return   new ResponseEntity<>(productoService.listarProductos(), HttpStatus.OK);
    }

    @Operation(summary = "Endpoint que  permite obtener un producto por ID", description = "Endpoint que  permite obtener un producto por ID")
    @GetMapping("/productos/{productoId}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable("productoId") Integer productoId){
        return  new ResponseEntity<> ( productoService.obtenerProductoPorID(productoId),HttpStatus.OK);
    }

    @Operation(summary = "Endpoint que  permite registrar un nuevo producto", description = "Endpoint que  permite registrar un nuevo producto")
    @PostMapping("productos")
    public ResponseEntity<ProductoDTO> registrarProducto(@RequestBody ProductoCreateDTO productoCreateDTO){
        return  new ResponseEntity <>( productoService.registrarProducto(productoCreateDTO) ,HttpStatus.OK) ;
    }

    @Operation(summary = "Endpoint que  permite actualizar un producto existente", description = "Endpoint que  permite actualizar un producto existente")
    @PutMapping("productos")
    public ResponseEntity<ProductoDTO> actualizarProducto(@RequestBody ProductoUpdateDTO productoUpdateDTO){
        return new ResponseEntity<>(productoService.actualizarProducto(productoUpdateDTO),HttpStatus.OK);
    }

    @Operation(summary = "Endpoint que  permite eliminar un producto por ID", description = "Endpoint que  permite eliminar un producto por ID")
    @DeleteMapping("/productos/{productoId}")
    public ResponseEntity<?> eliminarProducto(@PathVariable("productoId") Integer productoId) {
        return new ResponseEntity<>( productoService.eliminarProducto(productoId),HttpStatus.OK);
    }

    @Operation(summary = "Endpoint que  permite generar reporte de productos", description = "Endpoint que  permite generar reporte de productos")
    @GetMapping("/productos/reporte")
    public ResponseEntity<String> generateReportBase64() throws Exception {
        return   new ResponseEntity<> (productoService.generarPdftoBase64(),HttpStatus.OK);
    }
}
