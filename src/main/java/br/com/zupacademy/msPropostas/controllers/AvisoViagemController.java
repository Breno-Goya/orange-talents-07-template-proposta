package br.com.zupacademy.msPropostas.controllers;

import br.com.zupacademy.msPropostas.clients.avisoviagem.AvisoViagem;
import br.com.zupacademy.msPropostas.clients.avisoviagem.AvisoViagemRequest;
import br.com.zupacademy.msPropostas.clients.cartao.Cartao;
import br.com.zupacademy.msPropostas.repositories.AvisoViagemRepository;
import br.com.zupacademy.msPropostas.repositories.CartaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/avisos-viagens/cartoes")
public class AvisoViagemController {

    private final Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);

    private final CartaoRepository cartaoRepository;
    private final AvisoViagemRepository avisoViagemRepository;

    public AvisoViagemController(CartaoRepository cartaoRepository, AvisoViagemRepository avisoViagemRepository) {
        this.cartaoRepository = cartaoRepository;
        this.avisoViagemRepository = avisoViagemRepository;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> cadastroAvisoViagem (@PathVariable Long id,
                                                  @Valid @RequestBody AvisoViagemRequest request,
                                                  @RequestHeader(value = "User-Agent") String userAgent,
                                                  @RequestHeader(value = "ip") String ip) {

        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);
        if(possivelCartao.isPresent()) {
            if(Objects.isNull(userAgent)) return ResponseEntity.badRequest().build();
            if (Objects.isNull(ip)) return ResponseEntity.badRequest().build();

            Cartao cartao = possivelCartao.get();

            AvisoViagem avisoViagem = request.convertAvisoViagem(ip, userAgent, cartao);
            avisoViagemRepository.save(avisoViagem);

            logger.info("method = cadastroAvisoViagem, msg = Cadastrando aviso de viagem do cartao: {}", id);
            return ResponseEntity.ok().build();
        }
        logger.error("método: cadastroAvisoViagem, mensagem: Não foi encontrado o cartão: {}", id);
        return ResponseEntity.notFound().build();
    }

}
