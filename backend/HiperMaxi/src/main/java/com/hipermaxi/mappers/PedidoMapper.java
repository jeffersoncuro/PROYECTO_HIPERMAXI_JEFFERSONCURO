package com.hipermaxi.mappers;

import com.hipermaxi.dtos.PedidoCreateDTO;
import com.hipermaxi.dtos.PedidoDTO;
import com.hipermaxi.dtos.PedidoDetalleCreateDTO;
import com.hipermaxi.dtos.PedidoDetalleDTO;
import com.hipermaxi.model.Pedido;
import com.hipermaxi.model.PedidoDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PedidoMapper {

    PedidoMapper instancia= Mappers.getMapper(PedidoMapper.class);

    @Mapping(target = "pedidoDetalleDTO", source = "pedidoDetalle",qualifiedByName ="mapPedidoDetalleToPedidoDetalleDTO" )
    @Mapping(target = "clienteDTO", source = "cliente") // Mapea el cliente
    PedidoDTO pedidoAPedidoDTO(Pedido pedido);


    @Named("mapPedidoDetalleToPedidoDetalleDTO" )
    default PedidoDetalleDTO mapPedidoDetalleToPedidoDetalleDTO(PedidoDetalle pedidoDetalle) {
        PedidoDetalleDTO detalle = new PedidoDetalleDTO();
        detalle.setId(pedidoDetalle.getId());
        detalle.setCantidad(pedidoDetalle.getCantidad());
        detalle.setPreciovta(pedidoDetalle.getPreciovta());
        detalle.setImporte(pedidoDetalle.getImporte());
       detalle.setProductoDTO(ProductoMapper.instancia.productoAProductoDTO(pedidoDetalle.getProducto()));
        return detalle;
    }


    @Mapping(target = "pedidoDetalleDTO", source = "pedidoDetalle", qualifiedByName ="mapPedidoDetalleToPedidoDetalleDTO" )
    @Mapping(target = "clienteDTO", source = "cliente")
    List<PedidoDTO> listaPedidoAListaPedidoDTO (List<Pedido> listaPedido);

    @Mapping(target = "pedidoDetalle", source = "pedidoDetalleCreateDTO", qualifiedByName ="mapPedidoDetalleCreateDTOToPedidoDetalle" )
    @Mapping(target = "cliente", source = "clienteDTO")
    Pedido pedidoCreateDTOAPedido(PedidoCreateDTO pedidoCreateDTO);

    @Named("mapPedidoDetalleCreateDTOToPedidoDetalle" )
    default PedidoDetalle mapPedidoDetalleCreateDTOToPedidoDetalle(PedidoDetalleCreateDTO detalleDTO) {
        PedidoDetalle detalle = new PedidoDetalle();
        detalle.setCantidad(detalleDTO.getCantidad());
        detalle.setPreciovta(detalleDTO.getPreciovta());
        detalle.setImporte(detalleDTO.getImporte());
        detalle.setProducto(ProductoMapper.instancia.productoDTOAProducto(detalleDTO.getProductoDTO()));
        return detalle;
    }
}
