package br.com.zupacademy.msPropostas.clients.carteiras;

import br.com.zupacademy.msPropostas.clients.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class CarteiraDigital {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email @NotNull @NotBlank
    private String email;

    @Enumerated(EnumType.STRING)
    private TipoCarteiraDigital nomeCarteira;

    @ManyToOne @NotNull
    private Cartao cartao;

    @Deprecated
    public CarteiraDigital() {}

    public CarteiraDigital(String email, TipoCarteiraDigital nomeCarteira, Cartao cartao) {
        this.email = email;
        this.nomeCarteira = nomeCarteira;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteiraDigital getNomeCarteira() {
        return nomeCarteira;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarteiraDigital that = (CarteiraDigital) o;
        return nomeCarteira == that.nomeCarteira && Objects.equals(cartao, that.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeCarteira, cartao);
    }
}

