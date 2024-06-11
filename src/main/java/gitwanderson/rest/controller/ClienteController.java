package gitwanderson.rest.controller;

import gitwanderson.domain.entity.Cliente;
import gitwanderson.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping(value = "/{id}")
    public Cliente getCliente(@PathVariable("id") Integer id) {

        /*

        Opção 1

        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()){
            return cliente.get(); // Devolvo 200 e o cliente
        }
        throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Cliente não encontrado." );

        Opção 2 otimizando

        */

        return clienteRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado.")
                );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // Retorno padrão é 200 mas se quiser retornar um status diferente então deve se colocar responsestatus
    public Cliente saveCliente(@RequestBody @Valid Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer id) {

        clienteRepository.findById( id )
                .map( cliente -> {
                    clienteRepository.delete( cliente );
                    return cliente;
                })
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado.")
                );
    }

    // PUT atualiza integralmente a entity // Se passar algum campo null ou não passar todas as propriedades então sera salvo null
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCliente(@PathVariable("id") Integer id, @RequestBody @Valid Cliente cliente) {
        clienteRepository
                .findById(id)
                .map(clienteFind -> {
                    cliente.setId(clienteFind.getId());
                    clienteRepository.save(cliente);
                    return clienteFind;
                }).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")
                );
    }

    // aula 42
    // Listando clientes
    @GetMapping
    public List<Cliente> find(Cliente filtro) {

        /*
        // Modo manual
        // verificar o que veio no filtro para então montar o select para buscar no banco

        String sql = "select * from cliente ";

        if(filtro.getNome() != null){
            sql += " where nome = :nome ";
        }

        if(filtro.getCpf() != null){
            sql += " where cpf = :cpf ";
        }
        */

        // parametro para o example onde definimos as configuracoes
        // .withIgnoreCase() // Ignore maiuscula e minuscula para buscar
        // .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING ) // busca a string que CONTEM
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        // example recebe uma classe onde ele vai procurar as propriedades
        // pacote data.domain
        Example example = Example.of(filtro, matcher);
        return clienteRepository.findAll(example);
    }
}
