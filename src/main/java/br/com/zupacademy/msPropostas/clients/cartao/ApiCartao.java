package br.com.zupacademy.msPropostas.clients.cartao;

import br.com.zupacademy.msPropostas.clients.avisoviagem.AvisoViagemRequest;
import br.com.zupacademy.msPropostas.clients.avisoviagem.AvisoViagemResponse;
import br.com.zupacademy.msPropostas.clients.bloqueio.AvisoBloqueioRequest;
import br.com.zupacademy.msPropostas.clients.bloqueio.AvisoBloqueioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(value ="contas-api", url = "${analise.conta.host}")
public interface ApiCartao {

    @GetMapping("/api/cartoes")
    CartaoResponse gerarCartao (@RequestParam String idProposta);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    AvisoBloqueioResponse bloqueioCartao (@PathVariable String id, @RequestBody AvisoBloqueioRequest request);

    @PostMapping("/api/cartoes/{id}/avisos")
    AvisoViagemResponse avisoViagem (@PathVariable String id, @RequestBody  @Valid AvisoViagemRequest request);
}
