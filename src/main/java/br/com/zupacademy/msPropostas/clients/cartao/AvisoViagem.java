package br.com.zupacademy.msPropostas.clients.cartao;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class AvisoViagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate validoAte;
    private String destino;
    private LocalDateTime criadoEm;
    private String ip;
    private String userAgent;

    @ManyToOne
    @NotNull
    private Cartao cartao;

    @Deprecated
    public AvisoViagem() {}

    public AvisoViagem(String destino, String ip, String userAgent, LocalDate validoAte, Cartao cartao) {
        this.destino = destino;
        this.ip = ip;
        this.userAgent = userAgent;
        this.validoAte = validoAte;
        this.cartao = cartao;
        this.criadoEm = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvisoViagem that = (AvisoViagem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
