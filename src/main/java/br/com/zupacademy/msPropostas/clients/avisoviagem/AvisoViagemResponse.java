package br.com.zupacademy.msPropostas.clients.avisoviagem;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AvisoViagemResponse {

    private String resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoViagemResponse (String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
