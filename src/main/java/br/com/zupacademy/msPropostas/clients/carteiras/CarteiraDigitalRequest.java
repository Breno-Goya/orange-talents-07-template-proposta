package br.com.zupacademy.msPropostas.clients.carteiras;

import br.com.zupacademy.msPropostas.clients.cartao.Cartao;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraDigitalRequest {

    @NotNull
    private TipoCarteiraDigital carteira;

    @Email @NotBlank
    private String email;

    public CarteiraDigitalRequest(TipoCarteiraDigital carteira, String email) {
        this.carteira = carteira;
        this.email = email;
    }

    public TipoCarteiraDigital getCarteira() {
        return carteira;
    }

    public String getEmail() {
        return email;
    }

    public CarteiraDigital convert(Cartao cartao) {
        return new CarteiraDigital(email, carteira, cartao);
    }
}
