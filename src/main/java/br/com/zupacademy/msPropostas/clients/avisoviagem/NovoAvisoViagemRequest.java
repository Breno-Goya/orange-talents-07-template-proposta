package br.com.zupacademy.msPropostas.clients.avisoviagem;

import br.com.zupacademy.msPropostas.clients.cartao.AvisoViagem;
import br.com.zupacademy.msPropostas.clients.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NovoAvisoViagemRequest {

    @Future
    @NotNull
    @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
    private LocalDate validoAte;

    @NotBlank
    private String destino;

    @Deprecated
    public NovoAvisoViagemRequest(){}

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovoAvisoViagemRequest(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public AvisoViagem convert(Cartao cartao, String ip, String userAgent) {
        return new AvisoViagem(destino, ip, userAgent, validoAte, cartao);
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
