package br.com.zupacademy.msPropostas.response;

import br.com.zupacademy.msPropostas.entities.Proposta;

import java.math.BigDecimal;

public class PropostaResponse {

    private Long id;
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    private String statusProsposta;

    @Deprecated
    public PropostaResponse() {}

    public PropostaResponse(Proposta proposta){
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.statusProsposta = proposta.getStatusProposta().toString();
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getStatusProsposta() {
        return statusProsposta;
    }
}
