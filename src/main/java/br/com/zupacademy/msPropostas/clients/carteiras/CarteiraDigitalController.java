package br.com.zupacademy.msPropostas.clients.carteiras;

import br.com.zupacademy.msPropostas.clients.cartao.ApiCartao;
import br.com.zupacademy.msPropostas.clients.cartao.Cartao;
import br.com.zupacademy.msPropostas.repositories.CartaoRepository;
import br.com.zupacademy.msPropostas.repositories.CarteiraDigitalRepository;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

@RestController
public class CarteiraDigitalController {

    private final CartaoRepository cartaoRepository;
    private final CarteiraDigitalRepository carteiraRepository;
    private ApiCartao apiCartao;

    public CarteiraDigitalController(CartaoRepository cartaoRepository, CarteiraDigitalRepository carteiraRepository, ApiCartao apiCartao) {
        this.cartaoRepository = cartaoRepository;
        this.carteiraRepository = carteiraRepository;
        this.apiCartao = apiCartao;
    }

    @PostMapping("/api/cartoes/{id}/carteiras")
    @Transactional
    public ResponseEntity<?> cadastraCarteira(@PathVariable Long id, @RequestBody CarteiraDigitalRequest request) {

        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);

        if(possivelCartao.isEmpty()) return ResponseEntity.notFound().build();

        if(carteiraRepository.existsByNomeCarteiraAndCartaoId(request.getCarteira(), id)) return ResponseEntity.unprocessableEntity().build();

        Cartao cartao = possivelCartao.get();
        CarteiraDigital carteiraDigital = request.convert(cartao);
        carteiraRepository.save(carteiraDigital);

        try {
            apiCartao.gerarCarteira(cartao.getNumeroCartao(), new CarteiraDigitalRequest(request.getCarteira(), request.getEmail()));
        }catch (FeignException.UnprocessableEntity feu) {
            return ResponseEntity.unprocessableEntity().build();
        }catch (FeignException.BadRequest feb) {
            return ResponseEntity.badRequest().build();
        }catch (FeignException e) {
            return  new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(carteiraDigital.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
