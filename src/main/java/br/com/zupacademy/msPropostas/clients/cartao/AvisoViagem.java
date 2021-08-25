package br.com.zupacademy.msPropostas.clients.cartao;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class AvisoViagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate validoAte;

    private String destino;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem(){}

    public AvisoViagem(AvisoViagemResponse response) {
        this.validoAte = response.getValidoAte();
        this.destino = response.getDestino();
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
