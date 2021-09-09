package br.com.zupacademy.msPropostas.entities;

import br.com.zupacademy.msPropostas.clients.analiseFinanceira.StatusProposta;
import br.com.zupacademy.msPropostas.clients.cartao.Cartao;
import br.com.zupacademy.msPropostas.utils.Encryption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String documento;

    @NotBlank
    @Column(nullable = false)
    private String documentoHash;

    @Email @NotBlank @Column(nullable = false)
    private String email;

    private String nome;

    private String endereco;

    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documentoTextoLimpo, String email, String nome, String endereco, BigDecimal salario) {
        Encryption encryptDocumento = Encryption.getInstance();
        this.documento = encryptDocumento.encrypt(documentoTextoLimpo);
        this.documentoHash = encryptDocumento.hashGenerate(documentoTextoLimpo);
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        Encryption encrypt = Encryption.getInstance();
        return encrypt.decipher(documento);
    }

    public String getNome() {
        return nome;
    }

    public void setStatusProposta(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public void associaCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
}
