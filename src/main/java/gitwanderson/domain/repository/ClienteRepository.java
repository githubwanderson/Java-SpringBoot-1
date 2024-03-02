package gitwanderson.domain.repository;

import gitwanderson.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


    List<Cliente> findByNomeLike(String nome);

//    Fazendo a mesma consulta do findByNomeLike utilizando @query com HQL
    @Query(value = " select c from Cliente c where c.nome like :nome ")
    List<Cliente> encontrarCliente( @Param("nome") String nome);

//    Utilizando sql puro
    @Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontrarCliente2( @Param("nome") String nome);

//    QueryMethods
    List<Cliente> findByNomeOrId(String nome, Integer Id);

//    Colocar os parametros na ordem da declaração
    List<Cliente> findByNomeOrIdOrderById(String nome, Integer Id);

//    Retornando apenas um registro, caso tenha mais de um sera gerado um exception
    Cliente findOneByNome(String nome);

    boolean existsByNome(String nome);

//    Delete tem que usar @Modifying para informar que não é consulta
    @Modifying
    void deleteByNome(String nome);

//    Buscar pedidos de um cliente
    @Query( " select c from Cliente c left join fetch c.pedidos where c.id = :id " )
    Cliente findClienteFetchPedido( @Param("id") Integer id );


}
