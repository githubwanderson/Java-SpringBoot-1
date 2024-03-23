package gitwanderson.service.impl;

import gitwanderson.domain.entity.Pedido;
import gitwanderson.domain.repository.PedidoRepository;
import gitwanderson.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

}
