package br.com.zupacademy.msPropostas.clients.analiseFinanceira;

import br.com.zupacademy.msPropostas.entities.Proposta;

public class AnaliseRequest {

    private String documento;
    private String nome;
    private Long idProposta;

    public AnaliseRequest (Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
