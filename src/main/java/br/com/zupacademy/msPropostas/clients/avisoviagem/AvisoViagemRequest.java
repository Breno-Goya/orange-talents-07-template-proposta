package br.com.zupacademy.msPropostas.clients.avisoviagem;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @Future @NotNull
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

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
