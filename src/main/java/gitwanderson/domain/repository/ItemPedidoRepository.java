package gitwanderson.repository;

import gitwanderson.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

// SimpleJpaRepository é uma class que o JpaRepository extend e nela contem os metodos mais simples (save, update, delete, list)
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
