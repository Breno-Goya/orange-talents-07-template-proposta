package br.com.zupacademy.msPropostas.repositories;

import br.com.zupacademy.msPropostas.clients.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
