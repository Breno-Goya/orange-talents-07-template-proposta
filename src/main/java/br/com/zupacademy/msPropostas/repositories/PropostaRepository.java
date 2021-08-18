package br.com.zupacademy.msPropostas.repositories;

import br.com.zupacademy.msPropostas.entities.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
