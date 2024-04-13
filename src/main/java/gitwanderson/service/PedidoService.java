package gitwanderson.service;

import gitwanderson.domain.entity.Pedido;
import gitwanderson.rest.dto.PedidoDTO;
import java.util.Optional;

public interface PedidoService {
    Pedido save(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer Id);
}
