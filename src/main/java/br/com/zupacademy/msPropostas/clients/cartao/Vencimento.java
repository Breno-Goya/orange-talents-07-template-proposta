package br.com.zupacademy.msPropostas.clients.cartao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Vencimento {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String apiId;

    private Integer dia;

    private LocalDateTime dataDeCriacao;

    @Deprecated
    public Vencimento() {}

    public Vencimento(VencimentoResponse response) {
        this.apiId = response.getId() ;
        this.dia = response.getDia();
        this.dataDeCriacao = response.getDataDeCriacao();
    }
}
