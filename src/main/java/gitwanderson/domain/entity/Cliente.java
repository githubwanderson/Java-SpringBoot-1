package gitwanderson.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cpf", length = 11)
    private String cpf;

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    // Listar os pedidos de um cliente
    // 1 para N Um cliente pode ter muitos pedidos
    // add mappedBy para informar qual entidade de Pedido vai ser mapeada aqui
    @JsonIgnore // Ignorar essa propriedade
    @OneToMany(mappedBy = "cliente" , fetch = FetchType.LAZY )
    private Set<Pedido> pedidos; // Collection or list ou set

    // FetchType.EAGER sempre que listar um cliente sera listado tambem os pedidos desse cliente // NAO é Bom
    // FetchType.LAZY default
    // @OneToMany(mappedBy = "cliente" , fetch = FetchType.EAGER )
    // private Set<Pedido> pedidos; // Collection or list ou set

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
