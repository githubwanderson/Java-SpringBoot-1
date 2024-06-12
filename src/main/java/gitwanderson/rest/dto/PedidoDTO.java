package gitwanderson.rest.dto;
import gitwanderson.domain.enums.StatusPedido;
import gitwanderson.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/*
{
  "cliente": 1,
  "total": 100,
  "items": [
    {
      "produto": 1,
      "quantidade": 10
    }
  ]
}
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    @NotNull(message = "Cliente não informado.")
    private Integer cliente;
//    private BigDecimal total;
    private StatusPedido status;
    @NotEmptyList(message = "Para um pedido é necessario ao menos 1 item.")
    private List<ItemPedidoDTO> items;
}
