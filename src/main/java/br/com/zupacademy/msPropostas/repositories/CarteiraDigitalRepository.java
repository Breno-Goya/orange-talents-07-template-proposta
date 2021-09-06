package br.com.zupacademy.msPropostas.repositories;

import br.com.zupacademy.msPropostas.clients.carteiras.CarteiraDigital;
import br.com.zupacademy.msPropostas.clients.carteiras.TipoCarteiraDigital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraDigitalRepository extends JpaRepository<CarteiraDigital, Long> {
    boolean existsByNomeCarteiraAndCartaoId(TipoCarteiraDigital nomeCarteira, Long id);
}
