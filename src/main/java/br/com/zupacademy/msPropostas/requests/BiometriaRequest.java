package br.com.zupacademy.msPropostas.requests;

import br.com.zupacademy.msPropostas.clients.cartao.Cartao;
import br.com.zupacademy.msPropostas.entities.Biometria;
import br.com.zupacademy.msPropostas.repositories.CartaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;
import java.util.Optional;

public class BiometriaRequest {

    private Long cartaoId;

    private String fingerPrint;


    public String getFingerPrint() {
       return fingerPrint;
   }

    public Biometria toModel(Long cartaoId, CartaoRepository cartaoRepository) {

        Optional<Cartao> possivelCartao =  cartaoRepository.findById(cartaoId);

        if (possivelCartao.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartao não encontrado");

        validarBase64();

        Cartao cartao = possivelCartao.get();

        return new Biometria(fingerPrint, cartao);
    }

    private void validarBase64() {

        Base64.Decoder decoder = Base64.getDecoder();

        try {
            decoder.decode(fingerPrint);
        }catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato inválido de biometria");
        }
    }
}
