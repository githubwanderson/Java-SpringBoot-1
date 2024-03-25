package gitwanderson.service;

import gitwanderson.domain.entity.Pedido;
import gitwanderson.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido save(PedidoDTO dto);
}
