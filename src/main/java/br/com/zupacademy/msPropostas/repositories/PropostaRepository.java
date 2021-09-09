package br.com.zupacademy.msPropostas.repositories;

import br.com.zupacademy.msPropostas.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);

    boolean existsByDocumentoHash(String documentoHash);
}
