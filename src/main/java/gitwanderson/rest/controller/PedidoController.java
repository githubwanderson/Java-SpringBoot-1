package gitwanderson.rest.controller;

import gitwanderson.domain.entity.Pedido;
import gitwanderson.rest.dto.PedidoDTO;
import gitwanderson.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

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
}
