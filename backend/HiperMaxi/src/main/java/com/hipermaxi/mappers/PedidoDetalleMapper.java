package com.hipermaxi.mappers;

import com.hipermaxi.dtos.PedidoDetalleCreateDTO;
import com.hipermaxi.dtos.PedidoDetalleDTO;
import com.hipermaxi.model.PedidoDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PedidoDetalleMapper {

    PedidoDetalleMapper instancia= Mappers.getMapper(PedidoDetalleMapper.class);


    @Mapping(target = "productoDTO", source = "producto")
    List<PedidoDetalleDTO> listaPedidoDetalleAListaPedidoDetalleDTO (List<PedidoDetalle> listaPedidoDetalle);
    PedidoDetalle pedidoDetalleCreateDTOAPedidoDetalle(PedidoDetalleCreateDTO pedDetalleCreateDTO);
}
