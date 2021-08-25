package br.com.zupacademy.msPropostas.clients.cartao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class CarteiraDigital {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String apiId;

    private String email;
    private LocalDateTime associadaEm;
    private String emissor;

    @ManyToOne
    private Cartao cartao;

    public CarteiraDigital(CarteiraDigitalResponse response) {
        this.apiId = response.getId();
        this.email = response.getEmail();
        this.associadaEm = response.getAssociadoEm();
        this.emissor = response.getEmissor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarteiraDigital that = (CarteiraDigital) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

