package gitwanderson.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesItensPedidoDTO {
    private String nomeProduto;
    private BigDecimal valorUnitario;
    private Integer quantidade;
}
