package br.com.zupacademy.msPropostas.clients.cartao;

import br.com.zupacademy.msPropostas.clients.bloqueio.Bloqueio;
import br.com.zupacademy.msPropostas.clients.carteiras.CarteiraDigital;
import br.com.zupacademy.msPropostas.entities.Biometria;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numeroCartao;

    private LocalDateTime emitidoEm;

    private String titular;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<Bloqueio> bloqueios;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<AvisoViagem> avisos;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<CarteiraDigital> carteiras;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Parcela> parcelas;

    private BigDecimal limite;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Renegociacao renegociacao;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Vencimento vencimento;

    private String idProposta;

    @OneToMany(mappedBy = "cartao")
    private Set<Biometria> biometrias;

    @Enumerated(EnumType.STRING)
    private StatusCartao statusCartao;

    @Deprecated
    public Cartao() {
    }

    public Cartao(CartaoResponse response) {

        this.numeroCartao = response.getId();
        this.titular = response.getTitular();
        this.limite = response.getLimite();
        this.emitidoEm = response.getEmitidoEm();
        this.statusCartao = StatusCartao.ATIVO;

        if (!response.getParcelas().isEmpty())
            this.parcelas = response.getParcelas().stream().map(Parcela::new).collect(Collectors.toSet());

        if (!Objects.isNull(response.getRenegociacao()))
            this.renegociacao = new Renegociacao(response.getRenegociacao());

        if (!Objects.isNull(response.getVecimento()))
            this.vencimento = new Vencimento(response.getVecimento());

        this.idProposta = response.getIdProposta();
    }

    public void bloqueiaCartao(Bloqueio bloqueio) {
        this.statusCartao = StatusCartao.BLOQUEADO;
        this.bloqueios.add(bloqueio);
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public Boolean isCartaoBloqueado(){
         return this.statusCartao.equals(StatusCartao.BLOQUEADO);
    }

    public void avisaViagem(AvisoViagem avisoViagem) {
        this.avisos.add(avisoViagem);
    }

    public Boolean adicionaCarteira (CarteiraDigital carteira) {
        return this.carteiras.add(carteira);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartao cartao = (Cartao) o;
        return Objects.equals(numeroCartao, cartao.numeroCartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCartao);
    }
}
