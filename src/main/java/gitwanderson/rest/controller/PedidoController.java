package gitwanderson.rest.controller;

import gitwanderson.domain.entity.Pedido;
import gitwanderson.exception.PedidoException;
import gitwanderson.rest.dto.PedidoDTO;
import gitwanderson.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Pedido save(@RequestBody PedidoDTO dto ){
//        try{
            return pedidoService.save(dto);
//        } catch (PedidoException e){
//            return new ResponseStatusException(BAD_REQUEST,e.getMessage());
//        }
    }


}
