package br.com.zupacademy.msPropostas.clients.analiseFinanceira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "analisefinanceira-api", url = "${analise.financeira.host}")
public interface ApiAnaliseFinanceira {

    @PostMapping("${analise.financeira.endpoint}")
    AnaliseResponse verificaAnaliseFinanceira (@RequestBody AnaliseRequest request);
}
