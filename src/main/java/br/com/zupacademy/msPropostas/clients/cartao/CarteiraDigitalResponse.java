package br.com.zupacademy.msPropostas.clients.cartao;

import java.time.LocalDateTime;

public class CarteiraDigitalResponse {

    private String id;
    private String email;
    private LocalDateTime associadoEm;
    private String emissor;

    @Deprecated
    public CarteiraDigitalResponse() {}

    public CarteiraDigitalResponse(String id, String email, LocalDateTime associadoEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadoEm = associadoEm;
        this.emissor = emissor;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadoEm() {
        return associadoEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
