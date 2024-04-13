package gitwanderson.rest.controller;

import gitwanderson.domain.entity.ItemPedido;
import gitwanderson.domain.entity.Pedido;
import gitwanderson.rest.dto.InformacoesItensPedidoDTO;
import gitwanderson.rest.dto.InformacoesPedidoDTO;
import gitwanderson.rest.dto.PedidoDTO;
import gitwanderson.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto ){
        Pedido p = pedidoService.save(dto);
        return p.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return pedidoService
                .obterPedidoCompleto(id)
                .map( p -> converterPedido(p) )
                .orElseThrow(() -> new ResponseStatusException(NO_CONTENT, "Pedido não encontrado"));
    }

    private InformacoesPedidoDTO converterPedido(Pedido pedido) {
        return InformacoesPedidoDTO
                .builder()
                .codigoPedido(pedido.getId())
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .data(pedido.getData())
                .total(pedido.getTotal())
                .items(convertItemsPedido(pedido.getItens()))
                .build();
    }

    private List<InformacoesItensPedidoDTO> convertItemsPedido(List<ItemPedido> items){
        if(CollectionUtils.isEmpty(items)){
            return Collections.emptyList();
        }
        // stream aula 260
        return items.stream()
                .map(
                    item -> InformacoesItensPedidoDTO
                        .builder()
                        .nomeProduto(item.getProduto().getDescricao())
                        .quantidade(item.getQuantidade())
                        .valorUnitario(item.getProduto().getPreco())
                        .build())
                .collect(Collectors.toList());
    }


}
