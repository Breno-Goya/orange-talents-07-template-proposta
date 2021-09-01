package br.com.zupacademy.msPropostas.repositories;

import br.com.zupacademy.msPropostas.clients.cartao.Bloqueio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloqueioCartaoRepository extends JpaRepository<Bloqueio, Long> {

    Optional<Bloqueio> findCartaoById(Long id);
}
