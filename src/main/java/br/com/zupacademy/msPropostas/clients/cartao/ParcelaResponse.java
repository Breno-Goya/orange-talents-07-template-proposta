package br.com.zupacademy.msPropostas.clients.cartao;

import java.math.BigDecimal;

public class ParcelaResponse {

    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    @Deprecated
    public ParcelaResponse() {}

    public ParcelaResponse(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
