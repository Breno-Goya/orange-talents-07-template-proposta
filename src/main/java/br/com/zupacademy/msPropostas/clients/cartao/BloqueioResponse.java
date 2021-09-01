package br.com.zupacademy.msPropostas.clients.cartao;

import java.time.LocalDateTime;

public class BloqueioResponse {

    private String sistemaResponsavel;

    private String apiId;
    private LocalDateTime bloqueadoEm;

    private boolean ativo;

    public BloqueioResponse(String apiId, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.apiId = apiId;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public String getApiId() {
        return apiId;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
