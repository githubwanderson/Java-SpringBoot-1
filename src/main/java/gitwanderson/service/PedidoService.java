package gitwanderson.service;

import gitwanderson.domain.entity.Pedido;
import gitwanderson.domain.enums.StatusPedido;
import gitwanderson.rest.dto.PedidoDTO;
import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer Id);

    void updateStatusPedido(StatusPedido statusPedido, Integer id);
}
