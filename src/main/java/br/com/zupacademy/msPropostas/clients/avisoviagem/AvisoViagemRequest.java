package br.com.zupacademy.msPropostas.clients.avisoviagem;

import br.com.zupacademy.msPropostas.clients.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @Future @NotNull @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
    private LocalDate validoAte;

    @NotBlank
    private String destino;

    @Deprecated
    public AvisoViagemRequest(){}

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoViagemRequest(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

   public AvisoViagem convertAvisoViagem(String ip, String userAgent, Cartao cartao) {
        return new AvisoViagem(destino, ip, userAgent, validoAte, cartao);
   }
}
