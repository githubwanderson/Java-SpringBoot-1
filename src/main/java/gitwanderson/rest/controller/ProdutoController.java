package gitwanderson.rest.controller;

import gitwanderson.domain.entity.Produto;
import gitwanderson.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping(value = "/{id}")
    public Produto findById(@PathVariable Integer id ){
        return produtoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT ));
    }

    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public Produto save( @RequestBody Produto produto ){
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void update( @PathVariable Integer id, @RequestBody Produto produto ){
        produtoRepository.findById(id)
                .map( produtoFinded -> {
                    produto.setId( produtoFinded.getId() );
                    produtoRepository.save( produto );
                    return produto;
                })
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Produto não encontrado" ) );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void delete( @PathVariable Integer id ){
        produtoRepository.findById( id )
                .map( produtoFinded -> {
                    produtoRepository.delete( produtoFinded );
                    return produtoFinded;
                })
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Produto não encontrado" ));
    }

    @GetMapping
    public List<Produto> list( Produto produtoFind ){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of( produtoFind, matcher );

        return produtoRepository.findAll(example);
    }
}
