package br.com.zupacademy.msPropostas.clients.bloqueio;

public class AvisoBloqueioRequest {

    private String sistemaResponsavel;

    public AvisoBloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
