package gitwanderson.service.impl;

import gitwanderson.domain.entity.Cliente;
import gitwanderson.domain.entity.ItemPedido;
import gitwanderson.domain.entity.Pedido;
import gitwanderson.domain.entity.Produto;
import gitwanderson.domain.enums.StatusPedido;
import gitwanderson.domain.repository.ClienteRepository;
import gitwanderson.domain.repository.ItemPedidoRepository;
import gitwanderson.domain.repository.PedidoRepository;
import gitwanderson.domain.repository.ProdutoRepository;
import gitwanderson.exception.PedidoNaoEncontradoException;
import gitwanderson.exception.RegraNegocioException;
import gitwanderson.rest.dto.StatusPedidoDTO;
import gitwanderson.rest.dto.ItemPedidoDTO;
import gitwanderson.rest.dto.PedidoDTO;
import gitwanderson.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    /*
    // Para usar Autowired deve coloca-lo em todas as dependencias por esse motivo se houver mais de uma fica mais legal usar a anotation RequiredArgsConstructor
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
     */

    @Override
    @Transactional
    public Pedido create(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();

        Cliente c = clienteRepository
                    .findById(idCliente)
                    .orElseThrow(()-> new RegraNegocioException("Código cliente invalido"));

        Pedido p = new Pedido();
        p.setData(LocalDate.now());
        p.setCliente(c);
        List<ItemPedido> itensPedido = converterItens(p, dto.getItems());
        /* SE FOSSE DOUBLE
        double total = itensPedido.stream()
           .map(i -> i.getProduto().getPreco() * i.getQuantidade())
           .reduce(0.0, Double::sum);
        */

        p.setTotal( itensPedido.stream()
                .map(item -> item.getProduto().getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        pedidoRepository.save(p);
        itemPedidoRepository.saveAll(itensPedido);
        p.setItens(itensPedido);
        p.setStatus(StatusPedido.REALIZADO);
        return p;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer Id) {
        return pedidoRepository.findByIdFetchItens(Id);
    }

    @Override
    @Transactional
    public void updateStatusPedido(StatusPedido statusPedido, Integer id) {
        pedidoRepository
                .findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidoRepository.save(pedido);
                })
                .orElseThrow(() -> new PedidoNaoEncontradoException());
    }


    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar um pedido sem itens");
        }

        return items
                .stream()
                .map( itemDto -> {
                    /**
                     * Validando se o produto existe
                     */
                    Integer idProduto = itemDto.getProduto();
                    Produto produto = produtoRepository
                            .findById(idProduto)
                            .orElseThrow(()-> new RegraNegocioException("Produto não encontrado [" + idProduto + "]"));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(itemDto.getQuantidade());
                    itemPedido.setProduto(produto);
                    itemPedido.setPedido(pedido);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

}
