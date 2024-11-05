package com.hipermaxi.service;

import com.hipermaxi.dtos.*;
import com.hipermaxi.mappers.PedidoDetalleMapper;
import com.hipermaxi.mappers.PedidoMapper;
import com.hipermaxi.model.Pedido;
import com.hipermaxi.model.PedidoDetalle;
import com.hipermaxi.repository.PedidoDetalleRepository;
import com.hipermaxi.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;

    @Override
    public List<PedidoDTO> listarPedidos() {
        List<PedidoDTO> lista= PedidoMapper.instancia.listaPedidoAListaPedidoDTO(  pedidoRepository.findAll()  );
        return lista;
    }

    @Override
    public PedidoDTO obtenerPedidoPorID(long id) {
       Optional<Pedido> pedido= pedidoRepository.findById(id);
       PedidoDTO pedidoDTO= null;
       if ( pedido.isPresent() ){
           pedidoDTO= PedidoMapper.instancia.pedidoAPedidoDTO(pedido.get());
       }
       return pedidoDTO;
    }

    @Override
    @Transactional
    public PedidoResponseDTO registrarPedido(PedidoCreateDTO pedidoCreateDTO) {
       //Registro la cabecera
        Pedido pedido= PedidoMapper.instancia.pedidoCreateDTOAPedido(pedidoCreateDTO);
        Pedido respuestaEntity= pedidoRepository.save(pedido);

        //Registrando el detalle
        for (PedidoDetalle pd: pedido.getPedidoDetalle()) {
            pd.setPedido(respuestaEntity);
            pedidoDetalleRepository.save(pd);
        }

        //armar la respuesta DTO
        PedidoResponseDTO respuestaDTO = new PedidoResponseDTO();
        respuestaDTO.setId(respuestaEntity.getId());

        return respuestaDTO;
    }
}
