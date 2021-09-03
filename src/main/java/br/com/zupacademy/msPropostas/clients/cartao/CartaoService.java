package br.com.zupacademy.msPropostas.clients.cartao;

import br.com.zupacademy.msPropostas.clients.analiseFinanceira.StatusProposta;
import br.com.zupacademy.msPropostas.clients.bloqueio.AvisoBloqueioRequest;
import br.com.zupacademy.msPropostas.clients.bloqueio.AvisoBloqueioResponse;
import br.com.zupacademy.msPropostas.clients.bloqueio.Bloqueio;
import br.com.zupacademy.msPropostas.entities.Proposta;
import br.com.zupacademy.msPropostas.repositories.CartaoRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Service
@EnableScheduling
public class CartaoService {

    Logger logger = LoggerFactory.getLogger(CartaoService.class);

    private final ApiCartao apiCartao;
    private final EntityManager entityManager;

    public CartaoService(ApiCartao apiCartao, EntityManager entityManager, CartaoRepository cartaoRepository) {
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

    @Transactional
    public void bloqueiaCartao(Cartao cartao, String xForwardFor, String userAgent) {

        try {
            AvisoBloqueioResponse response = apiCartao.bloqueioCartao(cartao.getNumeroCartao(), new AvisoBloqueioRequest("aplicação-propostas"));
            Bloqueio bloqueio = new Bloqueio(xForwardFor, userAgent, cartao);
            cartao.bloqueiaCartao();
            entityManager.persist(bloqueio);
            logger.info("method=bloquiaCartao, msg= Status atual do cartão: {}", response.getResultado());

        } catch (FeignException.UnprocessableEntity feu) {
            logger.error("method=bloqueiaCartao, msg=Cartão de número: {} , já esta bloqueado", cartao.getNumeroCartao());
        }catch (FeignException fe) {
            logger.error("method=bloqueiaCartao, msg=Cartão não bloqueado numero: {}", cartao.getNumeroCartao());
        }
    }
}

