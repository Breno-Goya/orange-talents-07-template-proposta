package br.com.zupacademy.msPropostas.clients;

import br.com.zupacademy.msPropostas.clients.status.StatusSolicitacao;

public class AnaliseResponse {

    private String documento;
    private String nome;
    private StatusSolicitacao resultadoSolicitacao;
    private Long idProposta;

    public AnaliseResponse(String documento, String nome, StatusSolicitacao resultadoSolicitacao, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public StatusSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
