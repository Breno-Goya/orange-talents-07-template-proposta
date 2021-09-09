package br.com.zupacademy.msPropostas.requests;

import br.com.zupacademy.msPropostas.entities.Proposta;
import br.com.zupacademy.msPropostas.exceptions.ApiRequestException;
import br.com.zupacademy.msPropostas.repositories.PropostaRepository;
import br.com.zupacademy.msPropostas.utils.Encryption;
import br.com.zupacademy.msPropostas.validations.CPForCNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank @CPForCNPJ
    private String documento;

    @Email @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    public PropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getDocumento() {
        return documento;
    }

    public Proposta convertToModel(PropostaRepository repository) throws ApiRequestException {

        String documentoHash = Encryption.getInstance().hashGenerate(documento);

        if(repository.existsByDocumentoHash(documentoHash))
            throw new ApiRequestException("Esse documento já possui proposta");
        return new Proposta(documento, email, nome, endereco, salario);
    }
}
