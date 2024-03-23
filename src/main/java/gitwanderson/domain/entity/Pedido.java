package gitwanderson.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    // Add um relacionamento
    // N p 1 Muitos pedidos para um cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Informando que podemos ter 20 casas e duas decimais que salva 100.00
    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;

    @Column(name = "data_pedido")
    private LocalDate data;

    // Para retornar uma lista com os itens do pedido
    // mappedBy vamos colocar o item que representa o pedido
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;
}
