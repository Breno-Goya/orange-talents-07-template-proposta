package br.com.zupacademy.msPropostas.clients.cartao;

import br.com.zupacademy.msPropostas.clients.analiseFinanceira.StatusProposta;
import br.com.zupacademy.msPropostas.entities.Proposta;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
@EnableScheduling
public class AssociaCartaoService {

    Logger logger = LoggerFactory.getLogger(AssociaCartaoService.class);

    private final ApiCartao apiCartao;
    private final EntityManager entityManager;

    public AssociaCartaoService(ApiCartao apiCartao, EntityManager entityManager) {
        this.apiCartao = apiCartao;
        this.entityManager = entityManager;
    }

    @Transactional
    @Scheduled(fixedDelayString = "${periodicidade.associa.cartao}")
    public void associaCartaoProposta() {

//      Como foi visto no check-out do dia 20/08 essa query é problemática e pode trazer inconsistência para a nossa aplicação
//       É importante limitar as queries. O JPQL não suporta LIMIT de queries.
//        Uma das estratégias e usar o EntitiManager.

        TypedQuery<Proposta> query = entityManager.createQuery("SELECT DISTINCT p FROM Proposta p WHERE p.statusProposta = :pstatus AND p.cartao IS NULL", Proposta.class);
        query.setParameter("pstatus", StatusProposta.ELEGIVEL);
        query.setMaxResults(50);

        logger.info("method=associaCartaoProposta, msg=associando cartões a proposta");
        List<Proposta> propostasElegiveis = query.getResultList();

        propostasElegiveis.forEach(proposta -> {
            try {
                CartaoResponse resposta = apiCartao.gerarCartao(proposta.getId().toString());
                proposta.associaCartao(new Cartao(resposta));
                logger.info("method=associaCartaoProposta, msg= cartao associado a proposta: {}", proposta.getId());
                entityManager.merge(proposta);
            }catch (FeignException e) {
                e.printStackTrace();
            }
        });
    }
}

