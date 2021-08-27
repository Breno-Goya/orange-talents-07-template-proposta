package br.com.zupacademy.msPropostas.entities;

import br.com.zupacademy.msPropostas.clients.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fingerPrint;

    private LocalDateTime criandoEm;

    @ManyToOne @NotNull
    private Cartao cartao;

    @Deprecated
    public Biometria() {}

    public Biometria(String fingerPrint, Cartao cartao) {
        this.criandoEm = LocalDateTime.now();
        this.fingerPrint = fingerPrint;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }


}
