package br.com.zupacademy.msPropostas.clients.cartao;

import br.com.zupacademy.msPropostas.clients.bloqueio.AvisoBloqueioRequest;
import br.com.zupacademy.msPropostas.clients.bloqueio.AvisoBloqueioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value ="contas-api", url = "${analise.conta.host}")
public interface ApiCartao {

    @GetMapping("/api/cartoes")
    CartaoResponse gerarCartao (@RequestParam String idProposta);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    AvisoBloqueioResponse bloqueioCartao (@PathVariable String id, @RequestBody AvisoBloqueioRequest request);
}
