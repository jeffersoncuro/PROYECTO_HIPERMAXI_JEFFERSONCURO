package com.hipermaxi.service;

import com.hipermaxi.dtos.ProductoCreateDTO;
import com.hipermaxi.dtos.ProductoDTO;
import com.hipermaxi.dtos.ProductoUpdateDTO;
import com.hipermaxi.mappers.ProductoMapper;
import com.hipermaxi.model.Producto;
import com.hipermaxi.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;

    private final TemplateEngine templateEngine;

    public ProductoServiceImpl(ProductoRepository productoRepository,
                               TemplateEngine templateEngine){
        this.productoRepository=productoRepository;
        this.templateEngine=templateEngine;
    }

    @Override
    public List<ProductoDTO> listarProductos() {
        return ProductoMapper.instancia.listaProductoAListaProductoDTO( productoRepository.findAll());
    }

    @Override
    public ProductoDTO obtenerProductoPorID(Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(ProductoMapper.instancia::productoAProductoDTO).orElse(null);
    }

    @Override
    public ProductoDTO registrarProducto(ProductoCreateDTO productoCreateDTO) {
        Producto producto=ProductoMapper.instancia.productoCreateDTOAProducto(productoCreateDTO);
        Producto respuestaEntity=productoRepository.save(producto);
        return ProductoMapper.instancia.productoAProductoDTO(respuestaEntity);
    }

    @Override
    public ProductoDTO actualizarProducto(ProductoUpdateDTO productoUpdateDTO) {

        Producto producto=ProductoMapper.instancia.productoUpdateDTOAProducto(productoUpdateDTO);
        Producto respuestaEntity=productoRepository.save(producto);
        return ProductoMapper.instancia.productoAProductoDTO(respuestaEntity);
    }

    @Override
    public HashMap eliminarProducto(Integer id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        HashMap hashMap = new HashMap();
        if (productoOptional.isPresent()) {
            productoRepository.delete(productoOptional.get());
            hashMap.put("mensaje","Registro Eliminado");
        } else {
            hashMap.put("mensaje","No se pudo realizar la eliminación");
        }
        return hashMap;
    }

    @Override
    public String generarPdftoBase64() {
        List<ProductoDTO> listaProductos= listarProductos();

        Context context = new Context();
        context.setVariable("listaProductos", listaProductos);

        // Generar el HTML del reporte utilizando Thymeleaf
        String htmlContent = templateEngine.process("report-template", context);

        // Convertir el HTML a PDF utilizando html2pdf
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(htmlContent, outputStream, converterProperties);

        // Convertir el PDF a Base64
        byte[] pdfBytes = outputStream.toByteArray();
        String base64Content = Base64.getEncoder().encodeToString(pdfBytes);

        return base64Content;
    }
}
