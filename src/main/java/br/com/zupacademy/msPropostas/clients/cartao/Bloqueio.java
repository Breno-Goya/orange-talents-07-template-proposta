package br.com.zupacademy.msPropostas.clients.cartao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Bloqueio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String apiId;

    private LocalDateTime bloqueadoEm;
    private String sistemaReponsavel;
    private Boolean ativo;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {}

    public Bloqueio(BloqueioResponse response) {
        this.apiId = response.getApiId();
        this.bloqueadoEm = response.getBloqueadoEm();
        this.sistemaReponsavel = response.getSistemaResponsavel();
        this.ativo = response.isAtivo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bloqueio bloqueio = (Bloqueio) o;
        return Objects.equals(id, bloqueio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
