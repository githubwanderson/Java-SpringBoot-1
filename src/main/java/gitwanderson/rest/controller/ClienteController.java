package gitwanderson.rest.controller;

import gitwanderson.domain.entity.Cliente;
import gitwanderson.domain.repository.ClienteRepository;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
    @ResponseBody // Informa que a string de retorno sera resposta da requisição
    public ResponseEntity getClienteById (@PathVariable("id") Integer id ){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get()); // Devolvo 200 e o cliente
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/cliente", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveCliente ( @RequestBody Cliente cliente ){
        Cliente c = clienteRepository.save(cliente);
        return ResponseEntity.ok(c);
    }

    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteCliente ( @PathVariable("id") Integer id ){
        Optional<Cliente> c = clienteRepository.findById(id);

        if(c.isPresent()){
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // retorna 204 no content
        }

        return ResponseEntity.notFound().build();
    }

    



}
