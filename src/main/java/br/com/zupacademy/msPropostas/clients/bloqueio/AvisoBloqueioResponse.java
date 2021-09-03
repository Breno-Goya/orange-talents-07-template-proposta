package br.com.zupacademy.msPropostas.clients.bloqueio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AvisoBloqueioResponse {

    private String resultado;


    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoBloqueioResponse(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
