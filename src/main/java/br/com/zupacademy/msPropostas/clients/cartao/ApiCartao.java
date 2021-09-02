package br.com.zupacademy.msPropostas.clients.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value ="contas-api", url = "${analise.conta.host}")
public interface ApiCartao {

    @GetMapping
    CartaoResponse gerarCartao (@RequestParam String idProposta);

    @PostMapping("/{id}/bloqueios")
    AvisoBloqueioResponse bloqueioCartao (@PathVariable String id, @RequestBody AvisoBloqueioRequest request);
}
