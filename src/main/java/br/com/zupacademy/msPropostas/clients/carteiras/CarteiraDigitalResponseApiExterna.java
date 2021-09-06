package br.com.zupacademy.msPropostas.clients.carteiras;

public class CarteiraDigitalResponseApiExterna {

    private String id;
    private String resultado;

    @Deprecated
    public CarteiraDigitalResponseApiExterna() {}

    public CarteiraDigitalResponseApiExterna(String id, String resultado) {
        this.id = id;
        this.resultado = resultado;
    }

    public String getId() {
        return id;
    }

    public String getResultado() {
        return resultado;
    }
}
