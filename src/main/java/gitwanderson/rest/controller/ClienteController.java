package gitwanderson.rest.controller;

import gitwanderson.domain.entity.Cliente;
import gitwanderson.domain.repository.ClienteRepository;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    // PUT atualiza integralmente a entity
    // Se passar algum campo null então deve ser salvo null
    @RequestMapping(value = "/cliente/{id}", method = RequestMethod.PUT )
    @ResponseBody
    public ResponseEntity putCliente( @PathVariable("id") Integer id, @RequestBody Cliente cliente ){

        return clienteRepository
                    .findById(id)
                    .map( clienteFind -> {
                        cliente.setId(clienteFind.getId());
                        clienteRepository.save(cliente);
                        return ResponseEntity.noContent().build();
                    } ).orElseGet( () -> ResponseEntity.notFound().build() );
    }

    // aula 42
    // Listando clientes
    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public ResponseEntity find( Cliente filtro ){

        // Modo manual
        // verificar o que veio no filtro para então montar o select para buscar no banco
        /*
        String sql = "select * from cliente ";

        if(filtro.getNome() != null){
            sql += " where nome = :nome ";
        }

        if(filtro.getCpf() != null){
            sql += " where cpf = :cpf ";
        }
        */

        // recurso que faz essa validacao

        // parametro para o example onde definimos as configuracoes
            // .withIgnoreCase() // Ignore maiuscula e minuscula para buscar
            // .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING ) // busca a string que CONTEM
        ExampleMatcher matcher = ExampleMatcher
                                        .matching()
                                        .withIgnoreCase()
                                        .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

        // example recebe uma classe onde ele vai procurar as propriedades
        // pacote data.domain
        Example example = Example.of(filtro, matcher);

        List<Cliente> lista = clienteRepository.findAll( example );

        return ResponseEntity.ok(lista);
    }
}
