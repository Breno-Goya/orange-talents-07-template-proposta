package br.com.zupacademy.msPropostas.controllers;

import br.com.zupacademy.msPropostas.entities.Proposta;
import br.com.zupacademy.msPropostas.exceptions.ApiRequestException;
import br.com.zupacademy.msPropostas.repositories.PropostaRepository;
import br.com.zupacademy.msPropostas.requests.PropostaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private PropostaRepository repository;

    public PropostaController(PropostaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest request) {

        Optional<Proposta> possivelProposta = repository.findByDocumento(request.getDocumento());

        if(possivelProposta.isPresent()) {
            throw new ApiRequestException("Esse documento já foi cadastrado");
        }

        Proposta proposta = request.convertToModel();
        repository.save(proposta);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
