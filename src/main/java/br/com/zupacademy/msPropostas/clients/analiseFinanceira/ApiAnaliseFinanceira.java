package br.com.zupacademy.msPropostas.clients.analiseFinanceira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "analisefinanceira-api", url = "${analise.financeira.host}")

public interface ApiAnaliseFinanceira {

    @RequestMapping(method = RequestMethod.POST, value = "/solicitacao")
    AnaliseResponse verificaAnaliseFinanceira (@RequestBody AnaliseRequest request);
}
