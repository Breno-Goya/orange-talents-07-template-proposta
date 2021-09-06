package br.com.zupacademy.msPropostas.clients.cartao;

import br.com.zupacademy.msPropostas.clients.avisoviagem.AvisoViagemRequest;
import br.com.zupacademy.msPropostas.clients.carteiras.CarteiraDigitalResponseApiExterna;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class CartaoResponse {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private Set<AvisoViagemRequest> avisos;
    private Set<CarteiraDigitalResponseApiExterna> carteiras;
    private Set<ParcelaResponse> parcelas;
    private BigDecimal limite;
    private RenegociacaoResponse renegociacao;
    private VencimentoResponse vecimento;

    private String idProposta;

    public CartaoResponse(String id, LocalDateTime emitidoEm, String titular, Set<AvisoViagemRequest> avisos, Set<CarteiraDigitalResponseApiExterna> carteiras, Set<ParcelaResponse> parcelas, BigDecimal limite, RenegociacaoResponse renegociacao, VencimentoResponse vecimento, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vecimento = vecimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

//    public Set<AvisoViagemRequest> getAvisos() {
//        return avisos;
//    }

    public Set<CarteiraDigitalResponseApiExterna> getCarteiras() {
        return carteiras;
    }

    public Set<ParcelaResponse> getParcelas() {
        return parcelas;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public RenegociacaoResponse getRenegociacao() {
        return renegociacao;
    }

    public VencimentoResponse getVecimento() {
        return vecimento;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
