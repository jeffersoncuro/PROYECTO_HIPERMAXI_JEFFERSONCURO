package com.hipermaxi.service;

import com.hipermaxi.dtos.PedidoCreateDTO;
import com.hipermaxi.dtos.PedidoDTO;
import com.hipermaxi.dtos.PedidoResponseDTO;

import java.util.List;

public interface PedidoService {

    List<PedidoDTO>  listarPedidos();
    PedidoDTO obtenerPedidoPorID(long id);
    PedidoResponseDTO registrarPedido(PedidoCreateDTO pedidoCreateDTO);
}


