package gitwanderson.rest.dto;
import lombok.Data;
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
public class pedidoDTO {
    private Integer cliente;
    private BigDecimal total;
    private List<itemPedidoDTO> itens;
}
