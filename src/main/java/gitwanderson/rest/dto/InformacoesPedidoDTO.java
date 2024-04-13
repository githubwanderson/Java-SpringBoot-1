package gitwanderson.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Builder cria uma class std (php) para o objeto
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidoDTO {
    private Integer codigoPedido;
    private String cpf;
    private String nomeCliente;
    private BigDecimal total;
    private LocalDate data;
    private List<InformacoesItensPedidoDTO> items;
}
