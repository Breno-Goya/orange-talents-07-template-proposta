package br.com.zupacademy.msPropostas.repositories;

import br.com.zupacademy.msPropostas.clients.cartao.AvisoViagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisoViagemRepository extends JpaRepository<AvisoViagem, Long> {
}
