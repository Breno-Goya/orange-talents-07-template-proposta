package br.com.zupacademy.msPropostas.clients.avisoviagem;

import br.com.zupacademy.msPropostas.clients.cartao.ApiCartao;
import br.com.zupacademy.msPropostas.clients.cartao.AvisoViagem;
import br.com.zupacademy.msPropostas.clients.cartao.Cartao;
import br.com.zupacademy.msPropostas.repositories.CartaoRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AvisoViagemController {

    private final Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);

    private final CartaoRepository cartaoRepository;
    private final ApiCartao apiCartao;

    public AvisoViagemController(CartaoRepository cartaoRepository, ApiCartao apiCartao) {
        this.cartaoRepository = cartaoRepository;
        this.apiCartao = apiCartao;
    }

    @PostMapping("api/cartoes/{id}/avisos")
    @Transactional
    public ResponseEntity<Void> cadastroAvisoViagem(@PathVariable Long id,
                                                 @RequestBody @Valid NovoAvisoViagemRequest requestDTO,
                                                 @RequestHeader(value = "User-Agent") String userAgent,
                                                 @RequestHeader(value = "ip") String ip) {

        logger.info("MÉTODO = cadastroAvisoViagem, MENSAGEM = CADASTRANDO AVISO DO CARTÃO {}",id);

        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

        if(possivelCartao.isEmpty()) return ResponseEntity.notFound().build();

        Cartao cartao = possivelCartao.get();

        AvisoViagem avisoViagem = requestDTO.convert(cartao, ip, userAgent);
        cartao.avisaViagem(avisoViagem);
        cartaoRepository.save(cartao);


        try {
           AvisoViagemRequest response =  new AvisoViagemRequest(requestDTO.getValidoAte(), requestDTO.getDestino());
            apiCartao.avisoViagem(cartao.getNumeroCartao(), response);

        } catch (FeignException.UnprocessableEntity feu) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (FeignException.BadRequest feb) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
        }

        logger.info("MÉTODO = cadastroAvisoViagem, MENSAGEM = AVISO DE VIAGEM PARA O CARTÃO {} FOI REALIZADO",id);
        return ResponseEntity.ok().build();
    }
}
