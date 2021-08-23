package br.com.zupacademy.msPropostas.controllers;

import br.com.zupacademy.msPropostas.clients.AnaliseRequest;
import br.com.zupacademy.msPropostas.clients.AnaliseResponse;
import br.com.zupacademy.msPropostas.clients.ApiAnaliseFinanceira;
import br.com.zupacademy.msPropostas.clients.status.StatusSolicitacao;
import br.com.zupacademy.msPropostas.entities.Proposta;
import br.com.zupacademy.msPropostas.exceptions.ApiRequestException;
import br.com.zupacademy.msPropostas.repositories.PropostaRepository;
import br.com.zupacademy.msPropostas.requests.PropostaRequest;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@Transactional
@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private PropostaRepository repository;
    private ApiAnaliseFinanceira apiAnaliseFinanceira;

    private Logger logger = LoggerFactory.getLogger(PropostaController.class);

    public PropostaController(PropostaRepository repository, ApiAnaliseFinanceira apiAnaliseFinanceira) {
        this.repository = repository;
        this.apiAnaliseFinanceira = apiAnaliseFinanceira;
    }

    @PostMapping
    public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest request) {

        logger.info("method=criaProposta, msg=criando nova proposta");

        Optional<Proposta> possivelProposta = repository.findByDocumento(request.getDocumento());

        if(possivelProposta.isPresent()) {
            logger.error("method=criaProposta, msg=a proposta não foi criada, documento: {} já existe", request.getDocumento());
            throw new ApiRequestException("Esse documento já foi cadastrado");
        }

        Proposta proposta = request.convertToModel();
        repository.save(proposta);

        verificaSolicitacaoFinanceira(proposta);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId()).toUri();

        logger.info("method=criaProposta, msg=proposta: {} para o documento: {} foi cadastrada com sucesso",
                proposta.getId(), proposta.getDocumento());

        return ResponseEntity.created(uri).build();
    }

    private void verificaSolicitacaoFinanceira (Proposta proposta) {

        AnaliseRequest request = new AnaliseRequest(proposta);
        StatusSolicitacao statusSolicitacao;

        try{
            AnaliseResponse  analiseResponse = apiAnaliseFinanceira.verificaAnaliseFinanceira(request);
            statusSolicitacao = analiseResponse.getResultadoSolicitacao();
            proposta.setStatusProposta(statusSolicitacao.verifica());
        } catch (FeignException e) {
            statusSolicitacao = StatusSolicitacao.COM_RESTRICAO;
            proposta.setStatusProposta(statusSolicitacao.verifica());
        }
    }

}
