package gitwanderson.domain.repository;

import gitwanderson.domain.entity.Cliente;
import gitwanderson.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);

    @Query(value = "Select p from Pedido p left join fetch p.itens Where p.id = :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
