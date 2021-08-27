package br.com.zupacademy.msPropostas.controllers;

import br.com.zupacademy.msPropostas.clients.cartao.Cartao;
import br.com.zupacademy.msPropostas.entities.Biometria;
import br.com.zupacademy.msPropostas.repositories.BiometriaRepository;
import br.com.zupacademy.msPropostas.repositories.CartaoRepository;
import br.com.zupacademy.msPropostas.requests.BiometriaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/biometrias")
public class BiometriaController {

    private CartaoRepository cartaoRepository;

    private BiometriaRepository biometriaRepository;

    public BiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.biometriaRepository = biometriaRepository;
    }

    @PostMapping("/{cartaoId}")
    public ResponseEntity<?> cadastrarNovaBiometria(@PathVariable Long cartaoId, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder uriComponentsBuilder) {
       Biometria biometria = request.toModel(cartaoId, cartaoRepository);
       biometriaRepository.save(biometria);

        URI uri = uriComponentsBuilder.path("/biometrias/{cartaoId/{id}").buildAndExpand(biometria.getCartao().getId(), biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
