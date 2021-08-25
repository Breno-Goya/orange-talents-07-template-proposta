package br.com.zupacademy.msPropostas.clients.cartao;

import java.time.LocalDate;

public class AvisoViagemResponse {

    private LocalDate validoAte;
    private String destino;

    @Deprecated
    public AvisoViagemResponse(){}

    public AvisoViagemResponse(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
