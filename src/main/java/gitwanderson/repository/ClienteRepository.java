package gitwanderson.repository;

import gitwanderson.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


    List<Cliente> findByNomeLike(String nome);

//    QueryMethods
    List<Cliente> findByNomeOrId(String nome, Integer Id);

//    Colocar os parametros na ordem da declaração
    List<Cliente> findByNomeOrIdOrderById(String nome, Integer Id);

//  Retornando apenas um registro, caso tenha mais de um sera gerado um exception
    Cliente findOneByNome(String nome);

    boolean existsByNome(String nome);


}
