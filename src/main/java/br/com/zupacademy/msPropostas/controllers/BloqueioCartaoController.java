package br.com.zupacademy.msPropostas.controllers;

import br.com.zupacademy.msPropostas.clients.cartao.Bloqueio;
import br.com.zupacademy.msPropostas.clients.cartao.Cartao;
import br.com.zupacademy.msPropostas.clients.cartao.CartaoService;
import br.com.zupacademy.msPropostas.repositories.BloqueioCartaoRepository;
import br.com.zupacademy.msPropostas.repositories.CartaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioCartaoController {

    private final BloqueioCartaoRepository bloqueioCartaoRepository;
    private final CartaoRepository cartaoRepository;
    private final CartaoService cartaoService;

    public BloqueioCartaoController(BloqueioCartaoRepository bloqueioCartaoRepository, CartaoRepository cartaoRepository, CartaoService cartaoService) {
        this.bloqueioCartaoRepository = bloqueioCartaoRepository;
        this.cartaoRepository = cartaoRepository;
        this.cartaoService = cartaoService;
    }

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> bloqueiaCartao (@PathVariable Long idCartao,
                                             @RequestHeader(value = "User-Agent") String userAgent,
                                             @RequestHeader(value = "X-Forward-For") String xForwardFor) {

        Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);
        if(possivelCartao.isPresent()) {

            // Verifica o User-Agent
            if(Objects.isNull(userAgent)) return ResponseEntity.badRequest().build();

            // Verifica o ip
            if(Objects.isNull(xForwardFor)) return ResponseEntity.badRequest().build();

            Optional<Bloqueio> possivelBloqueio = bloqueioCartaoRepository.findCartaoById(idCartao);

            // Verifica um possivel bloqueio
            if (possivelBloqueio.isPresent()) return ResponseEntity.unprocessableEntity().build();

            Cartao cartao = possivelCartao.get();

            cartaoService.bloqueiaCartao(cartao, xForwardFor, userAgent);

            if(!cartao.isCartaoBloqueado()) return ResponseEntity.badRequest().build();

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
