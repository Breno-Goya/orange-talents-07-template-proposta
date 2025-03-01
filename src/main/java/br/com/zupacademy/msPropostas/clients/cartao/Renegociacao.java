package br.com.zupacademy.msPropostas.clients.cartao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Renegociacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String apiId;

    private Integer quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public Renegociacao() {}

    public Renegociacao(RenegociacaoResponse response) {
        this.apiId = response.getId();
        this.quantidade = response.getQuantidade();
        this.valor = response.getValor();
        this.dataDeCriacao = response.getDataDeCriacao();
    }
}
